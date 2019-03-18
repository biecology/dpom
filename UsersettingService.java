package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FusersettingDAO;
import com.huagu.vcoin.main.model.Fusersetting;

@Service
public class UsersettingService {
    @Autowired
    private FusersettingDAO usersettingDAO;
    @Autowired
    private HttpServletRequest request;

    public Fusersetting findById(int id) {
        return this.usersettingDAO.findById(id);
    }

    public void saveObj(Fusersetting obj) {
        this.usersettingDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fusersetting obj = this.usersettingDAO.findById(id);
        this.usersettingDAO.delete(obj);
    }

    public void updateObj(Fusersetting obj) {
        this.usersettingDAO.attachDirty(obj);
    }

    public List<Fusersetting> findByProperty(String name, Object value) {
        return this.usersettingDAO.findByProperty(name, value);
    }

    public List<Fusersetting> findAll() {
        return this.usersettingDAO.findAll();
    }

    public List<Fusersetting> list(int firstResult, int maxResults, String filter, boolean isFY) {
        return this.usersettingDAO.list(firstResult, maxResults, filter, isFY);
    }
}