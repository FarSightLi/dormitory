package com.example.entity;

import org.springframework.stereotype.Component;

/**
 * @ClassName: Manager
 * @Description:
 * @author: LongSheng Li
 * @date: 2022/5/7 20:47
 */

@Component
public class Manager {
    private Integer id;
    private String pwd;
    private String salt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", pwd='" + pwd + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
