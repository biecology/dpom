package com.huagu.vcoin.main.controller.front;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huagu.BIB.common.cons.AppDB;
import com.huagu.BIB.common.cons.MyQuery;
import com.huagu.BIB.common.cons.Mysql;
import com.huagu.vcoin.common.JspPage;
import com.huagu.vcoin.main.Enum.CoinEnum;
import com.huagu.vcoin.main.Enum.CoinTypeEnum;
import com.huagu.vcoin.main.Enum.UserStatusEnum;
import com.huagu.vcoin.main.comm.ConstantMap;
import com.huagu.vcoin.main.comm.KeyValues;
import com.huagu.vcoin.main.controller.BaseController;
import com.huagu.vcoin.main.model.Farticle;
import com.huagu.vcoin.main.model.Farticletype;
import com.huagu.vcoin.main.model.Ftrademapping;
import com.huagu.vcoin.main.model.Fuser;
import com.huagu.vcoin.main.model.Fvirtualcointype;
import com.huagu.vcoin.main.model.Fvirtualwallet;
import com.huagu.vcoin.main.service.comm.redis.RedisConstant;
import com.huagu.vcoin.main.service.comm.redis.RedisUtil;
import com.huagu.vcoin.main.service.front.FrontOthersService;
import com.huagu.vcoin.main.service.front.FrontUserService;
import com.huagu.vcoin.main.service.front.FtradeMappingService;
import com.huagu.vcoin.main.service.front.UtilsService;
import com.huagu.vcoin.util.Utils;

import net.sf.json.JSONObject;

     //Biecology open source
	 //BIB交易所开源代码

@Controller
public class FrontIndexController extends BaseController {
    private static Logger log = Logger.getLogger(FrontIndexController.class);

