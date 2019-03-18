package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FcoinvotelogDAO;
import com.huagu.vcoin.main.model.Fcoinvotelog;

@Service
public class CoinVotelogService {
    @Autowired
    private FcoinvotelogDAO coinvotelogDAO;
    @Autowired
    private HttpServletRequest request;

    public Fcoinvotelog findById(int id) {
        return this.coinvotelogDAO.findById(id);
    }

    public void saveObj(Fcoinvotelog obj) {
        this.coinvotelogDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fcoinvotelog obj = this.coinvotelogDAO.findById(id);
        this.coinvotelogDAO.delete(obj);
    }

    public void updateObj(Fcoinvotelog obj) {
        this.coinvotelogDAO.attachDirty(obj);
    }

    public List<Fcoinvotelog> findByProperty(String name, Object value) {
        return this.coinvotelogDAO.findByProperty(name, value);
    }

    public List<Fcoinvotelog> findAll() {
        return this.coinvotelogDAO.findAll();
    }

    public List<Fcoinvotelog> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fcoinvotelog> lists = this.coinvotelogDAO.list(firstResult, maxResults, filter, isFY);
        for (Fcoinvotelog fcoinvotelog : lists) {
            if (fcoinvotelog.getFuser() != null) {
                fcoinvotelog.getFuser().getFnickName();
            }
        }

        return lists;
    }
}