package threadPool;

import java.io.*;
import java.util.UUID;

public class FileThread implements Runnable {
    @Override
    public void run() {
        charOutStream();
    }

    public static void charOutStream(){
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

    }

}
