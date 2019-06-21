package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.Enum.CapitalOperationOutStatus;
import com.huagu.vcoin.main.Enum.CapitalOperationTypeEnum;
import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fcapitaloperation;
import com.huagu.vcoin.main.model.Fuser;
import com.huagu.vcoin.util.Utils;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fcapitaloperation entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ztmp.Fcapitaloperation
 * @author MyEclipse Persistence Tools
 */

@Repository
public class FcapitaloperationDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FcapitaloperationDAO.class);
    // property constants
    public static final String FAMOUNT = "famount";
    public static final String FTYPE = "ftype";
    public static final String FSTATUS = "fstatus";
    public static final String FREMITTANCE_TYPE = "fremittanceType";
    public static final String FREMARK = "fremark";

    public void save(Fcapitaloperation transientInstance) {
        log.debug("saving Fcapitaloperation instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fcapitaloperation persistentInstance) {
        log.debug("deleting Fcapitaloperation instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fcapitaloperation findById(java.lang.Integer id) {
        log.debug("getting Fcapitaloperation instance with id: " + id);
        try {
            Fcapitaloperation instance = (Fcapitaloperation) getSession()
                    .get("com.huagu.vcoin.main.model.Fcapitaloperation", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fcapitaloperation> findByExample(Fcapitaloperation instance) {
        log.debug("finding Fcapitaloperation instance by example");
        try {
            List<Fcapitaloperation> results = getSession()
                    .createCriteria("com.huagu.vcoin.main.model.Fcapitaloperation").add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fcapitaloperation instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fcapitaloperation as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fcapitaloperation> findByFamount(Object famount) {
        return findByProperty(FAMOUNT, famount);
    }

    public List<Fcapitaloperation> findByFtype(Object ftype) {
        return findByProperty(FTYPE, ftype);
    }

    public List<Fcapitaloperation> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List<Fcapitaloperation> findByFremittanceType(Object fremittanceType) {
        return findByProperty(FREMITTANCE_TYPE, fremittanceType);
    }

    public List<Fcapitaloperation> findByFremark(Object fremark) {
        return findByProperty(FREMARK, fremark);
    }

    public List findAll() {
        log.debug("finding all Fcapitaloperation instances");
        try {
            String queryString = "from Fcapitaloperation";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fcapitaloperation merge(Fcapitaloperation detachedInstance) {
        log.debug("merging Fcapitaloperation instance");
        try {
            Fcapitaloperation result = (Fcapitaloperation) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fcapitaloperation instance) {
        log.debug("attaching dirty Fcapitaloperation instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fcapitaloperation instance) {
        log.debug("attaching clean Fcapitaloperation instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List findByParam(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fcapitaloperation> list = null;
        log.debug("finding Fcapitaloperation instance with filter");
        try {
            String queryString = "from Fcapitaloperation " + filter;
            Query queryObject = getSession().createQuery(queryString);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Fcapitaloperation by filter name failed", re);
            throw re;
        }
        return list;
    }

    public int findByParamCount(Map<String, Object> param) {
        log.debug("finding Fcapitaloperation findByParam");
        try {
            StringBuffer queryString = new StringBuffer("select count(f.fid) from Fcapitaloperation f where ");

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

    public List<Fcapitaloperation> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fcapitaloperation> list = null;
        log.debug("finding Fcapitaloperation instance with filter");
        try {
            String queryString = "from Fcapitaloperation " + filter;
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setCacheable(true);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Fcapitaloperation by filter name failed", re);
            throw re;
        }
        return list;
    }

    public boolean updateCapital(String capitalSelectSQL, String capitalUpdateSQL) throws Exception {
        Query queryObject = getSession().createSQLQuery(capitalSelectSQL);
        if (queryObject.list().size() != 1) {
            throw new Exception("Data modified");
        }
        SQLQuery sQLQuery = getSession().createSQLQuery(capitalUpdateSQL);
        sQLQuery.executeUpdate();
        return true;
    }

    public boolean updateWallet(String walletSelectSQL, String walletUpdateSQL) throws Exception {
        Query queryObject = getSession().createSQLQuery(walletSelectSQL);
        if (queryObject.list().size() != 1) {
            throw new Exception("Data modified");
        }
        SQLQuery sQLQuery = getSession().createSQLQuery(walletUpdateSQL);
        sQLQuery.executeUpdate();
        return true;
    }

    public Map getTotalAmountIn(int type, String fstatus, boolean isToday) {
        Map map = new HashMap();
        StringBuffer sql = new StringBuffer();
        sql.append("select 'The yuan',IFNULL(sum(famount),0) totalAmount from Fcapitaloperation where fType=" + type
                + " and fstatus in" + fstatus + " \n");
        if (isToday) {
            // sql.append("and DATE_FORMAT(fLastUpdateTime,'%Y-%m-%d') =
            // DATE_FORMAT(now(),'%Y-%m-%d') \n");
            sql.append("and fLastUpdateTime >= curdate()  \n");
        }
        // sql.append("group by fRemittanceType \n");
        SQLQuery queryObject = getSession().createSQLQuery(sql.toString());
        List allList = queryObject.list();
        if (allList != null && allList.size() > 0 && allList.get(0) != null) {
            for (int i = 0; i < allList.size(); i++) {
                Object[] o = (Object[]) allList.get(i);
                map.put(o[0], o[1]);
            }
        }
        return map;
    }

    public Map getTotalAmount(int type, String fstatus, boolean isToday) {
        Map map = new HashMap();
        StringBuffer sql = new StringBuffer();
        sql.append("select sum(famount) totalAmount from Fcapitaloperation where fType=" + type + " and fstatus in"
                + fstatus + " \n");
        if (isToday) {
            sql.append("and fLastUpdateTime >= curdate()  \n");
        }
        SQLQuery queryObject = getSession().createSQLQuery(sql.toString());
        List allList = queryObject.list();
        if (allList != null && allList.size() > 0) {
            map.put("totalAmount", allList.get(0));
        }
        return map;
    }

    public List getTotalGroup(String filter) {
        Map map = new HashMap();
        StringBuffer sql = new StringBuffer();
        sql.append(
                "select round(sum(famount),0) totalAmount,DATE_FORMAT(fLastUpdateTime,'%Y-%m-%d') fcreateTime from Fcapitaloperation \n");
        sql.append(filter);
        sql.append("group by DATE_FORMAT(fLastUpdateTime,'%Y-%m-%d') \n");
        SQLQuery queryObject = getSession().createSQLQuery(sql.toString());
        return queryObject.list();
    }

    public int getTodayCnyWithdrawTimes(Fuser fuser) {
        log.debug("getTodayCnyWithdrawTimes all Fcapitaloperation instances");
        try {
            String queryString = "select count(*) from Fcapitaloperation where fuser.fid=? " + "and fcreateTime>? "
                    + "and ftype=? " + "and fstatus!=?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, fuser.getFid());
            queryObject.setParameter(1, new Date(Utils.getTimesmorning()));
            queryObject.setParameter(2, CapitalOperationTypeEnum.RMB_OUT);
            queryObject.setParameter(3, CapitalOperationOutStatus.Cancel);
            return Integer.parseInt(queryObject.list().get(0).toString());
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List getTotalAmountForReport(String filter) {
        StringBuffer sql = new StringBuffer();
        sql.append("select sum(famount) totalAmount from Fcapitaloperation \n");
        sql.append(filter + " \n");
        SQLQuery queryObject = getSession().createSQLQuery(sql.toString());
        return queryObject.list();
    }

    public List getTotalOperationlog(String filter) {
        StringBuffer sql = new StringBuffer();
        sql.append("select sum(famount) totalAmount from Foperationlog \n");
        sql.append(filter + " \n");
        SQLQuery queryObject = getSession().createSQLQuery(sql.toString());
        return queryObject.list();
    }

    public Map getTotalAmount(int type, String fstatus, boolean isToday, boolean isFee) {
        Map map = new HashMap();
        StringBuffer sql = new StringBuffer();
        if (isFee) {
            sql.append("select sum(ffees) totalAmount from Fcapitaloperation where fType=" + type + " and fstatus in"
                    + fstatus + " \n");
        } else {
            sql.append("select sum(famount) totalAmount from Fcapitaloperation where fType=" + type + " and fstatus in"
                    + fstatus + " \n");
        }

        if (isToday) {
            sql.append("and fLastUpdateTime >= curdate()  \n");
        }
        SQLQuery queryObject = getSession().createSQLQuery(sql.toString());
        List allList = queryObject.list();
        if (allList != null && allList.size() > 0) {
            map.put("totalAmount", allList.get(0));
        }
        return map;
    }
}