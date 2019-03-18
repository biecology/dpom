package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FroleSecurityDAO;
import com.huagu.vcoin.main.model.FroleSecurity;

@Service
public class RoleSecurityService {
    @Autowired
    private FroleSecurityDAO roleSecurityDAO;
    @Autowired
    private HttpServletRequest request;

    public FroleSecurity findById(int id) {
        return this.roleSecurityDAO.findById(id);
    }

    public void saveObj(FroleSecurity obj) {
        this.roleSecurityDAO.save(obj);
    }

    public void deleteObj(int id) {
        FroleSecurity obj = this.roleSecurityDAO.findById(id);
        this.roleSecurityDAO.delete(obj);
    }

    public void updateObj(FroleSecurity obj) {
        this.roleSecurityDAO.attachDirty(obj);
    }

    public List<FroleSecurity> findByProperty(String name, Object value) {
        return this.roleSecurityDAO.findByProperty(name, value);
    }

    public List<FroleSecurity> findAll() {
        return this.roleSecurityDAO.findAll();
    }

    public List<FroleSecurity> list(int firstResult, int maxResults, String filter, boolean isFY) {
        return this.roleSecurityDAO.list(firstResult, maxResults, filter, isFY);
    }

    public void deleteByRoleId(int roleId) {
        this.roleSecurityDAO.deleteByRoleId(roleId);
    }
}