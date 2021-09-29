package com.neu.service;

import com.neu.dto.ReportCommDTO;
import com.neu.his.base.service.BaseService;
import com.neu.vo.ReportCommVO;

public interface ReportCommService extends BaseService<ReportCommVO, ReportCommDTO> {

    ReportCommVO findReport(Integer userId, Integer commId);
}
