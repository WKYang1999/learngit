package com.neu.service;

import com.neu.dto.VideoDTO;
import com.neu.his.base.service.BaseService;
import com.neu.vo.ComicVO;
import com.neu.vo.VideoVO;

import java.util.List;


public interface VideoService extends BaseService<VideoVO, VideoDTO> {
    List<ComicVO> findComic(String s);

    int addClick(Integer id);

    List<ComicVO> findHot();

    int delete(Integer[] idArray);
}
