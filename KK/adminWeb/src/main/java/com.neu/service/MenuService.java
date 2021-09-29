package com.neu.service;

import com.neu.domain.Menu;
import com.neu.domain.User;
import com.neu.vo.MenuVO;

import java.util.List;

public interface MenuService {

    List<Menu> findLeft();

    List<MenuVO> findByParent(Integer parentId);

    List<MenuVO> findByRole(int[] roleId);

    List<Menu> findLeafMenu();
}
