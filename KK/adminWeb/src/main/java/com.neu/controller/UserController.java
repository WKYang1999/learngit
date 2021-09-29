package com.neu.controller;

import com.github.pagehelper.PageInfo;
import com.neu.dto.DeptDTO;
import com.neu.dto.RoleDTO;
import com.neu.dto.UserDTO;
import com.neu.his.utils.JsonModel;
import com.neu.his.utils.StringUtil;
import com.neu.service.DeptService;
import com.neu.service.MenuService;
import com.neu.service.RoleService;
import com.neu.service.UserService;
import com.neu.utils.ConstUtil;
import com.neu.utils.ExcelUtil;
import com.neu.utils.FileUploadUtil;
import com.neu.vo.DeptVO;
import com.neu.vo.MenuVO;
import com.neu.vo.RoleVO;
import com.neu.vo.UserVO;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {


	@Autowired
	UserService userService;

	@Autowired
	DeptService deptService;

	@Autowired
	RoleService roleService;

	@Autowired
	MenuService menuService;

	@GetMapping("")
	public String list(){
		return "user/list";
	}

	@GetMapping("/modify")
	public String modify(){
		return "user/modify";
	}

	@GetMapping("/perInfor")
	public String perInfor(){
		return "user/perInfor";
	}

	@GetMapping("/xx")
	@ResponseBody
	public JsonModel<List<UserVO>> list(UserDTO userDTO){
		if(StringUtil.isEmpty(userDTO.getState())){
			userDTO.setState("正常");
		}else if("1".equals(userDTO.getState())){
			userDTO.setState("注销");
		}else if("2".equals(userDTO.getState())){
			userDTO.setState("正常");
		}
		PageInfo<UserVO> pageInfo = userService.find(userDTO);
		JsonModel<List<UserVO>> jsonModel = new JsonModel<>();
		jsonModel.setData(pageInfo.getList());
		jsonModel.setCode(200);
		jsonModel.setCount((int)pageInfo.getTotal());
		return jsonModel;
	}

	@GetMapping("/add")
	public String add(Model model){
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setPaging(false);
		List<RoleVO> roles = roleService.find(roleDTO).getList();
		model.addAttribute("roles",roles);

		List<DeptVO> result = new ArrayList<>();
		deal(result,null,null,"|--");
		model.addAttribute("depts",result);

		UserVO userVO = new UserVO();
		model.addAttribute("userVO", userVO);
		return "user/save";
	}

	@PostMapping("")
	public String add(UserDTO userDTO){
		UserVO userVO = userService.register(userDTO.getLoginName());
		if(userVO==null){
			userService.add(userDTO);
		}else{
			//TODO 错误提示
		}
		return  "redirect:/users";
	}

	@PostMapping("/perInfor")
	public String perInfor(@RequestParam("file") MultipartFile[] files, UserDTO userDTO, HttpSession session){
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				System.out.println(file.getOriginalFilename());
				userDTO.setHead(file.getOriginalFilename());
				//调用储存file的函数
				FileUploadUtil.saveFile(file);
			}
		}
		int userId = userDTO.getId();
		userDTO.setRoles(false);
		int count = userService.update(userDTO);
		if(count != 0){
			session.invalidate();
			return "redirect:/login";
		}else{
			return "user/perInfor";
		}
	}

	@RequestMapping(value="/register",method= RequestMethod.POST)
	@ResponseBody
	public JsonModel<String> register(String name,String loginName, String password) {
		UserVO userVO = userService.register(loginName);
		JsonModel jsonModel = new JsonModel();
		if(StringUtil.isEmpty(loginName)){
			jsonModel.setCode(401);
			jsonModel.setMsg("账号不能为空！");
		}
		if(StringUtil.isEmpty(password)){
			jsonModel.setCode(401);
			jsonModel.setMsg("密码不能为空！");
		}
		if(userVO!=null){
			jsonModel.setCode(401);
			jsonModel.setMsg("已有该账号！");
		}else{
			UserDTO userDTO = new UserDTO();
			userDTO.setName(name);
			userDTO.setLoginName(loginName);
			userDTO.setPassword(password);
			int result = userService.add(userDTO);
			if(result != 0){
				jsonModel.setCode(200);
				jsonModel.setMsg("注册成功！");
			}
		}
		return jsonModel;
	}


	@RequestMapping(value="/modify",method= RequestMethod.POST)
	@ResponseBody
	public JsonModel<String> modify(Integer id,String loginName,String password, String newPassword) {

		JsonModel jsonModel = new JsonModel();
		if(StringUtil.isEmpty(password) || StringUtil.isEmpty(newPassword)){
			jsonModel.setCode(401);
			jsonModel.setMsg("密码不能为空！");
		}else {
			UserVO userVO = userService.login(loginName,password);
			if(userVO==null){
				jsonModel.setCode(401);
				jsonModel.setMsg("原密码错误！");
			}else{
				UserDTO userDTO = new UserDTO();
				userDTO.setId(id);
				userDTO.setLoginName(loginName);
				userDTO.setPassword(newPassword);
				userService.updatePassword(userDTO);
				jsonModel.setCode(200);
				jsonModel.setMsg("修改密码成功！");
			}
		}
		return jsonModel;
	}


	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model){
		UserVO userVO = userService.findById(id);

		model.addAttribute("userVO", userVO);

		List<DeptVO> result = new ArrayList<>();
		deal(result,null,null,"|--");
		model.addAttribute("depts",result);

		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setPaging(false);
		List<RoleVO> roles = roleService.find(roleDTO).getList();
		model.addAttribute("roles",roles);

		return "user/save";
	}


	@PutMapping("/{id}")
	public String edit(@PathVariable("id") Integer id, UserDTO userDTO){
		userDTO.setId(id);
		userService.update(userDTO);
		return "redirect:/users";
	}


	@DeleteMapping("/{ids}")
	@ResponseBody
	public JsonModel delete(@PathVariable("ids") String ids){
		String id [] = ids.split(",");
		JsonModel jsonModel = new JsonModel();
		jsonModel.setCode(200);
		jsonModel.setMsg("注销成功");
		int len = id.length;
		Integer [] idArray = new Integer[len];
		for(int i=0;i<len;i++){
			idArray[i]=Integer.parseInt(id[i]);
		}
		userService.deleteByIds(idArray);
		return jsonModel;
	}

	@DeleteMapping("/yy/{ids}")
	@ResponseBody
	public JsonModel deleteSys(@PathVariable("ids") String ids){
		String id [] = ids.split(",");
		JsonModel jsonModel = new JsonModel();
		jsonModel.setCode(200);
		jsonModel.setMsg("启用成功！");
		int len = id.length;
		Integer [] idArray = new Integer[len];
		for(int i=0;i<len;i++){
			idArray[i]=Integer.parseInt(id[i]);
		}
		userService.delete(idArray);
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


	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {


		//步骤
		     //加载数据
		     //创建POI相关的对象
		     //下载

		UserDTO userDTO = new UserDTO();
		userDTO.setPaging(false);
		PageInfo<UserVO> pageInfo = userService.find(userDTO);

		List<UserVO> users = pageInfo.getList();
		//反射知识

		List<Map<String,String>> data = new ArrayList<Map<String,String>>();


		Map<String,String> one = new HashMap<>();
		one.put("name", "zs");
		one.put("loginName", "zs");

		Map<String,String> two = new HashMap<>();
		two.put("name", "zs");
		two.put("loginName", "zs");

		data.add(one);
		data.add(two);


		Workbook workbook = ExcelUtil.writer(new String[]{"名称","账号"},data,"用户数据");

		response.setHeader("content-disposition", "attachment;filename=xx.xls");

		workbook.write(response.getOutputStream());


	}
}
