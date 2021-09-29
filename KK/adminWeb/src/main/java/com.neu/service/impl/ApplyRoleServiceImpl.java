package com.neu.service.impl;

import com.neu.domain.ApplyRole;
import com.neu.dto.ApplyRoleDTO;
import com.neu.his.base.service.impl.BaseServiceImpl;
import com.neu.mapper.ApplyRoleMapper;
import com.neu.service.ApplyRoleService;
import com.neu.vo.ApplyRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyRoleServiceImpl extends BaseServiceImpl<ApplyRoleVO, ApplyRoleDTO, ApplyRole> implements ApplyRoleService {

	@Autowired
	public void setApplyRoleMapper(ApplyRoleMapper applyRoleMapper) {
		this.baseMapper = applyRoleMapper;
	}

	@Override
	public ApplyRoleVO findByUserId(Integer userId) {
		ApplyRoleMapper applyRoleMapper = (ApplyRoleMapper) this.baseMapper;
		return applyRoleMapper.findByUserId(userId);
	}

	@Override
	public int updateRole(Integer[] idArray) {
		ApplyRoleMapper applyRoleMapper = (ApplyRoleMapper) this.baseMapper;
		return applyRoleMapper.updateRole(idArray);
	}
}
