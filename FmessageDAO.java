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
import com.huagu.vcoin.main.model.Fmessage;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fmessage entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .Fmessage
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FmessageDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FmessageDAO.class);
    // property constants
    public static final String FSTATUS = "fstatus";
    public static final String FTITLE = "ftitle";
    public static final String FCONTENT = "fcontent";

    public void save(Fmessage transientInstance) {
        log.debug("saving Fmessage instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fmessage persistentInstance) {
        log.debug("deleting Fmessage instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fmessage findById(java.lang.Integer id) {
        log.debug("getting Fmessage instance with id: " + id);
        try {
            Fmessage instance = (Fmessage) getSession().get("com.huagu.vcoin.main.model.Fmessage", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fmessage> findByExample(Fmessage instance) {
        log.debug("finding Fmessage instance by example");
        try {
            List<Fmessage> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fmessage")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fmessage instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fmessage as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fmessage> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List<Fmessage> findByFtitle(Object ftitle) {
        return findByProperty(FTITLE, ftitle);
    }

    public List<Fmessage> findByFcontent(Object fcontent) {
        return findByProperty(FCONTENT, fcontent);
    }

    public List findAll() {
        log.debug("finding all Fmessage instances");
        try {
            String queryString = "from Fmessage";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fmessage merge(Fmessage detachedInstance) {
        log.debug("merging Fmessage instance");
        try {
            Fmessage result = (Fmessage) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fmessage instance) {
        log.debug("attaching dirty Fmessage instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fmessage instance) {
        log.debug("attaching clean Fmessage instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public int findFmessageByParamCount(Map<String, Object> param) {
        log.debug("finding Fmessage findByParam");
        try {
            StringBuffer queryString = new StringBuffer("select count(f.fid) from Fmessage f where ");

            Object[] keys = null;
            if (param != null) {
                keys = param.keySet().toArray();
                for (int i = 0; i < keys.length; i++) {
                    queryString.append(keys[i] + "=? and ");
                }
            }

            queryString.append(" 1=1 ");

            Query queryObject = getSession().createQuery(queryString.toString());
            if (keys != null) {
                for (int i = 0; i < keys.length; i++) {
                    queryObject.setParameter(i, param.get(keys[i]));
                }
            }

            List listResult = queryObject.list();
            if (listResult == null || listResult.size() == 0) {
                return 0;
            } else {
                return (int) ((Long) listResult.get(0)).longValue();
            }
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<Fmessage> findFmessageByParam(Map<String, Object> param, int firstResult, int maxResult, String order) {
        log.debug("finding Fmessage findByParam");
        try {
            StringBuffer queryString = new StringBuffer("from Fmessage f where ");

            Object[] keys = null;
            if (param != null) {
                keys = param.keySet().toArray();
                for (int i = 0; i < keys.length; i++) {
                    queryString.append(keys[i] + "=? and ");
                }
            }

            queryString.append(" 1=1 ");
            queryString.append(" order by " + order);

            Query queryObject = getSession().createQuery(queryString.toString());
            if (keys != null) {
                for (int i = 0; i < keys.length; i++) {
                    queryObject.setParameter(i, param.get(keys[i]));
                }
            }

            queryObject.setFirstResult(firstResult);
            queryObject.setMaxResults(maxResult);
            List<Fmessage> listResult = queryObject.list();
            return listResult;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

}