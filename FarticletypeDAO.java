package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Farticletype;

/**
 * A data access object (DAO) providing persistence and search support for
 * Farticletype entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.huagu.vcoin.main.model.Farticletype
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FarticletypeDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FarticletypeDAO.class);
    // property constants
    public static final String FNAME = "fname";
    public static final String FKEYWORDS = "fkeywords";
    public static final String FDESCRIPTION = "fdescription";

    public void save(Farticletype transientInstance) {
        log.debug("saving Farticletype instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Farticletype persistentInstance) {
        log.debug("deleting Farticletype instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Farticletype findById(java.lang.Integer id) {
        log.debug("getting Farticletype instance with id: " + id);
        try {
            Farticletype instance = (Farticletype) getSession().get("com.huagu.vcoin.main.model.Farticletype", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Farticletype> findByExample(Farticletype instance) {
        log.debug("finding Farticletype instance by example");
        try {
            List<Farticletype> results = getSession().createCriteria("com.huagu.vcoin.main.model.Farticletype")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Farticletype instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Farticletype as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Farticletype> findByFname(Object fname) {
        return findByProperty(FNAME, fname);
    }

    public List<Farticletype> findByFkeywords(Object fkeywords) {
        return findByProperty(FKEYWORDS, fkeywords);
    }

    public List<Farticletype> findByFdescription(Object fdescription) {
        return findByProperty(FDESCRIPTION, fdescription);
    }

    public List findAll() {
        log.debug("finding all Farticletype instances");
        try {
            String queryString = "from Farticletype";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Farticletype merge(Farticletype detachedInstance) {
        log.debug("merging Farticletype instance");
        try {
            Farticletype result = (Farticletype) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Farticletype instance) {
        log.debug("attaching dirty Farticletype instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Farticletype instance) {
        log.debug("attaching clean Farticletype instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Farticletype> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Farticletype> list = null;
        log.debug("finding Farticletype instance with filter");
        try {
            String queryString = "from Farticletype " + filter;
            Query queryObject = getSession().createQuery(queryString);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Farticletype by filter name failed", re);
            throw re;
        }
        return list;
    }
}