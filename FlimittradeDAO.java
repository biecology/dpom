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
import com.huagu.vcoin.main.model.Flimittrade;

/**
 * A data access object (DAO) providing persistence and search support for
 * Flimittrade entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .Flimittrade
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FlimittradeDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FlimittradeDAO.class);
    // property constants
    public static final String FPERCENT = "fpercent";

    public void save(Flimittrade transientInstance) {
        log.debug("saving Flimittrade instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Flimittrade persistentInstance) {
        log.debug("deleting Flimittrade instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Flimittrade findById(java.lang.Integer id) {
        log.debug("getting Flimittrade instance with id: " + id);
        try {
            Flimittrade instance = (Flimittrade) getSession().get("com.huagu.vcoin.main.model.Flimittrade", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Flimittrade> findByExample(Flimittrade instance) {
        log.debug("finding Flimittrade instance by example");
        try {
            List<Flimittrade> results = getSession().createCriteria("com.huagu.vcoin.main.model.Flimittrade")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Flimittrade instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Flimittrade as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Flimittrade> findByFpercent(Object fpercent) {
        return findByProperty(FPERCENT, fpercent);
    }

    public List findAll() {
        log.debug("finding all Flimittrade instances");
        try {
            String queryString = "from Flimittrade";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Flimittrade merge(Flimittrade detachedInstance) {
        log.debug("merging Flimittrade instance");
        try {
            Flimittrade result = (Flimittrade) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Flimittrade instance) {
        log.debug("attaching dirty Flimittrade instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Flimittrade instance) {
        log.debug("attaching clean Flimittrade instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Flimittrade> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Flimittrade> list = null;
        log.debug("finding Flimittrade instance with filter");
        try {
            String queryString = "from Flimittrade " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Flimittrade name failed", re);
            throw re;
        }
        return list;
    }
}