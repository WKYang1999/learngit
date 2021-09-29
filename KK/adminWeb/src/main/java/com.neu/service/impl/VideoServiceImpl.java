package com.neu.service.impl;

import com.neu.domain.Video;
import com.neu.dto.VideoDTO;
import com.neu.his.base.service.impl.BaseServiceImpl;
import com.neu.mapper.VideoMapper;
import com.neu.service.VideoService;
import com.neu.vo.ComicVO;
import com.neu.vo.VideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl
		extends BaseServiceImpl<VideoVO,VideoDTO,Video> implements VideoService {

	@Autowired
	public void setVideoMapper(VideoMapper videoMapper) {
		this.baseMapper = videoMapper;
	}

	@Override
	public List<ComicVO> findComic(String s) {
		VideoMapper videoMapper = (VideoMapper) this.baseMapper;
		return videoMapper.findComic(s);
	}

	@Override
	public int addClick(Integer id) {
		VideoMapper videoMapper = (VideoMapper) this.baseMapper;
		return videoMapper.addClick(id);
	}

	@Override
	public List<ComicVO> findHot() {
		VideoMapper videoMapper = (VideoMapper) this.baseMapper;
		return videoMapper.findHot();
	}

	@Override
	public int delete(Integer[] idArray) {
		VideoMapper videoMapper = (VideoMapper) this.baseMapper;
		return videoMapper.delete(idArray);
	}
}
