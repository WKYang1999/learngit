package com.neu.service;

import com.neu.dto.CommentsDTO;
import com.neu.his.base.service.BaseService;
import com.neu.vo.CommentsVO;

import java.util.List;

public interface CommentService extends BaseService<CommentsVO, CommentsDTO> {

    List<CommentsVO> findVideo(Integer id);
}
