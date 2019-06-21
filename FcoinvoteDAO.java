package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fcoinvote;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fcoinvote entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.huagu.vcoin.main.model.Fcoinvote
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FcoinvoteDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FcoinvoteDAO.class);
    // property constants
    public static final String VERSION = "version";
    public static final String FCN_NAME = "fcnName";
    public static final String FEN_NAME = "fenName";
    public static final String FSHORT_NAME = "fshortName";
    public static final String FDESC = "fdesc";
    public static final String FURL = "furl";
    public static final String FSTATUS = "fstatus";
    public static final String FYES = "fyes";
    public static final String FNO = "fno";

    public void save(Fcoinvote transientInstance) {
        log.debug("saving Fcoinvote instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fcoinvote persistentInstance) {
        log.debug("deleting Fcoinvote instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fcoinvote findById(java.lang.Integer id) {
        log.debug("getting Fcoinvote instance with id: " + id);
        try {
            Fcoinvote instance = (Fcoinvote) getSession().get("com.huagu.vcoin.main.model.Fcoinvote", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fcoinvote> findByExample(Fcoinvote instance) {
        log.debug("finding Fcoinvote instance by example");
        try {
            List<Fcoinvote> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fcoinvote")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fcoinvote instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fcoinvote as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fcoinvote> findByVersion(Object version) {
        return findByProperty(VERSION, version);
    }

    public List<Fcoinvote> findByFcnName(Object fcnName) {
        return findByProperty(FCN_NAME, fcnName);
    }

    public List<Fcoinvote> findByFenName(Object fenName) {
        return findByProperty(FEN_NAME, fenName);
    }

    public List<Fcoinvote> findByFshortName(Object fshortName) {
        return findByProperty(FSHORT_NAME, fshortName);
    }

    public List<Fcoinvote> findByFdesc(Object fdesc) {
        return findByProperty(FDESC, fdesc);
    }

    public List<Fcoinvote> findByFurl(Object furl) {
        return findByProperty(FURL, furl);
    }

    public List<Fcoinvote> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List<Fcoinvote> findByFyes(Object fyes) {
        return findByProperty(FYES, fyes);
    }

    public List<Fcoinvote> findByFno(Object fno) {
        return findByProperty(FNO, fno);
    }

    public List findAll() {
        log.debug("finding all Fcoinvote instances");
        try {
            String queryString = "from Fcoinvote";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fcoinvote merge(Fcoinvote detachedInstance) {
        log.debug("merging Fcoinvote instance");
        try {
            Fcoinvote result = (Fcoinvote) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fcoinvote instance) {
        log.debug("attaching dirty Fcoinvote instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fcoinvote instance) {
        log.debug("attaching clean Fcoinvote instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fcoinvote> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fcoinvote> list = null;
        log.debug("finding Fcoinvote instance with filter");
        try {
            String queryString = "from Fcoinvote " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fcoinvote name failed", re);
            throw re;
        }
        return list;
    }
}