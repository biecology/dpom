package com.huagu.vcoin.main.dao;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.coa.common.cons.MyQuery;
import com.huagu.coa.common.cons.Mysql;
import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fabout;
import com.huagu.vcoin.main.model.FuserReceipt;

import cn.cerc.jdb.core.Record;
/**
 * 
 * @author xinchang
 *
 */
@Repository
public class FuserReceiptDAO extends HibernateDaoSupport  {
	
	private static final Logger log = LoggerFactory.getLogger(FuserReceiptDAO.class);
	
	public List<FuserReceipt> findAll() {
        log.debug("finding all FuserReceipt instances");
        try {
            String hql = "from FuserReceipt";
            Query queryObject = getSession().createQuery(hql);
            
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
	
	public FuserReceipt findById(Integer id){
		
		log.debug("getting FuserReceipt instance with id: " + id);
        try {
        	FuserReceipt instance = (FuserReceipt) getSession().get("com.huagu.vcoin.main.model.FuserReceipt", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
	}
	
	
	public List<FuserReceipt> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<FuserReceipt> list = null;
        log.debug("finding FuserReceipt instance with filter");
        try {
            String queryString = "from FuserReceipt " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fabout name failed", re);
            throw re;
        }
        return list;
    }
	
	public void save(FuserReceipt fuserReceipt){
		log.debug("saving FuserReceipt instance");
        try {
            getSession().save(fuserReceipt);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
	}
	
	public void saveOrUpdate(FuserReceipt fuserReceipt){
		log.debug("saving FuserReceipt instance");
		try {
			getSession().saveOrUpdate(fuserReceipt);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void delete(FuserReceipt fuserReceipt){
		log.debug("deleting FuserReceipt instance");
		try {
			getSession().delete(fuserReceipt);
			log.debug("delet successful");
		} catch (RuntimeException e) {
			log.error("delet failed", e);
			throw e;
		}
	}
	
	public void update(FuserReceipt fuserReceipt){
		log.debug("update FuserReceipt instance");
		try {
			getSession().update(fuserReceipt);
			log.debug("update successful");
		} catch (RuntimeException e) {
			log.error("update failed",e);
			throw e;
		}
		
	}
	
}
