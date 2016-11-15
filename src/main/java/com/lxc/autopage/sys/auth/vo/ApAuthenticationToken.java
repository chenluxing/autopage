package com.lxc.autopage.sys.auth.vo;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by chenlx
 * on 2016/11/15.
 */
public class ApAuthenticationToken extends UsernamePasswordToken {

    private String captchaId;   // 验证码ID
    private String captcha;     // 验证码

    /**
     * @param username 用户名
     * @param password 密码
     * @param captchaId 验证码ID
     * @param captcha 验证码
     * @param rememberMe 记住我
     * @param host IP
     */
    public ApAuthenticationToken(String username, String password, String captchaId, String captcha,
                                 boolean rememberMe, String host) {
        super(username, password, rememberMe);
        setCaptcha(host);
        this.captchaId = captchaId;
        this.captcha = captcha;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
