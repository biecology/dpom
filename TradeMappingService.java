package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FtrademappingDAO;
import com.huagu.vcoin.main.model.Ftrademapping;

@Service
public class TradeMappingService {
    @Autowired
    private FtrademappingDAO trademappingDAO;
    @Autowired
    private HttpServletRequest request;

    public Ftrademapping findById(int id) {
        Ftrademapping ftrademapping = this.trademappingDAO.findById(id);
        if (ftrademapping != null && ftrademapping.getFvirtualcointypeByFvirtualcointype1() != null) {
            ftrademapping.getFvirtualcointypeByFvirtualcointype1().getFname();
        }
        if (ftrademapping != null && ftrademapping.getFvirtualcointypeByFvirtualcointype2() != null) {
            ftrademapping.getFvirtualcointypeByFvirtualcointype2().getFname();
        }
        return ftrademapping;
    }

    public Ftrademapping findById2(int id) {
        Ftrademapping ftrademapping = this.trademappingDAO.findById(id);
        return ftrademapping;
    }

    public void saveObj(Ftrademapping obj) {
        this.trademappingDAO.save(obj);
    }

    public void deleteObj(int id) {
        Ftrademapping obj = this.trademappingDAO.findById(id);
        this.trademappingDAO.delete(obj);
    }

    public void updateObj(Ftrademapping obj) {
        this.trademappingDAO.attachDirty(obj);
    }

    public List<Ftrademapping> findByProperty(String name, Object value) {
        return this.trademappingDAO.findByProperty(name, value);
    }

    public List<Ftrademapping> findAll() {
        return this.trademappingDAO.findAll();
    }

    public List<Ftrademapping> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Ftrademapping> lists = this.trademappingDAO.list(firstResult, maxResults, filter, isFY);
        for (Ftrademapping ftrademapping : lists) {
            if (ftrademapping.getFvirtualcointypeByFvirtualcointype1() != null) {
                ftrademapping.getFvirtualcointypeByFvirtualcointype1().getFname();
            }
            if (ftrademapping.getFvirtualcointypeByFvirtualcointype2() != null) {
                ftrademapping.getFvirtualcointypeByFvirtualcointype2().getFname();
            }
        }
        return lists;
    }
}