package com.neu.service.impl;

import com.neu.domain.Recomm;
import com.neu.dto.RecommDTO;
import com.neu.his.base.service.impl.BaseServiceImpl;
import com.neu.mapper.RecommMapper;
import com.neu.service.RecommService;
import com.neu.vo.RecommVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommServiceImpl extends BaseServiceImpl<RecommVO, RecommDTO, Recomm> implements RecommService {

	@Autowired
	public void setRecommMapper(RecommMapper recommMapper) {
		this.baseMapper = recommMapper;
	}

	@Override
	public RecommVO findRecomm(Integer userId, Integer videoId) {
		RecommMapper recommMapper = (RecommMapper) this.baseMapper;
		return recommMapper.findRecomm(userId,videoId);
	}

	@Override
	public List<RecommVO> findCC(Integer id) {
		RecommMapper recommMapper = (RecommMapper) this.baseMapper;
		return recommMapper.findCC(id);
	}
}
