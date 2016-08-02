package com.lxc.autopage.base.service;

import com.lxc.autopage.base.module.Element;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2016/7/29.
 */
public interface IElementService {

    /**
     * 查询分组信息
     * @return
     */
    List<Element> getGroups();

    /**
     * 查询分组下的元素列表
     * @param groupId
     * @return
     */
    List<Element> getListByGroupId(Integer groupId);

}
