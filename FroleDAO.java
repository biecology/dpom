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
import com.huagu.vcoin.main.model.Frole;

/**
 * A data access object (DAO) providing persistence and search support for Frole
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see .Frole
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FroleDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FroleDAO.class);
    // property constants
    public static final String FDESCRIPTION = "fdescription";
    public static final String FNAME = "fname";

    public void save(Frole transientInstance) {
        log.debug("saving Frole instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Frole persistentInstance) {
        log.debug("deleting Frole instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Frole findById(java.lang.Integer id) {
        log.debug("getting Frole instance with id: " + id);
        try {
            Frole instance = (Frole) getSession().get("com.huagu.vcoin.main.model.Frole", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Frole> findByExample(Frole instance) {
        log.debug("finding Frole instance by example");
        try {
            List<Frole> results = getSession().createCriteria("com.huagu.vcoin.main.model.Frole").add(create(instance))
                    .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Frole instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Frole as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Frole> findByFdescription(Object fdescription) {
        return findByProperty(FDESCRIPTION, fdescription);
    }

    public List<Frole> findByFname(Object fname) {
        return findByProperty(FNAME, fname);
    }

    public List findAll() {
        log.debug("finding all Frole instances");
        try {
            String queryString = "from Frole";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Frole merge(Frole detachedInstance) {
        log.debug("merging Frole instance");
        try {
            Frole result = (Frole) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Frole instance) {
        log.debug("attaching dirty Frole instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Frole instance) {
        log.debug("attaching clean Frole instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Frole> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Frole> list = null;
        log.debug("finding Frole instance with filter");
        try {
            String queryString = "from Frole " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Frole by filter name failed", re);
            throw re;
        }
        return list;
    }

}