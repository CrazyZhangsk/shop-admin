package ThreadTest;

import com.fh.shop.backend.po.user.User;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private String userName;

    private static UserInfo userInfo ;

    private UserInfo(){

    }

    public static UserInfo getInstance(){
        if (null==userInfo){
            userInfo = new UserInfo();
        }
        return  userInfo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
