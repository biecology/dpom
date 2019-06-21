package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fcoinvotelog;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fcoinvotelog entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.huagu.vcoin.main.model.Fcoinvotelog
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FcoinvotelogDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FcoinvotelogDAO.class);

    // property constants

    public void save(Fcoinvotelog transientInstance) {
        log.debug("saving Fcoinvotelog instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fcoinvotelog persistentInstance) {
        log.debug("deleting Fcoinvotelog instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fcoinvotelog findById(java.lang.Integer id) {
        log.debug("getting Fcoinvotelog instance with id: " + id);
        try {
            Fcoinvotelog instance = (Fcoinvotelog) getSession().get("com.huagu.vcoin.main.model.Fcoinvotelog", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fcoinvotelog> findByExample(Fcoinvotelog instance) {
        log.debug("finding Fcoinvotelog instance by example");
        try {
            List<Fcoinvotelog> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fcoinvotelog")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fcoinvotelog instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fcoinvotelog as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findAll() {
        log.debug("finding all Fcoinvotelog instances");
        try {
            String queryString = "from Fcoinvotelog";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fcoinvotelog merge(Fcoinvotelog detachedInstance) {
        log.debug("merging Fcoinvotelog instance");
        try {
            Fcoinvotelog result = (Fcoinvotelog) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fcoinvotelog instance) {
        log.debug("attaching dirty Fcoinvotelog instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fcoinvotelog instance) {
        log.debug("attaching clean Fcoinvotelog instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fcoinvotelog> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fcoinvotelog> list = null;
        log.debug("finding Fcoinvotelog instance with filter");
        try {
            String queryString = "from Fcoinvotelog " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fcoinvotelog name failed", re);
            throw re;
        }
        return list;
    }
}