package com.buct.portal.service.impl;

import com.buct.portal.model.Comment;
import com.buct.portal.mapper.CommentMapper;
import com.buct.portal.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dysprosium
 * @since 2023-04-18
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
