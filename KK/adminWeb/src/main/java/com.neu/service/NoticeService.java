package com.neu.service;

import com.neu.dto.NoticeDTO;
import com.neu.his.base.service.BaseService;
import com.neu.vo.NoticeVO;

import java.util.List;

public interface NoticeService extends BaseService<NoticeVO, NoticeDTO> {


    List<NoticeVO> findNotices();
}
