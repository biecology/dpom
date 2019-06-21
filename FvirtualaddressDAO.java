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
import com.huagu.vcoin.main.model.Fvirtualaddress;
import com.huagu.vcoin.main.model.Fvirtualcointype;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fvirtualaddress entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ztmp.Fvirtualaddress
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FvirtualaddressDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FvirtualaddressDAO.class);
    // property constants
    public static final String FADDERESS = "fadderess";
    public static final String FINOUT_TYPE = "finoutType";

    public void save(Fvirtualaddress transientInstance) {
        log.debug("saving Fvirtualaddress instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fvirtualaddress persistentInstance) {
        log.debug("deleting Fvirtualaddress instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fvirtualaddress findById(java.lang.Integer id) {
        log.debug("getting Fvirtualaddress instance with id: " + id);
        try {
            Fvirtualaddress instance = (Fvirtualaddress) getSession().get("com.huagu.vcoin.main.model.Fvirtualaddress",
                    id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fvirtualaddress> findByExample(Fvirtualaddress instance) {
        log.debug("finding Fvirtualaddress instance by example");
        try {
            List<Fvirtualaddress> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fvirtualaddress")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fvirtualaddress instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fvirtualaddress as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fvirtualaddress> findByFadderess(Object fadderess) {
        return findByProperty(FADDERESS, fadderess);
    }

    public List<Fvirtualaddress> findByFinoutType(Object finoutType) {
        return findByProperty(FINOUT_TYPE, finoutType);
    }

    public List findAll() {
        log.debug("finding all Fvirtualaddress instances");
        try {
            String queryString = "from Fvirtualaddress";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fvirtualaddress merge(Fvirtualaddress detachedInstance) {
        log.debug("merging Fvirtualaddress instance");
        try {
            Fvirtualaddress result = (Fvirtualaddress) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fvirtualaddress instance) {
        log.debug("attaching dirty Fvirtualaddress instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fvirtualaddress instance) {
        log.debug("attaching clean Fvirtualaddress instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public Fvirtualaddress findFvirtualaddress(Fuser fuser, Fvirtualcointype fvirtualcointype) {
        log.debug("findFvirtualaddress all Fvirtualaddress instances");
        try {
            String queryString = "from Fvirtualaddress where fuser.fid=? and fvirtualcointype.fid=? ";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, fuser.getFid());
            queryObject.setParameter(1, fvirtualcointype.getFid());
            List<Fvirtualaddress> fvirtualaddresses = queryObject.list();
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

    public List<Fvirtualaddress> findFvirtualaddress(Fvirtualcointype fvirtualcointype, String address) {
        log.debug("findFvirtualaddress all Fvirtualaddress instances");
        try {
            String queryString = "from Fvirtualaddress where fadderess=? and fvirtualcointype.fid=? ";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, address);
            queryObject.setParameter(1, fvirtualcointype.getFid());
            List<Fvirtualaddress> fvirtualaddresses = queryObject.list();
            return fvirtualaddresses;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<Fvirtualaddress> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fvirtualaddress> list = null;
        log.debug("finding Fvirtualaddress instance with filter");
        try {
            String queryString = "from Fvirtualaddress " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fvirtualaddress name failed", re);
            throw re;
        }
        return list;
    }

}