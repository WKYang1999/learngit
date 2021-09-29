package com.neu.service.impl;

import com.neu.domain.Notice;
import com.neu.dto.NoticeDTO;
import com.neu.his.base.service.impl.BaseServiceImpl;
import com.neu.mapper.NoticeMapper;
import com.neu.service.NoticeService;
import com.neu.vo.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl extends BaseServiceImpl<NoticeVO, NoticeDTO,Notice> implements NoticeService {

	@Autowired
	public void setNoticeMapper(NoticeMapper noticeMapper) {
		this.baseMapper = noticeMapper;
	}

	@Override
	public List<NoticeVO> findNotices() {
		NoticeMapper noticeMapper = (NoticeMapper)this.baseMapper;
		return noticeMapper.findNotices();
	}
}
