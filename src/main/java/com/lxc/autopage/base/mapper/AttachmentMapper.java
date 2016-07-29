package com.lxc.autopage.base.mapper;

import com.lxc.autopage.base.module.Attachment;
import org.apache.ibatis.annotations.Param;

/**
 * Created by admin on 2016/7/29.
 */
public interface AttachmentMapper {

    /**
     * 根据ID查找对象
     * @param id
     * @return
     */
    Attachment selectById(@Param("id") Integer id);

}
