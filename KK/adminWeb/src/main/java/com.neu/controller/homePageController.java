package com.neu.controller;

import com.github.pagehelper.PageInfo;
import com.neu.dto.HomePageDTO;
import com.neu.his.utils.JsonModel;
import com.neu.service.CommentService;
import com.neu.service.HomePageService;
import com.neu.vo.HomePageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/homePages")
public class homePageController {

	@Autowired
	HomePageService homePageService;

	@Autowired
	CommentService commentService;

	@GetMapping("")
	public String list(){
		return "homePage/list";
	}

	@GetMapping("/xx")
	@ResponseBody
	public JsonModel<List<HomePageVO>> list(HomePageDTO homePageDTO){
		PageInfo<HomePageVO> pageInfo = homePageService.find(homePageDTO);
		JsonModel<List<HomePageVO>> jsonModel = new JsonModel<>();
		jsonModel.setData(pageInfo.getList());
		jsonModel.setCode(200);
		jsonModel.setCount((int)pageInfo.getTotal());
		return jsonModel;
	}

	@DeleteMapping("/{ids}")
	@ResponseBody
	public JsonModel delete (@PathVariable("ids") String ids){
		String id[] = ids.split(",");
		JsonModel jsonModel = new JsonModel();
		jsonModel.setCode(200);
		jsonModel.setMsg("已下架！");
		int len = id.length;
		Integer[] idArray = new Integer[len];
		for (int i = 0; i < len; i++) {
			idArray[i] = Integer.parseInt(id[i]);
		}
		homePageService.updateState(idArray);
		return jsonModel;
	}

	@DeleteMapping("/delete/{ids}")
	@ResponseBody
	public JsonModel update (@PathVariable("ids") String ids){
		String id[] = ids.split(",");
		JsonModel jsonModel = new JsonModel();
		jsonModel.setCode(200);
		jsonModel.setMsg("已上架！");
		int len = id.length;
		Integer[] idArray = new Integer[len];
		for (int i = 0; i < len; i++) {
			idArray[i] = Integer.parseInt(id[i]);
		}
		homePageService.updateSS(idArray);
		return jsonModel;
	}

}
