package com.rest.controller;
import com.server.annotation.SystemLog;
import com.util.FileUpload;
import com.util.ResultUtil;
import com.util.ResultVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@RestController
@RequestMapping("/app/upload")
@Transactional
public class UploadController {

    @PostMapping("/file")
    public ResultVo uploadImg(HttpServletRequest request, HttpServletResponse response){
        MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
        MultipartFile imgUrl = mhs.getFile("file");// 
        try {
           
            String idCardFront = FileUpload.uploadFile(imgUrl, request);
            return new ResultUtil().setData(idCardFront) ;
        } catch (IOException e) {
      
        }
    }

    @PostMapping("/fileWen")

    public ResultVo fileWen(HttpServletRequest request, HttpServletResponse response){
        MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
        MultipartFile imgUrl = mhs.getFile("file");// 
        try {
            String idCardFront = FileUpload.uploadFile(imgUrl, request);
            return new ResultUtil().setData(idCardFront) ;
        } catch (IOException e) {
          
        }
    }
}
