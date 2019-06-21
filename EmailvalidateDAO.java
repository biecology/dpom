package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.Enum.SendMailTypeEnum;
import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Emailvalidate;
import com.huagu.vcoin.util.Utils;

/**
 * A data access object (DAO) providing persistence and search support for
 * Emailvalidate entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ztmp.Emailvalidate
 * @author MyEclipse Persistence Tools
 */
@Repository
public class EmailvalidateDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(EmailvalidateDAO.class);
    // property constants
    public static final String FUUID = "fuuid";

    public void save(Emailvalidate transientInstance) {
        log.debug("saving Emailvalidate instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Emailvalidate persistentInstance) {
        log.debug("deleting Emailvalidate instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Emailvalidate findById(java.lang.Integer id) {
        log.debug("getting Emailvalidate instance with id: " + id);
        try {
            Emailvalidate instance = (Emailvalidate) getSession().get("com.huagu.vcoin.main.model.Emailvalidate", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Emailvalidate> findByExample(Emailvalidate instance) {
        log.debug("finding Emailvalidate instance by example");
        try {
            List<Emailvalidate> results = getSession().createCriteria("com.huagu.vcoin.main.model.Emailvalidate")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Emailvalidate instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Emailvalidate as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Emailvalidate> findByFuuid(Object fuuid) {
        return findByProperty(FUUID, fuuid);
    }

    public List findAll() {
        log.debug("finding all Emailvalidate instances");
        try {
            String queryString = "from Emailvalidate";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Emailvalidate merge(Emailvalidate detachedInstance) {
        log.debug("merging Emailvalidate instance");
        try {
            Emailvalidate result = (Emailvalidate) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Emailvalidate instance) {
        log.debug("attaching dirty Emailvalidate instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Emailvalidate instance) {
        log.debug("attaching clean Emailvalidate instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public Emailvalidate findByUidUuid(int uid, String uuid, int type) {
        log.debug("finding all findByUidUuid instances");
        try {
            String queryString = "from Emailvalidate where fuser.fid=? and fuuid=? and type=? and fcreateTime>?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, uid);
            queryObject.setParameter(1, uuid);
            queryObject.setParameter(2, type);
            queryObject.setParameter(3, new Timestamp(Utils.getTimestamp().getTime() - 15L * 60 * 1000));
            List<Emailvalidate> list = queryObject.list();
            if (list.size() == 0) {
                return null;
            } else {
                return list.get(0);
            }
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<Emailvalidate> canSendFindPwdMsg(int fid, int ev_id, String newuuid) {
        log.debug("finding all canSendFindPwdMsg instances");
        try {
            String queryString = "from Emailvalidate where fuser.fid=? and fNewUUid=? and type=? and fid=?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, fid);
            queryObject.setParameter(1, newuuid);
            queryObject.setParameter(2, SendMailTypeEnum.FindPassword);
            queryObject.setParameter(3, ev_id);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }

    }
}