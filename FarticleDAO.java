package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Farticle;

/**
 * A data access object (DAO) providing persistence and search support for
 * Farticle entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.huagu.vcoin.main.model.Farticle
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FarticleDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FarticleDAO.class);
    // property constants
    public static final String FTITLE = "ftitle";
    public static final String FKEYWORD = "fkeyword";
    public static final String FDESCRIPTION = "fdescription";
    public static final String FCONTENT = "fcontent";

    public void save(Farticle transientInstance) {
        log.debug("saving Farticle instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Farticle persistentInstance) {
        log.debug("deleting Farticle instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Farticle findById(java.lang.Integer id) {
        log.debug("getting Farticle instance with id: " + id);
        try {
            Farticle instance = (Farticle) getSession().get("com.huagu.vcoin.main.model.Farticle", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Farticle> findByExample(Farticle instance) {
        log.debug("finding Farticle instance by example");
        try {
            List<Farticle> results = getSession().createCriteria("com.huagu.vcoin.main.model.Farticle")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Farticle instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Farticle as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Farticle> findByFtitle(Object ftitle) {
        return findByProperty(FTITLE, ftitle);
    }

    public List<Farticle> findByFkeyword(Object fkeyword) {
        return findByProperty(FKEYWORD, fkeyword);
    }

    public List<Farticle> findByFdescription(Object fdescription) {
        return findByProperty(FDESCRIPTION, fdescription);
    }

    public List<Farticle> findByFcontent(Object fcontent) {
        return findByProperty(FCONTENT, fcontent);
    }

    public List findAll() {
        log.debug("finding all Farticle instances");
        try {
            String queryString = "from Farticle";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Farticle merge(Farticle detachedInstance) {
        log.debug("merging Farticle instance");
        try {
            Farticle result = (Farticle) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Farticle instance) {
        log.debug("attaching dirty Farticle instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Farticle instance) {
        log.debug("attaching clean Farticle instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Farticle> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Farticle> list = null;
        log.debug("finding Farticle instance with filter");
        try {
            String queryString = "from Farticle " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Farticle by filter name failed", re);
            throw re;
        }
        return list;
    }

    public List<Farticle> findFarticles(int[] type, int firstResult, int maxResult, String order) {
        log.debug("findFarticles all Farticle instances");
        try {
            StringBuffer queryString = new StringBuffer("from Farticle  ");

            if (type != null) {

                for (int i = 0; i < type.length; i++) {
                    if (i == 0) {
                        queryString.append(" where ftype=? ");
                    } else {
                        queryString.append(" or ftype=? ");
                    }

                }
            }
            queryString.append(" order by " + order);
            Query queryObject = getSession().createQuery(queryString.toString());
            queryObject.setCacheable(true);
            queryObject.setFirstResult(firstResult);
            queryObject.setMaxResults(maxResult);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<Farticle> findFarticle(int farticletype, int firstResult, int maxResult) {
        log.debug("findFarticle all Farticle instances");
        try {
            String queryString = "from Farticle where farticletype.fid=? order by fisding desc,id desc";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            queryObject.setParameter(0, farticletype);
            queryObject.setFirstResult(firstResult);
            queryObject.setMaxResults(maxResult);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public int findFarticleCount(int farticletype) {
        log.debug("findFarticleCount all Farticle instances");
        try {
            String queryString = "select count(*) from Farticle where farticletype.fid=? order by id desc";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            queryObject.setParameter(0, farticletype);
            long l = (Long) queryObject.list().get(0);
            return (int) l;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
}