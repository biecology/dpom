package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FintrolinfoDAO;
import com.huagu.vcoin.main.dao.FsubscriptionDAO;
import com.huagu.vcoin.main.dao.FsubscriptionlogDAO;
import com.huagu.vcoin.main.dao.FvirtualwalletDAO;
import com.huagu.vcoin.main.model.Fintrolinfo;
import com.huagu.vcoin.main.model.Fsubscription;
import com.huagu.vcoin.main.model.Fsubscriptionlog;
import com.huagu.vcoin.main.model.Fvirtualwallet;

@Service
public class SubscriptionLogService {
    @Autowired
    private FsubscriptionlogDAO subscriptionlogDAO;
    @Autowired
    private FvirtualwalletDAO virtualwalletDAO;
    @Autowired
    private FintrolinfoDAO introlinfoDAO;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FsubscriptionDAO subscriptionDAO;

    public Fsubscriptionlog findById(int id) {
        return this.subscriptionlogDAO.findById(id);
    }

    public void saveObj(Fsubscriptionlog obj) {
        this.subscriptionlogDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fsubscriptionlog obj = this.subscriptionlogDAO.findById(id);
        this.subscriptionlogDAO.delete(obj);
    }

    public void updateObj(Fsubscriptionlog obj) {
        this.subscriptionlogDAO.attachDirty(obj);
    }

    public List<Fsubscriptionlog> findByProperty(String name, Object value) {
        return this.subscriptionlogDAO.findByProperty(name, value);
    }

    public List<Fsubscriptionlog> findAll() {
        return this.subscriptionlogDAO.findAll();
    }

    public void updateChargeLog1(List<Fvirtualwallet> fvirtualwallets, List<Fintrolinfo> fintrolinfos,
            Fsubscriptionlog fsubscriptionlog) {
        try {
            for (Fintrolinfo fintrolinfo : fintrolinfos) {
                this.introlinfoDAO.save(fintrolinfo);
            }
            for (Fvirtualwallet fvirtualwallet : fvirtualwallets) {
                this.virtualwalletDAO.attachDirty(fvirtualwallet);
            }
            this.subscriptionlogDAO.attachDirty(fsubscriptionlog);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Fsubscriptionlog> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fsubscriptionlog> all = this.subscriptionlogDAO.list(firstResult, maxResults, filter, isFY);
        for (Fsubscriptionlog fsubscriptionlog : all) {
            if (fsubscriptionlog.getFuser() != null) {
                fsubscriptionlog.getFuser().getFnickName();
            }
        }
        return all;
    }

    public void updateChargeLog(Fsubscriptionlog fsubscriptionlog, Fvirtualwallet w, Fvirtualwallet v) {
        try {
            this.subscriptionlogDAO.attachDirty(fsubscriptionlog);
            this.virtualwalletDAO.attachDirty(w);
            this.virtualwalletDAO.attachDirty(v);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void updateChargeLog(Fsubscriptionlog fsubscriptionlog, Fvirtualwallet w1, Fvirtualwallet v1,
            Fvirtualwallet v) {
        try {
            this.subscriptionlogDAO.attachDirty(fsubscriptionlog);
            if (w1 != null) {
                this.virtualwalletDAO.attachDirty(w1);
            }
            if (v1 != null) {
                this.virtualwalletDAO.attachDirty(v1);
            }
            this.virtualwalletDAO.attachDirty(v);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void updateSendFrozen(Fvirtualwallet fvirtualwallet, Fsubscriptionlog fsubscriptionlog, Fintrolinfo info) {
        try {
            this.virtualwalletDAO.attachDirty(fvirtualwallet);
            this.subscriptionlogDAO.attachDirty(fsubscriptionlog);
            this.introlinfoDAO.save(info);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void updateChargeLog(Fsubscriptionlog fsubscriptionlog, Fsubscription sub) {
        try {
            this.subscriptionlogDAO.attachDirty(fsubscriptionlog);
            this.subscriptionDAO.attachDirty(sub);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}