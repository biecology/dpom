package com.huagu.vcoin.main.dao;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;

@Repository
public class UserReconciliationRecordDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(UserReconciliationRecordDAO.class);

    public List findAll() {
        log.debug("finding all FuserReconciliationRecordss instances");
        try {
            String queryString = "from FuserReconciliationRecordss";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
}