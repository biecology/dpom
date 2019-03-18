package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FfriendlinkDAO;
import com.huagu.vcoin.main.model.Ffriendlink;

@Service
public class FriendLinkService {
    @Autowired
    private FfriendlinkDAO friendlinkDAO;
    @Autowired
    private HttpServletRequest request;

    public Ffriendlink findById(int id) {
        return this.friendlinkDAO.findById(id);
    }

    public void saveObj(Ffriendlink obj) {
        this.friendlinkDAO.save(obj);
    }

    public void deleteObj(int id) {
        Ffriendlink obj = this.friendlinkDAO.findById(id);
        this.friendlinkDAO.delete(obj);
    }

    public void updateObj(Ffriendlink obj) {
        this.friendlinkDAO.attachDirty(obj);
    }

    public List<Ffriendlink> findByProperty(String name, Object value) {
        return this.friendlinkDAO.findByProperty(name, value);
    }

    public List<Ffriendlink> findAll() {
        return this.friendlinkDAO.findAll();
    }

    public List<Ffriendlink> list(int firstResult, int maxResults, String filter, boolean isFY) {
        return this.friendlinkDAO.list(firstResult, maxResults, filter, isFY);
    }

}