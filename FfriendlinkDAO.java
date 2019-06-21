package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Ffriendlink;

/**
 * A data access object (DAO) providing persistence and search support for
 * Ffriendlink entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.huagu.vcoin.main.model.Ffriendlink
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FfriendlinkDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FfriendlinkDAO.class);
    // property constants
    public static final String FNAME = "fname";
    public static final String FDESCRIPTION = "fdescription";
    public static final String FORDER = "forder";
    public static final String FURL = "furl";
    public static final String FTYPE = "ftype";

    public void save(Ffriendlink transientInstance) {
        log.debug("saving Ffriendlink instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Ffriendlink persistentInstance) {
        log.debug("deleting Ffriendlink instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Ffriendlink findById(java.lang.Integer id) {
        log.debug("getting Ffriendlink instance with id: " + id);
        try {
            Ffriendlink instance = (Ffriendlink) getSession().get("com.huagu.vcoin.main.model.Ffriendlink", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Ffriendlink> findByExample(Ffriendlink instance) {
        log.debug("finding Ffriendlink instance by example");
        try {
            List<Ffriendlink> results = getSession().createCriteria("com.ruizcon.main.model.Ffriendlink")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Ffriendlink instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Ffriendlink as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Ffriendlink> findByFname(Object fname) {
        return findByProperty(FNAME, fname);
    }

    public List<Ffriendlink> findByFdescription(Object fdescription) {
        return findByProperty(FDESCRIPTION, fdescription);
    }

    public List<Ffriendlink> findByForder(Object forder) {
        return findByProperty(FORDER, forder);
    }

    public List<Ffriendlink> findByFurl(Object furl) {
        return findByProperty(FURL, furl);
    }

    public List<Ffriendlink> findByFtype(Object ftype) {
        return findByProperty(FTYPE, ftype);
    }

    public List findAll() {
        log.debug("finding all Ffriendlink instances");
        try {
            String queryString = "from Ffriendlink";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Ffriendlink merge(Ffriendlink detachedInstance) {
        log.debug("merging Ffriendlink instance");
        try {
            Ffriendlink result = (Ffriendlink) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Ffriendlink instance) {
        log.debug("attaching dirty Ffriendlink instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Ffriendlink instance) {
        log.debug("attaching clean Ffriendlink instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Ffriendlink> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Ffriendlink> list = null;
        log.debug("finding Ffriendlink instance with filter");
        try {
            String queryString = "from Ffriendlink " + filter;
            Query queryObject = getSession().createQuery(queryString);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Ffriendlink by filter name failed", re);
            throw re;
        }
        return list;
    }

    public List<Ffriendlink> findFriendLink(int type, int firstResult, int maxResult) {
        List<Ffriendlink> list = null;
        log.debug("findFriendLink Ffriendlink instance ");
        try {
            String queryString = "from Ffriendlink where ftype=?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            queryObject.setParameter(0, type);
            queryObject.setFirstResult(firstResult);
            queryObject.setMaxResults(maxResult);
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Ffriendlink by filter name failed", re);
            throw re;
        }
        return list;
    }
}