package com.huagu.vcoin.main.service.admin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.Enum.SharePlanLogStatusEnum;
import com.huagu.vcoin.main.Enum.SharePlanTypeEnum;
import com.huagu.vcoin.main.dao.FadminDAO;
import com.huagu.vcoin.main.dao.FentrustDAO;
import com.huagu.vcoin.main.dao.FentrustlogDAO;
import com.huagu.vcoin.main.dao.FmessageDAO;
import com.huagu.vcoin.main.dao.FshareplanDAO;
import com.huagu.vcoin.main.dao.FshareplanlogDAO;
import com.huagu.vcoin.main.dao.FuserDAO;
import com.huagu.vcoin.main.dao.FusersettingDAO;
import com.huagu.vcoin.main.dao.FvirtualwalletDAO;
import com.huagu.vcoin.main.model.Fshareplan;
import com.huagu.vcoin.main.model.Fshareplanlog;
import com.huagu.vcoin.main.model.Fuser;
import com.huagu.vcoin.main.model.Fusersetting;
import com.huagu.vcoin.main.model.Fvirtualwallet;
import com.huagu.vcoin.util.Utils;

@Service
public class SharePlanService {
    @Autowired
    private FshareplanDAO shareplanDAO;
    @Autowired
    private FvirtualwalletDAO virtualwalletDAO;
    @Autowired
    private FmessageDAO messageDAO;
    @Autowired
    private FuserDAO userDAO;
    @Autowired
    private FshareplanlogDAO shareplanlogDAO;
    @Autowired
    private FentrustlogDAO entrustlogDAO;
    @Autowired
    private VirtualCoinService virtualCoinService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FentrustDAO fentrustDAO;
    @Autowired
    private FusersettingDAO usersettingDAO;
    @Autowired
    private FadminDAO adminDAO;

    public Fshareplan findById(int id) {
        return this.shareplanDAO.findById(id);
    }

    public void saveObj(Fshareplan obj) {
        this.shareplanDAO.save(obj);
    }

    public void saveObj(Fshareplan obj, Fuser fuser) {
        try {
            this.shareplanDAO.save(obj);
            this.userDAO.attachDirty(fuser);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void deleteObj(int id) {
        Fshareplan obj = this.shareplanDAO.findById(id);
        this.shareplanDAO.delete(obj);
    }

    public void updateObj(Fshareplan obj) {
        this.shareplanDAO.attachDirty(obj);
    }

    public List<Fshareplan> findByProperty(String name, Object value) {
        return this.shareplanDAO.findByProperty(name, value);
    }

    public List<Fshareplan> findAll() {
        return this.shareplanDAO.findAll();
    }

    public List<Fshareplan> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fshareplan> all = this.shareplanDAO.list(firstResult, maxResults, filter, isFY);
        for (Fshareplan fshareplan : all) {
            if (fshareplan.getFcreator() != null) {
                fshareplan.getFcreator().getFname();
            }
            int sharePlanId = fshareplan.getFid();
            String filter1 = "where fshareplan.fid=" + sharePlanId + " and fstatus=" + SharePlanLogStatusEnum.NOSEND;
            fshareplan.setNoSend(this.adminDAO.getAllCount("Fshareplanlog", filter1));

            String filter2 = "where fshareplan.fid=" + sharePlanId + " and fstatus=" + SharePlanLogStatusEnum.HASSEND;
            fshareplan.setHasSend(this.adminDAO.getAllCount("Fshareplanlog", filter2));
        }
        return all;
    }

    public boolean updateSharePlanLog(Fvirtualwallet fvirtualwallet, Fshareplanlog fshareplanlog)
            throws RuntimeException {
        try {
            this.virtualwalletDAO.attachDirty(fvirtualwallet);
            this.shareplanlogDAO.attachDirty(fshareplanlog);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return true;
    }

    public boolean updateSharePlanLog1(Fusersetting usersetting, Fshareplanlog fshareplanlog) throws RuntimeException {
        this.usersettingDAO.attachDirty(usersetting);
        this.shareplanlogDAO.attachDirty(fshareplanlog);
        return true;
    }

    public boolean updateSharePlanLog1(Fvirtualwallet virtualwallet, Fshareplanlog fshareplanlog)
            throws RuntimeException {
        this.virtualwalletDAO.attachDirty(virtualwallet);
        this.shareplanlogDAO.attachDirty(fshareplanlog);
        return true;
    }

    public boolean update(Fshareplan sharePlan) throws RuntimeException {
        boolean flag = false;
        int vid = sharePlan.getFvirtualcointype().getFid();
        BigDecimal totalAmt = BigDecimal.ZERO;// 平台总数
        BigDecimal amount = BigDecimal.ZERO;// 分红总数
        BigDecimal oneAmount = BigDecimal.ZERO;// 每一个分红数
        String filter = "";
        totalAmt = this.virtualwalletDAO.getTotalQty(vid);
        oneAmount = amount.divide(totalAmt, 8, RoundingMode.HALF_UP);
        filter = "where fvirtualcointype.fid=" + vid + " and ftotal >0";
        if (sharePlan.getFtype() == SharePlanTypeEnum.NORMAL) {// 发钱，取用户币的总量
            amount = sharePlan.getFamount();
        } else if (sharePlan.getFtype() == SharePlanTypeEnum.HANDING) {// 发币
            amount = sharePlan.getFtotalCoinQty();
        }

        if (oneAmount.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        Date startDate = sharePlan.getFstartDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, 1);
        List<Fvirtualwallet> all = this.virtualwalletDAO.list(0, 0, filter, false);
        for (Fvirtualwallet fvirtualwallet : all) {
            BigDecimal totalShareAmt = BigDecimal.ZERO;
            double total = fvirtualwallet.getFtotal();
            totalShareAmt = new BigDecimal(total).multiply(oneAmount).setScale(6, RoundingMode.HALF_UP);

            if (totalShareAmt.compareTo(BigDecimal.ZERO) == 0)
                continue;
            Fshareplanlog sharePlanLog = new Fshareplanlog();
            sharePlanLog.setFamount(totalShareAmt);// 最后分到多少
            sharePlanLog.setFselfAmount(total);
            sharePlanLog.setfTotalAmount(Double.valueOf(amount.toString()));
            sharePlanLog.setFcreatetime(Utils.getTimestamp());
            sharePlanLog.setFshareplan(sharePlan);
            sharePlanLog.setFuser(fvirtualwallet.getFuser());
            sharePlanLog.setFstatus(SharePlanLogStatusEnum.NOSEND);
            if (sharePlan.getFtype() == SharePlanTypeEnum.NORMAL) {
                sharePlanLog.setFtype("CNY");
                sharePlanLog.setFoneAmount(oneAmount.doubleValue());
                sharePlanLog.setfTotalQty(amount.doubleValue());
            } else {
                String name = sharePlan.getFsendcointype().getFname();
                sharePlanLog.setFtype(name);
                sharePlanLog.setFoneAmount(oneAmount.doubleValue());
                sharePlanLog.setfTotalQty(amount.doubleValue());
            }

            this.shareplanlogDAO.attachDirty(sharePlanLog);
        }
        this.shareplanDAO.attachDirty(sharePlan);
        flag = true;
        return flag;
    }
}