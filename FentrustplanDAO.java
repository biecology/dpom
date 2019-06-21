package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fentrustplan;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fentrustplan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.ruizton.main.com.ruizton.main.model.Fentrustplan
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FentrustplanDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FentrustplanDAO.class);
    // property constants
    public static final String FCOUNT = "fcount";
    public static final String FPRIZE = "fprize";
    public static final String FAMOUNT = "famount";
    public static final String FSTATUS = "fstatus";

    public void save(Fentrustplan transientInstance) {
        log.debug("saving Fentrustplan instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fentrustplan persistentInstance) {
        log.debug("deleting Fentrustplan instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fentrustplan findById(java.lang.Integer id) {
        log.debug("getting Fentrustplan instance with id: " + id);
        try {
            Fentrustplan instance = (Fentrustplan) getSession().get("com.huagu.vcoin.main.model.Fentrustplan", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fentrustplan> findByExample(Fentrustplan instance) {
        log.debug("finding Fentrustplan instance by example");
        try {
            List<Fentrustplan> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fentrustplan")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fentrustplan instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fentrustplan as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fentrustplan> findByFcount(Object fcount) {
        return findByProperty(FCOUNT, fcount);
    }

    public List<Fentrustplan> findByFprize(Object fprize) {
        return findByProperty(FPRIZE, fprize);
    }

    public List<Fentrustplan> findByFamount(Object famount) {
        return findByProperty(FAMOUNT, famount);
    }

    public List<Fentrustplan> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List findAll() {
        log.debug("finding all Fentrustplan instances");
        try {
            String queryString = "from Fentrustplan";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fentrustplan merge(Fentrustplan detachedInstance) {
        log.debug("merging Fentrustplan instance");
        try {
            Fentrustplan result = (Fentrustplan) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fentrustplan instance) {
        log.debug("attaching dirty Fentrustplan instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fentrustplan instance) {
        log.debug("attaching clean Fentrustplan instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fentrustplan> findFentrustplan(int fuser, int fvirtualcointype, int[] fstatus, int firtResult,
            int maxResult, String order) {
        log.debug("findFentrustplan all Fentrustplan instances");
        try {
            StringBuffer queryString = new StringBuffer(
                    "from Fentrustplan where fuser.fid=? " + " and fvirtualcointype.fid=? and ");
            queryString.append(" ( ");
            if (fstatus != null) {
                for (int i = 0; i < fstatus.length; i++) {
                    if (i == 0) {
                        queryString.append(" fstatus=? ");
                    } else {
                        queryString.append(" or fstatus=? ");
                    }
                }
            }

            queryString.append(" ) ");
            if (order != null) {
                queryString.append(" order by " + order);
            }
            Query queryObject = getSession().createQuery(queryString.toString());
            queryObject.setParameter(0, fuser);
            queryObject.setParameter(1, fvirtualcointype);
            if (fstatus != null) {
                for (int i = 0; i < fstatus.length; i++) {
                    queryObject.setParameter(i + 2, fstatus[i]);
                }
            }

            queryObject.setFirstResult(firtResult);
            queryObject.setMaxResults(maxResult);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public long findFentrustplanCount(int fuser, int fvirtualcointype, int[] fstatus) {
        log.debug("findFentrustplan all Fentrustplan instances");
        try {
            StringBuffer queryString = new StringBuffer(
                    "select count(fid) from Fentrustplan where fuser.fid=? " + " and fvirtualcointype.fid=? and ");
            queryString.append(" ( ");
            if (fstatus != null) {
                for (int i = 0; i < fstatus.length; i++) {
                    if (i == 0) {
                        queryString.append(" fstatus=? ");
                    } else {
                        queryString.append(" or fstatus=? ");
                    }
                }
            }

            queryString.append(" ) ");
            Query queryObject = getSession().createQuery(queryString.toString());
            queryObject.setParameter(0, fuser);
            queryObject.setParameter(1, fvirtualcointype);
            if (fstatus != null) {
                for (int i = 0; i < fstatus.length; i++) {
                    queryObject.setParameter(i + 2, fstatus[i]);
                }
            }

            return (Long) queryObject.list().get(0);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<Fentrustplan> findEntrustPlan(int type, int status[]) {
        log.debug("findEntrustPlan all Fentrustplan instances");
        try {
            StringBuffer queryString = new StringBuffer("from Fentrustplan where ftype=? and ");
            queryString.append(" ( ");
            if (status != null) {
                for (int i = 0; i < status.length; i++) {
                    if (i == 0) {
                        queryString.append(" fstatus=? ");
                    } else {
                        queryString.append(" or fstatus=? ");
                    }
                }
            }
            queryString.append(" ) ");
            Query queryObject = getSession().createQuery(queryString.toString());
            queryObject.setParameter(0, type);
            if (status != null) {
                for (int i = 0; i < status.length; i++) {
                    queryObject.setParameter(1 + i, status[i]);
                }
            }
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<Fentrustplan> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fentrustplan> list = null;
        log.debug("finding Fentrustplan instance with filter");
        try {
            String queryString = "from Fentrustplan " + filter;
            Query queryObject = getSession().createQuery(queryString);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Fentrustplan by filter name failed", re);
            throw re;
        }
        return list;
    }

}