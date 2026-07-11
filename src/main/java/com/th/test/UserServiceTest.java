package com.th.test;

import com.th.model.User;
import com.th.service.UserService;
import com.th.serviceimpl.UserServiceImpl;

public class UserServiceTest {

    public static void main(String[] args) {

        UserService userService =
                new UserServiceImpl();

        User user =
                userService.login(
                        "shruti@gmail.com",
                        "shru07"
                );

        if(user != null) {

            System.out.println(
                    "Login Successful");

            System.out.println(
                    user.getUsername());

            System.out.println(
                    user.getRole());
        }
        else {

            System.out.println(
                    "Invalid Credentials");
        }
    }
}