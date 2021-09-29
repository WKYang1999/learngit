package com.neu.mapper;

import com.neu.domain.ReportVideo;
import com.neu.dto.ReportVideoDTO;
import com.neu.his.base.mapper.BaseMapper;
import com.neu.vo.ReportVideoVO;
import org.apache.ibatis.annotations.Param;

public interface ReportVideoMapper extends BaseMapper<ReportVideoVO, ReportVideoDTO, ReportVideo> {

    int updateVideo(Integer[] idArray);

    ReportVideoVO findReport(@Param("userId") Integer userId, @Param("videoId") Integer videoId);
}
