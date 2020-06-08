package com.example.Library.App.Util;

import com.example.Library.App.DataAccessLayer.User;

public class UserValidator {

    public boolean isValid(User user)
    {
        if(user.getName()==null||user.getName().equals(""))
            return false;
        return true;
    }
}
