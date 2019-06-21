package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fvirtualcointype;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fvirtualcointype entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.ruizton.main.com.ruizton.main.model.Fvirtualcointype
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FvirtualcointypeDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FvirtualcointypeDAO.class);
    // property constants
    public static final String FNAME = "fname";
    public static final String FDESCRIPTION = "fdescription";

    public void save(Fvirtualcointype transientInstance) {
        log.debug("saving Fvirtualcointype instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fvirtualcointype persistentInstance) {
        log.debug("deleting Fvirtualcointype instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fvirtualcointype findById(java.lang.Integer id) {
        log.debug("getting Fvirtualcointype instance with id: " + id);
        try {
            Fvirtualcointype instance = (Fvirtualcointype) getSession()
                    .get("com.huagu.vcoin.main.model.Fvirtualcointype", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fvirtualcointype> findByExample(Fvirtualcointype instance) {
        log.debug("finding Fvirtualcointype instance by example");
        try {
            List<Fvirtualcointype> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fvirtualcointype")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fvirtualcointype instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fvirtualcointype as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fvirtualcointype> findByFname(Object fname) {
        return findByProperty(FNAME, fname);
    }

    public List<Fvirtualcointype> findByFdescription(Object fdescription) {
        return findByProperty(FDESCRIPTION, fdescription);
    }

    // selectType 0 is equal sign, 1 is <> sign
    public List findAll(int coinType, int selectType) {
        log.debug("finding all Fvirtualcointype instances");
        try {
            String eq = "=";
            if (selectType == 1) {
                eq = "<>";
            }
            String queryString = "from Fvirtualcointype where ftype" + eq + "?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, coinType);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List findAll() {
        log.debug("finding all Fvirtualcointype instances");
        try {
            String queryString = "from Fvirtualcointype";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fvirtualcointype merge(Fvirtualcointype detachedInstance) {
        log.debug("merging Fvirtualcointype instance");
        try {
            Fvirtualcointype result = (Fvirtualcointype) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fvirtualcointype instance) {
        log.debug("attaching dirty Fvirtualcointype instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fvirtualcointype instance) {
        log.debug("attaching clean Fvirtualcointype instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public boolean isTrue(String sql) {
        boolean flag = false;
        Query queryObject = getSession().createSQLQuery(sql);
        if (queryObject.list().size() > 0) {
            flag = true;
        }
        return flag;
    }

    public List<Fvirtualcointype> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fvirtualcointype> list = null;
        log.debug("finding Fvirtualcointype instance with filter");
        try {
            String queryString = "from Fvirtualcointype " + filter;
            Query queryObject = getSession().createQuery(queryString);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Fvirtualcointype by filter name failed", re);
            throw re;
        }
        return list;
    }

    public String startCoinType(int fid) {
        String result = "";
        Connection conn = null;
        CallableStatement statement = null;
        try {
            conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
            statement = conn.prepareCall("{call startVirtualCoinType(?,?)}");
            statement.setInt(1, fid);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.execute();
            result = statement.getObject(2) + "";
            statement.close();
            conn.close();
        } catch (SQLException e) {
            result = e.getMessage();
        }
        return result;
    }
}