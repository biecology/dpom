package com.huagu.vcoin.main.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.dao.FtradehistoryDAO;
import com.huagu.vcoin.main.model.Ftradehistory;
import com.huagu.vcoin.main.model.Ftrademapping;
import com.huagu.vcoin.main.model.Fvirtualcointype;

@Service
public class TradehistoryService {
    private static Logger log = Logger.getLogger(TradehistoryService.class);

    @Autowired
    private FtradehistoryDAO tradehistoryDAO;
    @Autowired
    private HttpServletRequest request;

    public Ftradehistory findById(int id) {
        return this.tradehistoryDAO.findById(id);
    }

    public void updateUser(String sql) {
        this.tradehistoryDAO.updateUser(sql);
    }

    public void saveObj(Ftradehistory obj) {
        this.tradehistoryDAO.save(obj);
    }

    public void deleteObj(int id) {
        Ftradehistory obj = this.tradehistoryDAO.findById(id);
        this.tradehistoryDAO.delete(obj);
    }

    public void updateObj(Ftradehistory obj) {
        this.tradehistoryDAO.attachDirty(obj);
    }

    public List<Ftradehistory> findByProperty(String name, Object value) {
        return this.tradehistoryDAO.findByProperty(name, value);
    }

    public List<Ftradehistory> findAll() {
        return this.tradehistoryDAO.findAll();
    }

    public List<Ftradehistory> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Ftradehistory> lists = this.tradehistoryDAO.list(firstResult, maxResults, filter, isFY);
        for (Ftradehistory item : lists) {
            Ftrademapping mp = item.getFtrademapping();
            if (mp == null)
                log.error("Ftrademapping is null");
            Fvirtualcointype type1 = mp.getFvirtualcointypeByFvirtualcointype1();
            if (type1 != null) {
                type1.getFname();
            }
            Fvirtualcointype type2 = mp.getFvirtualcointypeByFvirtualcointype1();
            if (type2 != null) {
                type2.getFname();
            }
        }
        return lists;
    }
}