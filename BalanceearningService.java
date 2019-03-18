package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FbalanceearningDAO;
import com.huagu.vcoin.main.dao.FbalancelogDAO;
import com.huagu.vcoin.main.dao.FmessageDAO;
import com.huagu.vcoin.main.dao.FscoreDAO;
import com.huagu.vcoin.main.dao.FuserDAO;
import com.huagu.vcoin.main.dao.FvirtualwalletDAO;
import com.huagu.vcoin.main.model.Fbalanceearning;
import com.huagu.vcoin.main.model.Fbalancelog;
import com.huagu.vcoin.main.model.Fmessage;
import com.huagu.vcoin.main.model.Fscore;
import com.huagu.vcoin.main.model.Fuser;
import com.huagu.vcoin.main.model.Fvirtualwallet;

@Service
public class BalanceearningService {
    @Autowired
    private FbalanceearningDAO balanceearningDAO;
    @Autowired
    private FscoreDAO fscoreDAO;
    @Autowired
    private FmessageDAO messageDAO;
    @Autowired
    private FuserDAO userDAO;
    @Autowired
    private FvirtualwalletDAO virtualwalletDAO;
    @Autowired
    private FbalancelogDAO balancelogDAO;
    @Autowired
    private HttpServletRequest request;

    public Fbalanceearning findById(int id) {
        return this.balanceearningDAO.findById(id);
    }

    public void saveObj(Fbalanceearning obj) {
        this.balanceearningDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fbalanceearning obj = this.balanceearningDAO.findById(id);
        this.balanceearningDAO.delete(obj);
    }

    public void updateObj(Fbalanceearning obj) {
        this.balanceearningDAO.attachDirty(obj);
    }

    public List<Fbalanceearning> findByProperty(String name, Object value) {
        return this.balanceearningDAO.findByProperty(name, value);
    }

    public List<Fbalanceearning> findAll() {
        return this.balanceearningDAO.findAll();
    }

    public List<Fbalanceearning> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fbalanceearning> list = this.balanceearningDAO.list(firstResult, maxResults, filter, isFY);
        for (Fbalanceearning fbalanceearning : list) {
            if (fbalanceearning.getFuser() != null) {
                fbalanceearning.getFuser().getFareaCode();
            }
        }
        return list;
    }

    public void updateLog(Fvirtualwallet fvirtualwallet, Fbalanceearning balanceearning, Fmessage message) {
        try {
            this.virtualwalletDAO.attachDirty(fvirtualwallet);
            this.balanceearningDAO.attachDirty(balanceearning);
            this.messageDAO.save(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void updateLog(Fscore fscore, Fbalanceearning balanceearning, Fmessage message) {
        try {
            this.fscoreDAO.attachDirty(fscore);
            this.balanceearningDAO.attachDirty(balanceearning);
            this.messageDAO.save(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void updateLog(Fuser fuser, Fbalancelog fbalancelog) {
        try {
            this.userDAO.attachDirty(fuser);
            this.balancelogDAO.attachDirty(fbalancelog);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    // public Map<String,Double> getTotalMap(boolean isToday) {
    // return this.balanceearningDAO.getTotalMap(isToday);
    // }
}