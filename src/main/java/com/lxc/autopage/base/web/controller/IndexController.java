package com.lxc.autopage.base.web.controller;

import com.lxc.autopage.base.module.Element;
import com.lxc.autopage.base.service.ElementServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2016/8/1.
 */
@Controller
public class IndexController extends BaseController {

    @Resource
    private ElementServiceImpl elementService;

    @RequestMapping("/index")
    public String getIndex(ModelMap modelMap){
        List<Element> groups = elementService.getGroups();
        modelMap.addAttribute("groups", groups);
        return "index";
    }

}
