package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fsecurity;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fsecurity entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ztmp.Fsecurity
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FsecurityDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FsecurityDAO.class);
    // property constants
    public static final String FDESCRIPTION = "fdescription";
    public static final String FNAME = "fname";
    public static final String FPRIORITY = "fpriority";

    public void save(Fsecurity transientInstance) {
        log.debug("saving Fsecurity instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fsecurity persistentInstance) {
        log.debug("deleting Fsecurity instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fsecurity findById(java.lang.Integer id) {
        log.debug("getting Fsecurity instance with id: " + id);
        try {
            Fsecurity instance = (Fsecurity) getSession().get("com.huagu.vcoin.main.model.Fsecurity", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fsecurity> findByExample(Fsecurity instance) {
        log.debug("finding Fsecurity instance by example");
        try {
            List<Fsecurity> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fsecurity")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fsecurity instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fsecurity as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fsecurity> findByFdescription(Object fdescription) {
        return findByProperty(FDESCRIPTION, fdescription);
    }

    public List<Fsecurity> findByFname(Object fname) {
        return findByProperty(FNAME, fname);
    }

    public List<Fsecurity> findByFpriority(Object fpriority) {
        return findByProperty(FPRIORITY, fpriority);
    }

    public List findAll() {
        log.debug("finding all Fsecurity instances");
        try {
            String queryString = "from Fsecurity";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fsecurity merge(Fsecurity detachedInstance) {
        log.debug("merging Fsecurity instance");
        try {
            Fsecurity result = (Fsecurity) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fsecurity instance) {
        log.debug("attaching dirty Fsecurity instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fsecurity instance) {
        log.debug("attaching clean Fsecurity instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fsecurity> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fsecurity> list = null;
        log.debug("finding Fsecurity instance with filter");
        try {
            String queryString = "from Fsecurity " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fsecurity name failed", re);
            throw re;
        }
        return list;
    }

}