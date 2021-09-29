package com.neu.controller;

import com.github.pagehelper.PageInfo;
import com.neu.dto.VideoDTO;
import com.neu.dto.VideoNumDTO;
import com.neu.his.utils.JsonModel;
import com.neu.his.utils.StringUtil;
import com.neu.his.utils.TimeUtil;
import com.neu.service.CommentService;
import com.neu.service.VideoNumService;
import com.neu.service.VideoService;
import com.neu.utils.FileUploadUtil;
import com.neu.utils.SpringContextUtil;
import com.neu.vo.CommentsVO;
import com.neu.vo.VideoNumVO;
import com.neu.vo.VideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/videos")
public class VideoController {

	@Autowired
	VideoService videoService;

	@Autowired
	VideoNumService videoNumService;

	@Autowired
	CommentService commentService;

	@GetMapping("")
	public String list(){
		return "video/list";
	}

	@GetMapping("/videoUP")
	public String lists(){
		return "videoUP/list";
	}

	@GetMapping("/{id}/sp")
	public String watch (@PathVariable("id") Integer id,Model model){
		videoService.addClick(id);
		VideoVO videoVO = videoService.findById(id);
		List<VideoNumVO> result = videoNumService.findVideo(id);
		List<CommentsVO> comm = commentService.findVideo(id);
		model.addAttribute("comm",comm);
		model.addAttribute("xx",result);
		model.addAttribute("videoDemo", videoVO);
		return "videoDemo";
	}

	@GetMapping("/xx")
	@ResponseBody
	public JsonModel<List<VideoVO>> list(VideoDTO videoDTO){
		if(StringUtil.isEmpty(videoDTO.getState())){
			videoDTO.setState("已上架");
		}else if("1".equals(videoDTO.getState())){
			videoDTO.setState("已上架");
		}else if("2".equals(videoDTO.getState())){
			videoDTO.setState("已下架");
		}
		PageInfo<VideoVO> pageInfo = videoService.find(videoDTO);
		JsonModel<List<VideoVO>> jsonModel = new JsonModel<>();
		jsonModel.setData(pageInfo.getList());
		jsonModel.setCode(200);
		jsonModel.setCount((int)pageInfo.getTotal());
		return jsonModel;
	}

	@GetMapping("/{userId}/xx")
	@ResponseBody
	public JsonModel<List<VideoVO>> listUP(@PathVariable("userId") Integer userId,VideoDTO videoDTO){
		videoDTO.setUploadId(userId);
		if(StringUtil.isEmpty(videoDTO.getState())){
			videoDTO.setState("已上架");
		}else if("1".equals(videoDTO.getState())){
			videoDTO.setState("已上架");
		}else if("2".equals(videoDTO.getState())){
			videoDTO.setState("已下架");
		}
		PageInfo<VideoVO> pageInfo = videoService.find(videoDTO);
		JsonModel<List<VideoVO>> jsonModel = new JsonModel<>();
		jsonModel.setData(pageInfo.getList());
		jsonModel.setCode(200);
		jsonModel.setCount((int)pageInfo.getTotal());
		return jsonModel;
	}

	@GetMapping("/add")
	public String add(){
		return "video/save";
	}

	@GetMapping("/myVideo/add")
	public String addUP(){
		return "videoUP/save";
	}

