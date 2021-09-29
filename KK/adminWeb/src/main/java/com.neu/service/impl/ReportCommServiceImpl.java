package com.neu.service.impl;

import com.neu.domain.ReportComm;
import com.neu.dto.ReportCommDTO;
import com.neu.his.base.service.impl.BaseServiceImpl;
import com.neu.mapper.ReportCommMapper;
import com.neu.service.ReportCommService;
import com.neu.vo.ReportCommVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportCommServiceImpl extends BaseServiceImpl<ReportCommVO, ReportCommDTO, ReportComm> implements ReportCommService {

	@Autowired
	public void sereportCommMapper(ReportCommMapper reportCommMapper) {
		this.baseMapper = reportCommMapper;
	}

	@Override
	public ReportCommVO findReport(Integer userId, Integer commId) {
		ReportCommMapper reportCommMapper = (ReportCommMapper) this.baseMapper;
		return reportCommMapper.findReport(userId,commId);
	}
}
