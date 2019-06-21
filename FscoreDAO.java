package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fscore;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fscore entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ztmp.Fscore
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FscoreDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FscoreDAO.class);
    // property constants
    public static final String FSCORE = "fscore";
    public static final String FLEVEL = "flevel";

    public void save(Fscore transientInstance) {
        log.debug("saving Fscore instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fscore persistentInstance) {
        log.debug("deleting Fscore instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fscore findById(java.lang.Integer id) {
        log.debug("getting Fscore instance with id: " + id);
        try {
            Fscore instance = (Fscore) getSession().get("com.huagu.vcoin.main.model.Fscore", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fscore> findByExample(Fscore instance) {
        log.debug("finding Fscore instance by example");
        try {
            List<Fscore> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fscore")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fscore instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fscore as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fscore> findByFscore(Object fscore) {
        return findByProperty(FSCORE, fscore);
    }

    public List<Fscore> findByFlevel(Object flevel) {
        return findByProperty(FLEVEL, flevel);
    }

    public List findAll() {
        log.debug("finding all Fscore instances");
        try {
            String queryString = "from Fscore";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fscore merge(Fscore detachedInstance) {
        log.debug("merging Fscore instance");
        try {
            Fscore result = (Fscore) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fscore instance) {
        log.debug("attaching dirty Fscore instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fscore instance) {
        log.debug("attaching clean Fscore instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void updateData(String sql) {
        Query queryObject = getSession().createSQLQuery(sql);
        queryObject.executeUpdate();
    }
}