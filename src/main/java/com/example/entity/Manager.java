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

    public Manager() {

    }

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

//    public Manager(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//    }
//    public Manager(String username, String password, String userId,Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//        this.userId=userId;
//    }
//
//    public Manager(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
}
