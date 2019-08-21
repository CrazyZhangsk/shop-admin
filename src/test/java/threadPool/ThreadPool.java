package threadPool;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,8,5,TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>());


    public static void main(String[] args) {
        for (int i = 0; i < 50 ; i++) {
            /*threadPoolExecutor.execute(new FileThread());*/
            threadPoolExecutor.execute(()->{
                String fileName = UUID.randomUUID().toString()+".txt";
                FileOutputStream out= null;
                String str="文件已创建";
                try {
                    out = new FileOutputStream("D:/test/"+fileName);
                    Thread.sleep(1000);
                    out.write(str.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (out!=null){
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
