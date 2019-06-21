package com.huagu.vcoin.main.dao;

// default package

import static org.hibernate.criterion.Example.create;

import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fshoppinglog;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fshoppinglog entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .Fshoppinglog
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FshoppinglogDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FshoppinglogDAO.class);
    // property constants
    public static final String VERSION = "version";
    public static final String FPRICE_AMT = "fpriceAmt";
    public static final String FPRICE_COIN = "fpriceCoin";
    public static final String FSTATUS = "fstatus";
    public static final String FPASSWORD_A = "fpasswordA";
    public static final String FPASSWORD_B = "fpasswordB";
    public static final String FPHONE = "fphone";
    public static final String FRECEIVE_ADDRESS = "freceiveAddress";
    public static final String FEXPRESS_NO = "fexpressNo";

    public void save(Fshoppinglog transientInstance) {
        log.debug("saving Fshoppinglog instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fshoppinglog persistentInstance) {
        log.debug("deleting Fshoppinglog instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fshoppinglog findById(java.lang.Integer id) {
        log.debug("getting Fshoppinglog instance with id: " + id);
        try {
            Fshoppinglog instance = (Fshoppinglog) getSession().get("com.huagu.vcoin.main.model.Fshoppinglog", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fshoppinglog> findByExample(Fshoppinglog instance) {
        log.debug("finding Fshoppinglog instance by example");
        try {
            List<Fshoppinglog> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fshoppinglog")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fshoppinglog instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fshoppinglog as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fshoppinglog> findByVersion(Object version) {
        return findByProperty(VERSION, version);
    }

    public List<Fshoppinglog> findByFpriceAmt(Object fpriceAmt) {
        return findByProperty(FPRICE_AMT, fpriceAmt);
    }

    public List<Fshoppinglog> findByFpriceCoin(Object fpriceCoin) {
        return findByProperty(FPRICE_COIN, fpriceCoin);
    }

    public List<Fshoppinglog> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List<Fshoppinglog> findByFpasswordA(Object fpasswordA) {
        return findByProperty(FPASSWORD_A, fpasswordA);
    }

    public List<Fshoppinglog> findByFpasswordB(Object fpasswordB) {
        return findByProperty(FPASSWORD_B, fpasswordB);
    }

    public List<Fshoppinglog> findByFphone(Object fphone) {
        return findByProperty(FPHONE, fphone);
    }

    public List<Fshoppinglog> findByFreceiveAddress(Object freceiveAddress) {
        return findByProperty(FRECEIVE_ADDRESS, freceiveAddress);
    }

    public List<Fshoppinglog> findByFexpressNo(Object fexpressNo) {
        return findByProperty(FEXPRESS_NO, fexpressNo);
    }

    public List findAll() {
        log.debug("finding all Fshoppinglog instances");
        try {
            String queryString = "from Fshoppinglog";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fshoppinglog merge(Fshoppinglog detachedInstance) {
        log.debug("merging Fshoppinglog instance");
        try {
            Fshoppinglog result = (Fshoppinglog) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fshoppinglog instance) {
        log.debug("attaching dirty Fshoppinglog instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fshoppinglog instance) {
        log.debug("attaching clean Fshoppinglog instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fshoppinglog> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fshoppinglog> list = null;
        log.debug("finding Fshoppinglog instance with filter");
        try {
            String queryString = "from Fshoppinglog " + filter;
            Query queryObject = getSession().createQuery(queryString);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fshoppinglog name failed", re);
            throw re;
        }
        return list;
    }

    public List<Fshoppinglog> findByMap(Map<String, Object> param) {
        log.debug("getting Fshoppinglog instance with param");
        try {
            StringBuffer queryString = new StringBuffer("from Fshoppinglog as model where ");
            Object[] keys = null;
            if (param != null) {
                keys = param.keySet().toArray();
                for (Object object : keys) {
                    String keystr = (String) object;
                    queryString.append(keystr + "= ? and ");
                }

            }

            queryString.append(" 1=1 ");

            Query queryObject = getSession().createQuery(queryString.toString());
            if (keys != null) {
                for (int i = 0; i < keys.length; i++) {
                    Object value = param.get(keys[i]);
                    queryObject.setParameter(i, value);
                }
            }
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }
}