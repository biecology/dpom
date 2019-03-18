package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FbalanceearningDAO;
import com.huagu.vcoin.main.model.Fbalanceearning;

@Service
public class BalanceearingService {
    @Autowired
    private FbalanceearningDAO balanceearningDAO;
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
        return this.balanceearningDAO.list(firstResult, maxResults, filter, isFY);
    }
}