package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Ftransportlog;

/**
 * A data access object (DAO) providing persistence and search support for
 * Ftransportlog entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.huagu.vcoin.main.model.Ftransportlog
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FtransportlogDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FtransportlogDAO.class);
    // property constants
    public static final String FAMOUNT = "famount";
    public static final String FADDRESS = "faddress";
    public static final String FTYPE = "ftype";

    public void save(Ftransportlog transientInstance) {
        log.debug("saving Ftransportlog instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Ftransportlog persistentInstance) {
        log.debug("deleting Ftransportlog instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Ftransportlog findById(java.lang.Integer id) {
        log.debug("getting Ftransportlog instance with id: " + id);
        try {
            Ftransportlog instance = (Ftransportlog) getSession().get("com.huagu.vcoin.main.model.Ftransportlog", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Ftransportlog> findByExample(Ftransportlog instance) {
        log.debug("finding Ftransportlog instance by example");
        try {
            List<Ftransportlog> results = getSession().createCriteria("com.huagu.vcoin.main.model.Ftransportlog")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Ftransportlog instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Ftransportlog as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Ftransportlog> findByFamount(Object famount) {
        return findByProperty(FAMOUNT, famount);
    }

    public List<Ftransportlog> findByFaddress(Object faddress) {
        return findByProperty(FADDRESS, faddress);
    }

    public List<Ftransportlog> findByFtype(Object ftype) {
        return findByProperty(FTYPE, ftype);
    }

    public List findAll() {
        log.debug("finding all Ftransportlog instances");
        try {
            String queryString = "from Ftransportlog";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Ftransportlog merge(Ftransportlog detachedInstance) {
        log.debug("merging Ftransportlog instance");
        try {
            Ftransportlog result = (Ftransportlog) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Ftransportlog instance) {
        log.debug("attaching dirty Ftransportlog instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Ftransportlog instance) {
        log.debug("attaching clean Ftransportlog instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Ftransportlog> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Ftransportlog> list = null;
        log.debug("finding Ftransportlog instance with filter");
        try {
            String queryString = "from Ftransportlog " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Ftransportlog by filter name failed", re);
            throw re;
        }
        return list;
    }
}