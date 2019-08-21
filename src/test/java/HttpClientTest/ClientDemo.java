package HttpClientTest;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;

public class ClientDemo {
    /*public static void main(String[] args) {
        //打开浏览器
        CloseableHttpClient client = HttpClientBuilder.create().build();
        //发送请求
        HttpGet httpGet = new HttpGet("https://mvnrepository.com/");
        CloseableHttpResponse execute = null;
        try {
            execute = client.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            String string = EntityUtils.toString(entity, "utf-8");
            System.out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (execute!=null) {
                    execute.close();
                    execute=null;
                }
                if (client!=null){
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (httpGet!=null){
                httpGet.releaseConnection();
            }

        }
    }*/


    public static void main(String[] args) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        //发送请求
        HttpGet httpGet = new HttpGet("https://mvnrepository.com/");
        CloseableHttpResponse execute = null;
        InputStream content = null ;
        FileOutputStream outputStream = null;
        try {
            execute = client.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            content = entity.getContent();
            File file = new File("D:/stu.html");
            if (!file.exists()){
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int readLength = 0;
            while ((readLength = content.read(buffer)) > 0) {
                byte[] bytes = new byte[readLength];
                System.arraycopy(buffer, 0, bytes, 0, readLength);
                outputStream.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (outputStream!=null){
                    outputStream.close();
                }
                if (content!=null){
                    content.close();
                }
                if (execute!=null) {
                    execute.close();
                    execute = null ;
                }
                if (client!=null){
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (httpGet!=null){
                httpGet.releaseConnection();
            }

        }
    }
}
