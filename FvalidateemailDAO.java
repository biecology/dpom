package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fvalidateemail;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fvalidateemail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ztmp.Fvalidateemail
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FvalidateemailDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FvalidateemailDAO.class);
    // property constants
    public static final String FTITLE = "ftitle";
    public static final String FCONTENT = "fcontent";
    public static final String FSTATUS = "fstatus";

    public void save(Fvalidateemail transientInstance) {
        log.debug("saving Fvalidateemail instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fvalidateemail persistentInstance) {
        log.debug("deleting Fvalidateemail instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fvalidateemail findById(java.lang.Integer id) {
        log.debug("getting Fvalidateemail instance with id: " + id);
        try {
            Fvalidateemail instance = (Fvalidateemail) getSession().get("com.huagu.vcoin.main.model.Fvalidateemail",
                    id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fvalidateemail> findByExample(Fvalidateemail instance) {
        log.debug("finding Fvalidateemail instance by example");
        try {
            List<Fvalidateemail> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fvalidateemail")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fvalidateemail instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fvalidateemail as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fvalidateemail> findByFtitle(Object ftitle) {
        return findByProperty(FTITLE, ftitle);
    }

    public List<Fvalidateemail> findByFcontent(Object fcontent) {
        return findByProperty(FCONTENT, fcontent);
    }

    public List<Fvalidateemail> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List findAll() {
        log.debug("finding all Fvalidateemail instances");
        try {
            String queryString = "from Fvalidateemail";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fvalidateemail merge(Fvalidateemail detachedInstance) {
        log.debug("merging Fvalidateemail instance");
        try {
            Fvalidateemail result = (Fvalidateemail) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fvalidateemail instance) {
        log.debug("attaching dirty Fvalidateemail instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fvalidateemail instance) {
        log.debug("attaching clean Fvalidateemail instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}