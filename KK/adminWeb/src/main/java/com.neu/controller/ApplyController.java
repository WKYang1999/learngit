package com.neu.controller;

import com.github.pagehelper.PageInfo;
import com.neu.dto.ApplyRoleDTO;
import com.neu.his.utils.JsonModel;
import com.neu.service.ApplyRoleService;
import com.neu.service.CommentService;
import com.neu.vo.ApplyRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/applys")
public class ApplyController {

	@Autowired
	ApplyRoleService applyRoleService;

	@Autowired
	CommentService commentService;

	@GetMapping("")
	public String list(){
		return "apply/list";
	}

	@GetMapping("/save")
	public String save(){
		return "apply/save";
	}

	@GetMapping("/xx")
	@ResponseBody
	public JsonModel<List<ApplyRoleVO>> list(ApplyRoleDTO applyDTO){
		PageInfo<ApplyRoleVO> pageInfo = applyRoleService.find(applyDTO);
		JsonModel<List<ApplyRoleVO>> jsonModel = new JsonModel<>();
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
		jsonModel.setMsg("已处理！");

		int len = id.length;
		Integer[] idArray = new Integer[len];

		for (int i = 0; i < len; i++) {
			idArray[i] = Integer.parseInt(id[i]);
		}
		applyRoleService.deleteByIds(idArray);
		return jsonModel;
	}

	@DeleteMapping("/delete/{ids}")
	@ResponseBody
	public JsonModel updateRole (@PathVariable("ids") String ids){
		String id[] = ids.split(",");
		JsonModel jsonModel = new JsonModel();
		jsonModel.setCode(200);
		jsonModel.setMsg("已审批！");

		int len = id.length;
		Integer[] idArray = new Integer[len];
		for (int i = 0; i < len; i++) {
			idArray[i] = Integer.parseInt(id[i]);
		}
		applyRoleService.updateRole(idArray);
		applyRoleService.deleteByIds(idArray);
		return jsonModel;
	}

	@RequestMapping(value="/add",method= RequestMethod.POST)
	@ResponseBody
	public JsonModel<String> applyAdd(Integer userId){
		JsonModel jsonModel = new JsonModel();
		ApplyRoleVO applyVO = applyRoleService.findByUserId(userId);
		if(applyVO != null){
			jsonModel.setCode(401);
			jsonModel.setMsg("您已申请！");
			return jsonModel;
		}
		ApplyRoleDTO applyRoleDTO = new ApplyRoleDTO();
		applyRoleDTO.setUserId(userId);
		int count = applyRoleService.add(applyRoleDTO);
		if(count != 0){
			jsonModel.setCode(200);
			jsonModel.setMsg("申请成功！");
		}else{
			jsonModel.setCode(401);
			jsonModel.setMsg("申请失败！");
		}
		return jsonModel;
	}
}
