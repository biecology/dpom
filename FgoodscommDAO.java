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
import com.huagu.vcoin.main.model.Fgoodscomm;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fgoodscomm entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .Fgoodscomm
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FgoodscommDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FgoodscommDAO.class);
    // property constants
    public static final String FTYPE = "ftype";
    public static final String FCONTENT = "fcontent";

    public void save(Fgoodscomm transientInstance) {
        log.debug("saving Fgoodscomm instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fgoodscomm persistentInstance) {
        log.debug("deleting Fgoodscomm instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fgoodscomm findById(java.lang.Integer id) {
        log.debug("getting Fgoodscomm instance with id: " + id);
        try {
            Fgoodscomm instance = (Fgoodscomm) getSession().get("com.huagu.vcoin.main.model.Fgoodscomm", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fgoodscomm> findByExample(Fgoodscomm instance) {
        log.debug("finding Fgoodscomm instance by example");
        try {
            List<Fgoodscomm> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fgoodscomm")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fgoodscomm instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fgoodscomm as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fgoodscomm> findByFtype(Object ftype) {
        return findByProperty(FTYPE, ftype);
    }

    public List<Fgoodscomm> findByFcontent(Object fcontent) {
        return findByProperty(FCONTENT, fcontent);
    }

    public List findAll() {
        log.debug("finding all Fgoodscomm instances");
        try {
            String queryString = "from Fgoodscomm";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fgoodscomm merge(Fgoodscomm detachedInstance) {
        log.debug("merging Fgoodscomm instance");
        try {
            Fgoodscomm result = (Fgoodscomm) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fgoodscomm instance) {
        log.debug("attaching dirty Fgoodscomm instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fgoodscomm instance) {
        log.debug("attaching clean Fgoodscomm instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fgoodscomm> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fgoodscomm> list = null;
        log.debug("finding Fgoodscomm instance with filter");
        try {
            String queryString = "from Fgoodscomm " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fgoodscomm name failed", re);
            throw re;
        }
        return list;
    }
}