package com.neu.service.impl;

import com.neu.domain.Friend;
import com.neu.dto.FriendDTO;
import com.neu.his.base.service.impl.BaseServiceImpl;
import com.neu.mapper.FriendMapper;
import com.neu.service.FriendService;
import com.neu.vo.FriendVO;
import com.neu.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl
		extends BaseServiceImpl<FriendVO, FriendDTO, Friend> implements FriendService {

	@Autowired
	public void setFriendMapper(FriendMapper friendMapper) {
		this.baseMapper = friendMapper;
	}

	@Override
	public List<UserVO> findFriend(Integer id) {
		FriendMapper friendMapper = (FriendMapper) this.baseMapper;
		return friendMapper.findFriend(id);
	}

	@Override
	public int delete(Integer a, Integer b) {
		FriendMapper friendMapper = (FriendMapper) this.baseMapper;
		return friendMapper.delete(a,b);
	}
}
