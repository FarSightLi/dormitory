package com.example.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

/**
 * @ClassName: UserDetails
 * @Description:
 * @author: LongSheng Li
 * @date: 2022/5/22 19:55
 */

//TODO 返回的布尔值应该加以根据数据库的值判断，否则会有安全隐患
public class SysUser {
    Integer id;
    String account;
    String userName;
    String password;
    boolean enabled;
    boolean accountNonExpired;
    boolean credentialsNonExpired;
    boolean accountNonLocked;
    Collection<? extends GrantedAuthority> authorities;
    Date lastLoginTime;
    Date lastUpdateTime;
    Date creatTime;
    Integer updateUser;
    Integer createUser;

    public SysUser() {
    }

    public SysUser(Integer id, String account, String userName, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Date lastLoginTime, Date updateTime, Date creatTime, Integer updateUser, Integer creatUser) {
        this.id = id;
        this.account = account;
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = authorities;
        this.lastLoginTime = lastLoginTime;
        this.lastUpdateTime = updateTime;
        this.creatTime = creatTime;
        this.updateUser = updateUser;
        this.createUser = creatUser;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }


    public String getUsername() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isEnabled() {
        return true;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }


    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }


    public boolean isAccountNonLocked() {
        return true;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }


    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", accountNonExpired=" + accountNonExpired +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", authorities=" + authorities +
                ", lastLoginTime=" + lastLoginTime +
                ", updateTime=" + lastUpdateTime +
                ", creatTime=" + creatTime +
                ", updateUser=" + updateUser +
                ", createUser=" + createUser +
                '}';
    }
}

