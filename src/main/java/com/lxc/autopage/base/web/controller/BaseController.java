package com.lxc.autopage.base.web.controller;

import com.lxc.autopage.base.module.Attachment;
import com.lxc.autopage.base.service.AttachmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by chenlx
 * on 2016/7/25.
 */
@Controller
public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    /**
     * 请求地址
     */
    protected String contextPath;

    /**
     * 数据绑定
     *
     * @param binder WebDataBinder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder, HttpServletRequest request) {

        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        this.contextPath = request.getServletPath();
    }

    /**
     * 异常处理跳转
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception ex) {
        log.error(ex.getMessage(), ex);
        ModelAndView view = new ModelAndView("/error");
        view.addObject("content", ex.getMessage());
        return view;
    }

}
