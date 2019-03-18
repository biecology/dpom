package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FsubscriptionDAO;
import com.huagu.vcoin.main.dao.FsubscriptionlogDAO;
import com.huagu.vcoin.main.dao.FvirtualwalletDAO;
import com.huagu.vcoin.main.model.Fsubscription;
import com.huagu.vcoin.main.model.Fsubscriptionlog;
import com.huagu.vcoin.main.model.Fvirtualwallet;

@Service
public class SubscriptionService {
    @Autowired
    private FsubscriptionDAO subscriptionDAO;
    @Autowired
    private FsubscriptionlogDAO subscriptionlogDAO;
    @Autowired
    private FvirtualwalletDAO virtualwalletDAO;
    @Autowired
    private HttpServletRequest request;

    public Fsubscription findById(int id) {
        return this.subscriptionDAO.findById(id);
    }

    public void saveObj(Fsubscription obj) {
        this.subscriptionDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fsubscription obj = this.subscriptionDAO.findById(id);
        this.subscriptionDAO.delete(obj);
    }

    public void updateObj(Fsubscription obj) {
        this.subscriptionDAO.attachDirty(obj);
    }

    public List<Fsubscription> findByProperty(String name, Object value) {
        return this.subscriptionDAO.findByProperty(name, value);
    }

    public List<Fsubscription> findAll() {
        return this.subscriptionDAO.findAll();
    }

    public void updateSubscription(Fvirtualwallet fvirtualwallet1, Fvirtualwallet fvirtualwallet,
            Fsubscriptionlog fsubscriptionlog, Fsubscription fsubscription) {
        try {
            this.virtualwalletDAO.attachDirty(fvirtualwallet);
            this.virtualwalletDAO.attachDirty(fvirtualwallet1);
            this.subscriptionDAO.attachDirty(fsubscription);
            this.subscriptionlogDAO.save(fsubscriptionlog);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public List<Fsubscription> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fsubscription> all = this.subscriptionDAO.list(firstResult, maxResults, filter, isFY);
        for (Fsubscription fsubscription : all) {
            if (fsubscription.getFvirtualcointype() != null) {
                fsubscription.getFvirtualcointype().getFname();
            }

            if (fsubscription.getFvirtualcointypeCost() != null) {
                fsubscription.getFvirtualcointypeCost().getFname();
            }
        }
        return all;
    }
}