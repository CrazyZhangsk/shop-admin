package ThreadTest;

import java.util.Random;

public class ReentrantReadWriterLockTest {

    public static void main(String[] args) {

        final ReadWrite readWrite = new ReadWrite();

        for (int i = 0; i < 3; i++) {  		//创建并启动3个读线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readWrite.put(new Random().nextInt(8));	//随机写入一个数据
                }
            }).start();
        }

        for(int i=0;i<9;i++){				//创建并启动了9个写线程
            new Thread(new Runnable() {

                @Override
                public void run() {
                    readWrite.get();

                }
            }).start();
        }

    }
}
