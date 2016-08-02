package com.lxc.autopage.base.service;

import com.lxc.autopage.base.vo.ElementVo;

import java.util.List;

/**
 * Created by admin on 2016/7/29.
 */
public interface IElementService {

    /**
     * 查询分组信息
     * @return
     */
    List<ElementVo> getGroups();

    /**
     * 查询分组下的元素列表
     * @param groupId
     * @return
     */
    List<ElementVo> getListByGroupId(Integer groupId) throws Exception;

}
