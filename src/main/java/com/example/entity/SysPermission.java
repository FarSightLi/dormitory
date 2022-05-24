package com.example.entity;

/**
 * @ClassName: SysPermission
 * @Description:
 * @author: LongSheng Li
 * @date: 2022/5/22 20:37
 */

public class SysPermission {
    Integer id;
    String permissionCode;
    String permissionName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
