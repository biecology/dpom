package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Ftrademapping;

/**
 * A data access object (DAO) providing persistence and search support for
 * Ftrademapping entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.huagu.vcoin.main.model.Ftrademapping
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FtrademappingDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FtrademappingDAO.class);
    // property constants
    public static final String VERSION = "version";
    public static final String FTRADE_TIME = "ftradeTime";
    public static final String FUP_PRICE_REG = "fupPriceReg";
    public static final String FDOWN_PRICE_REG = "fdownPriceReg";
    public static final String FISSHOW_KLINE_NOTTRADE = "fisshowKlineNottrade";

    public void save(Ftrademapping transientInstance) {
        log.debug("saving Ftrademapping instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Ftrademapping persistentInstance) {
        log.debug("deleting Ftrademapping instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Ftrademapping findById(java.lang.Integer id) {
        log.debug("getting Ftrademapping instance with id: " + id);
        try {
            Ftrademapping instance = (Ftrademapping) getSession().get("com.huagu.vcoin.main.model.Ftrademapping", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Ftrademapping> findByExample(Ftrademapping instance) {
        log.debug("finding Ftrademapping instance by example");
        try {
            List<Ftrademapping> results = getSession().createCriteria("com.huagu.vcoin.main.model.Ftrademapping")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Ftrademapping instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Ftrademapping as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Ftrademapping> findByVersion(Object version) {
        return findByProperty(VERSION, version);
    }

    public List<Ftrademapping> findByFtradeTime(Object ftradeTime) {
        return findByProperty(FTRADE_TIME, ftradeTime);
    }

    public List<Ftrademapping> findByFupPriceReg(Object fupPriceReg) {
        return findByProperty(FUP_PRICE_REG, fupPriceReg);
    }

    public List<Ftrademapping> findByFdownPriceReg(Object fdownPriceReg) {
        return findByProperty(FDOWN_PRICE_REG, fdownPriceReg);
    }

    public List<Ftrademapping> findByFisshowKlineNottrade(Object fisshowKlineNottrade) {
        return findByProperty(FISSHOW_KLINE_NOTTRADE, fisshowKlineNottrade);
    }

    public List findAll() {
        log.debug("finding all Ftrademapping instances");
        try {
            String queryString = "from Ftrademapping";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Ftrademapping merge(Ftrademapping detachedInstance) {
        log.debug("merging Ftrademapping instance");
        try {
            Ftrademapping result = (Ftrademapping) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Ftrademapping instance) {
        log.debug("attaching dirty Ftrademapping instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Ftrademapping instance) {
        log.debug("attaching clean Ftrademapping instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Ftrademapping> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Ftrademapping> list = null;
        log.debug("finding Ftrademapping instance with filter");
        try {
            String queryString = "from Ftrademapping " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Ftrademapping by filter name failed", re);
            throw re;
        }
        return list;
    }
}