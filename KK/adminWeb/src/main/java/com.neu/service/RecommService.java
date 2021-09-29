package com.neu.service;

import com.neu.dto.RecommDTO;
import com.neu.his.base.service.BaseService;
import com.neu.vo.RecommVO;

import java.util.List;

public interface RecommService extends BaseService<RecommVO, RecommDTO> {

    RecommVO findRecomm(Integer userId, Integer videoId);

    List<RecommVO> findCC(Integer id);
}
