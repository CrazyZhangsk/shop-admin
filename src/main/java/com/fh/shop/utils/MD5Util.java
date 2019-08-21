/**
 *  
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.fh.shop.utils;


import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * MD5工具类.
 * @author 赵彤
 * @date   2012-1-9下午3:15:25
 */
public class MD5Util {    
     /**  
     * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符,apache校验下载的文件的正确性用的就是默认的这个组合  
     */    
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6','7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };    
    protected static MessageDigest messagedigest = null;    
    static {    
        try {    
            messagedigest = MessageDigest.getInstance("MD5");    
        } catch (NoSuchAlgorithmException e) {    
            e.printStackTrace();    
        }    
    }    
    /**
     * 获取文件MD5值
     * @param file
     * @return
     * @throws IOException
     * @date   2012-1-9下午3:15:43
     */
    public static String getFileMD5String(File file) throws IOException {    
        InputStream fis;    
        fis = new FileInputStream(file);    
        byte[] buffer = new byte[1024];    
        int numRead = 0;    
        while ((numRead = fis.read(buffer)) > 0) {    
            messagedigest.update(buffer, 0, numRead);    
        }    
        fis.close();    
        return bufferToHex(messagedigest.digest());    
    }    
      
    /**
     * 密码字符串MD5加密 32位小写
     * @param str
     * @return
     * @date   2012-1-9下午3:16:04
     */
    public static String getStringMD5(String str){
        if(StringUtils.isEmpty(str)){
    		return "";
    	}
         byte[] buffer=str.getBytes();  
         messagedigest.update(buffer);  
        return bufferToHex(messagedigest.digest());  
    }  
    
    public static String bufferToHex(byte bytes[]) {    
        return bufferToHex(bytes, 0, bytes.length);    
    }    
    
    private static String bufferToHex(byte bytes[], int m, int n) {    
        StringBuffer stringbuffer = new StringBuffer(2 * n);    
        int k = m + n;    
        for (int l = m; l < k; l++) {    
            appendHexPair(bytes[l], stringbuffer);    
        }    
        return stringbuffer.toString();    
    }    
    
    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {    
        char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换    
        // 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同    
        char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换    
        stringbuffer.append(c0);    
        stringbuffer.append(c1);    
    }

    private static final String toHex(byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String hash(String s) {
        try {
            return new String(toHex(getStringMD5(s).getBytes("UTF-8")).getBytes("UTF-8"), "UTF-8");
        } catch (Exception e) {
            return s;
        }
    }

    public final static String MD5(String pwd) {
        //用于加密的字符
        char md5String[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
            byte[] btInput = pwd.getBytes();

            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(btInput);

            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                str[k++] = md5String[byte0 & 0xf];   //   F
            }

            //返回经过加密后的字符串
            return new String(str);

        } catch (Exception e) {
            return null;
        }
    }

    //测试
    public static void main(String[] args) {
//        for (int i=1, len=100; i<len; i++) {
//            System.out.println(MD5Util.getStringMD5("token_admin"));//21232f297a57a5a743894a0e4a801fc3
//        }
//		System.out.println(MD5Util.hash("123456"));//6531306164633339343962613539616262653536653035376632306638383365
        String s = UUID.randomUUID().toString();
        System.out.println(s);
        String stringMD5 = MD5Util.getStringMD5(MD5Util.getStringMD5("123")+s);
        System.out.println(stringMD5);
    }
}    