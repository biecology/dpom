package com.huagu.vcoin.main.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FcoinvoteDAO;
import com.huagu.vcoin.main.dao.FcoinvotelogDAO;
import com.huagu.vcoin.main.dao.FvirtualwalletDAO;
import com.huagu.vcoin.main.model.Fcoinvote;
import com.huagu.vcoin.main.model.Fcoinvotelog;
import com.huagu.vcoin.main.model.Fvirtualwallet;

@Service
public class CoinVoteService {
    @Autowired
    private FcoinvoteDAO coinvoteDAO;
    @Autowired
    private FvirtualwalletDAO virtualwalletDAO;
    @Autowired
    private FcoinvotelogDAO coinvotelogDAO;

    public Fcoinvote findById(int id) {
        return this.coinvoteDAO.findById(id);
    }

    public void saveObj(Fcoinvote obj) {
        this.coinvoteDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fcoinvote obj = this.coinvoteDAO.findById(id);
        this.coinvoteDAO.delete(obj);
    }

    public void updateObj(Fcoinvote obj) {
        this.coinvoteDAO.attachDirty(obj);
    }

    public List<Fcoinvote> findByProperty(String name, Object value) {
        return this.coinvoteDAO.findByProperty(name, value);
    }

    public List<Fcoinvote> findAll() {
        return this.coinvoteDAO.findAll();
    }

    public List<Fcoinvote> list(int firstResult, int maxResults, String filter, boolean isFY) {
        return this.coinvoteDAO.list(firstResult, maxResults, filter, isFY);
    }

    public void updateFcoinvote(Fvirtualwallet fvirtualwallet, Fcoinvote fcoinvote, Fcoinvotelog fcoinvotelog) {
        try {
            this.virtualwalletDAO.attachDirty(fvirtualwallet);
            this.coinvoteDAO.attachDirty(fcoinvote);
            this.coinvotelogDAO.save(fcoinvotelog);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}