	@PostMapping("")
	public String add(@RequestParam("file") MultipartFile[] files, VideoDTO videoDTO) {
		videoDTO.setTime(TimeUtil.getTime());
		if("1".equals(videoDTO.getPrimary())){
			videoDTO.setPrimary("动漫");
		}else if("2".equals(videoDTO.getPrimary())){
			videoDTO.setPrimary("电影");
		}else if("3".equals(videoDTO.getPrimary())){
			videoDTO.setPrimary("纪录片");
		}
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				System.out.println(file.getOriginalFilename());
				videoDTO.setPic(file.getOriginalFilename());
				//调用储存file的函数
				FileUploadUtil.saveFile(file);
			}
		}
		videoService.add(videoDTO);
		return "redirect:/videos";
	}

	@PostMapping("/myVideo")
	public String addUP(@RequestParam("file") MultipartFile[] files, VideoDTO videoDTO) {

		videoDTO.setTime(TimeUtil.getTime());
		if("1".equals(videoDTO.getPrimary())){
			videoDTO.setPrimary("动漫");
		}else if("2".equals(videoDTO.getPrimary())){
			videoDTO.setPrimary("电影");
		}else if("3".equals(videoDTO.getPrimary())){
			videoDTO.setPrimary("纪录片");
		}
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				System.out.println(file.getOriginalFilename());
				videoDTO.setPic(file.getOriginalFilename());
				//调用储存file的函数
				FileUploadUtil.saveFile(file);
			}
		}
		videoService.add(videoDTO);
		return "redirect:/videos/videoUP";
	}


	@GetMapping("/{id}/edit")
	public String edit (@PathVariable("id") Integer id, Model model){
		VideoVO videoVO = videoService.findById(id);
		model.addAttribute("videoVO", videoVO);
		return "video/save";
	}

	@GetMapping("/myVideo/{id}/edit")
	public String editUP (@PathVariable("id") Integer id, Model model){
		VideoVO videoVO = videoService.findById(id);
		model.addAttribute("videoVO", videoVO);
		return "videoUP/save";
	}


	@PutMapping("/{id}")
	public String edit (@RequestParam("file") MultipartFile[] files,@PathVariable("id") Integer id, VideoDTO videoDTO){
		videoDTO.setId(id);
		if("1".equals(videoDTO.getPrimary())){
			videoDTO.setPrimary("动漫");
		}else if("2".equals(videoDTO.getPrimary())){
			videoDTO.setPrimary("电影");
		}else if("3".equals(videoDTO.getPrimary())){
			videoDTO.setPrimary("纪录片");
		}
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				videoDTO.setPic(file.getOriginalFilename());
				//调用储存file的函数
				FileUploadUtil.saveFile(file);
			}
		}
		videoService.update(videoDTO);
		return "redirect:/videos";
	}

	@PutMapping("/myVideo/{id}")
	public String editUP (@RequestParam("file") MultipartFile[] files,@PathVariable("id") Integer id, VideoDTO videoDTO){
		videoDTO.setId(id);
		if("1".equals(videoDTO.getPrimary())){
			videoDTO.setPrimary("动漫");
		}else if("2".equals(videoDTO.getPrimary())){
			videoDTO.setPrimary("电影");
		}else if("3".equals(videoDTO.getPrimary())){
			videoDTO.setPrimary("纪录片");
		}
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				videoDTO.setPic(file.getOriginalFilename());
				//调用储存file的函数
				FileUploadUtil.saveFile(file);
			}
		}
		videoService.update(videoDTO);
		return "redirect:/videos/videoUP";
	}


	@DeleteMapping("/{ids}")
	@ResponseBody
	public JsonModel delete (@PathVariable("ids") String ids){
		String id[] = ids.split(",");
		JsonModel jsonModel = new JsonModel();
		jsonModel.setCode(200);
		jsonModel.setMsg("下架成功");
		int len = id.length;
		Integer[] idArray = new Integer[len];
		for (int i = 0; i < len; i++) {
			idArray[i] = Integer.parseInt(id[i]);
		}
		videoService.deleteByIds(idArray);
		return jsonModel;
	}

	@DeleteMapping("/yy/{ids}")
	@ResponseBody
	public JsonModel deleteSys (@PathVariable("ids") String ids){
		String id[] = ids.split(",");
		JsonModel jsonModel = new JsonModel();
		jsonModel.setCode(200);
		jsonModel.setMsg("上架成功");
		int len = id.length;
		Integer[] idArray = new Integer[len];
		for (int i = 0; i < len; i++) {
			idArray[i] = Integer.parseInt(id[i]);
		}
		videoService.delete(idArray);
		return jsonModel;
	}
}
