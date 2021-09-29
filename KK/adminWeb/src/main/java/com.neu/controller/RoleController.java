package com.neu.controller;

import com.github.pagehelper.PageInfo;
import com.neu.dto.ApplyRoleDTO;
import com.neu.dto.RoleDTO;
import com.neu.his.utils.JsonModel;
import com.neu.service.ApplyRoleService;
import com.neu.service.RoleService;
import com.neu.vo.ApplyRoleVO;
import com.neu.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	RoleService roleService;

	@GetMapping("")
	public String list(){
		return "role/list";
	}

	@GetMapping("/xx")
	@ResponseBody
	public JsonModel<List<RoleVO>> list(RoleDTO roleDTO){
		PageInfo<RoleVO> pageInfo = roleService.find(roleDTO);
		JsonModel<List<RoleVO>> jsonModel = new JsonModel<>();
		jsonModel.setData(pageInfo.getList());
		jsonModel.setCode(200);
		jsonModel.setCount((int)pageInfo.getTotal());
		return jsonModel;
	}

	@GetMapping("/add")
	public String add(Model model){
		return "role/save";
	}

	@PostMapping("")
	public String add(RoleDTO roleDTO){
		roleService.add(roleDTO);
		return  "redirect:/roles";
	}


	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model){
		RoleVO roleVO = roleService.findById(id);
		model.addAttribute("roleVO", roleVO);
		return "role/save";
	}


	@PutMapping("/{id}")
	public String edit(@PathVariable("id") Integer id,RoleDTO roleDTO){
		roleDTO.setId(id);
		roleService.update(roleDTO);
		return "redirect:/roles";
	}


	@DeleteMapping("/{ids}")
	@ResponseBody
	public JsonModel delete(@PathVariable("ids") String ids){
		String id [] = ids.split(",");
		JsonModel jsonModel = new JsonModel();
		jsonModel.setCode(200);
		jsonModel.setMsg("删除成功");

		int len = id.length;
		Integer [] idArray = new Integer[len];

		for(int i=0;i<len;i++){
			idArray[i]=Integer.parseInt(id[i]);
		}
		roleService.deleteByIds(idArray);
		return jsonModel;
	}


	@PutMapping("/{id}/auth")
	@ResponseBody
	public JsonModel auth(@PathVariable("id") int roleId,int [] menuId){
		roleService.unbind(roleId);
		int result = roleService.bind(roleId,menuId);
		JsonModel jsonModel = new JsonModel();
		jsonModel.setCode(200);
		jsonModel.setMsg("授权成功");
		return jsonModel;
	}

	@GetMapping("/{id}/auth")
	@ResponseBody
	public JsonModel auth(@PathVariable("id") int roleId){

		int [] menuId = roleService.findMenuIdById(roleId);

		JsonModel<int[]> jsonModel = new JsonModel();
		jsonModel.setCode(200);
		jsonModel.setData(menuId);
		jsonModel.setMsg("授权成功");
		return jsonModel;
	}

}
