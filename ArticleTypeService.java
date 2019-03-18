package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FarticletypeDAO;
import com.huagu.vcoin.main.model.Farticletype;

@Service
public class ArticleTypeService {
    @Autowired
    private FarticletypeDAO farticletypeDAO;
    @Autowired
    private HttpServletRequest request;

    public Farticletype findById(int id) {
        return this.farticletypeDAO.findById(id);
    }

    public void saveObj(Farticletype obj) {
        this.farticletypeDAO.save(obj);
    }

    public void deleteObj(int id) {
        Farticletype obj = this.farticletypeDAO.findById(id);
        this.farticletypeDAO.delete(obj);
    }

    public void updateObj(Farticletype obj) {
        this.farticletypeDAO.attachDirty(obj);
    }

    public List<Farticletype> findByProperty(String name, Object value) {
        return this.farticletypeDAO.findByProperty(name, value);
    }

    public List<Farticletype> findAll() {
        return this.farticletypeDAO.findAll();
    }

    public List<Farticletype> list(int firstResult, int maxResults, String filter, boolean isFY) {
        return this.farticletypeDAO.list(firstResult, maxResults, filter, isFY);
    }
}