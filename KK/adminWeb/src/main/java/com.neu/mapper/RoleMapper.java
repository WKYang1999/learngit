package com.neu.mapper;

import com.neu.domain.Role;
import com.neu.dto.RoleDTO;
import com.neu.his.base.mapper.BaseMapper;
import com.neu.vo.RoleVO;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper  extends BaseMapper<RoleVO, RoleDTO, Role> {
    int[] findByUserId(int userId);

    int bind(@Param("roleId") int roleId,@Param("menuId") int menuId);

    int unbind(int roleId);

    int[] findMenuIdById(int roleId);
}
