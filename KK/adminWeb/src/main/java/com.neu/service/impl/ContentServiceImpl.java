package com.neu.service.impl;

import com.neu.domain.Content;
import com.neu.dto.ContentDTO;
import com.neu.his.base.service.impl.BaseServiceImpl;
import com.neu.mapper.ContentMapper;
import com.neu.service.ContentService;
import com.neu.vo.ContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl extends BaseServiceImpl<ContentVO, ContentDTO, Content> implements ContentService {

	@Autowired
	public void setContentMapper(ContentMapper contentMapper) {
		this.baseMapper = contentMapper;
	}

	@Override
	public List<ContentVO> findLT(Integer yourId, Integer userId) {
		ContentMapper contentMapper = (ContentMapper) this.baseMapper;
		return contentMapper.findLT(yourId,userId);
	}
}
