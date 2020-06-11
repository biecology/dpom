package com.vcoin.main.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huagu.vcoin.main.dao.comm.HibernateDaoSupport;
import com.huagu.vcoin.main.model.Fuser;

@Repository
public class AppDao extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FaboutDAO.class);

    public int findTradeRecordAllCount(Fuser fuser) {
        try {
            String filter = "";

            String queryString = "select count(a.a1) from "
                    + "( select fcreateTime a1,ftype  a2,0  a3,famount  a4,ffees  a5,0  a6,fstatus  a7,1  a8  from Fcapitaloperation where FUs_fId=? "
                    + "union all "
                    + "select fcreateTime a1,ftype a2,famount a3,0 a4,ffees a5,0 a6,fstatus a7,2 a8 from Fvirtualcaptualoperation where FUs_fId2=? "
                    + "union all "
                    + "select fcreateTime a1,fentrustType a2,fcount a3,famount a4,ffees a5,0 a6,fstatus a7,3 a8 from Fentrust where FUs_fId=? ) as a "
                    + filter + " order by a.a1 desc ";
            SQLQuery queryObject = getSession().createSQLQuery(queryString);
            queryObject.setParameter(0, fuser.getFid());
            queryObject.setParameter(1, fuser.getFid());
            queryObject.setParameter(2, fuser.getFid());
            Object obj = queryObject.uniqueResult();
            if (obj != null) {
                return Integer.parseInt(obj.toString());
            }
            return 0;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<Object[]> findTradeRecordAll(Fuser fuser, int firstResult, int maxResult, boolean isFY) {
        try {
            String filter = "";

            String queryString = "select a.a1,a.a2,a.a3,a.a4,a.a5,a.a6,a.a7,a.a8,a9,a10,a11,a12,a13 from "
                    + "( select fcreateTime a1,ftype  a2,0  a3,famount  a4,ffees  a5,0  a6,fstatus  a7,1  a8, 0 a9,0 a10,0 a11,0 a12,0 a13  from Fcapitaloperation where FUs_fId=? "
                    + "union all "
                    + "select fcreateTime a1,ftype a2,famount a3,0 a4,ffees a5,0 a6,fstatus a7,2 a8, 0 a9,0 a10,0 a11,fVi_fId2 a12,0 a13 from Fvirtualcaptualoperation where FUs_fId2=? "
                    + "union all "
                    + "select fcreateTime a1,fentrustType a2,fcount a3,famount a4,ffees a5,0 a6,fstatus a7,3 a8,fsuccessAmount a9,(fcount-fleftCount) a10,fentrustType a11,fVi_fId a12,0 a13 from Fentrust where FUs_fId=? ) as a "
                    + filter + " order by a.a1 desc ";
            System.out.println(queryString);
            SQLQuery queryObject = getSession().createSQLQuery(queryString);
            queryObject.setParameter(0, fuser.getFid());
            queryObject.setParameter(1, fuser.getFid());
            queryObject.setParameter(2, fuser.getFid());
            if (isFY == true) {
                queryObject.setFirstResult(firstResult);
                queryObject.setMaxResults(maxResult);
            }
            List<Object[]> list = queryObject.list();
            return list;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
}
