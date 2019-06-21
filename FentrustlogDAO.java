package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.sql.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fentrustlog;
import com.huagu.vcoin.util.Utils;

import cn.cerc.jdb.core.TDateTime;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fentrustlog entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.ruizton.main.com.ruizton.main.model.Fentrustlog
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FentrustlogDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FentrustlogDAO.class);
    // property constants
    public static final String FAMOUNT = "famount";
    public static final String FPRIZE = "fprize";
    public static final String FCOUNT = "fcount";

    public void save(Fentrustlog transientInstance) {
        log.debug("saving Fentrustlog instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fentrustlog persistentInstance) {
        log.debug("deleting Fentrustlog instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fentrustlog findById(java.lang.Integer id) {
        log.debug("getting Fentrustlog instance with id: " + id);
        try {
            Fentrustlog instance = (Fentrustlog) getSession().get("com.huagu.vcoin.main.model.Fentrustlog", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fentrustlog> findByExample(Fentrustlog instance) {
        log.debug("finding Fentrustlog instance by example");
        try {
            List<Fentrustlog> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fentrustlog")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fentrustlog instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fentrustlog as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value, String propertyName2, Object value2) {
        log.debug("finding Fentrustlog instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fentrustlog as model where model." + propertyName + "= ? and model."
                    + propertyName2 + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            queryObject.setParameter(1, value2);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fentrustlog> findByFamount(Object famount) {
        return findByProperty(FAMOUNT, famount);
    }

    public List<Fentrustlog> findByFprize(Object fprize) {
        return findByProperty(FPRIZE, fprize);
    }

    public List<Fentrustlog> findByFcount(Object fcount) {
        return findByProperty(FCOUNT, fcount);
    }

    public List findAll() {
        log.debug("finding all Fentrustlog instances");
        try {
            String queryString = "from Fentrustlog";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fentrustlog merge(Fentrustlog detachedInstance) {
        log.debug("merging Fentrustlog instance");
        try {
            Fentrustlog result = (Fentrustlog) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fentrustlog instance) {
        log.debug("attaching dirty Fentrustlog instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fentrustlog instance) {
        log.debug("attaching clean Fentrustlog instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fentrustlog> findLatestSuccessDeal24(int coinTypeId, int hour) {
        log.debug("findLatestSuccessDeal all Fentrustlog instances");
        try {
            String queryString = "from Fentrustlog where " + "ftrademapping.fid=? and " + "isactive=? and "
                    + "fentrust!=null and " + "fcreateTime>?  " + "order by fid desc";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, coinTypeId);
            queryObject.setParameter(1, true);
            queryObject.setParameter(2, new Date(Utils.getTimestamp().getTime() - hour * 60L * 60 * 1000));
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fentrustlog getLastFpeFentrustlog(int fvirtualcointype) {
        log.debug("getLastFpeFentrustlog all Fentrustlog instances");
        try {
            String queryString = "from Fentrustlog where " + "fvirtualcointype.fid=? " + "order by fid desc";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, fvirtualcointype);
            queryObject.setFirstResult(0);
            queryObject.setMaxResults(1);
            List<Fentrustlog> fentrustlogs = queryObject.list();
            if (fentrustlogs.size() == 0) {
                return null;
            } else {
                return fentrustlogs.get(0);
            }
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List list(int firstResult, int maxResults, String filter, boolean isFY) {
        List list = null;
        log.debug("finding Fentrustlog instance with filter");
        try {
            StringBuffer sf = new StringBuffer();
            sf.append("select * from ( \n");
            sf.append("select \n");
            sf.append("c.fNickName,c.fRealName,c.floginName \n");
            sf.append(",sum(case WHEN a.fEntrustType=0 then a.fCount ELSE 0 END) totalBuy \n");
            sf.append(",sum(case WHEN a.fEntrustType=1 then a.fCount ELSE 0 END) totalSell \n");
            sf.append(
                    ",(sum(case WHEN a.fEntrustType=0 then a.fCount ELSE 0 END)+sum(case WHEN a.fEntrustType=1 then a.fCount ELSE 0 END)) total \n");
            sf.append(",date_format(a.fCreateTime,'%Y-%m-%d') createDate \n");
            sf.append("from fentrustlog a LEFT OUTER JOIN fentrust b on a.FEn_fId = b.fId \n");
            sf.append("left outer join fuser c on b.FUs_fId=c.fId \n");
            sf.append(filter + "\n");
            sf.append("GROUP BY date_format(a.fCreateTime,'%Y-%m-%d'),c.fNickName,c.fRealName,c.floginName \n");
            sf.append(")as t order by createDate,total desc");
            Query queryObject = getSession().createSQLQuery(sf.toString());
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Fentrustlog by filter name failed", re);
            throw re;
        }
        return list;
    }

    public Double getTotalTradeAmt() {
        double total = 0d;
        String sql = String.format(
                "SELECT SUM(famount) FROM fentrustlog WHERE (fCreateTime>='%s' AND fCreateTime<'%s') AND isactive=1;",
                TDateTime.Now().getDate(), TDateTime.Now().incDay(1).getDate());
        Query queryObject = getSession().createSQLQuery(sql);
        List list = queryObject.list();
        if (list != null && list.size() > 0 && list.get(0) != null) {
            total = Double.valueOf(list.get(0).toString());
        }
        return total;
    }
}