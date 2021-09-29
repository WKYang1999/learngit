package com.neu.controller;

import com.github.pagehelper.PageInfo;
import com.neu.dto.FriendApplyDTO;
import com.neu.dto.FriendDTO;
import com.neu.his.utils.JsonModel;
import com.neu.service.FriendApplyService;
import com.neu.service.FriendService;
import com.neu.vo.FriendApplyVO;
import com.neu.vo.FriendVO;
import com.neu.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/friendApplys")
public class FriendApplyController {

	@Autowired
	FriendApplyService friendApplyService;

	@Autowired
	FriendService friendService;

	@GetMapping("")
	public String list(){
		return "friendApply/list";
	}

	@GetMapping("/{userId}/xx")
	@ResponseBody
	public JsonModel<List<FriendApplyVO>> list(@PathVariable("userId") Integer userId, FriendApplyDTO friendApplyDTO){
		friendApplyDTO.setUserId(userId);
		PageInfo<FriendApplyVO> pageInfo = friendApplyService.find(friendApplyDTO);
		JsonModel<List<FriendApplyVO>> jsonModel = new JsonModel<>();
		jsonModel.setData(pageInfo.getList());
		jsonModel.setCode(200);
		jsonModel.setCount((int)pageInfo.getTotal());
		return jsonModel;
	}

	/*@RequestMapping(value="/add",method= RequestMethod.POST)
	@ResponseBody
	public JsonModel<String> login(Integer userId, Integer friendApplyId) {
		JsonModel jsonModel = new JsonModel();
		FriendVO friendApplyVO = friendApplyService.findColl(userId,friendApplyId);
		if(friendApplyVO == null){
			FriendDTO friendApplyDTO = new FriendDTO();
			friendApplyDTO.setUserId(userId);
			friendApplyDTO.setFriendId(friendApplyId);
			int result = friendApplyService.add(friendApplyDTO);
			if(result != 0){
				jsonModel.setCode(200);
				jsonModel.setMsg("收藏成功！");
			}else{
				jsonModel.setCode(401);
				jsonModel.setMsg("收藏失败！");
			}
		}else{
			jsonModel.setCode(401);
			jsonModel.setMsg("您已收藏！");
		}
		return jsonModel;
	}*/

	@DeleteMapping("/{ids}")
	@ResponseBody
	public JsonModel delete (@PathVariable("ids") String ids){
		String id[] = ids.split(",");
		JsonModel jsonModel = new JsonModel();
		jsonModel.setCode(200);
		jsonModel.setMsg("不同意");
		int len = id.length;
		Integer[] idArray = new Integer[len];

		for (int i = 0; i < len; i++) {
			idArray[i] = Integer.parseInt(id[i]);
		}
		friendApplyService.deleteByIds(idArray);
		return jsonModel;
	}

	@DeleteMapping("/delete/{ids}")
	@ResponseBody
	public JsonModel update (@PathVariable("ids") String ids){
		String id[] = ids.split(",");
		JsonModel jsonModel = new JsonModel();
		jsonModel.setCode(200);
		jsonModel.setMsg("已同意");
		int len = id.length;
		Integer[] idArray = new Integer[len];

		for (int i = 0; i < len; i++) {
			idArray[i] = Integer.parseInt(id[i]);
			deal(Integer.parseInt(id[i]));
		}
		friendApplyService.deleteByIds(idArray);
		return jsonModel;
	}

	private void deal(Integer id){
		FriendApplyVO friendApplyVO = friendApplyService.findById(id);
		int a = friendApplyVO.getUserId();
		int b = friendApplyVO.getYourId();
		FriendDTO friendDTO = new FriendDTO();
		friendDTO.setYourId(a);
		friendDTO.setUserId(b);
		friendService.add(friendDTO);
		friendDTO.setYourId(b);
		friendDTO.setUserId(a);
		friendService.add(friendDTO);
	}
}
