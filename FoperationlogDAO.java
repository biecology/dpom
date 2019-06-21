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
import com.huagu.vcoin.main.model.Foperationlog;

/**
 * A data access object (DAO) providing persistence and search support for
 * Foperationlog entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ztmp.Foperationlog
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FoperationlogDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FoperationlogDAO.class);
    // property constants
    public static final String FTYPE = "ftype";
    public static final String FSTATUS = "fstatus";
    public static final String FAMOUNT = "famount";
    public static final String FDESCRIPTION = "fdescription";
    public static final String FKEY1 = "fkey1";
    public static final String FKEY2 = "fkey2";
    public static final String FKEY3 = "fkey3";
    public static final String FKEY4 = "fkey4";
    public static final String FKEY5 = "fkey5";

    public void save(Foperationlog transientInstance) {
        log.debug("saving Foperationlog instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Foperationlog persistentInstance) {
        log.debug("deleting Foperationlog instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Foperationlog findById(java.lang.Integer id) {
        log.debug("getting Foperationlog instance with id: " + id);
        try {
            Foperationlog instance = (Foperationlog) getSession().get("com.huagu.vcoin.main.model.Foperationlog", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Foperationlog> findByExample(Foperationlog instance) {
        log.debug("finding Foperationlog instance by example");
        try {
            List<Foperationlog> results = getSession().createCriteria("com.huagu.vcoin.main.model.Foperationlog")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Foperationlog instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Foperationlog as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Foperationlog> findByFtype(Object ftype) {
        return findByProperty(FTYPE, ftype);
    }

    public List<Foperationlog> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List<Foperationlog> findByFamount(Object famount) {
        return findByProperty(FAMOUNT, famount);
    }

    public List<Foperationlog> findByFdescription(Object fdescription) {
        return findByProperty(FDESCRIPTION, fdescription);
    }

    public List<Foperationlog> findByFkey1(Object fkey1) {
        return findByProperty(FKEY1, fkey1);
    }

    public List<Foperationlog> findByFkey2(Object fkey2) {
        return findByProperty(FKEY2, fkey2);
    }

    public List<Foperationlog> findByFkey3(Object fkey3) {
        return findByProperty(FKEY3, fkey3);
    }

    public List<Foperationlog> findByFkey4(Object fkey4) {
        return findByProperty(FKEY4, fkey4);
    }

    public List<Foperationlog> findByFkey5(Object fkey5) {
        return findByProperty(FKEY5, fkey5);
    }

    public List findAll() {
        log.debug("finding all Foperationlog instances");
        try {
            String queryString = "from Foperationlog";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Foperationlog merge(Foperationlog detachedInstance) {
        log.debug("merging Foperationlog instance");
        try {
            Foperationlog result = (Foperationlog) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Foperationlog instance) {
        log.debug("attaching dirty Foperationlog instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Foperationlog instance) {
        log.debug("attaching clean Foperationlog instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void updateData(String sql) {
        SQLQuery sQLQuery = getSession().createSQLQuery(sql);
        sQLQuery.executeUpdate();
    }

    public List<Foperationlog> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Foperationlog> list = null;
        log.debug("finding Foperationlog instance with filter");
        try {
            String queryString = "from Foperationlog " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Foperationlog by filter name failed", re);
            throw re;
        }
        return list;
    }

    public double getTotalAmt1(int userid) {
        double amt = 0d;
        List list = null;
        try {
            String queryString = "SELECT SUM(fAmount) from fcapitaloperation where ftype=1 and fStatus=3 and FUs_fId="
                    + userid;
            Query queryObject = getSession().createSQLQuery(queryString);
            list = queryObject.list();
            if (list != null && list.size() > 0 && list.get(0) != null) {
                amt = Double.valueOf(list.get(0).toString());
            }
        } catch (RuntimeException re) {
            throw re;
        }
        return amt;
    }

    public double getTotalAmt2(int userid) {
        double amt = 0d;
        List list = null;
        try {
            String queryString = "SELECT SUM(famount) from foperationlog where fstatus=2 and fuid=" + userid;
            Query queryObject = getSession().createSQLQuery(queryString);
            list = queryObject.list();
            if (list != null && list.size() > 0 && list.get(0) != null) {
                amt = Double.valueOf(list.get(0).toString());
            }
        } catch (RuntimeException re) {
            throw re;
        }
        return amt;
    }
}