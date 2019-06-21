package com.huagu.vcoin.main.dao;
// default package

import static org.hibernate.criterion.Example.create;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fshareplan;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fshareplan entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .Fshareplan
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FshareplanDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FshareplanDAO.class);
    // property constants
    public static final String FSTATUS = "fstatus";
    public static final String FTITLE = "ftitle";
    public static final String FAMOUNT = "famount";

    public void save(Fshareplan transientInstance) {
        log.debug("saving Fshareplan instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fshareplan persistentInstance) {
        log.debug("deleting Fshareplan instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fshareplan findById(java.lang.Integer id) {
        log.debug("getting Fshareplan instance with id: " + id);
        try {
            Fshareplan instance = (Fshareplan) getSession().get("com.huagu.vcoin.main.model.Fshareplan", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fshareplan> findByExample(Fshareplan instance) {
        log.debug("finding Fshareplan instance by example");
        try {
            List<Fshareplan> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fshareplan")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fshareplan instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fshareplan as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fshareplan> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List<Fshareplan> findByFtitle(Object ftitle) {
        return findByProperty(FTITLE, ftitle);
    }

    public List<Fshareplan> findByFamount(Object famount) {
        return findByProperty(FAMOUNT, famount);
    }

    public List findAll() {
        log.debug("finding all Fshareplan instances");
        try {
            String queryString = "from Fshareplan";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fshareplan merge(Fshareplan detachedInstance) {
        log.debug("merging Fshareplan instance");
        try {
            Fshareplan result = (Fshareplan) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fshareplan instance) {
        log.debug("attaching dirty Fshareplan instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fshareplan instance) {
        log.debug("attaching clean Fshareplan instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fshareplan> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fshareplan> list = null;
        log.debug("finding Fshareplan instance with filter");
        try {
            String queryString = "from Fshareplan " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fshareplan name failed", re);
            throw re;
        }
        return list;
    }

    public BigDecimal getAllCount(int vid) {
        BigDecimal amt = BigDecimal.ZERO;
        String queryString = "select sum(ftotal) amt from Fvirtualwallet where fVi_fId=" + vid;
        Query queryObject = getSession().createSQLQuery(queryString);
        List all = queryObject.list();
        if (all.size() > 0) {
            amt = new BigDecimal(all.get(0).toString());
        }
        return amt;
    }

}