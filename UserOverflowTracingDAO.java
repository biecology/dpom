package com.huagu.vcoin.main.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;


@Repository
public class UserOverflowTracingDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(UserOverflowTracingDAO.class);

    public String UserOverflowTracingInsert(String stratTime,String endTime,String storedProcedureName) {
    	String result = "";
        Connection conn = null;
        CallableStatement statement = null;
        try {
            conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
            statement = conn.prepareCall("{call "+storedProcedureName+ "(?,?,?)}");
            statement.setString(1, stratTime);
            statement.setString(2, endTime);
            statement.registerOutParameter(3, Types.VARCHAR);
            statement.execute();
            result = statement.getObject(3) + "";
            statement.close();
            conn.close();
        } catch (SQLException e) {
        	result = "0"+e.getMessage();
        }
        return result;
    }
    
    public String UserOverflowTracingInsert(String storedProcedureName) {
    	String result = "";
        Connection conn = null;
        CallableStatement statement = null;
        try {
            conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
            statement = conn.prepareCall("{call "+storedProcedureName+"(?)}");
            statement.registerOutParameter(1, Types.VARCHAR);
            statement.execute();
            result = statement.getObject(1) + "";
            statement.close();
            conn.close();
        } catch (SQLException e) {
        	result = "0"+e.getMessage();
        }
        return result;
    }
    
    public List findAll() {
        log.debug("finding all FcheckOverflowList instances");
        try {
            String queryString = "from FcheckOverflowList";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
    
    public int getAllCount(String tableName, String filter) {
   	   String queryString = "select * from " + tableName + filter;
       Query queryObject = getSession().createSQLQuery(queryString);
       return queryObject.list().size();
   }
}