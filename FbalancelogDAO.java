package com.huagu.vcoin.main.dao;

// default package

import static org.hibernate.criterion.Example.create;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fbalancelog;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fbalancelog entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .Fbalancelog
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FbalancelogDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FbalancelogDAO.class);
    // property constants
    public static final String VERSION = "version";
    public static final String FAMOUNT = "famount";
    public static final String FINCOME_AMOUNT1 = "fincomeAmount1";
    public static final String FINCOME_AMOUNT2 = "fincomeAmount2";
    public static final String FSTATUS = "fstatus";
    public static final String FTYPE = "ftype";
    public static final String FRATE = "frate";

    public void save(Fbalancelog transientInstance) {
        log.debug("saving Fbalancelog instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fbalancelog persistentInstance) {
        log.debug("deleting Fbalancelog instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fbalancelog findById(java.lang.Integer id) {
        log.debug("getting Fbalancelog instance with id: " + id);
        try {
            Fbalancelog instance = (Fbalancelog) getSession().get("com.huagu.vcoin.main.model.Fbalancelog", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fbalancelog> findByExample(Fbalancelog instance) {
        log.debug("finding Fbalancelog instance by example");
        try {
            List<Fbalancelog> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fbalancelog")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fbalancelog instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fbalancelog as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fbalancelog> findByVersion(Object version) {
        return findByProperty(VERSION, version);
    }

    public List<Fbalancelog> findByFamount(Object famount) {
        return findByProperty(FAMOUNT, famount);
    }

    public List<Fbalancelog> findByFincomeAmount1(Object fincomeAmount1) {
        return findByProperty(FINCOME_AMOUNT1, fincomeAmount1);
    }

    public List<Fbalancelog> findByFincomeAmount2(Object fincomeAmount2) {
        return findByProperty(FINCOME_AMOUNT2, fincomeAmount2);
    }

    public List<Fbalancelog> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List<Fbalancelog> findByFtype(Object ftype) {
        return findByProperty(FTYPE, ftype);
    }

    public List<Fbalancelog> findByFrate(Object frate) {
        return findByProperty(FRATE, frate);
    }

    public List findAll() {
        log.debug("finding all Fbalancelog instances");
        try {
            String queryString = "from Fbalancelog";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fbalancelog merge(Fbalancelog detachedInstance) {
        log.debug("merging Fbalancelog instance");
        try {
            Fbalancelog result = (Fbalancelog) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fbalancelog instance) {
        log.debug("attaching dirty Fbalancelog instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fbalancelog instance) {
        log.debug("attaching clean Fbalancelog instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fbalancelog> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fbalancelog> list = null;
        log.debug("finding Fbalancelog instance with filter");
        try {
            String queryString = "from Fbalancelog " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by Fbalancelog name failed", re);
            throw re;
        }
        return list;
    }

    public Map<String, Double> getReport() {
        Map<String, Double> map = new HashMap<String, Double>();
        log.debug("finding Fbalancelog instance with filter");
        try {
            String queryString = "SELECT SUM(a.famount) amt,a.ftype,b.fname from fbalancelog a"
                    + " left outer join fvirtualcointype b "
                    + "on a.fvid=b.fid where 1=1 and a.fstatus in(1,2) GROUP BY b.fname,a.ftype";
            Query queryObject = getSession().createSQLQuery(queryString);
            List list = queryObject.list();
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != null) {
                        Object[] o = (Object[]) list.get(i);
                        map.put(o[1].toString() + "-" + o[2].toString(), Double.valueOf(o[0].toString()));
                    }
                }
            }
        } catch (RuntimeException re) {
            log.error("find by Fbalancelog name failed", re);
            throw re;
        }
        return map;
    }
}