package com.lxc.autopage.base.service.impl;

import com.lxc.autopage.base.mapper.ElementMapper;
import com.lxc.autopage.base.module.ElementHtmlPo;
import com.lxc.autopage.base.module.ElementPo;
import com.lxc.autopage.base.module.enums.RelationType;
import com.lxc.autopage.base.service.IElementService;
import com.lxc.autopage.base.vo.ElementHtmlVo;
import com.lxc.autopage.base.vo.ElementVo;
import com.lxc.autopage.base.vo.GroupVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            List<ElementHtmlPo> htmls = null;
            ElementHtmlVo elementHtml = null;
            ElementVo evo = null;
            try{
                for (ElementPo elementPo : elements){
                    evo = new ElementVo();
                    evo.copyPoValue(elementPo);
                    htmls = elementMapper.selectElementHtmlListByElementId(elementPo.getId());
                    elementHtml = convertListToElement(htmls);
                    if (elementHtml != null){
                        evo.setElementHtml(elementHtml);
                    }
                    resultList.add(evo);
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
     * 将一个html元素组转换成元素对象
     * @param elementHtmlPos
     * @return
     */
    private ElementHtmlVo convertListToElement(List<ElementHtmlPo> elementHtmlPos) throws Exception{
        ElementHtmlVo resultVo = null;
        if (CollectionUtils.isNotEmpty(elementHtmlPos)){
            ElementHtmlPo elementHtmlPo = elementHtmlPos.get(0);  // 获取第一个元素，并判断是否是主元素
            if (elementHtmlPo != null && RelationType.MASTER.getValue().equals(elementHtmlPo.getRelationType())){
                resultVo = new ElementHtmlVo();
                resultVo.copyPoValue(elementHtmlPo);

                // 如果存在从属元素或者子元素，遍历元素
                if (elementHtmlPos.size() > 1){
                    Map<Integer, ElementHtmlVo> slaveMap = new HashMap<>();
                    ElementHtmlVo tempSlaveVo = null;
                    ElementHtmlVo tempVo = null;
                    for (ElementHtmlPo tempPo : elementHtmlPos){
                        tempVo = new ElementHtmlVo();
                        tempVo.copyPoValue(tempPo);
                        // 遍历节点为从节点
                        if (RelationType.SLAVE.equals(tempVo.getRelationType())){
                            slaveMap.put(tempPo.getId(), tempVo);
                        }else if(RelationType.SUB.equals(tempVo.getRelationType())){  // 遍历节点为子节点
                            // 判断是主节点的子元素
                            if (tempPo.getEhParentId().equals(resultVo.getId())){
                                resultVo.addSubElement(tempVo);
                            }else if(slaveMap.containsKey(tempPo.getEhParentId())){   // 判断是从节点的子元素
                                tempSlaveVo = slaveMap.get(tempPo.getEhParentId());
                                tempSlaveVo.addSubElement(tempVo);
                            }
                        }
                    }
                    // 将从属元素拷贝添加到主元素中
                    if (slaveMap.size() > 0){
                        resultVo.addSlaveElements(slaveMap.values());
                    }
                }
            }
        }
        return resultVo;
    }
}
