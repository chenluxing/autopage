package com.lxc.autopage.base.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenlx
 * on 2016/7/25.
 */
public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    public static final String HTTP_REQUEST = "http_request";
    private static ThreadLocal<Map<String, Object>> localParams = new ThreadLocal<>();

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
        Map<String, Object> params = localParams.get();
        if (params == null) {
            params = new HashMap<>();
        }
        params.put(HTTP_REQUEST, request);
        localParams.set(params);

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
