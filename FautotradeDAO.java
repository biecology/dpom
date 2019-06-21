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
import com.huagu.vcoin.main.model.Fautotrade;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fautotrade entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .Fautotrade
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FautotradeDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FautotradeDAO.class);
    // property constants
    public static final String FTYPE = "ftype";
    public static final String FQTY = "fqty";
    public static final String FPRICE = "fprice";

    public void save(Fautotrade transientInstance) {
        log.debug("saving Fautotrade instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fautotrade persistentInstance) {
        log.debug("deleting Fautotrade instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fautotrade findById(java.lang.Integer id) {
        log.debug("getting Fautotrade instance with id: " + id);
        try {
            Fautotrade instance = (Fautotrade) getSession().get("com.huagu.vcoin.main.model.Fautotrade", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fautotrade> findByExample(Fautotrade instance) {
        log.debug("finding Fautotrade instance by example");
        try {
            List<Fautotrade> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fautotrade")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fautotrade instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fautotrade as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fautotrade> findByFtype(Object ftype) {
        return findByProperty(FTYPE, ftype);
    }

    public List<Fautotrade> findByFqty(Object fqty) {
        return findByProperty(FQTY, fqty);
    }

    public List<Fautotrade> findByFprice(Object fprice) {
        return findByProperty(FPRICE, fprice);
    }

    public List findAll() {
        log.debug("finding all Fautotrade instances");
        try {
            String queryString = "from Fautotrade";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fautotrade merge(Fautotrade detachedInstance) {
        log.debug("merging Fautotrade instance");
        try {
            Fautotrade result = (Fautotrade) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fautotrade instance) {
        log.debug("attaching dirty Fautotrade instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fautotrade instance) {
        log.debug("attaching clean Fautotrade instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fautotrade> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fautotrade> list = null;
        log.debug("finding Fautotrade instance with filter");
        try {
            String queryString = "from Fautotrade " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Fautotrade by filter name failed", re);
            throw re;
        }
        return list;
    }
}