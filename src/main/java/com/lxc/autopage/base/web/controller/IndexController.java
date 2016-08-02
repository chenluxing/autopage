package com.lxc.autopage.base.web.controller;

import com.lxc.autopage.base.service.IElementService;
import com.lxc.autopage.base.vo.ElementVo;
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
        try{
            List<ElementVo> groups = elementService.getGroups();
            for (ElementVo element : groups){
                List<ElementVo> elementHtmls = elementService.getListByGroupId(element.getId());
                System.out.println("elementHtmls size :" + elementHtmls.size());
            }
            modelMap.addAttribute("groups", groups);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return "index";
    }

}
