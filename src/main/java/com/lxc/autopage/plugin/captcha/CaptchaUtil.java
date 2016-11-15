package com.lxc.autopage.plugin.captcha;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;

/**
 * 图片验证码工具类
 */
public class CaptchaUtil {
	private final static Logger logger = LoggerFactory.getLogger(CaptchaUtil.class);

	private static ApCaptchaService imageCaptchaService;

	private static ApCaptchaService getCaptchaService() {
		if (imageCaptchaService == null) {
			init();
		}
		return imageCaptchaService;
	}

	/**
	 * 默认实现
	 */
	private static void init() {
		imageCaptchaService = new ApCaptchaService();
		imageCaptchaService.setCaptchaEngine(new CaptchaEngine());
		imageCaptchaService.setMinGuarantedStorageDelayInSeconds(3600);
	}

	/**
	 * 生成验证码图片
	 *
	 * @param captchaId
	 *            验证ID
	 * @return 验证码图片
	 */
	public static BufferedImage buildImage(String captchaId) {
		return (BufferedImage) getCaptchaService().getChallengeForID(captchaId);
	}

	/**
	 * 验证码验证
	 *
	 * @param captchaId
	 *            验证ID
	 * @param captcha
	 *            验证码(忽略大小写)
	 * @return 验证码验证是否通过
	 */
	public static boolean isValid(String captchaId, String captcha) {
		if (StringUtils.isNotBlank(captchaId) && !getCaptchaService().getStore().hasCaptcha(captchaId)){
			return true;
		}
		if (StringUtils.isNotBlank(captchaId) && StringUtils.isNotBlank(captcha)) {
			try {
                boolean result = getCaptchaService().validateResponseForID(captchaId, captcha.toUpperCase());
				return result;
			} catch (Exception e) {
                e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}

}
