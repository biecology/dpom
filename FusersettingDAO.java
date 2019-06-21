package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fusersetting;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fusersetting entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see temp.Fusersetting
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FusersettingDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FusersettingDAO.class);
    // property constants
    public static final String FIS_AUTO_RETURN_TO_ACCOUNT = "fisAutoReturnToAccount";

    public void save(Fusersetting transientInstance) {
        log.debug("saving Fusersetting instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fusersetting persistentInstance) {
        log.debug("deleting Fusersetting instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fusersetting findById(java.lang.Integer id) {
        log.debug("getting Fusersetting instance with id: " + id);
        try {
            Fusersetting instance = (Fusersetting) getSession().get("com.huagu.vcoin.main.model.Fusersetting", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fusersetting> findByExample(Fusersetting instance) {
        log.debug("finding Fusersetting instance by example");
        try {
            List<Fusersetting> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fusersetting")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fusersetting instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fusersetting as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fusersetting> findByFisAutoReturnToAccount(Object fisAutoReturnToAccount) {
        return findByProperty(FIS_AUTO_RETURN_TO_ACCOUNT, fisAutoReturnToAccount);
    }

    public List findAll() {
        log.debug("finding all Fusersetting instances");
        try {
            String queryString = "from Fusersetting";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fusersetting merge(Fusersetting detachedInstance) {
        log.debug("merging Fusersetting instance");
        try {
            Fusersetting result = (Fusersetting) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fusersetting instance) {
        log.debug("attaching dirty Fusersetting instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fusersetting instance) {
        log.debug("attaching clean Fusersetting instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fusersetting> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fusersetting> list = null;
        log.debug("finding Fusersetting instance with filter");
        try {
            String queryString = "from Fusersetting " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fusersetting name failed", re);
            throw re;
        }
        return list;
    }

    public void updateCleanScore() {
        try {
            String queryString = " update Fusersetting set fscore=0 ,flotteryeggTimes=0 ";
            SQLQuery queryObject = getSession().createSQLQuery(queryString);
            queryObject.executeUpdate();
        } catch (RuntimeException re) {
            throw re;
        }
    }
}