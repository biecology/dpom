package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Systembankinfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * Systembankinfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ztmp.Systembankinfo
 * @author MyEclipse Persistence Tools
 */

@Repository
public class SystembankinfoDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(SystembankinfoDAO.class);
    // property constants
    public static final String FBANK_NAME = "fbankName";
    public static final String FBANK_ADDRESS = "fbankAddress";
    public static final String FBANK_NUMBER = "fbankNumber";
    public static final String FSTATUS = "fstatus";

    public void save(Systembankinfo transientInstance) {
        log.debug("saving Systembankinfo instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Systembankinfo persistentInstance) {
        log.debug("deleting Systembankinfo instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Systembankinfo findById(java.lang.Integer id) {
        log.debug("getting Systembankinfo instance with id: " + id);
        try {
            Systembankinfo instance = (Systembankinfo) getSession().get("com.huagu.vcoin.main.model.Systembankinfo",
                    id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Systembankinfo> findByExample(Systembankinfo instance) {
        log.debug("finding Systembankinfo instance by example");
        try {
            List<Systembankinfo> results = getSession().createCriteria("com.huagu.vcoin.main.model.Systembankinfo")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Systembankinfo instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Systembankinfo as model where model." + propertyName
                    + "= ? order by model.fid desc";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Systembankinfo> findByFbankName(Object fbankName) {
        return findByProperty(FBANK_NAME, fbankName);
    }

    public List<Systembankinfo> findByFbankAddress(Object fbankAddress) {
        return findByProperty(FBANK_ADDRESS, fbankAddress);
    }

    public List<Systembankinfo> findByFbankNumber(Object fbankNumber) {
        return findByProperty(FBANK_NUMBER, fbankNumber);
    }

    public List<Systembankinfo> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List findAll() {
        log.debug("finding all Systembankinfo instances");
        try {
            String queryString = "from Systembankinfo";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Systembankinfo merge(Systembankinfo detachedInstance) {
        log.debug("merging Systembankinfo instance");
        try {
            Systembankinfo result = (Systembankinfo) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Systembankinfo instance) {
        log.debug("attaching dirty Systembankinfo instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Systembankinfo instance) {
        log.debug("attaching clean Systembankinfo instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Systembankinfo> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Systembankinfo> list = null;
        log.debug("finding Systembankinfo instance with filter");
        try {
            String queryString = "from Systembankinfo " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by filter name failed", re);
            throw re;
        }
        return list;
    }
}