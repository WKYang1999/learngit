package com.neu.mapper;

import com.neu.domain.Notice;
import com.neu.dto.NoticeDTO;
import com.neu.his.base.mapper.BaseMapper;
import com.neu.vo.NoticeVO;

import java.util.List;

public interface NoticeMapper extends BaseMapper<NoticeVO, NoticeDTO,Notice> {

    List<NoticeVO> findNotices();
}
