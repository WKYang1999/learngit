package com.neu.mapper;

import com.neu.domain.Comments;
import com.neu.dto.CommentsDTO;
import com.neu.his.base.mapper.BaseMapper;
import com.neu.vo.CommentsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CommentMapper extends BaseMapper<CommentsVO, CommentsDTO,Comments> {

    List<CommentsVO> findVideo(@Param("videoId") Integer id);

}
