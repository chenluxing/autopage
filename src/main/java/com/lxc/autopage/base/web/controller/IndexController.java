package com.lxc.autopage.base.web.controller;

import com.lxc.autopage.base.module.Element;
import com.lxc.autopage.base.service.IElementService;
import com.lxc.autopage.base.service.impl.ElementServiceImpl;
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
    private IElementService elementService;

    @RequestMapping("/index")
    public String getIndex(ModelMap modelMap){
        List<Element> groups = elementService.getGroups();
        for (Element element : groups){
            List<Element> elementHtmls = elementService.getListByGroupId(element.getId());
            System.out.println("elementHtmls size :" + elementHtmls.size());
        }
        modelMap.addAttribute("groups", groups);
        return "index";
    }

}
