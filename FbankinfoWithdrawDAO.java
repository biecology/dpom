package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.FbankinfoWithdraw;

/**
 * A data access object (DAO) providing persistence and search support for
 * FbankinfoWithdraw entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ztmp.FbankinfoWithdraw
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FbankinfoWithdrawDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FbankinfoWithdrawDAO.class);
    // property constants
    public static final String VERSION = "version";
    public static final String FNAME = "fname";
    public static final String FBANK_NUMBER = "fbankNumber";
    public static final String FBANK_TYPE = "fbankType";
    public static final String FSTATUS = "fstatus";

    public void save(FbankinfoWithdraw transientInstance) {
        log.debug("saving FbankinfoWithdraw instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(FbankinfoWithdraw persistentInstance) {
        log.debug("deleting FbankinfoWithdraw instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public FbankinfoWithdraw findById(java.lang.Integer id) {
        log.debug("getting FbankinfoWithdraw instance with id: " + id);
        try {
            FbankinfoWithdraw instance = (FbankinfoWithdraw) getSession()
                    .get("com.huagu.vcoin.main.model.FbankinfoWithdraw", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<FbankinfoWithdraw> findByExample(FbankinfoWithdraw instance) {
        log.debug("finding FbankinfoWithdraw instance by example");
        try {
            List<FbankinfoWithdraw> results = getSession()
                    .createCriteria("com.huagu.vcoin.main.model.FbankinfoWithdraw").add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding FbankinfoWithdraw instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from FbankinfoWithdraw as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<FbankinfoWithdraw> findByVersion(Object version) {
        return findByProperty(VERSION, version);
    }

    public List<FbankinfoWithdraw> findByFname(Object fname) {
        return findByProperty(FNAME, fname);
    }

    public List<FbankinfoWithdraw> findByFbankNumber(Object fbankNumber) {
        return findByProperty(FBANK_NUMBER, fbankNumber);
    }

    public List<FbankinfoWithdraw> findByFbankType(Object fbankType) {
        return findByProperty(FBANK_TYPE, fbankType);
    }

    public List<FbankinfoWithdraw> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List findAll() {
        log.debug("finding all FbankinfoWithdraw instances");
        try {
            String queryString = "from FbankinfoWithdraw";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public FbankinfoWithdraw merge(FbankinfoWithdraw detachedInstance) {
        log.debug("merging FbankinfoWithdraw instance");
        try {
            FbankinfoWithdraw result = (FbankinfoWithdraw) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FbankinfoWithdraw instance) {
        log.debug("attaching dirty FbankinfoWithdraw instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(FbankinfoWithdraw instance) {
        log.debug("attaching clean FbankinfoWithdraw instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<FbankinfoWithdraw> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<FbankinfoWithdraw> list = null;
        log.debug("finding FbankinfoWithdraw instance with filter");
        try {
            String queryString = "from FbankinfoWithdraw " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by FbankinfoWithdraw name failed", re);
            throw re;
        }
        return list;
    }
}