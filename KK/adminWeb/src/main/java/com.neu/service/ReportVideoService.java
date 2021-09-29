package com.neu.service;

import com.neu.dto.ReportVideoDTO;
import com.neu.his.base.service.BaseService;
import com.neu.vo.ReportVideoVO;

public interface ReportVideoService extends BaseService<ReportVideoVO, ReportVideoDTO> {

    int updateVideo(Integer[] idArray);

    ReportVideoVO findReport(Integer userId, Integer videoId);
}
