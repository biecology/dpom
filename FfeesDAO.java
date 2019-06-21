package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Ffees;

/**
 * A data access object (DAO) providing persistence and search support for Ffees
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see ztmp.Ffees
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FfeesDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FfeesDAO.class);
    // property constants
    public static final String FFEE = "ffee";

    public void save(Ffees transientInstance) {
        log.debug("saving Ffees instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Ffees persistentInstance) {
        log.debug("deleting Ffees instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Ffees findById(java.lang.Integer id) {
        log.debug("getting Ffees instance with id: " + id);
        try {
            Ffees instance = (Ffees) getSession().get("com.huagu.vcoin.main.model.Ffees", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Ffees> findByExample(Ffees instance) {
        log.debug("finding Ffees instance by example");
        try {
            List<Ffees> results = getSession().createCriteria("com.huagu.vcoin.main.model.Ffees").add(create(instance))
                    .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Ffees instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Ffees as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Ffees> findByFfee(Object ffee) {
        return findByProperty(FFEE, ffee);
    }

    public List findAll() {
        log.debug("finding all Ffees instances");
        try {
            String queryString = "from Ffees";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Ffees merge(Ffees detachedInstance) {
        log.debug("merging Ffees instance");
        try {
            Ffees result = (Ffees) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Ffees instance) {
        log.debug("attaching dirty Ffees instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Ffees instance) {
        log.debug("attaching clean Ffees instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Ffees> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Ffees> list = null;
        log.debug("finding Ffees instance with filter");
        try {
            String queryString = "from Ffees " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Ffees by filter name failed", re);
            throw re;
        }
        return list;
    }

    public Ffees findFfee(int tradeMappingID, int level) {
        log.debug("findFfee all Ffees instances");
        try {
            String queryString = "from Ffees where ftrademapping.fid=? and flevel=?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, tradeMappingID);
            queryObject.setParameter(1, level);
            return (Ffees) queryObject.list().get(0);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
}