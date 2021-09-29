package com.neu.service.impl;

import com.neu.domain.ReportVideo;
import com.neu.dto.ReportVideoDTO;
import com.neu.his.base.service.impl.BaseServiceImpl;
import com.neu.mapper.ReportVideoMapper;
import com.neu.service.ReportVideoService;
import com.neu.vo.ReportVideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportVideoServiceImpl extends BaseServiceImpl<ReportVideoVO, ReportVideoDTO, ReportVideo> implements ReportVideoService {

	@Autowired
	public void sereportVideoMapper(ReportVideoMapper videoMapper) {
		this.baseMapper = videoMapper;
	}

	@Override
	public int updateVideo(Integer[] idArray) {
		ReportVideoMapper videoMapper = (ReportVideoMapper) this.baseMapper;
		return videoMapper.updateVideo(idArray);
	}

	@Override
	public ReportVideoVO findReport(Integer userId, Integer videoId) {
		ReportVideoMapper videoMapper = (ReportVideoMapper) this.baseMapper;
		return videoMapper.findReport(userId,videoId);
	}
}
