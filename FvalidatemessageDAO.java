package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fvalidatemessage;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fvalidatemessage entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ztmp.Fvalidatemessage
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FvalidatemessageDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FvalidatemessageDAO.class);
    // property constants
    public static final String FPHONE = "fphone";
    public static final String FCONTENT = "fcontent";
    public static final String FSTATUS = "fstatus";

    public void save(Fvalidatemessage transientInstance) {
        log.debug("saving Fvalidatemessage instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fvalidatemessage persistentInstance) {
        log.debug("deleting Fvalidatemessage instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fvalidatemessage findById(java.lang.Integer id) {
        log.debug("getting Fvalidatemessage instance with id: " + id);
        try {
            Fvalidatemessage instance = (Fvalidatemessage) getSession()
                    .get("com.huagu.vcoin.main.model.Fvalidatemessage", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fvalidatemessage> findByExample(Fvalidatemessage instance) {
        log.debug("finding Fvalidatemessage instance by example");
        try {
            List<Fvalidatemessage> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fvalidatemessage")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fvalidatemessage instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fvalidatemessage as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fvalidatemessage> findByFphone(Object fphone) {
        return findByProperty(FPHONE, fphone);
    }

    public List<Fvalidatemessage> findByFcontent(Object fcontent) {
        return findByProperty(FCONTENT, fcontent);
    }

    public List<Fvalidatemessage> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List findAll() {
        log.debug("finding all Fvalidatemessage instances");
        try {
            String queryString = "from Fvalidatemessage";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fvalidatemessage merge(Fvalidatemessage detachedInstance) {
        log.debug("merging Fvalidatemessage instance");
        try {
            Fvalidatemessage result = (Fvalidatemessage) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fvalidatemessage instance) {
        log.debug("attaching dirty Fvalidatemessage instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fvalidatemessage instance) {
        log.debug("attaching clean Fvalidatemessage instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public Fvalidatemessage findLastValidatMessage(int uid) {
        log.debug("finding all findLastValidatMessage instances");
        try {
            String queryString = "from Fvalidatemessage order by fcreateTime desc";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setMaxResults(1);
            queryObject.setFirstResult(0);

            List<Fvalidatemessage> fvalidatemessages = queryObject.list();
            if (fvalidatemessages.size() > 0) {
                return fvalidatemessages.get(0);
            } else {
                return null;
            }
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
}