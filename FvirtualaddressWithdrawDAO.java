package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fuser;
import com.huagu.vcoin.main.model.FvirtualaddressWithdraw;
import com.huagu.vcoin.main.model.Fvirtualcointype;

/**
 * A data access object (DAO) providing persistence and search support for
 * FvirtualaddressWithdraw entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ztmp.FvirtualaddressWithdraw
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FvirtualaddressWithdrawDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FvirtualaddressWithdrawDAO.class);
    // property constants
    public static final String VERSION = "version";
    public static final String FADDERESS = "fadderess";
    public static final String FUID = "fuid";

    public void save(FvirtualaddressWithdraw transientInstance) {
        log.debug("saving FvirtualaddressWithdraw instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(FvirtualaddressWithdraw persistentInstance) {
        log.debug("deleting FvirtualaddressWithdraw instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public FvirtualaddressWithdraw findById(java.lang.Integer id) {
        log.debug("getting FvirtualaddressWithdraw instance with id: " + id);
        try {
            FvirtualaddressWithdraw instance = (FvirtualaddressWithdraw) getSession()
                    .get("com.huagu.vcoin.main.model.FvirtualaddressWithdraw", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<FvirtualaddressWithdraw> findByExample(FvirtualaddressWithdraw instance) {
        log.debug("finding FvirtualaddressWithdraw instance by example");
        try {
            List<FvirtualaddressWithdraw> results = getSession()
                    .createCriteria("com.huagu.vcoin.main.model.FvirtualaddressWithdraw").add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding FvirtualaddressWithdraw instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from FvirtualaddressWithdraw as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<FvirtualaddressWithdraw> findByVersion(Object version) {
        return findByProperty(VERSION, version);
    }

    public List<FvirtualaddressWithdraw> findByFadderess(Object fadderess) {
        return findByProperty(FADDERESS, fadderess);
    }

    public List<FvirtualaddressWithdraw> findByFuid(Object fuid) {
        return findByProperty(FUID, fuid);
    }

    public List findAll() {
        log.debug("finding all FvirtualaddressWithdraw instances");
        try {
            String queryString = "from FvirtualaddressWithdraw";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public FvirtualaddressWithdraw merge(FvirtualaddressWithdraw detachedInstance) {
        log.debug("merging FvirtualaddressWithdraw instance");
        try {
            FvirtualaddressWithdraw result = (FvirtualaddressWithdraw) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FvirtualaddressWithdraw instance) {
        log.debug("attaching dirty FvirtualaddressWithdraw instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(FvirtualaddressWithdraw instance) {
        log.debug("attaching clean FvirtualaddressWithdraw instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public FvirtualaddressWithdraw findFvirtualaddressWithdraw(Fuser fuser, Fvirtualcointype fvirtualcointype) {
        log.debug("findFvirtualaddress all FvirtualaddressWithdraw instances");
        try {
            String queryString = "from FvirtualaddressWithdraw where fuser.fid=? and fvirtualcointype.fid=? ";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, fuser.getFid());
            queryObject.setParameter(1, fvirtualcointype.getFid());
            List<FvirtualaddressWithdraw> fvirtualaddresses = queryObject.list();
            if (fvirtualaddresses.size() > 0) {
                return fvirtualaddresses.get(0);
            } else {
                return null;
            }
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<FvirtualaddressWithdraw> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<FvirtualaddressWithdraw> list = null;
        log.debug("finding FvirtualaddressWithdraw instance with filter");
        try {
            String queryString = "from FvirtualaddressWithdraw " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find FvirtualaddressWithdraw by filter name failed", re);
            throw re;
        }
        return list;
    }
}