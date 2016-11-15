package com.lxc.autopage.plugin.captcha;

import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

public class ApCaptchaService extends DefaultManageableImageCaptchaService{

    public CaptchaStore getStore(){
        return store;
    }
}
