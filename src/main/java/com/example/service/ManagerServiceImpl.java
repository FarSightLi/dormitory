package com.example.service;

import com.example.entity.Manager;
import com.example.mapper.ManagerMapper;
import com.example.service.exception.PasswordDifferentException;
import com.example.service.exception.UsernameDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @ClassName: ManagerServiceImpl
 * @Description:
 * @author: LongSheng Li
 * @date: 2022/5/7 21:05
 */

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerMapper managerMapper;

    /**
     * md5算法的加密处理
     */
    private String getMD5PassWord(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            //md5加密算法的方法
            //传递一个字节
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

    @Override
    public void signUp(Manager manager, String pwdRe) {
        Integer id = manager.getId();
        String password1 = manager.getPwd();
        int idCount = managerMapper.idCount(id);
        if (idCount != 0) {
            // 是：表示用户名已被占用，则抛出UsernameDuplicateException异常
            throw new UsernameDuplicateException("尝试注册的用户名[" + id + "]已经被占用");
        }
        if (!(password1.equals(pwdRe))) {
            //表示前后两次密码输入不一致
            throw new PasswordDifferentException("两次输入密码不一致");
        }
        String salt = UUID.randomUUID().toString().toUpperCase();
        //将密码和盐值进行加密处理
        String md5PassWord = getMD5PassWord(password1, salt);
        manager.setPwd(md5PassWord);
        // 补全数据：盐值
        manager.setSalt(salt);
        manager.setPwd(md5PassWord);
        manager.setId(id);
        managerMapper.insert(manager);

    }

//    @Override
//    public void login(Manager manager) {
//        Integer id = manager.getId();
//        String password = manager.getPwd();
//
//        Manager manager1 = managerMapper.findInfo(id);
//        String salt = manager1.getSalt();
//        //库中存储的密码
//        String oldPassword = manager1.getPwd();
//        String md5PassWord = getMD5PassWord(password, salt);
//        if (!md5PassWord.equals(oldPassword)) {
//            throw new PasswordWrongException("用户密码错误");
//        }
//    }

    /**
     * 这里根据传进来的用户账号进行用户信息的构建
     * 通常的做法是
     * 1 根据username查询数据库对应的用户信息
     * 2 根据用户信息查询出用户权限信息  例如菜单添加权限  sys:menu:add
     * 3 根据用户账号，密码，权限构建对应的UserDetails对象返回
     * 这里实际上是没有进行用户认证功能的，真正的验证是在UsernamePasswordAuthenticationFilter对象当中
     * UsernamePasswordAuthenticationFilter对象会自动根据前端传入的账号信息和UserDetails对象对比进行账号的验证
     * 通常情况下，已经满足常见的使用常见，不过如果有特殊需求，需要使用自己实现的具体认证方式，可以继承UsernamePasswordAuthenticationFilter对象
     * 重写attemptAuthentication 方法和successfulAuthentication方法
     * 最后在WebSecurityConfiguration里面添加自己的过滤器即可
     *
     * @param manager
     * @return UserInfo
     * @throws UsernameNotFoundException
     */
    @Override
    public Manager login(Manager manager) throws UsernameNotFoundException {
        Manager info = managerMapper.findInfo(manager.getId());

        return info;
    }
}
