package com.lxc.autopage.base.service.impl;

import com.lxc.autopage.base.mapper.ElementMapper;
import com.lxc.autopage.base.module.Element;
import com.lxc.autopage.base.module.ElementHtml;
import com.lxc.autopage.base.service.IElementService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/1.
 */
@Service
public class ElementServiceImpl implements IElementService{

    @Resource
    private ElementMapper elementMapper;

    /**
     * 查询分组信息
     * @return
     */
    public List<Element> getGroups(){
        return elementMapper.selectListByGroupId(0);
    }

    /**
     * 查询分组下的元素列表
     * @param groupId
     * @return
     */
    public List<Element> getListByGroupId(Integer groupId){
        List<Element> resultList = null;
        List<Element> elements = elementMapper.selectListByGroupId(groupId);
        if (CollectionUtils.isNotEmpty(elements)){
            resultList = new ArrayList<>();
            List<ElementHtml> htmls = null;
            ElementHtml elementHtml = null;
            for (Element element : elements){
                htmls = elementMapper.selectElementHtmlListByElementId(element.getId());
                elementHtml = convertListToElement(htmls);
                if (elementHtml != null){
                    element.setElementHtml(elementHtml);
                }
                resultList.add(element);
            }
        }
        return resultList;
    }

    /**
     * 将一个html元素组转换成元素对象
     * @param elementHtmls
     * @return
     */
    private ElementHtml convertListToElement(List<ElementHtml> elementHtmls){
        ElementHtml elementHtml = null;
        if (CollectionUtils.isNotEmpty(elementHtmls)){

        }
        return elementHtml;
    }
}
