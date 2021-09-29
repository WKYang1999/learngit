package com.neu.mapper;

import com.neu.domain.Video;
import com.neu.dto.VideoDTO;
import com.neu.his.base.mapper.BaseMapper;
import com.neu.vo.ComicVO;
import com.neu.vo.VideoVO;

import java.util.List;

public interface VideoMapper extends BaseMapper<VideoVO, VideoDTO, Video> {

    List<ComicVO> findComic(String s);

    int addClick(Integer id);

    List<ComicVO> findHot();

    int delete(Integer[] idArray);
}
