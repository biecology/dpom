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
import com.huagu.vcoin.main.model.Fgoodsaddress;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fgoodsaddress entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .Fgoodsaddress
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FgoodsaddressDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FgoodsaddressDAO.class);
    // property constants
    public static final String FPROVINCE = "fprovince";
    public static final String FCITY = "fcity";
    public static final String FDESC = "fdesc";
    public static final String FREC_NAME = "frecName";
    public static final String FPHONE = "fphone";
    public static final String POSTALCODE = "postalcode";

    public void save(Fgoodsaddress transientInstance) {
        log.debug("saving Fgoodsaddress instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fgoodsaddress persistentInstance) {
        log.debug("deleting Fgoodsaddress instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fgoodsaddress findById(java.lang.Integer id) {
        log.debug("getting Fgoodsaddress instance with id: " + id);
        try {
            Fgoodsaddress instance = (Fgoodsaddress) getSession().get("com.huagu.vcoin.main.model.Fgoodsaddress", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fgoodsaddress> findByExample(Fgoodsaddress instance) {
        log.debug("finding Fgoodsaddress instance by example");
        try {
            List<Fgoodsaddress> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fgoodsaddress")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fgoodsaddress instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fgoodsaddress as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findAll() {
        log.debug("finding all Fgoodsaddress instances");
        try {
            String queryString = "from Fgoodsaddress";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fgoodsaddress merge(Fgoodsaddress detachedInstance) {
        log.debug("merging Fgoodsaddress instance");
        try {
            Fgoodsaddress result = (Fgoodsaddress) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fgoodsaddress instance) {
        log.debug("attaching dirty Fgoodsaddress instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fgoodsaddress instance) {
        log.debug("attaching clean Fgoodsaddress instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fgoodsaddress> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fgoodsaddress> list = null;
        log.debug("finding Fgoodsaddress instance with filter");
        try {
            String queryString = "from Fgoodsaddress " + filter;
            Query queryObject = getSession().createQuery(queryString);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Fgoodsaddress by filter name failed", re);
            throw re;
        }
        return list;
    }
}