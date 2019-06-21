package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.coa.common.cons.AppDB;
import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fpool;
import com.huagu.vcoin.main.model.Fvirtualcointype;

/**
 * A data access object (DAO) providing persistence and search support for Fpool
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see ztmp.Fpool
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FpoolDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FpoolDAO.class);
    // property constants
    public static final String FADDRESS = "faddress";

    public void save(Fpool transientInstance) {
        log.debug("saving Fpool instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fpool persistentInstance) {
        log.debug("deleting Fpool instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fpool findById(java.lang.Integer id) {
        log.debug("getting Fpool instance with id: " + id);
        try {
            Fpool instance = (Fpool) getSession().get("com.huagu.vcoin.main.model.Fpool", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fpool> findByExample(Fpool instance) {
        log.debug("finding Fpool instance by example");
        try {
            List<Fpool> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fpool").add(create(instance))
                    .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fpool instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fpool as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fpool> findByFaddress(Object faddress) {
        return findByProperty(FADDRESS, faddress);
    }

    public List findAll() {
        log.debug("finding all Fpool instances");
        try {
            String queryString = "from Fpool";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fpool merge(Fpool detachedInstance) {
        log.debug("merging Fpool instance");
        try {
            Fpool result = (Fpool) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fpool instance) {
        log.debug("attaching dirty Fpool instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fpool instance) {
        log.debug("attaching clean Fpool instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public Fpool getOneFpool(Fvirtualcointype fvirtualcointype) {
        log.debug("finding all Fpool instances");
        try {
            String queryString = "from Fpool where fvirtualcointype.fid=? and fstatus=?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, fvirtualcointype.getFid());
            queryObject.setParameter(1, 0);
            queryObject.setFirstResult(0);
            queryObject.setMaxResults(1);

            List<Fpool> fpools = queryObject.list();
            if (fpools.size() > 0) {
                return fpools.get(0);
            } else {
                return null;
            }
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<Map> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Map> list = null;
        try {
            StringBuffer sf = new StringBuffer();
            sf.append(String.format("select b.fname,count(a.fid) c from %s a left outer join \n", AppDB.fpool));
            sf.append("fvirtualcointype b on a.fvi_type = b.fid \n");
            sf.append(filter + " \n");
            sf.append("group by a.fvi_type \n");
            Query queryObject = getSession().createSQLQuery(sf.toString());
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by fpool name failed", re);
            throw re;
        }
        return list;
    }
}