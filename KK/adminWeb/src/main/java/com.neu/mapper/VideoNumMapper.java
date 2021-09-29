package com.neu.mapper;

import com.neu.domain.VideoNum;
import com.neu.dto.VideoNumDTO;
import com.neu.his.base.mapper.BaseMapper;
import com.neu.vo.VideoNumVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoNumMapper extends BaseMapper<VideoNumVO, VideoNumDTO, VideoNum> {

    List<VideoNumVO> findVideo(Integer id);

    VideoNumVO findGK(@Param("videoId") Integer videoId,@Param("numId") Integer numId);
}
