package ThreadTest;

public class TreadInfoA {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            Thread aaa = new Thread(new ThreadInfo());
            aaa.start();
            /*aaa.sleep(10);*/
        }
        System.out.println(Thread.currentThread().getName());
    }
}
