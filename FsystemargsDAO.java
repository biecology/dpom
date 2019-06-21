package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fsystemargs;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fsystemargs entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.huagu.vcoin.main.model.Fsystemargs
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FsystemargsDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FsystemargsDAO.class);
    // property constants
    public static final String FKEY = "fkey";
    public static final String FVALUE = "fvalue";
    public static final String FDESCRIPTION = "fdescription";

    public void save(Fsystemargs transientInstance) {
        log.debug("saving Fsystemargs instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fsystemargs persistentInstance) {
        log.debug("deleting Fsystemargs instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fsystemargs findById(java.lang.Integer id) {
        log.debug("getting Fsystemargs instance with id: " + id);
        try {
            Fsystemargs instance = (Fsystemargs) getSession().get("com.huagu.vcoin.main.model.Fsystemargs", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fsystemargs> findByExample(Fsystemargs instance) {
        log.debug("finding Fsystemargs instance by example");
        try {
            List<Fsystemargs> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fsystemargs")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fsystemargs instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fsystemargs as model where model." + propertyName + "=?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fsystemargs> findByFkey(Object fkey) {
        return findByProperty(FKEY, fkey);
    }

    public List<Fsystemargs> findByFvalue(Object fvalue) {
        return findByProperty(FVALUE, fvalue);
    }

    public List<Fsystemargs> findByFdescription(Object fdescription) {
        return findByProperty(FDESCRIPTION, fdescription);
    }

    public List findAll() {
        log.debug("finding all Fsystemargs instances");
        try {
            String queryString = "from Fsystemargs";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fsystemargs merge(Fsystemargs detachedInstance) {
        log.debug("merging Fsystemargs instance");
        try {
            Fsystemargs result = (Fsystemargs) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fsystemargs instance) {
        log.debug("attaching dirty Fsystemargs instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fsystemargs instance) {
        log.debug("attaching clean Fsystemargs instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fsystemargs> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fsystemargs> list = null;
        log.debug("finding Fsystemargs instance with filter");
        try {
            String queryString = "from Fsystemargs " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fsystemargs name failed", re);
            throw re;
        }
        return list;
    }
}