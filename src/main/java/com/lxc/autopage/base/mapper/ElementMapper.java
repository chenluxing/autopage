package com.lxc.autopage.base.mapper;

import com.lxc.autopage.base.module.ElementHtmlPo;
import com.lxc.autopage.base.module.ElementPo;

import java.util.List;

/**
 * Created by admin on 2016/7/29.
 */
public interface ElementMapper {

    /**
     * 根据groupId查询分组元素
     * @param groupId
     * @return
     */
    List<ElementPo> selectListByGroupId(Integer groupId);

    /**
     * 根据元素ID，查询html元素组
     * @param elementId
     * @return
     */
    List<ElementHtmlPo> selectElementHtmlListByElementId(Integer elementId);

}
