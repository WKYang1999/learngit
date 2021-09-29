package com.neu.controller;

import com.github.pagehelper.PageInfo;
import com.neu.dto.RecommDTO;
import com.neu.his.utils.JsonModel;
import com.neu.his.utils.TimeUtil;
import com.neu.service.RecommService;
import com.neu.vo.RecommVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/recomms")
public class RecommController {

	@Autowired
	RecommService recommService;


	@GetMapping("")
	public String list(){
		return "recomm/list";
	}

	@GetMapping("/{userId}/xx")
	@ResponseBody
	public JsonModel<List<RecommVO>> list(@PathVariable("userId") Integer userId, RecommDTO recommDTO){
		recommDTO.setUserId(userId);
		PageInfo<RecommVO> pageInfo = recommService.find(recommDTO);
		JsonModel<List<RecommVO>> jsonModel = new JsonModel<>();
		jsonModel.setData(pageInfo.getList());
		jsonModel.setCode(200);
		jsonModel.setCount((int)pageInfo.getTotal());
		return jsonModel;
	}

	@RequestMapping(value="/add",method= RequestMethod.POST)
	@ResponseBody
	public JsonModel<String> login(Integer userId, Integer videoId) {
		JsonModel jsonModel = new JsonModel();
		RecommVO recommVO = recommService.findRecomm(userId,videoId);
		if(recommVO == null){
			RecommDTO recommsDTO = new RecommDTO();
			recommsDTO.setUserId(userId);
			recommsDTO.setVideoId(videoId);
			recommsDTO.setDate(TimeUtil.getTime());
			int result = recommService.add(recommsDTO);
			if(result != 0){
				jsonModel.setCode(200);
				jsonModel.setMsg("分享成功！");
			}else{
				jsonModel.setCode(401);
				jsonModel.setMsg("分享失败！");
			}
		}else{
			jsonModel.setCode(401);
			jsonModel.setMsg("分享重复！");
		}
		return jsonModel;
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
		recommService.deleteByIds(idArray);
		return jsonModel;
	}

	@GetMapping("/{id}/cc")
	public String search(@PathVariable("id") Integer id,RecommDTO recommDTO, Model model){
		List<RecommVO> recommVOS = recommService.findCC(id);
		if(recommVOS != null){
			model.addAttribute("recom",recommVOS);
		}
		return "recomm/recommend";
	}
}
