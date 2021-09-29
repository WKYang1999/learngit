package com.neu.mapper;

import com.neu.domain.LikeDomain;
import com.neu.dto.LikeDTO;
import com.neu.his.base.mapper.BaseMapper;
import com.neu.vo.LikeVO;
import org.apache.ibatis.annotations.Param;

public interface LikeMapper extends BaseMapper<LikeVO, LikeDTO, LikeDomain> {

    LikeVO findLike(@Param("userId") Integer userId, @Param("videoId") Integer videoId);
}
