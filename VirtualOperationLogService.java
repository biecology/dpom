package com.huagu.vcoin.main.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.Enum.MessageStatusEnum;
import com.huagu.vcoin.main.dao.FmessageDAO;
import com.huagu.vcoin.main.dao.FvirtualoperationlogDAO;
import com.huagu.vcoin.main.dao.FvirtualwalletDAO;
import com.huagu.vcoin.main.model.Fmessage;
import com.huagu.vcoin.main.model.Fvirtualoperationlog;
import com.huagu.vcoin.main.model.Fvirtualwallet;
import com.huagu.vcoin.util.Utils;

@Service
public class VirtualOperationLogService {
    @Autowired
    private FvirtualoperationlogDAO virtualoperationlogDAO;
    @Autowired
    private FvirtualwalletDAO virtualwalletDao;
    @Autowired
    private FmessageDAO messageDAO;

    public Fvirtualoperationlog findById(int id) {
        Fvirtualoperationlog operationLog = this.virtualoperationlogDAO.findById(id);
        ;
        return operationLog;
    }

    public void saveObj(Fvirtualoperationlog obj) {
        this.virtualoperationlogDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fvirtualoperationlog obj = this.virtualoperationlogDAO.findById(id);
        this.virtualoperationlogDAO.delete(obj);
    }

    public void updateObj(Fvirtualoperationlog obj) {
        this.virtualoperationlogDAO.attachDirty(obj);
    }

    public List<Fvirtualoperationlog> findByProperty(String name, Object value) {
        return this.virtualoperationlogDAO.findByProperty(name, value);
    }

    public List<Fvirtualoperationlog> findAll() {
        return this.virtualoperationlogDAO.findAll();
    }

    public List<Fvirtualoperationlog> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fvirtualoperationlog> all = this.virtualoperationlogDAO.list(firstResult, maxResults, filter, isFY);
        for (Fvirtualoperationlog foperationlog : all) {
            foperationlog.getFuser().getFemail();
            if (foperationlog.getFcreator() != null) {
                foperationlog.getFcreator().getFname();
            }
            foperationlog.getFvirtualcointype().getFname();
        }
        return all;
    }

    public void updateVirtualOperationLog(Fvirtualwallet virtualwallet, Fvirtualoperationlog virtualoperationlog) {
        try {
            if (virtualwallet != null)
                this.virtualwalletDao.attachDirty(virtualwallet);
            this.virtualoperationlogDAO.attachDirty(virtualoperationlog);
            if (virtualoperationlog.getFisSendMsg() == 1) {
                String title = "管理员向您充值" + virtualoperationlog.getFvirtualcointype().getFname() + "币"
                        + virtualoperationlog.getFqty() + "个,已发放，请注意查收";
                Fmessage msg = new Fmessage();
                msg.setFcreateTime(Utils.getTimestamp());
                msg.setFcontent(title);
                msg.setFreceiver(virtualoperationlog.getFuser());
                msg.setFcreator(virtualoperationlog.getFcreator());
                msg.setFtitle(title);
                msg.setFstatus(MessageStatusEnum.NOREAD_VALUE);
                this.messageDAO.save(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}