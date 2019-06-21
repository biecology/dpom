package com.huagu.vcoin.main.dao;

import static org.hibernate.criterion.Example.create;

import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.huagu.vcoin.main.Enum.VirtualCapitalOperationOutStatusEnum;
import com.huagu.vcoin.main.Enum.VirtualCapitalOperationTypeEnum;
import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fuser;
import com.huagu.vcoin.main.model.Fvirtualcaptualoperation;
import com.huagu.vcoin.main.model.Fvirtualcointype;
import com.huagu.vcoin.util.Utils;

/**
 * A data access object (DAO) providing persistence and search support for
 * Fvirtualcaptualoperation entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ztmp.Fvirtualcaptualoperation
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FvirtualcaptualoperationDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FvirtualcaptualoperationDAO.class);
    // property constants
    public static final String FVI_FID = "fviFId";
    public static final String FUS_FID = "fusFId";
    public static final String FVI_FID2 = "fviFId2";
    public static final String FAMOUNT = "famount";
    public static final String FTYPE = "ftype";
    public static final String FSTATUS = "fstatus";

    public void save(Fvirtualcaptualoperation transientInstance) {
        log.debug("saving Fvirtualcaptualoperation instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Fvirtualcaptualoperation persistentInstance) {
        log.debug("deleting Fvirtualcaptualoperation instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Fvirtualcaptualoperation findById(java.lang.Integer id) {
        log.debug("getting Fvirtualcaptualoperation instance with id: " + id);
        try {
            Fvirtualcaptualoperation instance = (Fvirtualcaptualoperation) getSession()
                    .get("com.huagu.vcoin.main.model.Fvirtualcaptualoperation", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Fvirtualcaptualoperation> findByExample(Fvirtualcaptualoperation instance) {
        log.debug("finding Fvirtualcaptualoperation instance by example");
        try {
            List<Fvirtualcaptualoperation> results = getSession()
                    .createCriteria("com.huagu.vcoin.main.model.Fvirtualcaptualoperation").add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Fvirtualcaptualoperation instance with property: " + propertyName + ", value: " + value);
        try {
            String queryString = "from Fvirtualcaptualoperation as model where model." + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Fvirtualcaptualoperation> findByFviFId(Object fviFId) {
        return findByProperty(FVI_FID, fviFId);
    }

    public List<Fvirtualcaptualoperation> findByFusFId(Object fusFId) {
        return findByProperty(FUS_FID, fusFId);
    }

    public List<Fvirtualcaptualoperation> findByFviFId2(Object fviFId2) {
        return findByProperty(FVI_FID2, fviFId2);
    }

    public List<Fvirtualcaptualoperation> findByFamount(Object famount) {
        return findByProperty(FAMOUNT, famount);
    }

    public List<Fvirtualcaptualoperation> findByFtype(Object ftype) {
        return findByProperty(FTYPE, ftype);
    }

    public List<Fvirtualcaptualoperation> findByFstatus(Object fstatus) {
        return findByProperty(FSTATUS, fstatus);
    }

    public List findAll() {
        log.debug("finding all Fvirtualcaptualoperation instances");
        try {
            String queryString = "from Fvirtualcaptualoperation";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Fvirtualcaptualoperation merge(Fvirtualcaptualoperation detachedInstance) {
        log.debug("merging Fvirtualcaptualoperation instance");
        try {
            Fvirtualcaptualoperation result = (Fvirtualcaptualoperation) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Fvirtualcaptualoperation instance) {
        log.debug("attaching dirty Fvirtualcaptualoperation instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Fvirtualcaptualoperation instance) {
        log.debug("attaching clean Fvirtualcaptualoperation instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public List<Fvirtualcaptualoperation> list(int firstResult, int maxResults, String filter, boolean isFY) {
        List<Fvirtualcaptualoperation> list = null;
        log.debug("finding Fvirtualcaptualoperation instance with filter");
        try {
            String queryString = "from Fvirtualcaptualoperation " + filter;
            Query queryObject = getSession().createQuery(queryString);
            if (isFY) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResults);
            }
            list = queryObject.list();
        } catch (RuntimeException re) {
            log.error("find Fvirtualcaptualoperation by filter name failed", re);
            throw re;
        }
        return list;
    }

    public int findFvirtualcaptualoperationCount(Fuser fuser, int type[], int status[],
            Fvirtualcointype[] fvirtualcointypes, String order) {
        log.debug("findFvirtualcaptualoperation all Fvirtualcaptualoperation instances");
        try {
            StringBuffer queryString = new StringBuffer("select count(fid) from Fvirtualcaptualoperation where ");

            if (fuser != null) {
                queryString.append(" fuser.fid=? and ");
            }

            if (status != null) {
                queryString.append(" ( ");
                for (int i = 0; i < status.length; i++) {
                    if (i == 0) {
                        queryString.append(" status=? ");
                    } else {
                        queryString.append(" or status=? ");
                    }
                }
                queryString.append(" ) and ");
            }

            if (type != null) {
                queryString.append(" ( ");
                for (int i = 0; i < type.length; i++) {
                    if (i == 0) {
                        queryString.append(" ftype=? ");
                    } else {
                        queryString.append(" or ftype=? ");
                    }
                }
                queryString.append(" ) and ");
            }

            if (fvirtualcointypes != null) {
                queryString.append(" ( ");
                for (int i = 0; i < fvirtualcointypes.length; i++) {
                    if (i == 0) {
                        queryString.append(" fvirtualcointype.fid=? ");
                    } else {
                        queryString.append(" or fvirtualcointype.fid=? ");
                    }
                }
                queryString.append(" ) and ");
            }

            queryString.append(" 1=1 ");
            if (order != null) {
                queryString.append(" order by " + order);
            }

            Query queryObject = getSession().createQuery(queryString.toString());
            int index = 0;
            if (fuser != null) {
                queryObject.setParameter(0, fuser.getFid());
                index = 1;
            }
            if (status != null) {
                for (int i = 0; i < status.length; i++) {
                    queryObject.setParameter(index + i, status[i]);
                }
                index += status.length;
            }

            if (type != null) {
                for (int i = 0; i < type.length; i++) {
                    queryObject.setParameter(index + i, type[i]);
                }
                index += type.length;
            }

            if (fvirtualcointypes != null) {
                for (int i = 0; i < fvirtualcointypes.length; i++) {
                    queryObject.setParameter(index + i, fvirtualcointypes[i].getFid());
                }
            }
            long l = (Long) queryObject.list().get(0);
            return (int) l;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<Map> getTotalQty(int type, String fstatus, boolean isToday) {
        List<Map> all = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append("select sum(famount) amount,b.fName from Fvirtualcaptualoperation a left outer join \n");
        sql.append("fvirtualcointype b on a.fVi_fId2 = b.fId  where \n");
        sql.append("a.ftype=" + type + " and a.fstatus IN" + fstatus + "  \n");
        if (isToday) {
            // sql.append("and DATE_FORMAT(a.flastUpdateTime,'%Y-%m-%d') =
            // DATE_FORMAT(now(),'%Y-%m-%d') \n");
            sql.append("and a.flastUpdateTime >= curdate() \n");
        }
        sql.append("group by b.fName \n");
        SQLQuery queryObject = getSession().createSQLQuery(sql.toString());
        List allList = queryObject.list();
        Iterator it = allList.iterator();
        while (it.hasNext()) {
            Map map = new HashMap();
            Object[] o = (Object[]) it.next();
            map.put("amount", o[0]);
            map.put("fName", o[1]);
            all.add(map);
        }
        return all;
    }

    public List<Map> getTotalFees(int type, String fstatus, boolean isToday) {
        List<Map> all = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append("select sum(ffees) amount,b.fName from Fvirtualcaptualoperation a left outer join \n");
        sql.append("fvirtualcointype b on a.fVi_fId2 = b.fId  where \n");
        sql.append("a.ftype=" + type + " and a.fstatus IN" + fstatus + "  \n");
        if (isToday) {
            sql.append("and a.flastUpdateTime >= curdate() \n");
        }
        sql.append("group by b.fName \n");
        SQLQuery queryObject = getSession().createSQLQuery(sql.toString());
        List allList = queryObject.list();
        Iterator it = allList.iterator();
        while (it.hasNext()) {
            Map map = new HashMap();
            Object[] o = (Object[]) it.next();
            map.put("amount", o[0]);
            map.put("fName", o[1]);
            all.add(map);
        }
        return all;
    }

    public List getTotalGroup(String filter) {
        List all = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append(
                "select sum(a.famount) amount,DATE_FORMAT(a.flastUpdateTime,'%Y-%m-%d') fcreatetime from Fvirtualcaptualoperation a \n");
        sql.append(filter + "\n");
        sql.append("group by DATE_FORMAT(a.flastUpdateTime,'%Y-%m-%d') \n");
        SQLQuery queryObject = getSession().createSQLQuery(sql.toString());
        return queryObject.list();
    }

    public int getTodayVirtualCoinWithdrawTimes(Fuser fuser) {
        log.debug("finding all getTodayVirtualCoinWithdrawTimes instances");
        try {
            String queryString = "select count(fid) from Fvirtualcaptualoperation where fuser.fid=? "
                    + "and fcreateTime>? " + "and  ftype=? " + "and fstatus!=? ";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, fuser.getFid());
            queryObject.setParameter(1, new Timestamp(Utils.getTimesmorning()));
            queryObject.setParameter(2, VirtualCapitalOperationTypeEnum.COIN_OUT);
            queryObject.setParameter(3, VirtualCapitalOperationOutStatusEnum.Cancel);

            return Integer.parseInt(queryObject.list().get(0).toString());
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

}