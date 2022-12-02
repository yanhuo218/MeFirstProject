package com.ManageSystem.util;

import com.ManageSystem.pojo.User;
import com.ManageSystem.service.UserService;

public class UserLogin {
    private static final UserService user = new UserService();

    public static boolean UserExist(String username) {
        if (username != null) {
            User user1 = user.loginUser(new User(username));
            return user1 != null;
        }
        return false;
    }

    public static boolean UserValidation(User user1) {
        if (user1 != null) {
            User user2 = user.loginUserPass(user1);
            return user2 != null;
        }
        return false;
    }
    
}
