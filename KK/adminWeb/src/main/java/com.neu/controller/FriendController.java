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
@RequestMapping("/friends")
public class FriendController {

	@Autowired
	FriendService friendService;

	@Autowired
	FriendApplyService friendApplyService;

	@GetMapping("")
	public String list(){
		return "friend/list";
	}

	@GetMapping("/{yourId}/xx")
	@ResponseBody
	public JsonModel<List<FriendVO>> list(@PathVariable("yourId") Integer yourId, FriendDTO friendDTO){
		friendDTO.setYourId(yourId);
		PageInfo<FriendVO> pageInfo = friendService.find(friendDTO);
		JsonModel<List<FriendVO>> jsonModel = new JsonModel<>();
		jsonModel.setData(pageInfo.getList());
		jsonModel.setCode(200);
		jsonModel.setCount((int)pageInfo.getTotal());
		return jsonModel;
	}

	@GetMapping("/{id}/add")
	public String add(@PathVariable("id") Integer id, Model model){

		List<UserVO> result = new ArrayList<>();
		deal(result,id);
		model.addAttribute("friends",result);
		return "friend/save";
	}

	private void deal(List<UserVO> result, Integer id){
		List<UserVO> tops = friendService.findFriend(id);
		for(UserVO top:tops) {
			result.add(top);
		}
	}

	@PostMapping("")
	public String add(FriendApplyDTO friendApplyDTO) {
		friendApplyService.add(friendApplyDTO);
		return "redirect:/friends";
	}

	@DeleteMapping("/{ids}")
	@ResponseBody
	public JsonModel delete (@PathVariable("ids") String ids){
		String id[] = ids.split(",");
		JsonModel jsonModel = new JsonModel();
		int len = id.length;
		Integer[] idArray = new Integer[len];
		for (int i = 0; i < len; i++) {
			idArray[i] = Integer.parseInt(id[i]);
			deal(Integer.parseInt(id[i]));
		}
		jsonModel.setCode(200);
		jsonModel.setMsg("删除成功!");
		return jsonModel;
	}

	private void deal(Integer id){
		FriendVO friendVO = friendService.findById(id);
		Integer a = friendVO.getUserId();
		Integer b = friendVO.getYourId();
		friendService.delete(a,b);
		friendService.delete(b,a);
	}
}
