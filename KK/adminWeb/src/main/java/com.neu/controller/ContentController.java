package com.neu.controller;

import com.neu.dto.ContentDTO;
import com.neu.his.utils.JsonModel;
import com.neu.his.utils.TimeUtil;
import com.neu.service.CommentService;
import com.neu.service.ContentService;
import com.neu.vo.ContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contents")
public class ContentController {

	@Autowired
	ContentService contentService;

	@GetMapping("/{yourId}/{userId}/friend")
	public String list(@PathVariable("yourId") Integer yourId, @PathVariable("userId") Integer userId, Model model){
		List<ContentVO> result = contentService.findLT(yourId,userId);
		Integer a = userId;
		model.addAttribute("lts",result);
		model.addAttribute("a",a);
		return "content/save";
	}

	@PostMapping("/add")
	public String add(ContentDTO contentDTO){
		int userId = contentDTO.getUserId();
		int yourId = contentDTO.getYourId();
		contentDTO.setDate(TimeUtil.getTime());
		contentService.add(contentDTO);
		return "redirect:/contents/"+yourId+"/"+userId+"/friend";
	}

}
