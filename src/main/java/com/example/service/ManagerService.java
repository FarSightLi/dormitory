package com.example.service;

import com.example.entity.Manager;


public interface ManagerService {


    void signUp(Manager manager, String pwdRe);

    /**
     * 登录
     */
    Manager login(Manager manager);


}
