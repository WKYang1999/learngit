package com.neu.service;

import java.util.List;

import com.neu.domain.User;
import com.neu.dto.UserDTO;
import com.neu.his.base.service.BaseService;
import com.neu.his.base.service.impl.BaseServiceImpl;
import com.neu.vo.UserVO;

public interface UserService extends BaseService<UserVO, UserDTO> {
	UserVO login(String loginName,String password);

	int bind(int userId,int roleId);

	int unbind(int userId);

    UserVO register(String loginName);

    int updatePassword(UserDTO userDTO);

    int delete(Integer[] idArray);
}
