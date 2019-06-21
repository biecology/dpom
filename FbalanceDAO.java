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
import com.huagu.vcoin.main.model.Fbalance;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fbalance entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .Fbalance
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FbalanceDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FbalanceDAO.class);
    // property constants
    public static final String FTITLE = "ftitle";

    public void save(Fbalance transientInstance) {
        log.debug("saving Fbalance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fbalance persistentInstance) {
        log.debug("deleting Fbalance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fbalance findById(java.lang.Integer id) {
        log.debug("getting Fbalance instance with id: " + id);
        try {
            Fbalance instance = (Fbalance) getSession().get("com.huagu.vcoin.main.model.Fbalance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fbalance> findByExample(Fbalance instance) {
        log.debug("finding Fbalance instance by example");
        try {
            List<Fbalance> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fbalance")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fbalance instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fbalance as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fbalance> findByFtitle(Object ftitle) {
        return findByProperty(FTITLE, ftitle);
    }

    public List findAll() {
        log.debug("finding all Fbalance instances");
        try {
            String queryString = "from Fbalance";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fbalance merge(Fbalance detachedInstance) {
        log.debug("merging Fbalance instance");
        try {
            Fbalance result = (Fbalance) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fbalance instance) {
        log.debug("attaching dirty Fbalance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fbalance instance) {
        log.debug("attaching clean Fbalance instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fbalance> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fbalance> list = null;
        log.debug("finding Fbalance instance with filter");
        try {
            String queryString = "from Fbalance " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fbalance name failed", re);
            throw re;
        }
        return list;
    }
}