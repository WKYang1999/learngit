package com.neu.mapper;

import com.neu.domain.HomePage;
import com.neu.dto.HomePageDTO;
import com.neu.his.base.mapper.BaseMapper;
import com.neu.vo.HomePageVO;

public interface HomePageMapper extends BaseMapper<HomePageVO, HomePageDTO, HomePage> {

    HomePageVO findHome(String name);

    int updateState(Integer[] idArray);

    int updateSS(Integer[] idArray);
}
