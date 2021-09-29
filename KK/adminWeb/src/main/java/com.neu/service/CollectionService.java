package com.neu.service;

import com.neu.dto.CollectionsDTO;
import com.neu.his.base.service.BaseService;
import com.neu.vo.CollectionVO;

public interface CollectionService extends BaseService<CollectionVO, CollectionsDTO> {

    CollectionVO findColl(Integer userId, Integer videoId);

}
