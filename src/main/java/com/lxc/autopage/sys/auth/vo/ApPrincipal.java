package com.lxc.autopage.sys.auth.vo;

import java.io.Serializable;

/**
 * 身份信息
 * Created by chenlx
 * on 2016/11/15.
 */
public class ApPrincipal implements Serializable {

    private Integer id;
    private String userName;
    private String realName;

    public ApPrincipal(Integer id, String userName, String realName) {
        this.id = id;
        this.userName = userName;
        this.realName = realName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return realName;
    }
}
