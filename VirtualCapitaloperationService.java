package com.huagu.vcoin.main.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.Enum.VirtualCapitalOperationOutStatusEnum;
import com.huagu.vcoin.main.comm.ConstantMap;
import com.huagu.vcoin.main.dao.FvirtualcaptualoperationDAO;
import com.huagu.vcoin.main.dao.FvirtualwalletDAO;
import com.huagu.vcoin.main.model.Fvirtualcaptualoperation;
import com.huagu.vcoin.main.model.Fvirtualcointype;
import com.huagu.vcoin.main.model.Fvirtualwallet;

@Service
public class VirtualCapitaloperationService {
    @Autowired
    private FvirtualcaptualoperationDAO virtualcaptualoperationDAO;
    @Autowired
    private FvirtualwalletDAO virtualwalletDAO;
    @Autowired
    private ConstantMap map;
    @Autowired
    private VirtualCoinService virtualCoinService;

    public Fvirtualcaptualoperation findById(int id) {
        Fvirtualcaptualoperation info = this.virtualcaptualoperationDAO.findById(id);
        info.getFuser().getFnickName();
        info.getFvirtualcointype().getFname();
        return info;
    }

    public void saveObj(Fvirtualcaptualoperation obj) {
        this.virtualcaptualoperationDAO.save(obj);
    }

    public void deleteObj(int id) {
        Fvirtualcaptualoperation obj = this.virtualcaptualoperationDAO.findById(id);
        this.virtualcaptualoperationDAO.delete(obj);
    }

    public void updateObj(Fvirtualcaptualoperation obj) {
        this.virtualcaptualoperationDAO.attachDirty(obj);
    }

    public List<Fvirtualcaptualoperation> findByProperty(String name, Object value) {
        return this.virtualcaptualoperationDAO.findByProperty(name, value);
    }

    public List<Fvirtualcaptualoperation> findAll() {
        return this.virtualcaptualoperationDAO.findAll();
    }

    public List<Fvirtualcaptualoperation> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fvirtualcaptualoperation> all = this.virtualcaptualoperationDAO.list(firstResult, maxResults, filter,
                isFY);
        for (Fvirtualcaptualoperation virtualcaptualoperation : all) {
            if (virtualcaptualoperation.getFuser() != null) {
                virtualcaptualoperation.getFuser().getFemail();
            }
            if (virtualcaptualoperation.getFvirtualcointype() != null) {
                virtualcaptualoperation.getFvirtualcointype().getfShortName();
            }

        }
        return all;
    }

    public void updateCapital(Fvirtualcaptualoperation virtualcaptualoperation, Fvirtualwallet virtualwallet,
            Fvirtualcointype coin) throws RuntimeException {
        if (virtualcaptualoperation.getFtradeUniqueNumber() != null
                && virtualcaptualoperation.getFtradeUniqueNumber().trim().length() > 0) {
            return;
        }
        int status = virtualcaptualoperation.getFstatus();
        if (status != VirtualCapitalOperationOutStatusEnum.OperationLock) {
            return;
        }
        virtualcaptualoperation.setFstatus(VirtualCapitalOperationOutStatusEnum.OperationSuccess);
        if (coin.isFisautosend()) {
            virtualcaptualoperation.setFisaudit(false);
        } else {
            virtualcaptualoperation.setFisaudit(true);
        }

        try {
            this.virtualcaptualoperationDAO.attachDirty(virtualcaptualoperation);
            this.virtualwalletDAO.attachDirty(virtualwallet);
        } catch (Exception e1) {
            throw new RuntimeException("发送失败");
        }
    }

    public void updateCapital(Fvirtualcaptualoperation virtualcaptualoperation, Fvirtualwallet virtualwallet)
            throws RuntimeException {
        try {
            this.virtualcaptualoperationDAO.attachDirty(virtualcaptualoperation);
            this.virtualwalletDAO.attachDirty(virtualwallet);
        } catch (Exception e1) {
            throw new RuntimeException();
        }
    }

    public List<Map> getTotalAmount(int type, String fstatus, boolean isToday) {
        return this.virtualcaptualoperationDAO.getTotalQty(type, fstatus, isToday);
    }

    public List<Map> getTotalFees(int type, String fstatus, boolean isToday) {
        return this.virtualcaptualoperationDAO.getTotalFees(type, fstatus, isToday);
    }

    public List getTotalGroup(String filter) {
        return this.virtualcaptualoperationDAO.getTotalGroup(filter);
    }

    public void updateCharge(Fvirtualcaptualoperation v, Fvirtualwallet w) {
        try {
            this.virtualcaptualoperationDAO.attachDirty(v);
            this.virtualwalletDAO.attachDirty(w);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }
}