package ThreadTest;

public class UserThread implements Runnable{
    @Override
    public void run() {
        UserInfo userInfo = UserInfo.getInstance();
        System.out.println(userInfo);
    }
}
