package com.neu.mapper;

import com.neu.domain.ApplyRole;
import com.neu.dto.ApplyRoleDTO;
import com.neu.his.base.mapper.BaseMapper;
import com.neu.vo.ApplyRoleVO;

public interface ApplyRoleMapper extends BaseMapper<ApplyRoleVO, ApplyRoleDTO, ApplyRole> {

    ApplyRoleVO findByUserId(Integer userId);

    int updateRole(Integer[] idArray);
}
