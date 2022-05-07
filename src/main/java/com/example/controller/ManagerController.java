package com.example.controller;

import com.example.entity.Manager;
import com.example.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ManagerController
 * @Description:
 * @author: LongSheng Li
 * @date: 2022/5/7 21:45
 */

@RestController
@RequestMapping("/m")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @PostMapping("/login")
    private String login(Manager manager) {
        managerService.login(manager);
        return "登录成功";
    }

    @PostMapping("/signUp")
    private String signUp(Manager manager, String pwdRe) {
        managerService.signUp(manager, pwdRe);
        return "注册成功";
    }
}
