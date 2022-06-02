package com.example.controller;

import com.example.service.UserService;
import com.example.service.exception.PasswordDifferentException;
import com.example.service.exception.UsernameDuplicateException;
import com.example.service.exception.UsernameNullException;
import com.example.util.JsonResult;
import com.example.util.ResultCode;
import com.example.util.ResultTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: ManagerController
 * @Description:
 * @author: LongSheng Li
 * @date: 2022/5/25 17:48
 */

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Transactional
    @PostMapping("signUp")
    @ResponseBody
    public JsonResult signUp(@RequestParam("userName") String userName, @RequestParam("pwd1") String pwd1, @RequestParam("pwd2") String pwd2) {
        userService.signUp(userName, pwd1, pwd2);
        try {
            return ResultTool.success();
        } catch (UsernameNullException e) {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_NAME_NULL);
        } catch (UsernameDuplicateException e) {
            return ResultTool.fail(ResultCode.USER_ACCOUNT_NAME_EXIST);
        } catch (PasswordDifferentException e) {
            return ResultTool.fail(ResultCode.USER_PASSWORD_NOT_FIT);
        }
    }


}

