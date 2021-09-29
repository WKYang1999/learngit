package com.neu.controller;

import com.github.pagehelper.PageInfo;
import com.neu.dto.LikeDTO;
import com.neu.dto.ReportVideoDTO;
import com.neu.his.utils.JsonModel;
import com.neu.service.CommentService;
import com.neu.service.ReportVideoService;
import com.neu.vo.LikeVO;
import com.neu.vo.ReportVideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reportVideos")
public class ReportVideoController {

	@Autowired
	ReportVideoService reportVideoService;

	@Autowired
	CommentService commentService;

	@GetMapping("")
	public String list(){
		return "reportVideo/list";
	}

	@GetMapping("/xx")
	@ResponseBody
	public JsonModel<List<ReportVideoVO>> list(ReportVideoDTO reportVideoDTO){
		PageInfo<ReportVideoVO> pageInfo = reportVideoService.find(reportVideoDTO);
		JsonModel<List<ReportVideoVO>> jsonModel = new JsonModel<>();
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
		jsonModel.setMsg("已举报无效处理！");
		int len = id.length;
		Integer[] idArray = new Integer[len];
		for (int i = 0; i < len; i++) {
			idArray[i] = Integer.parseInt(id[i]);
		}
		reportVideoService.deleteByIds(idArray);
		return jsonModel;
	}

	@DeleteMapping("/delete/{ids}")
	@ResponseBody
	public JsonModel update (@PathVariable("ids") String ids){
		String id[] = ids.split(",");
		JsonModel jsonModel = new JsonModel();
		int len = id.length;
		Integer[] idArray = new Integer[len];
		for (int i = 0; i < len; i++) {
			idArray[i] = Integer.parseInt(id[i]);
		}
		reportVideoService.updateVideo(idArray);
		reportVideoService.deleteByIds(idArray);
		jsonModel.setCode(200);
		jsonModel.setMsg("下架受理！");
		return jsonModel;
	}


	@RequestMapping(value="/add",method= RequestMethod.POST)
	@ResponseBody
	public JsonModel<String> login(Integer userId, Integer videoId) {
		JsonModel jsonModel = new JsonModel();
		ReportVideoVO reportVO = reportVideoService.findReport(userId,videoId);
		if(reportVO == null){
			ReportVideoDTO reportDTO = new ReportVideoDTO();
			reportDTO.setUserId(userId);
			reportDTO.setVideoId(videoId);
			int result = reportVideoService.add(reportDTO);
			if(result != 0){
				jsonModel.setCode(200);
				jsonModel.setMsg("举报成功！");
			}else{
				jsonModel.setCode(401);
				jsonModel.setMsg("举报失败！");
			}
		}else{
			jsonModel.setCode(401);
			jsonModel.setMsg("您已举报！");
		}
		return jsonModel;
	}
}
