package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fcountlimit;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fcountlimit entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ztmp.Fcountlimit
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FcountlimitDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FcountlimitDAO.class);
    // property constants
    public static final String FIP = "fip";
    public static final String FCOUNT = "fcount";

    public void save(Fcountlimit transientInstance) {
        log.debug("saving Fcountlimit instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fcountlimit persistentInstance) {
        log.debug("deleting Fcountlimit instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fcountlimit findById(java.lang.Integer id) {
        log.debug("getting Fcountlimit instance with id: " + id);
        try {
            Fcountlimit instance = (Fcountlimit) getSession().get("com.huagu.vcoin.main.model.Fcountlimit", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fcountlimit> findByExample(Fcountlimit instance) {
        log.debug("finding Fcountlimit instance by example");
        try {
            List<Fcountlimit> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fcountlimit")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fcountlimit instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fcountlimit as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fcountlimit> findByFip(Object fip) {
        return findByProperty(FIP, fip);
    }

    public List<Fcountlimit> findByFcount(Object fcount) {
        return findByProperty(FCOUNT, fcount);
    }

    public List findAll() {
        log.debug("finding all Fcountlimit instances");
        try {
            String queryString = "from Fcountlimit";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fcountlimit merge(Fcountlimit detachedInstance) {
        log.debug("merging Fcountlimit instance");
        try {
            Fcountlimit result = (Fcountlimit) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fcountlimit instance) {
        log.debug("attaching dirty Fcountlimit instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fcountlimit instance) {
        log.debug("attaching clean Fcountlimit instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List findByIpType(String ip, Integer type) {
        log.debug("finding Fcountlimit instance findByIpType");
        try {
            String queryString = "from Fcountlimit as model where model.fip = ? and model.ftype = ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            queryObject.setParameter(0, ip);
            queryObject.setParameter(1, type);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fcountlimit> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fcountlimit> list = null;
        log.debug("finding Fcountlimit instance with filter");
        try {
            String queryString = "from Fcountlimit " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fcountlimit name failed", re);
            throw re;
        }
        return list;
    }
}