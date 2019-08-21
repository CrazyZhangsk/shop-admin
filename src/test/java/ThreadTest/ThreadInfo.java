package ThreadTest;

public class ThreadInfo implements Runnable {
    @Override
    public void run() {
        System.out.println("当前线程为q："+Thread.currentThread().getName()+":"+System.currentTimeMillis());
    }
}
