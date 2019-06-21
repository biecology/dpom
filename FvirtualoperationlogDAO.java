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
import com.huagu.vcoin.main.model.Fvirtualoperationlog;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fvirtualoperationlog entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .Fvirtualoperationlog
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FvirtualoperationlogDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FvirtualoperationlogDAO.class);
    // property constants
    public static final String VERSION = "version";
    public static final String FQTY = "fqty";
    public static final String FSTATUS = "fstatus";
    public static final String FIS_SEND_MSG = "fisSendMsg";

    public void save(Fvirtualoperationlog transientInstance) {
        log.debug("saving Fvirtualoperationlog instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fvirtualoperationlog persistentInstance) {
        log.debug("deleting Fvirtualoperationlog instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fvirtualoperationlog findById(java.lang.Integer id) {
        log.debug("getting Fvirtualoperationlog instance with id: " + id);
        try {
            Fvirtualoperationlog instance = (Fvirtualoperationlog) getSession()
                    .get("com.huagu.vcoin.main.model.Fvirtualoperationlog", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fvirtualoperationlog> findByExample(Fvirtualoperationlog instance) {
        log.debug("finding Fvirtualoperationlog instance by example");
        try {
            List<Fvirtualoperationlog> results = getSession()
                    .createCriteria("com.huagu.vcoin.main.model.Fvirtualoperationlog").add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fvirtualoperationlog instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fvirtualoperationlog as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fvirtualoperationlog> findByVersion(Object version) {
        return findByProperty(VERSION, version);
    }

    public List<Fvirtualoperationlog> findByFqty(Object fqty) {
        return findByProperty(FQTY, fqty);
    }

    public List<Fvirtualoperationlog> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List<Fvirtualoperationlog> findByFisSendMsg(Object fisSendMsg) {
        return findByProperty(FIS_SEND_MSG, fisSendMsg);
    }

    public List findAll() {
        log.debug("finding all Fvirtualoperationlog instances");
        try {
            String queryString = "from Fvirtualoperationlog";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fvirtualoperationlog merge(Fvirtualoperationlog detachedInstance) {
        log.debug("merging Fvirtualoperationlog instance");
        try {
            Fvirtualoperationlog result = (Fvirtualoperationlog) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fvirtualoperationlog instance) {
        log.debug("attaching dirty Fvirtualoperationlog instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fvirtualoperationlog instance) {
        log.debug("attaching clean Fvirtualoperationlog instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fvirtualoperationlog> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fvirtualoperationlog> list = null;
        log.debug("finding Fvirtualoperationlog instance with filter");
        try {
            String queryString = "from Fvirtualoperationlog " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Fvirtualoperationlog by filter name failed", re);
            throw re;
        }
        return list;
    }
}