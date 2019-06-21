package com.huagu.vcoin.main.dao;

// default package

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fgoodtype;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fgoodtype entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .Fgoodtype
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FgoodtypeDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FgoodtypeDAO.class);
    // property constants
    public static final String FNAME = "fname";

    public void save(Fgoodtype transientInstance) {
        log.debug("saving Fgoodtype instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fgoodtype persistentInstance) {
        log.debug("deleting Fgoodtype instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fgoodtype findById(java.lang.Integer id) {
        log.debug("getting Fgoodtype instance with id: " + id);
        try {
            Fgoodtype instance = (Fgoodtype) getSession().get("com.huagu.vcoin.main.model.Fgoodtype", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fgoodtype> findByExample(Fgoodtype instance) {
        log.debug("finding Fgoodtype instance by example");
        try {
            List<Fgoodtype> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fgoodtype")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fgoodtype instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fgoodtype as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fgoodtype> findByFname(Object fname) {
        return findByProperty(FNAME, fname);
    }

    public List findAll() {
        log.debug("finding all Fgoodtype instances");
        try {
            String queryString = "from Fgoodtype";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fgoodtype merge(Fgoodtype detachedInstance) {
        log.debug("merging Fgoodtype instance");
        try {
            Fgoodtype result = (Fgoodtype) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fgoodtype instance) {
        log.debug("attaching dirty Fgoodtype instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fgoodtype instance) {
        log.debug("attaching clean Fgoodtype instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fgoodtype> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fgoodtype> list = null;
        log.debug("finding Fgoodtype instance with filter");
        try {
            String queryString = "from Fgoodtype " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fgoodtype name failed", re);
            throw re;
        }
        return list;
    }

}