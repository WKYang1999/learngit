package com.neu.mapper;

import com.neu.domain.Collections;
import com.neu.dto.CollectionsDTO;
import com.neu.his.base.mapper.BaseMapper;
import com.neu.vo.CollectionVO;
import org.apache.ibatis.annotations.Param;

public interface CollectionMapper extends BaseMapper<CollectionVO, CollectionsDTO, Collections> {

    CollectionVO findColl(@Param("userId") Integer userId,@Param("videoId") Integer videoId);

}
