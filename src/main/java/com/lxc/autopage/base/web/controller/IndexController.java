package com.lxc.autopage.base.web.controller;

import com.lxc.autopage.base.service.IElementService;
import com.lxc.autopage.base.vo.GroupVo;
import com.lxc.autopage.plugin.captcha.CaptchaUtil;
import com.lxc.autopage.plugin.encrypt.RSA;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.UUID;

/**
 * Created by admin on 2016/8/1.
 */
@Controller
public class IndexController extends BaseController {

    private static final String PRIVATE_KEY_ATTRIBUTE_NAME = "RSA-PRIVATE";
    @Resource
    private IElementService elementService;

    /**
     * 登录跳转
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String toLogin(HttpServletRequest request){
        KeyPair keyPair = RSA.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        HttpSession session = request.getSession();
        session.setAttribute(PRIVATE_KEY_ATTRIBUTE_NAME, privateKey);
        String modulus = Base64.encodeBase64String(publicKey.getModulus().toByteArray());
        String exponent = Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray());
        request.setAttribute("modulus", modulus);
        request.setAttribute("exponent", exponent);
        request.setAttribute("captchaId", session.getId());
        return "login";
    }

    /**
     * 登录
     * @param request
     * @return
     */
    @RequestMapping("/loginIn")
    public String loginIn(HttpServletRequest request){
        if (SecurityUtils.getSubject().isAuthenticated()){
            return "/main";
        }else{
            return "redirect:/login.html";
        }
    }

    @RequestMapping("/index")
    public String getIndex(){
        return "main";
    }

    @RequestMapping("/indexTemp")
    public String getIndexTemp(ModelMap modelMap){
        try{
            List<GroupVo> groups = elementService.getGroups();
            modelMap.addAttribute("groups", groups);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return "index_temp";
    }
}
