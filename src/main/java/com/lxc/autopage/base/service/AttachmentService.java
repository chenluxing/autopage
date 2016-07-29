package com.lxc.autopage.base.service;

import com.lxc.autopage.base.mapper.AttachmentMapper;
import com.lxc.autopage.base.module.Attachment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by admin on 2016/7/29.
 */
@Service
public class AttachmentService {

    @Resource
    private AttachmentMapper attachmentMapper;

    public Attachment getAttachmentById(Integer id){
        return attachmentMapper.selectById(id);
    }
}
