package com.fh.shop.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

public class COSUtils {


    //上传图片到COS
    public static String upload2COS(String fileName, InputStream inputStream) {

        COSClient cosClient = null;
        try {
            cosClient = buildCosClient();
            // 指定要上传到 COS 上对象键
            String date = DateUtils.date2Str(new Date(), DateUtils.y_M_d);
            String key = date + "/" + getUUIDName(fileName);
            //设置上传文件的大小
            ObjectMetadata objectMetadata = new ObjectMetadata();
            try {
                objectMetadata.setContentLength(inputStream.available());
            } catch (IOException e) {
                e.printStackTrace();
            }
            PutObjectRequest putObjectRequest = new PutObjectRequest(SystemConstanUtil.COS_BUCKET_NAME, key, inputStream, new ObjectMetadata());
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            return SystemConstanUtil.COS_URL+ key;
        } finally {
            // 关闭客户端(关闭后台线程)
            cosClient.shutdown();
        }

    }

    //从服务器删除图片
    public static void delete(String path){
        COSClient cosClient = buildCosClient();
        cosClient.deleteObject(SystemConstanUtil.COS_BUCKET_NAME, path.replace(SystemConstanUtil.COS_URL, ""));
        // 关闭客户端(关闭后台线程)
        cosClient.shutdown();
    }

    private static COSClient buildCosClient() {
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(SystemConstanUtil.COS_APPKEY, SystemConstanUtil.COS_APPSECRET);
        // clientConfig中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者接口文档 FAQ 中说明。
        ClientConfig clientConfig = new ClientConfig(new Region(SystemConstanUtil.COS_REGION_NAME));
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }

    private static String getSuffix(String fileName) {
        int index = fileName.lastIndexOf(".");
        String suffix = fileName.substring(index);
        return suffix;
    }

    public static String getUUIDName(String fileName) {
        String uuidFileName = UUID.randomUUID().toString() + getSuffix(fileName);
        return uuidFileName;
    }
}
