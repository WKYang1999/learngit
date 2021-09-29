package com.neu.mapper;

import com.neu.domain.ReportComm;
import com.neu.dto.ReportCommDTO;
import com.neu.his.base.mapper.BaseMapper;
import com.neu.vo.ReportCommVO;
import org.apache.ibatis.annotations.Param;

public interface ReportCommMapper extends BaseMapper<ReportCommVO, ReportCommDTO, ReportComm> {

    ReportCommVO findReport(@Param("userId") Integer userId, @Param("commId") Integer commId);
}
