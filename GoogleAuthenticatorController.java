package com.rest.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dao.entity.StockUser;
import com.dao.entity.StockUserGoogleAuthentication;
import com.dao.mapper.StockUserMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.server.constant.RedisKeyPrefix;
import com.server.enums.CodeTypeEnum;
import com.server.service.IStockUserGoogleAuthenticationService;
import com.util.*;
import com.util.exception.CommonException;
import com.util.googleAuth.GoogleAuthenticator;
import com.util.redis.IJedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author chengxuan_chen
 */
@Controller
@RequestMapping(value = {"app/googleAuth"})
@Scope(value = "prototype")
@Slf4j
public class GoogleAuthenticatorController {

    @Autowired
    IStockUserGoogleAuthenticationService googleAuthenticatorService;

    @Autowired
    StockUserMapper stockUserMapper;

    @Autowired
    private IJedisService jedisService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "genSecret",method = {RequestMethod.GET, RequestMethod.POST})
 
    @ResponseBody
    public ResultVo genSecret(HttpServletRequest request) {
        Map<String,String> map = new HashMap<>();
        String secret = GoogleAuthenticator.generateSecretKey();
        String qrcode = GoogleAuthenticator.getQRBarcodeURL("BI", "(13803837962)", secret);
//        String codeUrlImg = "localhost:8088/app/googleAuth/getAddrQrcode?url=" + qrcode;
        String s = downloadPicture(qrcode);
        map.put("secret",secret);
        map.put("qrcode",s);
     
      
        return new ResultUtil().setData(map);
    }

    /*@GetMapping(value = "getAddrQrcode")
    public void getAgentQrcode(String url,HttpServletRequest req, HttpServletResponse res)
            throws IOException, WriterException {
        ServletOutputStream stream = res.getOutputStream();
        // 图片的宽度
        int width = 200;
        // 图片的高度
        int height = 200;
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, height, width);
        // 以流的方式输出到前台,action中return null就可以
        MatrixToImageWriter.writeToStream(m, "png", stream);
    }*/

    @RequestMapping(value = "verify",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultVo verify(HttpServletRequest request, Long code, Long stockUserId, String secret, String qrcode, String telCode) {
        StockUserGoogleAuthentication stockUser = googleAuthenticatorService.selectByStockUserId(stockUserId);
        if(!"".equals(secret)&&secret!=null){
            StockUser stockUser1 = stockUserMapper.selectById(stockUserId);
          
            //commonService.checkTelToken(request,stockUser1.getTel());
            
            String codeTel = jedisService.get(RedisKeyPrefix.REGISTER_ACCOUNT_CODE + CodeTypeEnum.STATUS_5.getCode() + stockUser1.getTel());
            if(codeTel==null){
                codeTel = jedisService.get(RedisKeyPrefix.REGISTER_ACCOUNT_CODE + CodeTypeEnum.STATUS_5.getCode() + stockUser1.getEmail());
            }
            if (!codeTel.equals(telCode)) {
         
              
            }
            if (StringUtils.isBlank(codeTel)) {
             
                if(request.getHeader("language").equalsIgnoreCase("eu")){
                    throw new CommonException("verification code error");
                }
            }
            long t = System.currentTimeMillis();
            GoogleAuthenticator ga = new GoogleAuthenticator();
            //should give 5 * 30 seconds of grace...
            ga.setWindowSize(5);
            boolean r = ga.check_code(secret, code, t);
            if(r){
                StockUserGoogleAuthentication stockUserGoogleAuthentication = new StockUserGoogleAuthentication();
                stockUserGoogleAuthentication.setSecretKey(secret);
                stockUserGoogleAuthentication.setQrCode(qrcode);
                stockUserGoogleAuthentication.setCreateTime(new Date());
                stockUserGoogleAuthentication.setStockUserId(stockUserId);
                googleAuthenticatorService.insertGoogleSercet(stockUserGoogleAuthentication);
                return new ResultUtil().setSuccessMsg();
            }
            System.out.println("Check code = " + r);
            return new ResultUtil().setErrorMsg();
        }else{
            long t = System.currentTimeMillis();
            GoogleAuthenticator ga = new GoogleAuthenticator();
            //should give 5 * 30 seconds of grace...
            ga.setWindowSize(5);
            boolean r = ga.check_code(stockUser.getSecretKey(), code, t);
            String value = request.getHeader("language");
            if (value.equals("cn")) {
                return new ResultUtil().setSuccessMsg(false);
            } else if (value.equals("eu")) {
                return new ResultUtil().setSuccessMsg(true);
            }
            return null;
        }
        //secret = "ZJTAQGLVOZ7ATWH2";
        //long code = 349394;
    }

    /*
      * @param stockUserId
     * @return
     */
    @RequestMapping(value = "closeGoolgeAuth",method = {RequestMethod.GET, RequestMethod.POST})
   
    @ResponseBody
    public ResultVo closeGoolgeAuth(HttpServletRequest request, Long stockUserId, Long code, String telCode) {
        StockUser stockUser1 = stockUserMapper.selectById(stockUserId);
     
        String codeTel = jedisService.get(RedisKeyPrefix.REGISTER_ACCOUNT_CODE + CodeTypeEnum.STATUS_5.getCode() + stockUser1.getTel());
        if(codeTel==null){
            codeTel = jedisService.get(RedisKeyPrefix.REGISTER_ACCOUNT_CODE + CodeTypeEnum.STATUS_5.getCode() + stockUser1.getEmail());
        }
        if (StringUtils.isBlank(codeTel)) {
          
            if(request.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException("verification code error");
            }
        }
        if (!codeTel.equals(telCode)) {
            //throw new CommonException("验证码不正确");
           if(request.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException("verification code error");
            }
        }
        StockUserGoogleAuthentication stockUser = googleAuthenticatorService.selectByStockUserId(stockUserId);
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        //should give 5 * 30 seconds of grace...
        ga.setWindowSize(5);
        boolean r = ga.check_code(stockUser.getSecretKey(), code, t);
        if(r){
            stockUserMapper.updateRelieveGoolgeAuth(stockUserId);
            String value = request.getHeader("language");
            if (value.equals("cn")) {
                return new ResultUtil().setSuccessMsg(false);
            } else if (value.equals("eu")) {
                return new ResultUtil().setSuccessMsg(true);
            }
            return null;
        }else{
            return new ResultUtil().setErrorMsg();
        }
    }

    /**
     * @param stockUserId
     * @return
     */
    @RequestMapping(value = "openGoolgeAuth",method = {RequestMethod.GET, RequestMethod.POST})
  
    @ResponseBody
    public ResultVo openGoolgeAuth(Long stockUserId,Long code) {
        StockUserGoogleAuthentication stockUser = googleAuthenticatorService.selectByStockUserId(stockUserId);
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        //should give 5 * 30 seconds of grace...
        ga.setWindowSize(5);
        boolean r = ga.check_code(stockUser.getSecretKey(), code, t);
        if(r){
            stockUserMapper.updateGoolgeAuth(stockUserId);
            String value = request.getHeader("language");
            if (value.equals("cn")) {
                return new ResultUtil().setSuccessMsg(false);
            } else if (value.equals("eu")) {
                return new ResultUtil().setSuccessMsg(true);
            }
            return null;
        }else{
            return new ResultUtil().setErrorMsg();
        }
    }

    /**

     * @param stockUserId
     * @return
     */
    @RequestMapping(value = "relieveGoolgeAuth",method = {RequestMethod.GET, RequestMethod.POST})
   
    @ResponseBody
    public ResultVo relieveGoolgeAuth(HttpServletRequest request, Long stockUserId, Long code, String telCode) {
        StockUser stockUser1 = stockUserMapper.selectById(stockUserId);
   
        String codeTel = jedisService.get(RedisKeyPrefix.REGISTER_ACCOUNT_CODE + CodeTypeEnum.STATUS_5.getCode() + stockUser1.getTel());
        if(codeTel==null){
            codeTel = jedisService.get(RedisKeyPrefix.REGISTER_ACCOUNT_CODE + CodeTypeEnum.STATUS_5.getCode() + stockUser1.getEmail());
        }
        if (StringUtils.isBlank(codeTel)) {
            //throw new C
             if(request.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException("verification code error");
            }
        }
        if (!codeTel.equals(telCode)) {
            
            if(request.getHeader("language").equalsIgnoreCase("eu")){
                throw new CommonException("verification code error");
            }
        }
        StockUserGoogleAuthentication stockUser = googleAuthenticatorService.selectByStockUserId(stockUserId);
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        //should give 5 * 30 seconds of grace...
        ga.setWindowSize(5);
        boolean r = ga.check_code(stockUser.getSecretKey(), code, t);
        if(r){
            stockUserMapper.relieveGoolgeAuth(stockUserId);
            googleAuthenticatorService.remove(new QueryWrapper<StockUserGoogleAuthentication>().eq("stock_user_id",stockUserId));
            String value = request.getHeader("language");
            if (value.equals("cn")) {
                return new ResultUtil().setSuccessMsg(false);
            } else if (value.equals("eu")) {
                return new ResultUtil().setSuccessMsg(true);
            }
            return null;
        }else{
            return new ResultUtil().setErrorMsg();
        }
    }

    /*
     * @param stockUserId
     * @return
     */
    @RequestMapping(value = "loginGoogleAuth",method = {RequestMethod.GET, RequestMethod.POST})
  
    @ResponseBody
    public ResultVo loginGoogleAuth(Long stockUserId,Long code) {
        StockUserGoogleAuthentication stockUser = googleAuthenticatorService.selectByStockUserId(stockUserId);
        
        long t = System.currentTimeMillis();
  
        GoogleAuthenticator ga = new GoogleAuthenticator();
        //should give 5 * 30 seconds of grace...
        ga.setWindowSize(5);
        boolean r = ga.check_code(stockUser.getSecretKey(), code, t);
        String value = request.getHeader("language");
        if(r){
          
        }
  
    }

    public static void main(String[] args) {
        GoogleAuthenticator ga = new GoogleAuthenticator();
        long t = System.currentTimeMillis();
        boolean r = ga.check_code("FYNWUOLPIV5QYJBM", 478521L, t);
        System.out.println(r);
        /*String secret = GoogleAuthenticator.generateSecretKey();
        String qrcode = GoogleAuthenticator.getQRBarcodeURL("BI", "(13803837962)", secret);
      
    }

    /*public static void main(String[] args) {
        String url = "https://www.google.com/chart?chs=200x200&chld=M%7C0&cht=qr&chl=otpauth://totp/BI(13803837962)%3Fsecret%3D3AJH3ZAYP4QDFSBM";
        downloadPicture(url);
    }*/

    private String downloadPicture(String urlList) {
        URL url = null;
        int imageNumber = 0;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            UUID uuid = UUID.randomUUID();
            String imageName =  "/home/project/file/"+ uuid + ".png";
            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            byte[] context=output.toByteArray();
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
            return "/file/" + uuid + ".png";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
