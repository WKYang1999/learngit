package com.neu.service;

import com.neu.dto.ApplyRoleDTO;
import com.neu.his.base.service.BaseService;
import com.neu.vo.ApplyRoleVO;

public interface ApplyRoleService extends BaseService<ApplyRoleVO, ApplyRoleDTO> {

    ApplyRoleVO findByUserId(Integer userId);

    int updateRole(Integer[] idArray);
}
