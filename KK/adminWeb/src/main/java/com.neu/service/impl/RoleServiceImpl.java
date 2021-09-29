package com.neu.service.impl;

import com.neu.domain.Role;
import com.neu.dto.RoleDTO;
import com.neu.his.base.service.impl.BaseServiceImpl;
import com.neu.mapper.RoleMapper;
import com.neu.service.RoleService;
import com.neu.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleVO,RoleDTO,Role> implements RoleService {
	@Autowired
	public void setRoleMapper(RoleMapper roleMapper) {
		this.baseMapper = roleMapper;
	}

	@Override
	public int[] findByUserId(int userId) {
		RoleMapper roleMapper = (RoleMapper) this.baseMapper;
		return roleMapper.findByUserId(userId);
	}

	@Override
	public int bind(int roleId, int[] menuId) {
		RoleMapper roleMapper = (RoleMapper) this.baseMapper;
		int result=0;
		for(int temp:menuId){
			result = result + roleMapper.bind(roleId,temp);
		}
		return result;
	}

	@Override
	public int unbind(int roleId) {
		RoleMapper roleMapper = (RoleMapper) this.baseMapper;
		return roleMapper.unbind(roleId);
	}

	@Override
	public int[] findMenuIdById(int roleId) {
		RoleMapper roleMapper = (RoleMapper) this.baseMapper;
		return roleMapper.findMenuIdById(roleId);
	}
}
