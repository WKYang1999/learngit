package com.neu.service;

import com.neu.dto.HomePageDTO;
import com.neu.his.base.service.BaseService;
import com.neu.vo.HomePageVO;

public interface HomePageService extends BaseService<HomePageVO, HomePageDTO> {

    HomePageVO findHome(String name);

    int updateState(Integer[] idArray);

    int updateSS(Integer[] idArray);
}
