package com.example.service;

import com.example.entity.Manager;
import com.example.mapper.ManagerMapper;
import com.example.service.exception.PasswordDifferentException;
import com.example.service.exception.PasswordWrongException;
import com.example.service.exception.UsernameDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void login(Manager manager) {
        Integer id = manager.getId();
        String password = manager.getPwd();

        Manager manager1 = managerMapper.findInfo(id);
        String salt = manager1.getSalt();
        //库中存储的密码
        String oldPassword = manager1.getPwd();
        String md5PassWord = getMD5PassWord(password, salt);
        if (!md5PassWord.equals(oldPassword)) {
            throw new PasswordWrongException("用户密码错误");
        }
        //应该是用不到，日后可删除
//        int num = managerMapper.login(manager);
//        if (num == 0) {
//            //密码或用户名不正确
//            throw new PasswordWrongException("密码或用户名不正确");
//        }
    }
}
