package com.neu.service;

import com.neu.dto.FriendDTO;
import com.neu.his.base.service.BaseService;
import com.neu.vo.FriendVO;
import com.neu.vo.UserVO;

import java.util.List;

public interface FriendService extends BaseService<FriendVO, FriendDTO> {

    List<UserVO> findFriend(Integer id);

    int delete(Integer a, Integer b);
}
