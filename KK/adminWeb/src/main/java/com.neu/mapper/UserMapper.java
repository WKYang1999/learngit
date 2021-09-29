package com.neu.mapper;

import com.neu.domain.User;
import com.neu.dto.UserDTO;
import com.neu.his.base.mapper.BaseMapper;
import com.neu.vo.UserVO;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<UserVO, UserDTO,User> {
    UserVO login(@Param("loginName") String loginName,@Param("password")String password);

    int bind(@Param("userId")int userId,@Param("roleId")int roleId);

    int unbind(int userId);

    UserVO register(String loginName);

    int updatePassword(User user);

    int delete(Integer[] idArray);
}
