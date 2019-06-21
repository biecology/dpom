package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.coa.common.cons.EntrustStatusEnum;
import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fentrust;
import com.huagu.vcoin.main.model.Fentrustlog;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fentrust entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.ruizton.main.com.ruizton.main.model.Fentrust
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FentrustDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FentrustDAO.class);
    // property constants
    public static final String FENTRUST_TYPE = "fentrustType";
    public static final String FPRIZE = "fprize";
    public static final String FAMOUNT = "famount";
    public static final String FCOUNT = "fcount";
    public static final String FSTATUS = "fstatus";

    public void save(Fentrust transientInstance) {
        log.debug("saving Fentrust instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fentrust persistentInstance) {
        log.debug("deleting Fentrust instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fentrust findById(java.lang.Integer id) {
        log.debug("getting Fentrust instance with id: " + id);
        try {
            Fentrust instance = (Fentrust) getSession().get("com.huagu.vcoin.main.model.Fentrust", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fentrust> findByExample(Fentrust instance) {
        log.debug("finding Fentrust instance by example");
        try {
            List<Fentrust> results = getSession().createCriteria("com.huagu.vcoin.main.model.Fentrust")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fentrust instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fentrust as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fentrust> findByFentrustType(Object fentrustType) {
        return findByProperty(FENTRUST_TYPE, fentrustType);
    }

    public List<Fentrust> findByFprize(Object fprize) {
        return findByProperty(FPRIZE, fprize);
    }

    public List<Fentrust> findByFamount(Object famount) {
        return findByProperty(FAMOUNT, famount);
    }

    public List<Fentrust> findByFcount(Object fcount) {
        return findByProperty(FCOUNT, fcount);
    }

    public List<Fentrust> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List findAll() {
        log.debug("finding all Fentrust instances");
        try {
            String queryString = "from Fentrust";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fentrust merge(Fentrust detachedInstance) {
        log.debug("merging Fentrust instance");
        try {
            Fentrust result = (Fentrust) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fentrust instance) {
        log.debug("attaching dirty Fentrust instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fentrust instance) {
        log.debug("attaching clean Fentrust instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public Fentrustlog findLatestDeal(int coinTypeId) {
        log.debug("findLatestSuccessDeal all Fentrust instances");
        try {
            String queryString = "from Fentrustlog where  ftrademapping.fid=? order by fid desc";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, coinTypeId);

            queryObject.setFirstResult(0);
            queryObject.setMaxResults(1);
            List<Fentrustlog> fentrustlogs = queryObject.list();
            if (fentrustlogs.size() > 0) {
                return fentrustlogs.get(0);
            } else {
                return null;
            }
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<Fentrust> findLatestSuccessDeal(int coinTypeId, int fentrustType, int count) {
        log.debug("findLatestSuccessDeal all Fentrust instances");
        try {
            String queryString = "from Fentrust where fstatus=? and ftrademapping.fid=? and fentrustType=?  order by flastUpdatTime desc";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, EntrustStatusEnum.AllDeal);
            queryObject.setParameter(1, coinTypeId);
            queryObject.setParameter(2, fentrustType);

            queryObject.setFirstResult(0);
            queryObject.setMaxResults(count);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<Fentrust> findAllGoingFentrust(int coinTypeId, int fentrustType, boolean isLimit) {
        log.debug("findAllGoingFentrust all Fentrust instances");
        try {
            String queryString = "from Fentrust where (fstatus=? or fstatus=?)" + " and ftrademapping.fid=?"
                    + " and fentrustType=? and fisLimit=?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, EntrustStatusEnum.Going);
            queryObject.setParameter(1, EntrustStatusEnum.PartDeal);
            queryObject.setParameter(2, coinTypeId);
            queryObject.setParameter(3, fentrustType);
            queryObject.setParameter(4, isLimit);

            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<Fentrust> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fentrust> list = null;
        log.debug("finding Fentrust instance with filter");
        try {
            String queryString = "from Fentrust " + filter;
            Query queryObject = getSession().createQuery(queryString);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Fentrust by filter name failed", re);
            throw re;
        }
        return list;
    }

    public List<Fentrust> getFentrustHistory(int fuid, int fvirtualCoinTypeId, int[] entrust_type, int first_result,
            int max_result, String order, int entrust_status[]) {

        log.debug("getFentrustHistory all Fentrust instances");
        try {
            StringBuffer queryString = new StringBuffer(
                    "from Fentrust where fuser.fid=? " + " and ftrademapping.fid=? and  ");

            if (entrust_type != null) {
                queryString.append(" ( ");
                for (int i = 0; i < entrust_type.length; i++) {
                    if (i == 0) {
                        queryString.append(" fentrustType=? ");
                    } else {
                        queryString.append(" or fentrustType=? ");
                    }
                }
                queryString.append(" ) and ");
            }

            if (entrust_status != null) {
                queryString.append(" ( ");
                for (int i = 0; i < entrust_status.length; i++) {
                    if (i == 0) {
                        queryString.append(" fstatus=? ");
                    } else {
                        queryString.append(" or fstatus=? ");
                    }
                }
                queryString.append(" ) and ");
            }

            queryString.append(" 1=1 ");

            if (order != null) {
                queryString.append(" order by " + order);
            }

            Query queryObject = getSession().createQuery(queryString.toString());
            queryObject.setParameter(0, fuid);
            queryObject.setParameter(1, fvirtualCoinTypeId);

            int index = 2;
            if (entrust_type != null) {
                for (int i = 0; i < entrust_type.length; i++) {
                    queryObject.setParameter(index + i, entrust_type[i]);
                }
                index += entrust_type.length;
            }

            if (entrust_status != null) {
                for (int i = 0; i < entrust_status.length; i++) {
                    queryObject.setParameter(index + i, entrust_status[i]);
                }
            }

            queryObject.setFirstResult(first_result);
            queryObject.setMaxResults(max_result);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }

    }

    public int getFentrustHistoryCount(int fuid, int fvirtualCoinTypeId, int[] entrust_type, int entrust_status[]) {

        log.debug("getFentrustHistory all Fentrust instances");
        try {
            StringBuffer queryString = new StringBuffer(
                    "select count(fid) from Fentrust where fuser.fid=? " + " and ftrademapping.fid=? and  ");

            if (entrust_type != null) {
                queryString.append(" ( ");
                for (int i = 0; i < entrust_type.length; i++) {
                    if (i == 0) {
                        queryString.append(" fentrustType=? ");
                    } else {
                        queryString.append(" or fentrustType=? ");
                    }
                }
                queryString.append(" ) and ");
            }

            if (entrust_status != null) {
                queryString.append(" ( ");
                for (int i = 0; i < entrust_status.length; i++) {
                    if (i == 0) {
                        queryString.append(" fstatus=? ");
                    } else {
                        queryString.append(" or fstatus=? ");
                    }
                }
                queryString.append(" ) and ");
            }

            queryString.append(" 1=1 ");

            Query queryObject = getSession().createQuery(queryString.toString());
            queryObject.setParameter(0, fuid);
            queryObject.setParameter(1, fvirtualCoinTypeId);

            int index = 2;
            if (entrust_type != null) {
                for (int i = 0; i < entrust_type.length; i++) {
                    queryObject.setParameter(index + i, entrust_type[i]);
                }
                index += entrust_type.length;
            }

            if (entrust_status != null) {
                for (int i = 0; i < entrust_status.length; i++) {
                    queryObject.setParameter(index + i, entrust_status[i]);
                }
            }

            long l = (Long) queryObject.list().get(0);
            return (int) l;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }

    }

    public List<Fentrust> getFentrustHistory(int fuid, int fvirtualCoinTypeId, int[] entrust_type, int first_result,
            int max_result, String order, int entrust_status[], Date beginDate, Date endDate) {

        log.debug("getFentrustHistory all Fentrust instances");
        try {
            StringBuffer queryString = new StringBuffer(
                    "from Fentrust where fuser.fid=? " + " and ftrademapping.fid=? and  ");

            if (beginDate != null && endDate != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                queryString.append(" fcreateTime>='" + sdf.format(beginDate) + "' and fcreateTime<='"
                        + sdf.format(endDate) + "' and ");
            }

            if (entrust_type != null) {
                queryString.append(" ( ");
                for (int i = 0; i < entrust_type.length; i++) {
                    if (i == 0) {
                        queryString.append(" fentrustType=? ");
                    } else {
                        queryString.append(" or fentrustType=? ");
                    }
                }
                queryString.append(" ) and ");
            }

            if (entrust_status != null) {
                queryString.append(" ( ");
                for (int i = 0; i < entrust_status.length; i++) {
                    if (i == 0) {
                        queryString.append(" fstatus=? ");
                    } else {
                        queryString.append(" or fstatus=? ");
                    }
                }
                queryString.append(" ) and ");
            }

            queryString.append(" 1=1 ");

            if (order != null) {
                queryString.append(" order by " + order);
            }

            Query queryObject = getSession().createQuery(queryString.toString());
            queryObject.setParameter(0, fuid);
            queryObject.setParameter(1, fvirtualCoinTypeId);

            int index = 2;
            if (entrust_type != null) {
                for (int i = 0; i < entrust_type.length; i++) {
                    queryObject.setParameter(index + i, entrust_type[i]);
                }
                index += entrust_type.length;
            }

            if (entrust_status != null) {
                for (int i = 0; i < entrust_status.length; i++) {
                    queryObject.setParameter(index + i, entrust_status[i]);
                }
            }

            queryObject.setFirstResult(first_result);
            queryObject.setMaxResults(max_result);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }

    }

    public int getFentrustHistoryCount(int fuid, int fvirtualCoinTypeId, int[] entrust_type, int entrust_status[],
            Date beginDate, Date endDate) {

        log.debug("getFentrustHistory all Fentrust instances");
        try {
            StringBuffer queryString = new StringBuffer(
                    "select count(fid) from Fentrust where fuser.fid=? " + " and ftrademapping.fid=? and  ");

            if (beginDate != null && endDate != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                queryString.append(" fcreateTime>='" + sdf.format(beginDate) + "' and fcreateTime<='"
                        + sdf.format(endDate) + "' and ");
            }

            if (entrust_type != null) {
                queryString.append(" ( ");
                for (int i = 0; i < entrust_type.length; i++) {
                    if (i == 0) {
                        queryString.append(" fentrustType=? ");
                    } else {
                        queryString.append(" or fentrustType=? ");
                    }
                }
                queryString.append(" ) and ");
            }

            if (entrust_status != null) {
                queryString.append(" ( ");
                for (int i = 0; i < entrust_status.length; i++) {
                    if (i == 0) {
                        queryString.append(" fstatus=? ");
                    } else {
                        queryString.append(" or fstatus=? ");
                    }
                }
                queryString.append(" ) and ");
            }

            queryString.append(" 1=1 ");

            Query queryObject = getSession().createQuery(queryString.toString());
            queryObject.setParameter(0, fuid);
            queryObject.setParameter(1, fvirtualCoinTypeId);

            int index = 2;
            if (entrust_type != null) {
                for (int i = 0; i < entrust_type.length; i++) {
                    queryObject.setParameter(index + i, entrust_type[i]);
                }
                index += entrust_type.length;
            }

            if (entrust_status != null) {
                for (int i = 0; i < entrust_status.length; i++) {
                    queryObject.setParameter(index + i, entrust_status[i]);
                }
            }

            long l = (Long) queryObject.list().get(0);
            return (int) l;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }

    }

    public List<Map> getTotalQty(int fentrustType) {
        List<Map> all = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append("select sum(ifnull(a.fleftCount,0)) total,d.fName from Fentrust a  \n");
        sql.append("left outer join ftrademapping b on a.ftrademapping=b.fId  \n");
        sql.append("LEFT OUTER JOIN fvirtualcointype c on b.fvirtualcointype1=c.fid  \n");
        sql.append("LEFT OUTER JOIN fvirtualcointype d on b.fvirtualcointype2=d.fid  \n");
        sql.append("where 1=1 and a.fstatus in(1,2) and a.fentrustType=" + fentrustType + " \n");
        sql.append("group by d.fName,c.fName  \n");
        SQLQuery queryObject = getSession().createSQLQuery(sql.toString());
        List allList = queryObject.list();
        Iterator it = allList.iterator();
        while (it.hasNext()) {
            Map map = new HashMap();
            Object[] o = (Object[]) it.next();
            map.put("total", o[0]);
            map.put("fName", o[1]);
            all.add(map);
        }
        return all;
    }

    public List<Map> getTotalQty(int fentrustType, boolean isToday) {
        List<Map> all = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        StringBuffer sql = new StringBuffer();
        sql.append("select sum(ifnull(a.ffees,0)) total,d.fName from Fentrustlog a  \n");
        sql.append("left outer join ftrademapping b on a.ftrademapping=b.fId  \n");
        sql.append("LEFT OUTER JOIN fvirtualcointype c on b.fvirtualcointype1=c.fId  \n");
        sql.append("LEFT OUTER JOIN fvirtualcointype d on b.fvirtualcointype2=d.fId  \n");
        sql.append("where a.fentrustType=" + fentrustType + "  \n");
        if (isToday) {
            sql.append("and a.fcreateTime>=curdate()  \n");
        }
        sql.append("group by d.fName,c.fName  \n");

        SQLQuery queryObject = getSession().createSQLQuery(sql.toString());
        List allList = queryObject.list();
        Iterator it = allList.iterator();
        while (it.hasNext()) {
            Map map = new HashMap();
            Object[] o = (Object[]) it.next();
            map.put("total", o[0]);
            map.put("fName", o[1]);
            all.add(map);
        }
        return all;
    }

    public double getTotalSumFees(int fentrustType, boolean isToday) {
        double last = 0d;
        StringBuffer sql = new StringBuffer();
        sql.append("select sum(ifnull(a.ffees,0)) total from Fentrustlog a  \n");
        sql.append("where a.fentrustType=" + fentrustType + "  \n");
        if (isToday) {
            sql.append("and a.fcreateTime>=curdate()  \n");
        }

        SQLQuery queryObject = getSession().createSQLQuery(sql.toString());
        List allList = queryObject.list();
        if (allList != null && allList.size() > 0 && allList.get(0) != null) {
            last = Double.valueOf(allList.get(0).toString());
        }
        return last;
    }
}