package ThreadTest;

public class CountThread implements Runnable {
    private Integer count = 0 ;
    @Override
    public void run() {
        increment();
    }

    public synchronized void increment(){
        System.out.println(count);
        count++;
    }
}
