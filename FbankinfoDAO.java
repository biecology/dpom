package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.Enum.BankInfoStatusEnum;
import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fbankinfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fbankinfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ztmp.Fbankinfo
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FbankinfoDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FbankinfoDAO.class);
    // property constants
    public static final String FNAME = "fname";
    public static final String FBANK_NUMBER = "fbankNumber";
    public static final String FBANK_TYPE = "fbankType";
    public static final String FSTATUS = "fstatus";

    public void save(Fbankinfo transientInstance) {
        log.debug("saving Fbankinfo instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fbankinfo persistentInstance) {
        log.debug("deleting Fbankinfo instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fbankinfo findById(java.lang.Integer id) {
        log.debug("getting Fbankinfo instance with id: " + id);
        try {
            Fbankinfo instance = (Fbankinfo) getSession().get("com.huagu.vcoin.main.model.Fbankinfo", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fbankinfo> findByExample(Fbankinfo instance) {
        log.debug("finding Fbankinfo instance by example");
        try {
            List<Fbankinfo> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fbankinfo")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fbankinfo instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fbankinfo as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fbankinfo> findByFname(Object fname) {
        return findByProperty(FNAME, fname);
    }

    public List<Fbankinfo> findByFbankNumber(Object fbankNumber) {
        return findByProperty(FBANK_NUMBER, fbankNumber);
    }

    public List<Fbankinfo> findByFbankType(Object fbankType) {
        return findByProperty(FBANK_TYPE, fbankType);
    }

    public List<Fbankinfo> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List findAll() {
        log.debug("finding all Fbankinfo instances");
        try {
            String queryString = "from Fbankinfo";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fbankinfo merge(Fbankinfo detachedInstance) {
        log.debug("merging Fbankinfo instance");
        try {
            Fbankinfo result = (Fbankinfo) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fbankinfo instance) {
        log.debug("attaching dirty Fbankinfo instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fbankinfo instance) {
        log.debug("attaching clean Fbankinfo instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public Fbankinfo findUserBankInfo(int fid) {
        Fbankinfo fbankinfo = null;
        log.debug("finding all findUserBankInfo instances");
        try {
            String queryString = "from Fbankinfo where fuser.fid=? and fstatus=?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, fid);
            queryObject.setParameter(1, BankInfoStatusEnum.NORMAL_VALUE);
            List<Fbankinfo> fbankinfos = queryObject.list();
            if (fbankinfos.size() > 0) {
                fbankinfo = fbankinfos.get(0);
                if (fbankinfos.size() > 1) {
                    log.debug("more than one bankInfo per user.");
                }
            }
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
        return fbankinfo;
    }
}