    @Autowired
    private FrontUserService frontUserService;
    @Autowired
    private UtilsService utilsService;
    @Autowired
    private FtradeMappingService ftradeMappingService;
    @Autowired
    private FrontOthersService frontOtherService;
    @Autowired
    private ConstantMap constantMap;
    @Autowired
    private RedisUtil redisUtil;

	
	//Biecology    
	//BIB交易所 
    @RequestMapping(value = { "/m/index", "/index" })
    public ModelAndView index(HttpServletRequest request, @RequestParam(required = false, defaultValue = "0") int index,
            @RequestParam(required = false, defaultValue = "") String forwardUrl,
            @RequestParam(required = false, defaultValue = "0") int symbol, HttpServletResponse resp,
            @RequestParam(required = false) String menuFlag) {
        JspPage jspPage = new JspPage(request);

        /*
         * if(request.getParameter("remove-m")!=null ){
         * request.getSession().setAttribute(Mobilutils.CONS_IS_FORCE_PC, true);
         * modelAndView.setViewName("redirect:/index.html"); return modelAndView
         * ; }
         */
        List<Integer> types = new ArrayList<Integer>();
        for (int i = CoinEnum.ONE_VALUE; i <= CoinEnum.THREE_VALUE; i++) {
            types.add(i);
        }
        jspPage.add("types", types);

		// Promotion registration
        // 推广注册
        try {
            int id = 0;
            String val = request.getParameter("r");
            if (val != null && !"".equals(val)) {
                id = Integer.parseInt(request.getParameter("r"));
                if (id != 0) {
                    Fuser intro = this.frontUserService.findById(id);
                    if (intro != null) {
                        resp.addCookie(new Cookie("r", String.valueOf(id)));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		// Registration login module
		// 注册登录模块
        List<Map<String, Object>> wques = new ArrayList<>();
        if (GetCurrentUser(request) == null) {
            jspPage.addObject("forwardUrl", forwardUrl);
            jspPage.addObject("msg", "您尚未登录或者登录凭证已经过期，请重新登录!");
        } else {
            Fuser fuser = this.frontUserService.findById(GetCurrentUser(request).getFid());
            if (fuser != null && fuser.getFstatus() == UserStatusEnum.FORBBIN_VALUE) {
                CleanSession(request);
            }
            // modelAndView.addObject("times",fuser.getFscore().getFkillQty()) ;
        }

        if (index == 1) {
            RemoveSecondLoginSession(request);
        }
         
		 
		// RExchange announcement
		// 交易所公告
        List<KeyValues> articles = new ArrayList<KeyValues>();
        List<Farticletype> farticletypes = this.frontOtherService.findFarticleTypeAll();
        for (int i = 0; i < farticletypes.size(); i++) {
            KeyValues keyValues = new KeyValues();
            Farticletype farticletype = farticletypes.get(i);
            List<Farticle> farticles = this.frontOtherService.findFarticle(farticletype.getFid(), 0, 6);
            keyValues.setKey(farticletype);
            keyValues.setValue(farticles);
            articles.add(keyValues);
        }
        jspPage.addObject("articles", articles);

        try (Mysql mysql = new Mysql()) {
            // Query French Currency Type
			// 查询法币种类
            MyQuery ds = new MyQuery(mysql);
            ds.add("select * from %s v  ", "fvirtualcointype");
            ds.add("where v.fId in(select fvirtualcointype1 from %s", "ftrademapping");
            ds.add("where fstatus=1 group by fvirtualcointype1)");
            ds.open();

			
            List<Map<String, String>> coins = new ArrayList<>();
            while (ds.fetch()) {
                Map<String, String> coinMap = new HashMap<>();
                coinMap.put("fShortName", ds.getString("fShortName"));
                coins.add(coinMap);
            }
            if (GetCurrentUser(request) != null) {
                Fuser fuser = this.frontUserService.findById(GetCurrentUser(request).getFid());
                MyQuery cds = new MyQuery(mysql);
                cds.add("select fqid,isallsys,fuid from %s fq ", AppDB.fquestion);
                cds.add(" where 1=1 ");
                if (fuser != null) {
                    cds.add(" and fq.fuid = '%s'", fuser.getFid());
                }
                cds.add(" and fq.isallsys = '1' group by fq.fqid ");
                cds.open();
                while (cds.fetch()) {
                    Map<String, Object> ques = new HashMap<String, Object>();
                    ques.put("isallsys", cds.getString("isallsys"));
                    wques.add(ques);
                }
            }
            jspPage.add("wques", 0);
            if (wques.size() > 0) {
                jspPage.add("wques", "1");
            }

            jspPage.add("coins", coins);
        }

		
		
        Map<Fvirtualcointype, List<Ftrademapping>> fMap = new TreeMap<Fvirtualcointype, List<Ftrademapping>>(
                new Comparator<Fvirtualcointype>() {

                    @Override
                    public int compare(Fvirtualcointype o1, Fvirtualcointype o2) {
                        Integer type1 = Integer.parseInt(o1.getFtype() + "");
                        Integer type2 = Integer.parseInt(o2.getFtype() + "");
                        if (o1.getFtype() == o2.getFtype()) {
                            return o1.getFid().compareTo(o2.getFid());
                        } else {
                            return type1.compareTo(type2);
                        }
                    }
                });
        List<Fvirtualcointype> fbs = this.utilsService.list(0, 0, " where ftype=? or ftype=? order by fid asc ", false,
                Fvirtualcointype.class, CoinTypeEnum.FB_CNY_VALUE, CoinTypeEnum.FB_COIN_VALUE);
        log.info("fbs.size: " + fbs.size());
        for (Fvirtualcointype coinItem : fbs) {
            List<Ftrademapping> ftrademappings = this.ftradeMappingService.findActiveTradeMappingByFB(coinItem);
            if (ftrademappings.size() > 0) {
                fMap.put(coinItem, ftrademappings);
            } else {
                log.info("coin: " + coinItem.getFname());
            }
        }
        jspPage.add("fMap", fMap);

        int isIndex = 1;
        jspPage.add("isIndex", isIndex);

        try {
            int alert = 1;
            Cookie cs[] = request.getCookies();
            if (cs != null) {
                for (Cookie cookie : cs) {
                    if (cookie.getName().endsWith("alert")) {
                        alert = 0;
                        break;
                    }
                }
            } else {
                log.warn("request.getCookies() is null");
            }
            if (alert == 1) {
                resp.addCookie(new Cookie("alert", String.valueOf(1)));
            }
            jspPage.add("alert", alert);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (isLogon(request)) {
            log.info(getUserId());
			
			// Estimated total assets
            // NY + Frozen CNY + (Currency + Frozen Currency)* Maximum Purchase Price
            // 估计总资产
            // CNY+冻结CNY+（币种+冻结币种）*最高买价
            double totalCapital = 0F;
			
			// User Wallet
            // 用户钱包
            Mysql handle = new Mysql();
            MyQuery ds = new MyQuery(handle);
            ds.add("select * from  %s", AppDB.Fuser);
            ds.add(" where fId = %d", getUserId());
            ds.open();
            if (ds.eof()) {
                removeAdminSession(request);
                CleanSession(request);
                RemoveSecondLoginSession(request);
                jspPage.setJspFile("front/user/sub_user_login");
                return jspPage;
            }
            Fvirtualwallet fwallet = this.frontUserService.findWalletByUser(getUserId());
            jspPage.add("fwallet", fwallet);
            if (fwallet == null) {
                log.error("没有取到用户钱包数据");
            } else {
				// Virtual wallet
                // 虚拟钱包
                Map<Integer, Fvirtualwallet> fvirtualwallets = this.frontUserService
                        .findVirtualWallet(GetCurrentUser(request).getFid());
                jspPage.add("fvirtualwallets", fvirtualwallets);
                totalCapital += fwallet.getFtotal() + fwallet.getFfrozen();
                Map<Integer, Integer> tradeMappings = (Map) this.constantMap.get("tradeMappings");
                for (Map.Entry<Integer, Fvirtualwallet> entry : fvirtualwallets.entrySet()) {
                    if (entry.getValue().getFvirtualcointype().getFtype() == CoinTypeEnum.FB_CNY_VALUE)
                        continue;
                    int coinId = entry.getValue().getFvirtualcointype().getFid();
                    Integer tmp2 = tradeMappings.get(coinId);
                    if (tmp2 != null) {
                        String rkey = RedisConstant.getLatestDealPrizeKey(tmp2);
                        double latestDealPrize = this.redisUtil.getDouble(rkey);
                        Fvirtualwallet obj = entry.getValue();
                        totalCapital += (obj.getFfrozen() + obj.getFtotal()) * latestDealPrize;
                    } else {
                        log.warn(String.format("coinId: %s is null", coinId));
                    }
                }
            }
            jspPage.add("totalCapitalTrade", Utils.getDouble(totalCapital, 2));
        }
        request.getSession().setAttribute("menuFlag", menuFlag);
        jspPage.setJspFile("front/index");
        return jspPage;
    }

    /**
	   BIB EXchange Frontpage
     * BIB交易所首页
     */
    @RequestMapping(value = { "/indexBitbs", "/m/trade/tradeIndex" })
    public ModelAndView indexConF(HttpServletRequest request,
            @RequestParam(required = false, defaultValue = "0") int index,
            @RequestParam(required = false, defaultValue = "") String forwardUrl,
            @RequestParam(required = false, defaultValue = "0") int symbol, HttpServletResponse resp,
            @RequestParam(required = false) String menuFlag) {
        request.getSession().setAttribute("menuFlag", menuFlag);
        JspPage modelAndView = new JspPage(request);

        /*
         * if(request.getParameter("remove-m")!=null ){//moo
         * request.getSession().setAttribute(Mobilutils.CONS_IS_FORCE_PC, true);
         * modelAndView.setViewName("redirect:/index.html"); return modelAndView
         * ; }
         */
        List<Integer> types = new ArrayList<Integer>();
        for (int i = CoinEnum.ONE_VALUE; i <= CoinEnum.THREE_VALUE; i++) {
            types.add(i);
        }
        modelAndView.addObject("types", types);

        // Promotion registration
		// 推广注册
        try {
            int id = 0;
            id = Integer.parseInt(request.getParameter("r"));
            if (id != 0) {
                Fuser intro = this.frontUserService.findById(id);
                if (intro != null) {
                    resp.addCookie(new Cookie("r", String.valueOf(id)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GetCurrentUser(request) == null) {
            modelAndView.addObject("forwardUrl", forwardUrl);
        } else {

            Fuser fuser = this.frontUserService.findById(GetCurrentUser(request).getFid());
            if (fuser.getFstatus() == UserStatusEnum.FORBBIN_VALUE) {
                CleanSession(request);
            }
            // modelAndView.addObject("times",fuser.getFscore().getFkillQty()) ;
        }

        if (index == 1) {
            RemoveSecondLoginSession(request);
        }

        List<KeyValues> articles = new ArrayList<KeyValues>();
        List<Farticletype> farticletypes = this.frontOtherService.findFarticleTypeAll();
        for (int i = 0; i < farticletypes.size(); i++) {
            KeyValues keyValues = new KeyValues();
            Farticletype farticletype = farticletypes.get(i);
            List<Farticle> farticles = this.frontOtherService.findFarticle(farticletype.getFid(), 0, 6);
            keyValues.setKey(farticletype);
            keyValues.setValue(farticles);
            articles.add(keyValues);
        }

        modelAndView.addObject("articles", articles);

        Map<Fvirtualcointype, List<Ftrademapping>> fMap = new TreeMap<Fvirtualcointype, List<Ftrademapping>>(
                new Comparator<Fvirtualcointype>() {

                    @Override
                    public int compare(Fvirtualcointype o1, Fvirtualcointype o2) {
                        Integer type1 = Integer.parseInt(o1.getFtype() + "");
                        Integer type2 = Integer.parseInt(o2.getFtype() + "");
                        if (o1.getFtype() == o2.getFtype()) {
                            return o1.getFid().compareTo(o2.getFid());
                        } else {
                            return type1.compareTo(type2);
                        }
                    }
                });
        List<Fvirtualcointype> fbs = this.utilsService.list(0, 0, " where ftype=? or ftype=? order by fid asc ", false,
                Fvirtualcointype.class, CoinTypeEnum.FB_CNY_VALUE, CoinTypeEnum.FB_COIN_VALUE);
        for (Fvirtualcointype fvirtualcointype : fbs) {
            List<Ftrademapping> ftrademappings = this.ftradeMappingService.findActiveTradeMappingByFB(fvirtualcointype);
            if (ftrademappings.size() > 0) {
                fMap.put(fvirtualcointype, ftrademappings);
            }
        }
        modelAndView.addObject("fMap", fMap);

        int isIndex = 1;
        modelAndView.addObject("isIndex", isIndex);

        try {
            int alert = 1;
            Cookie cs[] = request.getCookies();
            for (Cookie cookie : cs) {
                if (cookie.getName().endsWith("alert")) {
                    alert = 0;
                    break;
                }
            }
            if (alert == 1) {
                resp.addCookie(new Cookie("alert", String.valueOf(1)));
            }
            modelAndView.addObject("alert", alert);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GetCurrentUser(request) != null) {
            
			// USER Wallet
			// 用户钱包
            Mysql handle = new Mysql();
            MyQuery ds = new MyQuery(handle);
            ds.add("select * from  %s", AppDB.Fuser);
            ds.add(" where fId = %d", getUserId());
            ds.open();
            if (ds.eof()) {
                removeAdminSession(request);
                CleanSession(request);
                RemoveSecondLoginSession(request);
                modelAndView.setJspFile("front/user/sub_user_login");
                return modelAndView;
            }
            Fvirtualwallet fwallet = this.frontUserService.findWalletByUser(GetCurrentUser(request).getFid());
            modelAndView.addObject("fwallet", fwallet);
			// Virtual wallet
            // 虚拟钱包
            Map<Integer, Fvirtualwallet> fvirtualwallets = this.frontUserService
                    .findVirtualWallet(GetCurrentUser(request).getFid());
            modelAndView.addObject("fvirtualwallets", fvirtualwallets);
            
            // Estimated total assets
            // NY + Frozen CNY + (Currency + Frozen Currency)* Maximum Purchase Price
			// 估计总资产
            // CNY+冻结CNY+（币种+冻结币种）*最高买价
            double totalCapital = 0F;
            totalCapital += fwallet.getFtotal() + fwallet.getFfrozen();
            Map<Integer, Integer> tradeMappings = (Map) this.constantMap.get("tradeMappings");
            for (Map.Entry<Integer, Fvirtualwallet> entry : fvirtualwallets.entrySet()) {
                if (entry.getValue().getFvirtualcointype().getFtype() == CoinTypeEnum.FB_CNY_VALUE)
                    continue;
                try {
                    double latestDealPrize = (Double) this.redisUtil.get(RedisConstant
                            .getLatestDealPrizeKey(tradeMappings.get(entry.getValue().getFvirtualcointype().getFid())));
                    totalCapital += (entry.getValue().getFfrozen() + entry.getValue().getFtotal()) * latestDealPrize;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            modelAndView.addObject("totalCapitalTrade", Utils.getDouble(totalCapital, 2));
        }
        String uri = request.getRequestURI();
        if (!uri.startsWith("/m/")) {
            modelAndView.setJspFile("front/indexBitbs");
        } else {
            modelAndView.setJspFile("/front/trade/tradeIndex");
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = {"/index/ctcStatus1", "/m/index/ctcStatus1"}, produces = { JsonEncode })
    public String buyBtcSubmit(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();

        try (Mysql mysql = new Mysql()) {
            MyQuery ds = new MyQuery(mysql);
            ds.add("select v.*,ft.fid as coinType from %s v", "fvirtualcointype");
            ds.add("left join %s ft on v.fId=ft.fvirtualcointype1", "ftrademapping");
            ds.add("where v.fId in(select fvirtualcointype1 from %s", "ftrademapping");
            ds.add("where fstatus=1 group by fvirtualcointype1)");
            ds.add("group by v.fid");
            ds.open();

            while (ds.fetch()) {
                Map<String, String> coinMap = new HashMap<>();
                coinMap.put("coinType", ds.getString("coinType"));
                coinMap.put("fShortName", ds.getString("fShortName"));
                jsonObject.put(ds.getInt("coinType"), coinMap);
            }
        }
        return jsonObject.toString();
    }
    @ResponseBody
    @RequestMapping(value = { "/index/ctcMenu", "/m/index/ctcMenu" }, produces = { JsonEncode })
    public String ctcMenu(HttpServletRequest request) {
     JSONObject jsonObject = new JSONObject();
     try(Mysql mysql = new Mysql()){
            MyQuery ds = new MyQuery(mysql);
            ds.add("select fId,fShortName,fctcSellPrice,fctcBuyPrice,fcurrentCNY from %s", AppDB.fvirtualcointype);
            ds.add(" where fstatus=1 and fisOtcCoin = 1");
            ds.open();
         while(ds.fetch()){
                Map<String, String> ctcMap = new HashMap<>();
                ctcMap.put("coinType", ds.getString("fId"));
                ctcMap.put("fShortName", ds.getString("fShortName"));
                jsonObject.put(ds.getInt("fId"), ctcMap);
         }
     }
        return jsonObject.toString();
    }

    @ResponseBody
    @RequestMapping(value = {"/index/ctcStatus", "/m/index/ctcStatus"}, produces = { JsonEncode })
    public String ctcStatus(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String status = "";
        try (Mysql mysql = new Mysql()) {
            MyQuery ds = new MyQuery(mysql);
            ds.add("select fValue from %s", "fsystemargs");
            ds.add("where fKey = '%s'", "c2cSwitch");
            ds.setMaximum(1);
            ds.open();
            status = ds.getString("fValue");
        }
        jsonObject.accumulate("status", status);
        return jsonObject.toString();
    }
    
    /**
     * 链实时价格查询方法
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value= {"/index/atprice", "/m/index/atprice"},produces= {JsonEncode})
    public String  AtPrice(HttpServletRequest request) {
    	JSONObject jsonObject = new JSONObject();
        try (Mysql mysql = new Mysql()){
        	MyQuery ds4= new MyQuery(mysql);
            ds4.add("select fshou from fperiod where ftrademapping = 57 order by ftime desc");
            ds4.setMaximum(1);
            ds4.open();
            if (!ds4.eof())
            {
            	if(ds4.getDouble("fshou") > 0)
            	{
            		jsonObject.accumulate("price", ds4.getDouble("fshou"));
                	jsonObject.accumulate("msg", "0");
            	}
            	else
                {
                	jsonObject.accumulate("price", "0");
                	jsonObject.accumulate("msg", "1");
                }
            }
            else
            {
            	jsonObject.accumulate("price", "0");
            	jsonObject.accumulate("msg", "1");
            }
		} catch (Exception e) {
			jsonObject.accumulate("msg", "error");
			e.printStackTrace();
		}
        return jsonObject.toString();
    }
}
