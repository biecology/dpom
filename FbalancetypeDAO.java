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
import com.huagu.vcoin.main.model.Fbalancetype;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fbalancetype entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .Fbalancetype
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FbalancetypeDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FbalancetypeDAO.class);
    // property constants
    public static final String FNAME = "fname";
    public static final String FRATE = "frate";

    public void save(Fbalancetype transientInstance) {
        log.debug("saving Fbalancetype instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fbalancetype persistentInstance) {
        log.debug("deleting Fbalancetype instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fbalancetype findById(java.lang.Integer id) {
        log.debug("getting Fbalancetype instance with id: " + id);
        try {
            Fbalancetype instance = (Fbalancetype) getSession().get("com.huagu.vcoin.main.model.Fbalancetype", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fbalancetype> findByExample(Fbalancetype instance) {
        log.debug("finding Fbalancetype instance by example");
        try {
            List<Fbalancetype> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fbalancetype")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fbalancetype instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fbalancetype as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fbalancetype> findByFname(Object fname) {
        return findByProperty(FNAME, fname);
    }

    public List<Fbalancetype> findByFrate(Object frate) {
        return findByProperty(FRATE, frate);
    }

    public List findAll() {
        log.debug("finding all Fbalancetype instances");
        try {
            String queryString = "from Fbalancetype";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fbalancetype merge(Fbalancetype detachedInstance) {
        log.debug("merging Fbalancetype instance");
        try {
            Fbalancetype result = (Fbalancetype) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fbalancetype instance) {
        log.debug("attaching dirty Fbalancetype instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fbalancetype instance) {
        log.debug("attaching clean Fbalancetype instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fbalancetype> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fbalancetype> list = null;
        log.debug("finding Fbalancetype instance with filter");
        try {
            String queryString = "from Fbalancetype " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fbalancetype name failed", re);
            throw re;
        }
        return list;
    }
}