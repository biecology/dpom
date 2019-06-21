package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fbankin;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fbankin entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ztmp.Fbankin
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FbankinDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FbankinDAO.class);
    // property constants
    public static final String UID = "uid";
    public static final String RMB = "rmb";
    public static final String BANKNAME = "bankname";
    public static final String OK = "ok";

    public void save(Fbankin transientInstance) {
        log.debug("saving Fbankin instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fbankin persistentInstance) {
        log.debug("deleting Fbankin instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fbankin findById(java.lang.Integer id) {
        log.debug("getting Fbankin instance with id: " + id);
        try {
            Fbankin instance = (Fbankin) getSession().get("com.huagu.vcoin.main.model.Fbankin", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fbankin> findByExample(Fbankin instance) {
        log.debug("finding Fbankin instance by example");
        try {
            List<Fbankin> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fbankin")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fbankin instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fbankin as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fbankin> findByUid(Object uid) {
        return findByProperty(UID, uid);
    }

    public List<Fbankin> findByRmb(Object rmb) {
        return findByProperty(RMB, rmb);
    }

    public List<Fbankin> findByBankname(Object bankname) {
        return findByProperty(BANKNAME, bankname);
    }

    public List<Fbankin> findByOk(Object ok) {
        return findByProperty(OK, ok);
    }

    public List findAll() {
        log.debug("finding all Fbankin instances");
        try {
            String queryString = "from Fbankin";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fbankin merge(Fbankin detachedInstance) {
        log.debug("merging Fbankin instance");
        try {
            Fbankin result = (Fbankin) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fbankin instance) {
        log.debug("attaching dirty Fbankin instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fbankin instance) {
        log.debug("attaching clean Fbankin instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}