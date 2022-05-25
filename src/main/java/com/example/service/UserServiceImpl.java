package com.example.service;

import com.example.entity.SysUser;
import com.example.mapper.SysUserMapper;
import com.example.service.exception.PasswordDifferentException;
import com.example.service.exception.UsernameNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName: UserServiceImpl
 * @Description:
 * @author: LongSheng Li
 * @date: 2022/5/25 17:11
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void signUp(String userName, String pwd1, String pwd2) {
        //用户名输入为空
        if (userName == null || "".equals(userName)) {
            throw new UsernameNullException("用户名为空");
        }
        Integer count = sysUserMapper.nameNum(userName);
        //用户名已经存在
        if (count != 0) {
            throw new UsernameNotFoundException("用户名重复");
        }
        SysUser sysUser = new SysUser();
        //两次密码不一致
        if (!pwd1.equals(pwd2)) {
            throw new PasswordDifferentException("两次密码不一致");
        }
        //两次密码一致
        if (pwd1.equals(pwd2)) {
            sysUser.setUserName(userName);
            sysUser.setAccount(userName);
            //密码加密
            sysUser.setPassword(passwordEncoder.encode(pwd1));
            sysUser.setCreatTime(new Date());
            sysUser.setLastLoginTime(new Date());
            sysUser.setLastUpdateTime(new Date());
        }
        sysUserMapper.insert(sysUser);
    }

}
