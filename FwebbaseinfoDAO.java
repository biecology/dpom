package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fwebbaseinfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fwebbaseinfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.huagu.vcoin.main.model.Fwebbaseinfo
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FwebbaseinfoDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FwebbaseinfoDAO.class);
    // property constants
    public static final String FTITLE = "ftitle";
    public static final String FKEYWORDS = "fkeywords";
    public static final String FDESCRIPTION = "fdescription";
    public static final String FCOPY_RIGHTS = "fcopyRights";
    public static final String FBEIAN_INFO = "fbeianInfo";
    public static final String FSYSTEM_MAIL = "fsystemMail";

    public void save(Fwebbaseinfo transientInstance) {
        log.debug("saving Fwebbaseinfo instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fwebbaseinfo persistentInstance) {
        log.debug("deleting Fwebbaseinfo instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fwebbaseinfo findById(java.lang.Integer id) {
        log.debug("getting Fwebbaseinfo instance with id: " + id);
        try {
            Fwebbaseinfo instance = (Fwebbaseinfo) getSession().get("com.huagu.vcoin.main.model.Fwebbaseinfo", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fwebbaseinfo> findByExample(Fwebbaseinfo instance) {
        log.debug("finding Fwebbaseinfo instance by example");
        try {
            List<Fwebbaseinfo> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fwebbaseinfo")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fwebbaseinfo instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fwebbaseinfo as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fwebbaseinfo> findByFtitle(Object ftitle) {
        return findByProperty(FTITLE, ftitle);
    }

    public List<Fwebbaseinfo> findByFkeywords(Object fkeywords) {
        return findByProperty(FKEYWORDS, fkeywords);
    }

    public List<Fwebbaseinfo> findByFdescription(Object fdescription) {
        return findByProperty(FDESCRIPTION, fdescription);
    }

    public List<Fwebbaseinfo> findByFcopyRights(Object fcopyRights) {
        return findByProperty(FCOPY_RIGHTS, fcopyRights);
    }

    public List<Fwebbaseinfo> findByFbeianInfo(Object fbeianInfo) {
        return findByProperty(FBEIAN_INFO, fbeianInfo);
    }

    public List<Fwebbaseinfo> findByFsystemMail(Object fsystemMail) {
        return findByProperty(FSYSTEM_MAIL, fsystemMail);
    }

    public List findAll() {
        log.debug("finding all Fwebbaseinfo instances");
        try {
            String queryString = "from Fwebbaseinfo";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fwebbaseinfo merge(Fwebbaseinfo detachedInstance) {
        log.debug("merging Fwebbaseinfo instance");
        try {
            Fwebbaseinfo result = (Fwebbaseinfo) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fwebbaseinfo instance) {
        log.debug("attaching dirty Fwebbaseinfo instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fwebbaseinfo instance) {
        log.debug("attaching clean Fwebbaseinfo instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}