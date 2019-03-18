package com.huagu.vcoin.main.service.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huagu.vcoin.main.dao.UserOverflowTracingDAO;
import com.huagu.vcoin.main.model.FcheckOverflowList;


@Service
public class UserOverflowTracingService {
    @Autowired
    private UserOverflowTracingDAO userOverflowTracingDAO;
    
    public String UserOverflowTracingInsert(String stratTime,String endTime,String storedProcedureName) {
    	return userOverflowTracingDAO.UserOverflowTracingInsert(stratTime,endTime,storedProcedureName);
    }
    
    public String UserOverflowTracingInsert(String storedProcedureName) {
    	return userOverflowTracingDAO.UserOverflowTracingInsert(storedProcedureName);
    }
    
    public List<FcheckOverflowList> findAll() {
        return this.userOverflowTracingDAO.findAll();
    }
    
    public Object getAllCount(String tableName, String filter) {
        return this.userOverflowTracingDAO.getAllCount(tableName, filter);
    }
}