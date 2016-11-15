package com.lxc.autopage.sys.auth.realm;

import com.lxc.autopage.sys.auth.vo.ApAuthenticationToken;
import com.lxc.autopage.sys.auth.vo.ApPrincipal;
import com.lxc.autopage.plugin.encrypt.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by chenlx
 * on 2016/11/15.
 */
public class ApAuthenticationRealm extends AuthorizingRealm implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try {
            ApAuthenticationToken apAuthenticationToken = (ApAuthenticationToken)token;
            String username = apAuthenticationToken.getUsername();
            String captchaId = apAuthenticationToken.getCaptchaId();
            String captcha = apAuthenticationToken.getCaptcha();
            String ip = apAuthenticationToken.getHost();
            if (StringUtils.isNotEmpty(username)){
                String password = new String(apAuthenticationToken.getPassword());
                String md5Password = SecurityUtil.md5(password);
                return new SimpleAuthenticationInfo(new ApPrincipal(111, username, username), password, getName());
            }
            throw new UnknownAccountException();
        } catch (Exception e){
            e.printStackTrace();
            if(e instanceof UnsupportedTokenException){
                throw e;
            }else if(e instanceof UnsupportedTokenException){
                throw e;
            }
            throw new AuthenticationException("登录异常");
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ApPrincipal principal = (ApPrincipal) principals.fromRealm(getName()).iterator().next();
        if (principal != null) {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            /*List<String> menuList = principal.getMenuList();
            if (CollectionUtils.isNotEmpty(menuList)) {
                authorizationInfo.addStringPermissions(menuList);
            }*/
            return authorizationInfo;
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
