package com.neu.service.impl;

import com.neu.domain.VideoNum;
import com.neu.dto.VideoNumDTO;
import com.neu.his.base.service.impl.BaseServiceImpl;
import com.neu.mapper.VideoNumMapper;
import com.neu.service.VideoNumService;
import com.neu.vo.VideoNumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoNumServiceImpl extends BaseServiceImpl<VideoNumVO, VideoNumDTO, VideoNum> implements VideoNumService {

	@Autowired
	public void setVideoMapper(VideoNumMapper videoNumMapper) {
		this.baseMapper = videoNumMapper;
	}

	@Override
	public List<VideoNumVO> findVideo(Integer id) {
		VideoNumMapper videoNumMapper = (VideoNumMapper) this.baseMapper;
		return videoNumMapper.findVideo(id);
	}

	@Override
	public VideoNumVO findGK(Integer videoId, Integer numId) {
		VideoNumMapper videoNumMapper = (VideoNumMapper) this.baseMapper;
		return videoNumMapper.findGK(videoId,numId);
	}
}
