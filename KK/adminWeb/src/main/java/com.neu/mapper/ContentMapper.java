package com.neu.mapper;

import com.neu.domain.Content;
import com.neu.dto.ContentDTO;
import com.neu.his.base.mapper.BaseMapper;
import com.neu.vo.ContentVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ContentMapper extends BaseMapper<ContentVO, ContentDTO, Content> {

    List<ContentVO> findLT(@Param("yourId") Integer yourId,@Param("userId") Integer userId);
}
