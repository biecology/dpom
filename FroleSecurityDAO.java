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
import com.huagu.vcoin.main.model.FroleSecurity;

/**
 * A data access object (DAO) providing persistence and search support for
 * FroleSecurity entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .FroleSecurity
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FroleSecurityDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FroleSecurityDAO.class);

    // property constants

    public void save(FroleSecurity transientInstance) {
        log.debug("saving FroleSecurity instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(FroleSecurity persistentInstance) {
        log.debug("deleting FroleSecurity instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public FroleSecurity findById(java.lang.Integer id) {
        log.debug("getting FroleSecurity instance with id: " + id);
        try {
            FroleSecurity instance = (FroleSecurity) getSession().get("com.huagu.vcoin.main.model.FroleSecurity", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<FroleSecurity> findByExample(FroleSecurity instance) {
        log.debug("finding FroleSecurity instance by example");
        try {
            List<FroleSecurity> results = getSession().createCriteria("com.huagu.vcoin.main.model.FroleSecurity")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding FroleSecurity instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from FroleSecurity as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findAll() {
        log.debug("finding all FroleSecurity instances");
        try {
            String queryString = "from FroleSecurity";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public FroleSecurity merge(FroleSecurity detachedInstance) {
        log.debug("merging FroleSecurity instance");
        try {
            FroleSecurity result = (FroleSecurity) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FroleSecurity instance) {
        log.debug("attaching dirty FroleSecurity instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(FroleSecurity instance) {
        log.debug("attaching clean FroleSecurity instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<FroleSecurity> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<FroleSecurity> list = null;
        log.debug("finding FroleSecurity instance with filter");
        try {
            String queryString = "from FroleSecurity " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find FroleSecurity by filter name failed", re);
            throw re;
        }
        return list;
    }

    public void deleteByRoleId(int roleId) {
        try {
            Query query = getSession().createQuery("delete from FroleSecurity where frole.fid=?");
            query.setInteger(0, roleId);// Setting condition parameters
            query.executeUpdate();
        } catch (RuntimeException re) {
            throw re;
        }
    }
}