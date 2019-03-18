package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FbankinfoWithdrawDAO;
import com.huagu.vcoin.main.model.FbankinfoWithdraw;

@Service
public class BankinfoWithdrawService {
    @Autowired
    private FbankinfoWithdrawDAO bankinfoWithdrawDAO;
    @Autowired
    private HttpServletRequest request;

    public FbankinfoWithdraw findById(int id) {
        return this.bankinfoWithdrawDAO.findById(id);
    }

    public void saveObj(FbankinfoWithdraw obj) {
        this.bankinfoWithdrawDAO.save(obj);
    }

    public void deleteObj(int id) {
        FbankinfoWithdraw obj = this.bankinfoWithdrawDAO.findById(id);
        this.bankinfoWithdrawDAO.delete(obj);
    }

    public void updateObj(FbankinfoWithdraw obj) {
        this.bankinfoWithdrawDAO.attachDirty(obj);
    }

    public List<FbankinfoWithdraw> findByProperty(String name, Object value) {
        return this.bankinfoWithdrawDAO.findByProperty(name, value);
    }

    public List<FbankinfoWithdraw> findAll() {
        return this.bankinfoWithdrawDAO.findAll();
    }

    public List<FbankinfoWithdraw> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<FbankinfoWithdraw> list = this.bankinfoWithdrawDAO.list(firstResult, maxResults, filter, isFY);
        for (FbankinfoWithdraw fbankinfoWithdraw : list) {
            if (fbankinfoWithdraw.getFuser() != null)
                fbankinfoWithdraw.getFuser().getFnickName();
        }
        return list;
    }
}