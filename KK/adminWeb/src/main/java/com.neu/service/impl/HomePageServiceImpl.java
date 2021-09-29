package com.neu.service.impl;

import com.neu.domain.HomePage;
import com.neu.dto.HomePageDTO;
import com.neu.his.base.service.impl.BaseServiceImpl;
import com.neu.mapper.HomePageMapper;
import com.neu.service.HomePageService;
import com.neu.vo.HomePageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomePageServiceImpl extends BaseServiceImpl<HomePageVO, HomePageDTO, HomePage> implements HomePageService {

	@Autowired
	public void setHomePageMapper(HomePageMapper homePageMapper) {
		this.baseMapper = homePageMapper;
	}

	@Override
	public HomePageVO findHome(String name) {
		HomePageMapper homePageMapper = (HomePageMapper) this.baseMapper;
		return homePageMapper.findHome(name);
	}

	@Override
	public int updateState(Integer[] idArray) {
		HomePageMapper homePageMapper = (HomePageMapper) this.baseMapper;
		return homePageMapper.updateState(idArray);
	}

	@Override
	public int updateSS(Integer[] idArray) {
		HomePageMapper homePageMapper = (HomePageMapper) this.baseMapper;
		return homePageMapper.updateSS(idArray);
	}
}
