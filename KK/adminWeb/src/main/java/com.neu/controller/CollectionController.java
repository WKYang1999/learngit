package com.neu.controller;

import com.github.pagehelper.PageInfo;
import com.neu.dto.CollectionsDTO;
import com.neu.his.utils.JsonModel;
import com.neu.service.CollectionService;
import com.neu.vo.CollectionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/collections")
public class CollectionController {

	@Autowired
	CollectionService collectionService;


	@GetMapping("")
	public String list(){
		return "collection/list";
	}

	@GetMapping("/{userId}/xx")
	@ResponseBody
	public JsonModel<List<CollectionVO>> list(@PathVariable("userId") Integer userId, CollectionsDTO collectionsDTO){
		collectionsDTO.setUserId(userId);
		PageInfo<CollectionVO> pageInfo = collectionService.find(collectionsDTO);
		JsonModel<List<CollectionVO>> jsonModel = new JsonModel<>();
		jsonModel.setData(pageInfo.getList());
		jsonModel.setCode(200);
		jsonModel.setCount((int)pageInfo.getTotal());
		return jsonModel;
	}

	@GetMapping("/add")
	public String add(){
		return "collection/save";
	}

	@PostMapping("")
	public String add(CollectionsDTO collectionsDTO) {
		collectionService.add(collectionsDTO);
		return "redirect:/collections";
	}

	@RequestMapping(value="/add",method= RequestMethod.POST)
	@ResponseBody
	public JsonModel<String> login(Integer userId, Integer videoId) {
		JsonModel jsonModel = new JsonModel();
		CollectionVO collectionVO = collectionService.findColl(userId,videoId);
		if(collectionVO == null){
			CollectionsDTO collectionsDTO = new CollectionsDTO();
			collectionsDTO.setUserId(userId);
			collectionsDTO.setVideoId(videoId);
			int result = collectionService.add(collectionsDTO);
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
	}

		@GetMapping("/{id}/edit")
		public String edit (@PathVariable("id") Integer id, Model model){
			CollectionVO collectionVO = collectionService.findById(id);
			model.addAttribute("collectionVO", collectionVO);
			return "collection/save";
		}


		@PutMapping("/{id}")
		public String edit (@PathVariable("id") Integer id, CollectionsDTO collectionDTO){
			collectionDTO.setId(id);
			collectionService.update(collectionDTO);
			return "redirect:/collections";
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
			collectionService.deleteByIds(idArray);
			return jsonModel;
		}
}
