package com.neu.service;

import com.neu.dto.VideoNumDTO;
import com.neu.his.base.service.BaseService;
import com.neu.vo.VideoNumVO;

import java.util.List;


public interface VideoNumService extends BaseService<VideoNumVO, VideoNumDTO> {


    List<VideoNumVO> findVideo(Integer id);

    VideoNumVO findGK(Integer videoId, Integer numId);
}
