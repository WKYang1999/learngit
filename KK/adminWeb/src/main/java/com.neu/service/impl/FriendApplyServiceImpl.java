package com.neu.service.impl;

import com.neu.domain.FriendApply;
import com.neu.dto.FriendApplyDTO;
import com.neu.his.base.service.impl.BaseServiceImpl;
import com.neu.mapper.FriendApplyMapper;
import com.neu.service.FriendApplyService;
import com.neu.vo.FriendApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendApplyServiceImpl
		extends BaseServiceImpl<FriendApplyVO, FriendApplyDTO, FriendApply> implements FriendApplyService {
	@Autowired
	public void setFriendApplyMapper(FriendApplyMapper friendApplyMapper) {
		this.baseMapper = friendApplyMapper;
	}

}
