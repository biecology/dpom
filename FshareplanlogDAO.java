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
import com.huagu.vcoin.main.model.Fshareplanlog;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fshareplanlog entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .Fshareplanlog
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FshareplanlogDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FshareplanlogDAO.class);
    // property constants
    public static final String FAMOUNT = "famount";
    public static final String FSTATUS = "fstatus";

    public void save(Fshareplanlog transientInstance) {
        log.debug("saving Fshareplanlog instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fshareplanlog persistentInstance) {
        log.debug("deleting Fshareplanlog instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fshareplanlog findById(java.lang.Integer id) {
        log.debug("getting Fshareplanlog instance with id: " + id);
        try {
            Fshareplanlog instance = (Fshareplanlog) getSession().get("com.huagu.vcoin.main.model.Fshareplanlog", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fshareplanlog> findByExample(Fshareplanlog instance) {
        log.debug("finding Fshareplanlog instance by example");
        try {
            List<Fshareplanlog> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fshareplanlog")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fshareplanlog instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fshareplanlog as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fshareplanlog> findByFamount(Object famount) {
        return findByProperty(FAMOUNT, famount);
    }

    public List<Fshareplanlog> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List findAll() {
        log.debug("finding all Fshareplanlog instances");
        try {
            String queryString = "from Fshareplanlog";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fshareplanlog merge(Fshareplanlog detachedInstance) {
        log.debug("merging Fshareplanlog instance");
        try {
            Fshareplanlog result = (Fshareplanlog) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fshareplanlog instance) {
        log.debug("attaching dirty Fshareplanlog instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fshareplanlog instance) {
        log.debug("attaching clean Fshareplanlog instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fshareplanlog> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fshareplanlog> list = null;
        log.debug("finding Fshareplanlog instance with filter");
        try {
            String queryString = "from Fshareplanlog " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fshareplanlog name failed", re);
            throw re;
        }
        return list;
    }

}