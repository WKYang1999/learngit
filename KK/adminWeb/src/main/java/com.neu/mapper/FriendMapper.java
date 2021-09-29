package com.neu.mapper;

import com.neu.domain.Friend;
import com.neu.dto.FriendDTO;
import com.neu.his.base.mapper.BaseMapper;
import com.neu.vo.FriendVO;
import com.neu.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendMapper extends BaseMapper<FriendVO, FriendDTO, Friend> {

    List<UserVO> findFriend(Integer id);

    int delete(@Param("yourId") Integer a,@Param("userId") Integer b);
}
