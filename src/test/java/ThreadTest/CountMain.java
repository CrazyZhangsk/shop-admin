package ThreadTest;


public class CountMain {

    public static void main(String[] args) {
        CountThread thread = new CountThread();
        for (int i = 0; i < 300 ; i++) {
            Thread thread1 = new Thread(thread);
            thread1.start();
        }
    }
}
