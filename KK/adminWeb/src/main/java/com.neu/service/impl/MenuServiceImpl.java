package com.neu.service.impl;

import com.neu.domain.Menu;
import com.neu.mapper.MenuMapper;
import com.neu.service.MenuService;
import com.neu.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuMapper menuMapper;


	@Override
	public List<Menu> findLeft() {
		return menuMapper.findLeft();
	}

	@Override
	public List<MenuVO> findByParent(Integer parentId) {
		return menuMapper.findByParent(parentId);
	}

	@Override
	public List<MenuVO> findByRole(int[] roleId) {
		return menuMapper.findByRole(roleId);
	}

	@Override
	public List<Menu> findLeafMenu() {
		return menuMapper.findLeafMenu();
	}
}
