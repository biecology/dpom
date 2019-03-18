package com.huagu.vcoin.main.service.admin;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FbankinDAO;
import com.huagu.vcoin.main.dao.FcapitaloperationDAO;
import com.huagu.vcoin.main.dao.FintrolinfoDAO;
import com.huagu.vcoin.main.dao.FscoreDAO;
import com.huagu.vcoin.main.dao.FuserDAO;
import com.huagu.vcoin.main.dao.FvirtualwalletDAO;
import com.huagu.vcoin.main.model.Fbankin;
import com.huagu.vcoin.main.model.Fcapitaloperation;
import com.huagu.vcoin.main.model.Fintrolinfo;
import com.huagu.vcoin.main.model.Fscore;
import com.huagu.vcoin.main.model.Fuser;
import com.huagu.vcoin.main.model.Fvirtualwallet;
import com.huagu.vcoin.util.Utils;

@Service
public class CapitaloperationService {
    @Autowired
    private FcapitaloperationDAO fcapitaloperationDAO;
    @Autowired
    private FintrolinfoDAO fintrolinfoDAO;
    @Autowired
    private FuserDAO fuserDao;
    @Autowired
    private FscoreDAO fscoreDAO;
    @Autowired
    private FbankinDAO bankinDAO;
    @Autowired
    private SystemArgsService systemArgsService;
    @Autowired
    private FvirtualwalletDAO fvirtualwalletDAO;

    public Fcapitaloperation findById(int id) {
        Fcapitaloperation fcapitaloperation = this.fcapitaloperationDAO.findById(id);
        return fcapitaloperation;
    }

    public void saveObj(Fcapitaloperation obj) {
        this.fcapitaloperationDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fcapitaloperation obj = this.fcapitaloperationDAO.findById(id);
        this.fcapitaloperationDAO.delete(obj);
    }

    public void updateObj(Fcapitaloperation obj) {
        this.fcapitaloperationDAO.attachDirty(obj);
    }

    public List<Fcapitaloperation> findByProperty(String name, Object value) {
        return this.fcapitaloperationDAO.findByProperty(name, value);
    }

    public List<Fcapitaloperation> findAll() {
        return this.fcapitaloperationDAO.findAll();
    }

    public List<Fcapitaloperation> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fcapitaloperation> all = this.fcapitaloperationDAO.list(firstResult, maxResults, filter, isFY);
        for (Fcapitaloperation fcapitaloperation : all) {
            fcapitaloperation.getFuser().getFemail();
            if (fcapitaloperation.getfAuditee_id() != null) {
                fcapitaloperation.getfAuditee_id().getFname();
            }
            if (fcapitaloperation.getSystembankinfo() != null)
                fcapitaloperation.getSystembankinfo().getFbankAddress();
        }
        return all;
    }

    public void updateCapital(Fcapitaloperation fcapitaloperation, Fvirtualwallet walletInfo, Fbankin fbankin) {
        this.fcapitaloperationDAO.save(fcapitaloperation);
        this.fvirtualwalletDAO.attachDirty(walletInfo);
        this.bankinDAO.attachDirty(fbankin);
        if (true) {
            int userid = fcapitaloperation.getFuser().getFid();
            Fuser fuser = this.fuserDao.findById(userid);
            Fscore fscore = fuser.getFscore();
            double firstTimeRechargeRate = Double.valueOf(this.systemArgsService.getValue("firstTimeRechargeRate"));
            if (!fscore.isFissign() && firstTimeRechargeRate > 0) {
                fscore.setFissign(true);
                this.fscoreDAO.attachDirty(fscore);
                double amt = Utils.getDouble(fcapitaloperation.getFamount() * firstTimeRechargeRate, 4);
                walletInfo.setFtotal(walletInfo.getFtotal() + amt);
                Fintrolinfo info = new Fintrolinfo();
                info.setFcreatetime(Utils.getTimestamp());
                info.setFiscny(true);
                info.setFuser(fuser);
                info.setFname("人民币");
                info.setFtitle("首次充值,奖励￥" + new BigDecimal(amt).setScale(4, BigDecimal.ROUND_HALF_UP));
                info.setFqty(amt);
                this.fintrolinfoDAO.save(info);
                this.fvirtualwalletDAO.attachDirty(walletInfo);
            }
        }
    }

    public void updateCapital(Fcapitaloperation capitaloperation, Fvirtualwallet fvirtualwallet, boolean isRecharge)
            throws RuntimeException {
        try {
            this.fcapitaloperationDAO.attachDirty(capitaloperation);
            this.fvirtualwalletDAO.attachDirty(fvirtualwallet);
            if (isRecharge) {
                int userid = capitaloperation.getFuser().getFid();
                Fuser fuser = this.fuserDao.findById(userid);
                Fscore fscore = fuser.getFscore();
                double firstTimeRechargeRate = Double.valueOf(this.systemArgsService.getValue("firstTimeRechargeRate"));
                if (!fscore.isFissign() && firstTimeRechargeRate > 0) {
                    fscore.setFissign(true);
                    this.fscoreDAO.attachDirty(fscore);
                    double amt = Utils.getDouble(capitaloperation.getFamount() * firstTimeRechargeRate, 4);
                    fvirtualwallet.setFtotal(fvirtualwallet.getFtotal() + amt);
                    Fintrolinfo info = new Fintrolinfo();
                    info.setFcreatetime(Utils.getTimestamp());
                    info.setFiscny(true);
                    info.setFuser(fuser);
                    info.setFname("人民币");
                    info.setFtitle("首次充值,奖励￥" + new BigDecimal(amt).setScale(4, BigDecimal.ROUND_HALF_UP));
                    info.setFqty(amt);
                    this.fintrolinfoDAO.save(info);
                }
                this.fvirtualwalletDAO.attachDirty(fvirtualwallet);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void updateCapital(Fcapitaloperation fcapitaloperation, Fvirtualwallet fvirtualwallet, Fintrolinfo info) {
        try {
            this.fcapitaloperationDAO.attachDirty(fcapitaloperation);
            this.fvirtualwalletDAO.attachDirty(fvirtualwallet);
            this.fintrolinfoDAO.save(info);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Map getTotalAmount(int type, String fstatus, boolean isToday) {
        return this.fcapitaloperationDAO.getTotalAmount(type, fstatus, isToday);
    }

    public Map getTotalAmountIn(int type, String fstatus, boolean isToday) {
        return this.fcapitaloperationDAO.getTotalAmountIn(type, fstatus, isToday);
    }

    public List getTotalGroup(String filter) {
        return this.fcapitaloperationDAO.getTotalGroup(filter);
    }

    public List getTotalAmountForReport(String filter) {
        return this.fcapitaloperationDAO.getTotalAmountForReport(filter);
    }

    public List getTotalOperationlog(String filter) {
        return this.fcapitaloperationDAO.getTotalOperationlog(filter);
    }

    public Map getTotalAmount(int type, String fstatus, boolean isToday, boolean isFee) {
        return this.fcapitaloperationDAO.getTotalAmount(type, fstatus, isToday, isFee);
    }

    public void updateObj(Fcapitaloperation fcapitaloperation, Fuser fuser1, Fuser fuser2, Fuser fuser3) {
        try {
            this.fcapitaloperationDAO.attachDirty(fcapitaloperation);
            if (fuser1 != null) {
                this.fuserDao.attachDirty(fuser1);
            }
            if (fuser2 != null) {
                this.fuserDao.attachDirty(fuser2);
            }
            if (fuser3 != null) {
                this.fuserDao.attachDirty(fuser3);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}