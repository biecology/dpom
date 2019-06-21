package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fapi;

/**
 * A data access object (DAO) providing persistence and search support for Fapi
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see ztmp.Fapi
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FapiDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FapiDAO.class);
    // property constants
    public static final String FPARTNER = "fpartner";
    public static final String FSECRET = "fsecret";

    public void save(Fapi transientInstance) {
        log.debug("saving Fapi instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fapi persistentInstance) {
        log.debug("deleting Fapi instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fapi findById(java.lang.Integer id) {
        log.debug("getting Fapi instance with id: " + id);
        try {
            Fapi instance = (Fapi) getSession().get("com.huagu.vcoin.main.model.Fapi", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fapi> findByExample(Fapi instance) {
        log.debug("finding Fapi instance by example");
        try {
            List<Fapi> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fapi").add(create(instance))
                    .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fapi instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fapi as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fapi> findByFpartner(Object fpartner) {
        return findByProperty(FPARTNER, fpartner);
    }

    public List<Fapi> findByFsecret(Object fsecret) {
        return findByProperty(FSECRET, fsecret);
    }

    public List findAll() {
        log.debug("finding all Fapi instances");
        try {
            String queryString = "from Fapi";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fapi merge(Fapi detachedInstance) {
        log.debug("merging Fapi instance");
        try {
            Fapi result = (Fapi) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fapi instance) {
        log.debug("attaching dirty Fapi instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fapi instance) {
        log.debug("attaching clean Fapi instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}