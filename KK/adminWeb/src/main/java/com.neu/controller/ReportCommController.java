package com.neu.controller;

import com.github.pagehelper.PageInfo;
import com.neu.dto.ReportCommDTO;
import com.neu.his.utils.JsonModel;
import com.neu.service.CommentService;
import com.neu.service.ReportCommService;
import com.neu.vo.ReportCommVO;
import com.neu.vo.ReportVideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reportComms")
public class ReportCommController {

	@Autowired
	ReportCommService reportCommService;

	@Autowired
	CommentService commentService;

	@GetMapping("")
	public String list(){
		return "reportComm/list";
	}

	@GetMapping("/xx")
	@ResponseBody
	public JsonModel<List<ReportCommVO>> list(ReportCommDTO reportCommDTO){
		PageInfo<ReportCommVO> pageInfo = reportCommService.find(reportCommDTO);
		JsonModel<List<ReportCommVO>> jsonModel = new JsonModel<>();
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
			System.out.println("================="+Integer.parseInt(id[i])+"==================");
		}
		reportCommService.deleteByIds(idArray);
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
			System.out.println("================="+Integer.parseInt(id[i])+"==================");
			idArray[i] = Integer.parseInt(id[i]);
		}
		reportCommService.deleteByIds(idArray);
		commentService.deleteByIds(idArray);
		jsonModel.setCode(200);
		jsonModel.setMsg("删除评论成功！");
		return jsonModel;
	}

	@GetMapping("/{videoId}/{userId}/{id}")
	public String comm(@PathVariable("videoId") Integer videoId,@PathVariable("userId") Integer userId,@PathVariable("id") Integer id){

		ReportCommVO reportCommVO = reportCommService.findReport(userId,id);
		if(reportCommVO == null){
			ReportCommDTO reportCommDTO = new ReportCommDTO();
			reportCommDTO.setUserId(userId);
			reportCommDTO.setCommId(id);
			reportCommService.add(reportCommDTO);
		}
		return "redirect:/videos/"+videoId+"/sp";
	}
	/*@RequestMapping(value="/add",method= RequestMethod.POST)
	@ResponseBody
	public JsonModel<String> login(Integer userId, Integer videoId) {
		JsonModel jsonModel = new JsonModel();
		ReportCommVO reportVO = reportCommService.findReport(userId,videoId);
		if(reportVO == null){
			ReportCommDTO reportDTO = new ReportCommDTO();
			reportDTO.setUserId(userId);
			reportDTO.setCommId(videoId);
			int result = reportCommService.add(reportDTO);
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
	}*/
}
