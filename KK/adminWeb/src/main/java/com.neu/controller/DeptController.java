package com.neu.controller;

import com.github.pagehelper.PageInfo;
import com.neu.dto.DeptDTO;
import com.neu.his.utils.JsonModel;
import com.neu.service.DeptService;
import com.neu.vo.DeptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/depts")
public class DeptController {

	@Autowired
	DeptService deptService;

	@GetMapping("")
	public String list(){
		return "dept/list";
	}

	@GetMapping("/xx")
	@ResponseBody
	public JsonModel<List<DeptVO>> list(DeptDTO deptDTO){

		PageInfo<DeptVO> pageInfo = deptService.find(deptDTO);

		JsonModel<List<DeptVO>> jsonModel = new JsonModel<>();
		jsonModel.setData(pageInfo.getList());
		jsonModel.setCode(200);
		jsonModel.setCount((int)pageInfo.getTotal());
		return jsonModel;
	}

	@GetMapping("/add")
	public String add(Model model){
		//加载部门数据-->request作用域中
		List<DeptVO> result = new ArrayList<>();
		deal(result,null,null,"|--");
		model.addAttribute("depts",result);
		return "dept/save";
	}

	@PostMapping("")
	public String add(DeptDTO deptDTO){
		deptService.add(deptDTO);
		return  "redirect:/depts";
	}


	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model){
		DeptVO deptVO = deptService.findById(id);
		//查找所有的部门树
		List<DeptVO> result = new ArrayList<>();
		deal(result,null,id,"|--");
		model.addAttribute("depts",result);
		model.addAttribute("deptVO", deptVO);
		return "dept/save";
	}


	@PutMapping("/{id}")
	public String edit(@PathVariable("id") Integer id,DeptDTO deptDTO){
		deptDTO.setId(id);
		deptService.update(deptDTO);
		return "redirect:/depts";
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
		deptService.deleteByIds(idArray);
		return jsonModel;
	}

	private void deal(List<DeptVO> result,Integer parentId,Integer stopId,String prefix){
		DeptDTO deptDTO = new DeptDTO();
		deptDTO.setParentId(parentId);
		deptDTO.setPaging(false);

		List<DeptVO> tops = deptService.find(deptDTO).getList();

		for(DeptVO top:tops){
			if(stopId!=null && top.getId().equals(stopId)){
				continue;
			}
			top.setName(prefix+top.getName());
			result.add(top);
			deal(result,top.getId(),stopId,"&nbsp;&nbsp;"+prefix);
		}
	}
}
