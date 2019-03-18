package com.huagu.vcoin.main.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huagu.vcoin.main.Enum.VirtualCapitalOperationOutStatusEnum;
import com.huagu.vcoin.main.comm.ConstantMap;
import com.huagu.vcoin.main.dao.FvirtualcaptualoperationDAO;
import com.huagu.vcoin.main.dao.FvirtualwalletDAO;
import com.huagu.vcoin.main.dao.UserReconciliationRecordDAO;
import com.huagu.vcoin.main.model.FuserReconciliationRecord;
import com.huagu.vcoin.main.model.FuserReconciliationRecordss;
import com.huagu.vcoin.main.model.Fvirtualcaptualoperation;
import com.huagu.vcoin.main.model.Fvirtualcointype;
import com.huagu.vcoin.main.model.Fvirtualwallet;

@Service
public class UserReconciliationRecordService {
    @Autowired
    private UserReconciliationRecordDAO userReconciliationRecordDAO;

    public List<FuserReconciliationRecordss> findAll() {
        return this.userReconciliationRecordDAO.findAll();
    }
}