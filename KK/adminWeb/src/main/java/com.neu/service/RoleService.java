package com.neu.service;

import com.neu.dto.RoleDTO;
import com.neu.his.base.service.BaseService;
import com.neu.vo.RoleVO;

public interface RoleService extends BaseService<RoleVO, RoleDTO> {
    int [] findByUserId(int userId);

    int bind(int roleId, int[] menuId);

    int unbind(int roleId);

    int[] findMenuIdById(int roleId);
}
