package com.neu.controller;

import com.github.pagehelper.PageInfo;
import com.neu.dto.LikeDTO;
import com.neu.his.utils.JsonModel;
import com.neu.service.LikeService;
import com.neu.vo.LikeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/likes")
public class LikeController {

	@Autowired
	LikeService likeService;


	@GetMapping("")
	public String list(){
		return "like/list";
	}

	@GetMapping("/{userId}/xx")
	@ResponseBody
	public JsonModel<List<LikeVO>> list(@PathVariable("userId") Integer userId, LikeDTO likeDTO){
		likeDTO.setUserId(userId);
		PageInfo<LikeVO> pageInfo = likeService.find(likeDTO);
		JsonModel<List<LikeVO>> jsonModel = new JsonModel<>();
		jsonModel.setData(pageInfo.getList());
		jsonModel.setCode(200);
		jsonModel.setCount((int)pageInfo.getTotal());
		return jsonModel;
	}

	@GetMapping("/add")
	public String add(){
		return "like/save";
	}

	@PostMapping("")
	public String add(LikeDTO likeDTO) {
		likeService.add(likeDTO);
		return "redirect:/likes";
	}

	@RequestMapping(value="/add",method= RequestMethod.POST)
	@ResponseBody
	public JsonModel<String> likes(Integer userId, Integer videoId) {
		JsonModel jsonModel = new JsonModel();
		LikeVO likeVO = likeService.findLike(userId,videoId);
		if(likeVO == null){
			LikeDTO likesDTO = new LikeDTO();
			likesDTO.setUserId(userId);
			likesDTO.setVideoId(videoId);
			int result = likeService.add(likesDTO);
			if(result != 0){
				jsonModel.setCode(200);
				jsonModel.setMsg("点赞成功！");
			}else{
				jsonModel.setCode(401);
				jsonModel.setMsg("点赞失败！");
			}
		}else{
			jsonModel.setCode(401);
			jsonModel.setMsg("您已点赞！");
		}
		return jsonModel;
	}

		@GetMapping("/{id}/edit")
		public String edit (@PathVariable("id") Integer id, Model model){
			LikeVO likeVO = likeService.findById(id);
			model.addAttribute("likeVO", likeVO);
			return "like/save";
		}


		@PutMapping("/{id}")
		public String edit (@PathVariable("id") Integer id, LikeDTO likeDTO){
			likeDTO.setId(id);
			likeService.update(likeDTO);
			return "redirect:/likes";
		}


		@DeleteMapping("/{ids}")
		@ResponseBody
		public JsonModel delete (@PathVariable("ids") String ids){
			String id[] = ids.split(",");
			JsonModel jsonModel = new JsonModel();
			jsonModel.setCode(200);
			jsonModel.setMsg("删除成功");

			int len = id.length;
			Integer[] idArray = new Integer[len];

			for (int i = 0; i < len; i++) {
				idArray[i] = Integer.parseInt(id[i]);
			}
			likeService.deleteByIds(idArray);
			return jsonModel;
		}
}
