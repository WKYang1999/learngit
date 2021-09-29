package com.neu.service.impl;

import com.neu.domain.Collections;
import com.neu.dto.CollectionsDTO;
import com.neu.his.base.service.impl.BaseServiceImpl;
import com.neu.mapper.CollectionMapper;
import com.neu.service.CollectionService;
import com.neu.vo.CollectionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionServiceImpl
		extends BaseServiceImpl<CollectionVO, CollectionsDTO, Collections> implements CollectionService {

	@Autowired
	public void setCollectionMapper(CollectionMapper collectionMapper) {
		this.baseMapper = collectionMapper;
	}

	@Override
	public CollectionVO findColl(Integer userId, Integer videoId) {
		CollectionMapper collectionMapper = (CollectionMapper) this.baseMapper;
		return collectionMapper.findColl(userId,videoId);
	}
}
