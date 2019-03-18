package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FtransportlogDAO;
import com.huagu.vcoin.main.model.Ftransportlog;

@Service
public class TransportlogService {
    @Autowired
    private FtransportlogDAO transportlogDAO;
    @Autowired
    private HttpServletRequest request;

    public Ftransportlog findById(int id) {
        return this.transportlogDAO.findById(id);
    }

    public void saveObj(Ftransportlog obj) {
        this.transportlogDAO.save(obj);
    }

    public void deleteObj(int id) {
        Ftransportlog obj = this.transportlogDAO.findById(id);
        this.transportlogDAO.delete(obj);
    }

    public void updateObj(Ftransportlog obj) {
        this.transportlogDAO.attachDirty(obj);
    }

    public List<Ftransportlog> findByProperty(String name, Object value) {
        return this.transportlogDAO.findByProperty(name, value);
    }

    public List<Ftransportlog> findAll() {
        return this.transportlogDAO.findAll();
    }

    public List<Ftransportlog> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Ftransportlog> list = this.transportlogDAO.list(firstResult, maxResults, filter, isFY);
        for (Ftransportlog ftransportlog : list) {
            if (ftransportlog.getFuser() != null) {
                ftransportlog.getFuser().getFnickName();
            }
        }
        return list;
    }
}