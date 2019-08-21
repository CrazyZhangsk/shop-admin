package ThreadTest;

public class UserMain {

    public static void main(String[] args) {
        a();
        b();
        c();
        e();
        f();
        g();
    }

    public static void a(){
        for (int i = 0; i < 5 ; i++) {
            Thread thread = new Thread(new UserThread());
            thread.start();
        }
    }

    public static void c(){
        for (int i = 0; i < 5 ; i++) {
            Thread thread = new Thread(new UserThread());
            thread.start();
        }
    }

    public static void g(){
        for (int i = 0; i < 5 ; i++) {
            Thread thread = new Thread(new UserThread());
            thread.start();
        }
    }

    public static void b(){
        for (int i = 0; i < 5 ; i++) {
            Thread thread = new Thread(new UserThread());
            thread.start();
        }
    }

    public static void f(){
        for (int i = 0; i < 5 ; i++) {
            Thread thread = new Thread(new UserThread());
            thread.start();
        }
    }

    public static void e(){
        for (int i = 0; i < 5 ; i++) {
            Thread thread = new Thread(new UserThread());
            thread.start();
        }
    }
}
