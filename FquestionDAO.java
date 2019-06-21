package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fquestion;
import com.huagu.vcoin.util.Utils;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fquestion entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ztmp.Fquestion
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FquestionDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FquestionDAO.class);
    // property constants
    public static final String FTYPE = "ftype";
    public static final String FDESC = "fdesc";
    public static final String FTELEPHONE = "ftelephone";

    public void save(Fquestion transientInstance) {
        log.debug("saving Fquestion instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fquestion persistentInstance) {
        log.debug("deleting Fquestion instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fquestion findById(java.lang.Integer id) {
        log.debug("getting Fquestion instance with id: " + id);
        try {
            Fquestion instance = (Fquestion) getSession().get("com.huagu.vcoin.main.model.Fquestion", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fquestion> findByExample(Fquestion instance) {
        log.debug("finding Fquestion instance by example");
        try {
            List<Fquestion> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fquestion")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fquestion instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fquestion as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fquestion> findByFtype(Object ftype) {
        return findByProperty(FTYPE, ftype);
    }

    public List<Fquestion> findByFdesc(Object fdesc) {
        return findByProperty(FDESC, fdesc);
    }

    public List<Fquestion> findByFtelephone(Object ftelephone) {
        return findByProperty(FTELEPHONE, ftelephone);
    }

    public List findAll() {
        log.debug("finding all Fquestion instances");
        try {
            String queryString = "from Fquestion";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fquestion merge(Fquestion detachedInstance) {
        log.debug("merging Fquestion instance");
        try {
            Fquestion result = (Fquestion) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fquestion instance) {
        log.debug("attaching dirty Fquestion instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fquestion instance) {
        log.debug("attaching clean Fquestion instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public int findByTodayQuestionCount() {
        log.debug("finding Fquestion findByTodayQuestionCount instanc.");
        try {
            String queryString = "from Fquestion as model where model.fcreateTime > ? ";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, new Timestamp(Utils.getTimesmorning()));
            return queryObject.list().size();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public int findByParamCount(Map<String, Object> param) {
        log.debug("finding Fquestion findByParam");
        try {
            StringBuffer queryString = new StringBuffer("select count(f.fid) from Fquestion f where ");

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

    public List<Fquestion> findByParam(Map<String, Object> param, int firstResult, int maxResult, String order) {
        log.debug("finding Fquestion findByParam");
        try {
            StringBuffer queryString = new StringBuffer("from Fquestion f where ");

            Object[] keys = null;
            if (param != null) {
                keys = param.keySet().toArray();
                for (int i = 0; i < keys.length; i++) {
                    queryString.append(keys[i] + "=? and ");
                }
            }

            queryString.append(" 1=1 ");
            queryString.append(" group by fqid");
            queryString.append(" order by " + order);


            Query queryObject = getSession().createQuery(queryString.toString());
            if (keys != null) {
                for (int i = 0; i < keys.length; i++) {
                    queryObject.setParameter(i, param.get(keys[i]));
                }
            }

            queryObject.setCacheable(true);
            queryObject.setFirstResult(firstResult);
            queryObject.setMaxResults(maxResult);
            List<Fquestion> listResult = queryObject.list();
            return listResult;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<Fquestion> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fquestion> list = null;
        log.debug("finding Fquestion instance with filter");
        try {
            String queryString = "from Fquestion " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Fquestion by filter name failed", re);
            throw re;
        }
        return list;
    }

}