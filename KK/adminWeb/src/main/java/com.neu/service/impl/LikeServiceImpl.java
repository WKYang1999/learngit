package com.neu.service.impl;

import com.neu.domain.LikeDomain;
import com.neu.dto.LikeDTO;
import com.neu.his.base.service.impl.BaseServiceImpl;
import com.neu.mapper.LikeMapper;
import com.neu.service.LikeService;
import com.neu.vo.LikeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl extends BaseServiceImpl<LikeVO, LikeDTO, LikeDomain> implements LikeService {

	@Autowired
	public void setLikeMapper(LikeMapper likeMapper) {
		this.baseMapper = likeMapper;
	}

	@Override
	public LikeVO findLike(Integer userId, Integer videoId) {
		LikeMapper likeMapper = (LikeMapper) this.baseMapper;
		return likeMapper.findLike(userId,videoId);
	}
}
