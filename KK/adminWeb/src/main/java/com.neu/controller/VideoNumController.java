package com.neu.controller;

import com.github.pagehelper.PageInfo;
import com.neu.dto.VideoDTO;
import com.neu.dto.VideoNumDTO;
import com.neu.his.utils.JsonModel;
import com.neu.his.utils.TimeUtil;
import com.neu.service.VideoNumService;
import com.neu.service.VideoService;
import com.neu.utils.FileUploadUtil;
import com.neu.vo.VideoNumVO;
import com.neu.vo.VideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/videoNums")
public class VideoNumController {

	@Autowired
	VideoNumService videoNumService;

	@Autowired
	VideoService videoService;

	@GetMapping("")
	public String list(){
		return "videoNum/list";
	}

	@GetMapping("/videoNumUP")
	public String listUP(){
		return "videoNumUP/list";
	}

	@GetMapping("/xx")
	@ResponseBody
	public JsonModel<List<VideoNumVO>> list(VideoNumDTO videoNumDTO){
		PageInfo<VideoNumVO> pageInfo = videoNumService.find(videoNumDTO);
		JsonModel<List<VideoNumVO>> jsonModel = new JsonModel<>();
		jsonModel.setData(pageInfo.getList());
		jsonModel.setCode(200);
		jsonModel.setCount((int)pageInfo.getTotal());
		return jsonModel;
	}

	@GetMapping("/{id}/xx")
	@ResponseBody
	public JsonModel<List<VideoNumVO>> listUP(@PathVariable("id") Integer id,VideoNumDTO videoNumDTO){
		videoNumDTO.setUploadId(id);
		PageInfo<VideoNumVO> pageInfo = videoNumService.find(videoNumDTO);
		JsonModel<List<VideoNumVO>> jsonModel = new JsonModel<>();
		jsonModel.setData(pageInfo.getList());
		jsonModel.setCode(200);
		jsonModel.setCount((int)pageInfo.getTotal());
		return jsonModel;
	}

	@PostMapping("")
	public String add(@RequestParam("file") MultipartFile[] files, VideoNumDTO videoNumDTO){
		videoNumDTO.setDate(TimeUtil.getTime());
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				System.out.println(file.getOriginalFilename());
				videoNumDTO.setSp(file.getOriginalFilename());
				//调用储存file的函数
				FileUploadUtil.saveFile(file);
			}
		}
		videoNumService.add(videoNumDTO);
		return  "redirect:/videoNums";
	}

	@PostMapping("/myVideo")
	public String addUP(@RequestParam("file") MultipartFile[] files, VideoNumDTO videoNumDTO){
		videoNumDTO.setDate(TimeUtil.getTime());
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				System.out.println(file.getOriginalFilename());
				videoNumDTO.setSp(file.getOriginalFilename());
				//调用储存file的函数
				FileUploadUtil.saveFile(file);
			}
		}
		videoNumService.add(videoNumDTO);
		return  "redirect:/videoNums/videoNumUP";
	}

	@GetMapping("/add")
	public String add(Model model){
		//加载部门数据-->request作用域中
		List<VideoVO> result = new ArrayList<>();
		deal(result);
		model.addAttribute("videos",result);
		return "videoNum/save";
	}

	@GetMapping("/{userId}/add")
	public String addUP(@PathVariable("userId") Integer userId,Model model){
		//加载部门数据-->request作用域中
		List<VideoVO> result = new ArrayList<>();
		deal(result,userId);
		model.addAttribute("videos",result);
		return "videoNumUP/save";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model){
		VideoNumVO videoNumVO = videoNumService.findById(id);
		//查找上级名称
		List<VideoVO> result = new ArrayList<>();
		deal(result);
		model.addAttribute("videos",result);
		model.addAttribute("videoNumVOs", videoNumVO);
		return "videoNum/save";
	}

	@GetMapping("/{userId}/{id}/edit")
	public String editUP(@PathVariable("userId") Integer userId,@PathVariable("id") Integer id, Model model){
		VideoNumVO videoNumVO = videoNumService.findById(id);
		//查找上级名称
		List<VideoVO> result = new ArrayList<>();
		deal(result,userId);
		model.addAttribute("videos",result);
		model.addAttribute("videoNumVOs", videoNumVO);
		return "videoNumUP/save";
	}
	
	private void deal(List<VideoVO> result){
		VideoDTO videoDTO = new VideoDTO();
		videoDTO.setPaging(false);
		List<VideoVO> tops = videoService.find(videoDTO).getList();
		for(VideoVO top:tops) {
			result.add(top);
		}
	}

	private void deal(List<VideoVO> result,Integer id){
		VideoDTO videoDTO = new VideoDTO();
		videoDTO.setUploadId(id);
		videoDTO.setPaging(false);
		List<VideoVO> tops = videoService.find(videoDTO).getList();
		for(VideoVO top:tops) {
			result.add(top);
		}
	}

	@PutMapping("/{id}")
	public String edit(@RequestParam("file") MultipartFile[] files,@PathVariable("id") Integer id,VideoNumDTO videoNumDTO){
		videoNumDTO.setId(id);
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				System.out.println(file.getOriginalFilename());
				videoNumDTO.setSp(file.getOriginalFilename());
				//调用储存file的函数
				FileUploadUtil.saveFile(file);
			}
		}
		videoNumService.update(videoNumDTO);
		return "redirect:/videoNums";
	}

	@PutMapping("/myVideo/{id}")
	public String editUP(@RequestParam("file") MultipartFile[] files,@PathVariable("id") Integer id,VideoNumDTO videoNumDTO){
		videoNumDTO.setId(id);
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				System.out.println(file.getOriginalFilename());
				videoNumDTO.setSp(file.getOriginalFilename());
				//调用储存file的函数
				FileUploadUtil.saveFile(file);
			}
		}
		videoNumService.update(videoNumDTO);
		return "redirect:/videoNums/videoNumUP";
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
		videoNumService.deleteByIds(idArray);
		return jsonModel;
	}

	@RequestMapping(value="/login",method= RequestMethod.POST)
	@ResponseBody
	public JsonModel<String> login(Integer id) {

		VideoNumVO videoNumVO = videoNumService.findById(id);
		JsonModel jsonModel = new JsonModel();
		if(videoNumVO==null){
			jsonModel.setCode(401);
			jsonModel.setMsg("资源不存在，请联系管理员");
		}else {
			jsonModel.setData(videoNumVO.getSp());
			jsonModel.setCode(200);
		}
		return jsonModel;
	}

	@GetMapping("/{videoId}/{numId}/gk")
	public String watch (@PathVariable("videoId") Integer videoId,@PathVariable("numId") Integer numId, Model model) {
		VideoNumVO videoNumVO = videoNumService.findGK(videoId,numId);
		model.addAttribute("gkNum",videoNumVO);
		return "spDemo";
	}
}
