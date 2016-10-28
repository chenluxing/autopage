package com.lxc.autopage.base.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lxc.autopage.base.mapper.ElementMapper;
import com.lxc.autopage.base.module.ElementHtmlGroupPo;
import com.lxc.autopage.base.module.ElementHtmlPo;
import com.lxc.autopage.base.module.ElementPo;
import com.lxc.autopage.base.service.IElementService;
import com.lxc.autopage.base.vo.ElementHtmlGroupVo;
import com.lxc.autopage.base.vo.ElementHtmlVo;
import com.lxc.autopage.base.vo.ElementVo;
import com.lxc.autopage.base.vo.GroupVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/1.
 */
@Service
public class ElementServiceImpl implements IElementService{

    private Logger logger = LoggerFactory.getLogger(ElementServiceImpl.class);

    @Resource
    private ElementMapper elementMapper;

    /**
     * 查询分组信息
     * @return
     */
    public List<GroupVo> getGroups() throws Exception{
        List<GroupVo> resultList = null;
        List<ElementPo> elementPoList = elementMapper.selectListByGroupId(0);
        if (CollectionUtils.isNotEmpty(elementPoList)){
            resultList = new ArrayList<>();
            GroupVo tempGroupVo = null;
            List<ElementVo> tempList = null;
            for (ElementPo po : elementPoList){
                tempGroupVo = new GroupVo();
                tempGroupVo.copyPoValue(po);
                tempList = getListByGroupId(po.getId());
                tempGroupVo.setElementVos(tempList);
                resultList.add(tempGroupVo);
            }
        }
        return resultList;
    }

    /**
     * 查询分组下的元素列表
     * @param groupId
     * @return
     */
    public List<ElementVo> getListByGroupId(Integer groupId) throws Exception{
        List<ElementVo> resultList = null;
        List<ElementPo> elements = elementMapper.selectListByGroupId(groupId);
        if (CollectionUtils.isNotEmpty(elements)){
            resultList = new ArrayList<>();
            List<ElementHtmlGroupVo> ehGroupVoList = null;
            ElementVo evo = null;
            try{
                // 遍历元素头对象，组装元素体
                for (ElementPo elementPo : elements){
                    evo = new ElementVo();
                    evo.copyPoValue(elementPo);
                    ehGroupVoList = getElementHtmlVoListByElementId(elementPo.getId());
                    if (ehGroupVoList != null){
                        evo.setElementHtmlGroupVoList(ehGroupVoList);
                        resultList.add(evo);
                    }
                }
            }catch (Exception ex){
                logger.error("获取分组下元素数据异常，分组ID：", groupId);
                logger.error("获取节点数据异常！", ex);
                throw ex;
            }
        }
        return resultList;
    }

    /**
     * 获取元素的元素体
     * @param elementId
     * @return
     */
    private List<ElementHtmlGroupVo> getElementHtmlVoListByElementId(Integer elementId){
        List<ElementHtmlGroupVo> resultList = new ArrayList<>();
        // 获取元素下的元素组
        List<ElementHtmlGroupPo> ehgroupPoList = elementMapper.selectElementHtmlGroupListByElementId(elementId);
        if (CollectionUtils.isNotEmpty(ehgroupPoList)){
            ElementHtmlGroupVo ehGroupVo = null;
            List<ElementHtmlPo> ehPoList = null;
            ElementHtmlVo ehVo = null;
            ElementHtmlVo subEhVo = null;
            List<ElementHtmlPo> subList = null;
            // 遍历html元素组
            for (ElementHtmlGroupPo ehGroupPo : ehgroupPoList){
                // 获取元素组下的元素列表
                ehPoList = elementMapper.selectElementHtmlListByEhgId(ehGroupPo.getId());
                if(CollectionUtils.isNotEmpty(ehPoList)){
                    ehGroupVo = new ElementHtmlGroupVo(ehGroupPo.getId());
                    for (ElementHtmlPo ehPo : ehPoList){
                        ehVo = new ElementHtmlVo();
                        ehVo.copyPoValue(ehPo);
                        if (ehVo.getHtmlType() != null && ehVo.getHtmlType().hasSubElement()){
                            subList = elementMapper.selectElementHtmlListByEhParentId(ehVo.getId());
                            if (CollectionUtils.isNotEmpty(subList)){
                                for (ElementHtmlPo subPo : subList){
                                    subEhVo = new ElementHtmlVo();
                                    subEhVo.copyPoValue(subPo);
                                    ehVo.addSubElementVo(subEhVo);
                                }
                            }
                        }
                        ehGroupVo.addElementHtmlVo(ehVo);
                    }
                    resultList.add(ehGroupVo);
                }
            }
        }
        return resultList;
    }

    public Page getPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        Page page = (Page)elementMapper.selectListByGroupId(Integer.MIN_VALUE);
        return page;
    }

}
