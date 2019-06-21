package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fwithdrawfees;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fwithdrawfees entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ztmp.Fwithdrawfees
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FwithdrawfeesDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FwithdrawfeesDAO.class);
    // property constants
    public static final String FFEE = "ffee";
    public static final String FLEVEL = "flevel";

    public void save(Fwithdrawfees transientInstance) {
        log.debug("saving Fwithdrawfees instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fwithdrawfees persistentInstance) {
        log.debug("deleting Fwithdrawfees instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fwithdrawfees findById(java.lang.Integer id) {
        log.debug("getting Fwithdrawfees instance with id: " + id);
        try {
            Fwithdrawfees instance = (Fwithdrawfees) getSession().get("com.huagu.vcoin.main.model.Fwithdrawfees", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fwithdrawfees> findByExample(Fwithdrawfees instance) {
        log.debug("finding Fwithdrawfees instance by example");
        try {
            List<Fwithdrawfees> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fwithdrawfees")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fwithdrawfees instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fwithdrawfees as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fwithdrawfees> findByFfee(Object ffee) {
        return findByProperty(FFEE, ffee);
    }

    public List<Fwithdrawfees> findByFlevel(Object flevel) {
        return findByProperty(FLEVEL, flevel);
    }

    public List findAll() {
        log.debug("finding all Fwithdrawfees instances");
        try {
            String queryString = "from Fwithdrawfees";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fwithdrawfees merge(Fwithdrawfees detachedInstance) {
        log.debug("merging Fwithdrawfees instance");
        try {
            Fwithdrawfees result = (Fwithdrawfees) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fwithdrawfees instance) {
        log.debug("attaching dirty Fwithdrawfees instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fwithdrawfees instance) {
        log.debug("attaching clean Fwithdrawfees instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fwithdrawfees> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fwithdrawfees> list = null;
        log.debug("finding Fwithdrawfees instance with filter");
        try {
            String queryString = "from Fwithdrawfees " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Fwithdrawfees by filter name failed", re);
            throw re;
        }
        return list;
    }

    public Fwithdrawfees findFfee(int virtualCoinType, int level) {
        String queryString = "from Fwithdrawfees where fvirtualcointype.fid=? and flevel=?";
        Query queryObject = getSession().createQuery(queryString);
        queryObject.setParameter(0, virtualCoinType);
        queryObject.setParameter(1, level);
        List list = queryObject.list();
        if (list.size() == 0)
            throw new RuntimeException(
                    String.format("Fwithdrawfees Query failure，coinType: %d, level: %d", virtualCoinType, level));
        return (Fwithdrawfees) list.get(0);
    }
}