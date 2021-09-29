package com.neu.mapper;

import com.neu.domain.Recomm;
import com.neu.dto.RecommDTO;
import com.neu.his.base.mapper.BaseMapper;
import com.neu.vo.RecommVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommMapper extends BaseMapper<RecommVO, RecommDTO, Recomm> {

    RecommVO findRecomm(@Param("userId") Integer userId,@Param("videoId") Integer videoId);

    List<RecommVO> findCC(Integer id);
}
