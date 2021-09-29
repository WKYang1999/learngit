package com.neu.mapper;

import com.neu.domain.Menu;
import com.neu.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {

    List<Menu> findLeft();

    List<MenuVO> findByParent(@Param("parentId") Integer parentId);

    List<MenuVO> findByRole(int[] roleId);

    List<Menu> findLeafMenu();
}
