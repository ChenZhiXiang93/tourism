package com.bonc.pojo;

import java.io.Serializable;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 10:40 2018/8/24
 * @Modified By:
 */
public class User implements Serializable {

    private Integer id;

    private String username;

    private String password;

    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
