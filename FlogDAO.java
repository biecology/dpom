package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Flog;

/**
 * A data access object (DAO) providing persistence and search support for Flog
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see ztmp.Flog
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FlogDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FlogDAO.class);
    // property constants
    public static final String VERSION = "version";
    public static final String FTYPE = "ftype";
    public static final String FKEY1 = "fkey1";
    public static final String FKEY2 = "fkey2";
    public static final String FKEY3 = "fkey3";
    public static final String FKEY4 = "fkey4";
    public static final String FKEY5 = "fkey5";
    public static final String FKEY6 = "fkey6";

    public void save(Flog transientInstance) {
        log.debug("saving Flog instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Flog persistentInstance) {
        log.debug("deleting Flog instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Flog findById(java.lang.Integer id) {
        log.debug("getting Flog instance with id: " + id);
        try {
            Flog instance = (Flog) getSession().get("com.huagu.vcoin.main.model.Flog", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Flog> findByExample(Flog instance) {
        log.debug("finding Flog instance by example");
        try {
            List<Flog> results = getSession().createCriteria("com.huagu.vcoin.main.model.Flog").add(create(instance))
                    .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Flog instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Flog as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Flog> findByVersion(Object version) {
        return findByProperty(VERSION, version);
    }

    public List<Flog> findByFtype(Object ftype) {
        return findByProperty(FTYPE, ftype);
    }

    public List<Flog> findByFkey1(Object fkey1) {
        return findByProperty(FKEY1, fkey1);
    }

    public List<Flog> findByFkey2(Object fkey2) {
        return findByProperty(FKEY2, fkey2);
    }

    public List<Flog> findByFkey3(Object fkey3) {
        return findByProperty(FKEY3, fkey3);
    }

    public List<Flog> findByFkey4(Object fkey4) {
        return findByProperty(FKEY4, fkey4);
    }

    public List<Flog> findByFkey5(Object fkey5) {
        return findByProperty(FKEY5, fkey5);
    }

    public List<Flog> findByFkey6(Object fkey6) {
        return findByProperty(FKEY6, fkey6);
    }

    public List findAll() {
        log.debug("finding all Flog instances");
        try {
            String queryString = "from Flog";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Flog merge(Flog detachedInstance) {
        log.debug("merging Flog instance");
        try {
            Flog result = (Flog) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Flog instance) {
        log.debug("attaching dirty Flog instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Flog instance) {
        log.debug("attaching clean Flog instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Flog> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Flog> list = null;
        log.debug("finding Flog instance with filter");
        try {
            String queryString = "from Flog " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Flog name failed", re);
            throw re;
        }
        return list;
    }
}