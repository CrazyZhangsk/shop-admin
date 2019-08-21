package com.fh.shop.backend.controller.fileHandel;

import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.common.SystemConstants;
import com.fh.shop.backend.controller.BaseController;
import com.fh.shop.utils.COSUtils;
import com.fh.shop.utils.DateUtils;
import com.fh.shop.utils.FileUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.sun.corba.se.spi.orbutil.fsm.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileHandel extends BaseController {

    @Autowired
    private HttpServletRequest request;

    public static void deleteFile(String fileName){
        COSUtils.delete(fileName);
    }

    @RequestMapping("uploadFile")
    public ServerResponse uploadFile(@RequestParam MultipartFile file) {
        String uploadFileName = "";
        try {
            String filename = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            uploadFileName = COSUtils.upload2COS(filename, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  ServerResponse.success(uploadFileName);
    }
}
