package com.neu.service;

import com.neu.dto.ContentDTO;
import com.neu.his.base.service.BaseService;
import com.neu.vo.ContentVO;

import java.util.List;

public interface ContentService extends BaseService<ContentVO, ContentDTO> {

    List<ContentVO> findLT(Integer yourId, Integer userId);
}
