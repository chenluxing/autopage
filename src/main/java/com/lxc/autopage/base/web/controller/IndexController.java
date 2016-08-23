package com.lxc.autopage.base.web.controller;

import com.lxc.autopage.base.service.IElementService;
import com.lxc.autopage.base.vo.ElementVo;
import com.lxc.autopage.base.vo.GroupVo;
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
            List<GroupVo> groups = elementService.getGroups();
            modelMap.addAttribute("groups", groups);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return "index";
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
