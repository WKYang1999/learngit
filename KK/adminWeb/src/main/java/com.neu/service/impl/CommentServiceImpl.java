package com.neu.service.impl;

import com.neu.domain.Comments;
import com.neu.dto.CommentsDTO;
import com.neu.his.base.service.impl.BaseServiceImpl;
import com.neu.mapper.CommentMapper;
import com.neu.service.CommentService;
import com.neu.vo.CommentsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends BaseServiceImpl<CommentsVO, CommentsDTO,Comments> implements CommentService {

	@Autowired
	public void setCommentsMapper(CommentMapper commentMapper) {
		this.baseMapper = commentMapper;
	}

	@Override
	public List<CommentsVO> findVideo(Integer id) {
		CommentMapper commentMapper = (CommentMapper) this.baseMapper;
		return commentMapper.findVideo(id);
	}
}
