package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fperiod;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fperiod entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ztmp.Fperiod
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FperiodDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FperiodDAO.class);
    // property constants
    public static final String FKAI = "fkai";
    public static final String FGAO = "fgao";
    public static final String FDI = "fdi";
    public static final String FSHOU = "fshou";
    public static final String FLIANG = "fliang";

    public void save(Fperiod transientInstance) {
        log.debug("saving Fperiod instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fperiod persistentInstance) {
        log.debug("deleting Fperiod instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fperiod findById(java.lang.Integer id) {
        log.debug("getting Fperiod instance with id: " + id);
        try {
            Fperiod instance = (Fperiod) getSession().get("com.huagu.vcoin.main.model.Fperiod", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fperiod> findByExample(Fperiod instance) {
        log.debug("finding Fperiod instance by example");
        try {
            List<Fperiod> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fperiod")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fperiod instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fperiod as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fperiod> findByFkai(Object fkai) {
        return findByProperty(FKAI, fkai);
    }

    public List<Fperiod> findByFgao(Object fgao) {
        return findByProperty(FGAO, fgao);
    }

    public List<Fperiod> findByFdi(Object fdi) {
        return findByProperty(FDI, fdi);
    }

    public List<Fperiod> findByFshou(Object fshou) {
        return findByProperty(FSHOU, fshou);
    }

    public List<Fperiod> findByFliang(Object fliang) {
        return findByProperty(FLIANG, fliang);
    }

    public List findAll() {
        log.debug("finding all Fperiod instances");
        try {
            String queryString = "from Fperiod";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fperiod merge(Fperiod detachedInstance) {
        log.debug("merging Fperiod instance");
        try {
            Fperiod result = (Fperiod) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fperiod instance) {
        log.debug("attaching dirty Fperiod instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fperiod instance) {
        log.debug("attaching clean Fperiod instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fperiod> findAllFperiod(long fromTime, int fvirtualCoinType) {
        log.debug("findAllFperiod all Fperiod instances");
        try {
            String queryString = "from Fperiod where ftrademapping.fid = ? and ftime>?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, fvirtualCoinType);
            queryObject.setTimestamp(1, new Date(fromTime));
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
    // public Fperiod getLastFperiod(Fvirtualcointype fvirtualcointype){
    // log.debug("getLastFperiod all Fperiod instances");
    // try {
    // String queryString = "from Fperiod where ftrademapping.fid=? order by fid
    // desc";
    // Query queryObject = getSession().createQuery(queryString);
    // queryObject.setParameter(0, fvirtualcointype.getFid()) ;
    // queryObject.setFirstResult(0);
    // queryObject.setMaxResults(1) ;
    // List<Fperiod> fperiods = queryObject.list() ;
    // if(fperiods.size()>0){
    // return fperiods.get(0) ;
    // }else{
    // return null ;
    // }
    // } catch (RuntimeException re) {
    // log.error("find all failed", re);
    // throw re;
    // }
    // }

    public List<Fperiod> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fperiod> list = null;
        log.debug("finding Fperiod instance with filter");
        try {
            StringBuffer sb =  new StringBuffer();
            sb.append("from Fperiod fperiod inner join com.huagu.vcoin.main.model.Ftrademapping ftrademapping \n");
            sb.append("on fperiod.ftrademapping =ftrademapping.fid \n");
            sb.append("inner join com.huagu.vcoin.main.model.Fvirtualcointype fvirtualcointype \n");
            sb.append("on ftrademapping.fvirtualcointype1 =fvirtualcointype.fid \n");
            String queryString = sb + " " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Fperiod by filter name failed", re);
            throw re;
        }
        return list;
    }
}