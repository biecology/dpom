package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fsubscription;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fsubscription entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ztmp.Fsubscription
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FsubscriptionDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FsubscriptionDAO.class);
    // property constants
    public static final String FISOPEN = "fisopen";
    public static final String FTOTAL = "ftotal";
    public static final String FPRICE = "fprice";
    public static final String FBUY_COUNT = "fbuyCount";
    public static final String FBUY_TIMES = "fbuyTimes";

    public void save(Fsubscription transientInstance) {
        log.debug("saving Fsubscription instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fsubscription persistentInstance) {
        log.debug("deleting Fsubscription instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fsubscription findById(java.lang.Integer id) {
        log.debug("getting Fsubscription instance with id: " + id);
        try {
            Fsubscription instance = (Fsubscription) getSession().get("com.huagu.vcoin.main.model.Fsubscription", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fsubscription> findByExample(Fsubscription instance) {
        log.debug("finding Fsubscription instance by example");
        try {
            List<Fsubscription> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fsubscription")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fsubscription instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fsubscription as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fsubscription> findByFisopen(Object fisopen) {
        return findByProperty(FISOPEN, fisopen);
    }

    public List<Fsubscription> findByFtotal(Object ftotal) {
        return findByProperty(FTOTAL, ftotal);
    }

    public List<Fsubscription> findByFprice(Object fprice) {
        return findByProperty(FPRICE, fprice);
    }

    public List<Fsubscription> findByFbuyCount(Object fbuyCount) {
        return findByProperty(FBUY_COUNT, fbuyCount);
    }

    public List<Fsubscription> findByFbuyTimes(Object fbuyTimes) {
        return findByProperty(FBUY_TIMES, fbuyTimes);
    }

    public List findAll() {
        log.debug("finding all Fsubscription instances");
        try {
            String queryString = "from Fsubscription";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fsubscription merge(Fsubscription detachedInstance) {
        log.debug("merging Fsubscription instance");
        try {
            Fsubscription result = (Fsubscription) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fsubscription instance) {
        log.debug("attaching dirty Fsubscription instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fsubscription instance) {
        log.debug("attaching clean Fsubscription instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fsubscription> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fsubscription> list = null;
        log.debug("finding Fsubscription instance with filter");
        try {
            String queryString = "from Fsubscription " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fsubscription name failed", re);
            throw re;
        }
        return list;
    }
}