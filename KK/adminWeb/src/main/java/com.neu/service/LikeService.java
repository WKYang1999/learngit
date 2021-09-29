package com.neu.service;

import com.neu.dto.LikeDTO;
import com.neu.his.base.service.BaseService;
import com.neu.vo.LikeVO;

public interface LikeService extends BaseService<LikeVO, LikeDTO> {

    LikeVO findLike(Integer userId, Integer videoId);
}
