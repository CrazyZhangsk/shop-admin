package com.fh.shop.backend.fileHandle;

import com.fh.shop.backend.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FileHandel {

    @Autowired
    private HttpServletRequest request;

    public ServerResponse uploadFile(@RequestParam MultipartFile file){

        return ServerResponse.success();
    }
}
