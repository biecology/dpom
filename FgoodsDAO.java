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
import com.huagu.vcoin.main.model.Fgoods;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fgoods entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .Fgoods
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FgoodsDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FgoodsDAO.class);
    // property constants
    public static final String VERSION = "version";
    public static final String FNAME = "fname";
    public static final String FPRICE = "fprice";
    public static final String FSCORE = "fscore";
    public static final String FTOTAL_QTY = "ftotalQty";
    public static final String FLAST_QTY = "flastQty";
    public static final String FDESCRIPTION = "fdescription";
    public static final String FPROVINCE = "fprovince";
    public static final String FCITY = "fcity";
    public static final String FSTATUS = "fstatus";
    public static final String FSUPPLIER_NO = "fsupplierNo";
    public static final String FIS_VIRTUAL = "fisVirtual";
    public static final String FPICTURE_URL = "fpictureUrl";

    public void save(Fgoods transientInstance) {
        log.debug("saving Fgoods instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fgoods persistentInstance) {
        log.debug("deleting Fgoods instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fgoods findById(java.lang.Integer id) {
        log.debug("getting Fgoods instance with id: " + id);
        try {
            Fgoods instance = (Fgoods) getSession().get("com.huagu.vcoin.main.model.Fgoods", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fgoods> findByExample(Fgoods instance) {
        log.debug("finding Fgoods instance by example");
        try {
            List<Fgoods> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fgoods")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fgoods instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fgoods as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fgoods> findByVersion(Object version) {
        return findByProperty(VERSION, version);
    }

    public List<Fgoods> findByFname(Object fname) {
        return findByProperty(FNAME, fname);
    }

    public List<Fgoods> findByFprice(Object fprice) {
        return findByProperty(FPRICE, fprice);
    }

    public List<Fgoods> findByFscore(Object fscore) {
        return findByProperty(FSCORE, fscore);
    }

    public List<Fgoods> findByFtotalQty(Object ftotalQty) {
        return findByProperty(FTOTAL_QTY, ftotalQty);
    }

    public List<Fgoods> findByFlastQty(Object flastQty) {
        return findByProperty(FLAST_QTY, flastQty);
    }

    public List<Fgoods> findByFdescription(Object fdescription) {
        return findByProperty(FDESCRIPTION, fdescription);
    }

    public List<Fgoods> findByFprovince(Object fprovince) {
        return findByProperty(FPROVINCE, fprovince);
    }

    public List<Fgoods> findByFcity(Object fcity) {
        return findByProperty(FCITY, fcity);
    }

    public List<Fgoods> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List<Fgoods> findByFsupplierNo(Object fsupplierNo) {
        return findByProperty(FSUPPLIER_NO, fsupplierNo);
    }

    public List<Fgoods> findByFisVirtual(Object fisVirtual) {
        return findByProperty(FIS_VIRTUAL, fisVirtual);
    }

    public List<Fgoods> findByFpictureUrl(Object fpictureUrl) {
        return findByProperty(FPICTURE_URL, fpictureUrl);
    }

    public List findAll() {
        log.debug("finding all Fgoods instances");
        try {
            String queryString = "from Fgoods";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fgoods merge(Fgoods detachedInstance) {
        log.debug("merging Fgoods instance");
        try {
            Fgoods result = (Fgoods) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fgoods instance) {
        log.debug("attaching dirty Fgoods instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fgoods instance) {
        log.debug("attaching clean Fgoods instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fgoods> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fgoods> list = null;
        log.debug("finding Fgoods instance with filter");
        try {
            String queryString = "from Fgoods " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fgoods name failed", re);
            throw re;
        }
        return list;
    }
}