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
import com.huagu.vcoin.main.model.Fintrolinfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fintrolinfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .Fintrolinfo
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FintrolinfoDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FintrolinfoDAO.class);
    // property constants
    public static final String FTITLE = "ftitle";
    public static final String FQTY = "fqty";

    public void save(Fintrolinfo transientInstance) {
        log.debug("saving Fintrolinfo instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fintrolinfo persistentInstance) {
        log.debug("deleting Fintrolinfo instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fintrolinfo findById(java.lang.Integer id) {
        log.debug("getting Fintrolinfo instance with id: " + id);
        try {
            Fintrolinfo instance = (Fintrolinfo) getSession().get("com.huagu.vcoin.main.model.Fintrolinfo", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fintrolinfo> findByExample(Fintrolinfo instance) {
        log.debug("finding Fintrolinfo instance by example");
        try {
            List<Fintrolinfo> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fintrolinfo")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fintrolinfo instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fintrolinfo as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fintrolinfo> findByFtitle(Object ftitle) {
        return findByProperty(FTITLE, ftitle);
    }

    public List<Fintrolinfo> findByFqty(Object fqty) {
        return findByProperty(FQTY, fqty);
    }

    public List findAll() {
        log.debug("finding all Fintrolinfo instances");
        try {
            String queryString = "from Fintrolinfo";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fintrolinfo merge(Fintrolinfo detachedInstance) {
        log.debug("merging Fintrolinfo instance");
        try {
            Fintrolinfo result = (Fintrolinfo) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fintrolinfo instance) {
        log.debug("attaching dirty Fintrolinfo instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fintrolinfo instance) {
        log.debug("attaching clean Fintrolinfo instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fintrolinfo> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fintrolinfo> list = null;
        log.debug("finding Fintrolinfo instance with filter");
        try {
            String queryString = "from Fintrolinfo " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fintrolinfo name failed", re);
            throw re;
        }
        return list;
    }

    public List getAllIntrol(int firstResult, int maxResults, String filter, boolean isFY) {
        StringBuffer sf = new StringBuffer();
        sf.append("select * from ( \n");
        sf.append("SELECT  \n");
        sf.append("a.fid, \n");
        sf.append("a.floginName, \n");
        sf.append("a.fRealName, \n");
        sf.append("ROUND(IFNULL(b.qty,0),4) gtc, \n");
        sf.append("ROUND(IFNULL(d.qty,0),4) cny \n");
        sf.append("from  \n");
        sf.append("fuser a LEFT OUTER JOIN \n");
        sf.append(
                "(SELECT fuserid,SUM(fqty) qty from fintrolinfo where fiscny=0 GROUP BY fuserid)as b on a.fid = b.fuserid \n");
        sf.append("LEFT OUTER JOIN \n");
        sf.append(
                "(SELECT fuserid,SUM(fqty) qty from fintrolinfo where fiscny=1 GROUP BY fuserid)as d on a.fid = d.fuserid \n");
        sf.append(")as t  \n");
        sf.append(filter + " \n");
        sf.append("order by t.cny desc \n");
        Query queryObject = getSession().createSQLQuery(sf.toString());
        if (isFY) {
            queryObject.setFirstResult(firstResult);
            queryObject.setMaxResults(maxResults);
        }
        return queryObject.list();
    }
}