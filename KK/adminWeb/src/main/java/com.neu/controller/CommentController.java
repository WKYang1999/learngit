package com.neu.controller;

import com.github.pagehelper.PageInfo;
import com.neu.dto.CommentsDTO;
import com.neu.his.utils.JsonModel;
import com.neu.his.utils.StringUtil;
import com.neu.his.utils.TimeUtil;
import com.neu.service.CommentService;
import com.neu.utils.ConstUtil;
import com.neu.vo.ComicVO;
import com.neu.vo.CommentsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	CommentService commentService;


	@GetMapping("")
	public String list(){
		return "comment/list";
	}

	@GetMapping("/{userId}/xx")
	@ResponseBody
	public JsonModel<List<CommentsVO>> list(@PathVariable("userId") Integer userId,CommentsDTO commentsDTO){
		commentsDTO.setUserId(userId);
		PageInfo<CommentsVO> pageInfo = commentService.find(commentsDTO);
		JsonModel<List<CommentsVO>> jsonModel = new JsonModel<>();
		jsonModel.setData(pageInfo.getList());
		jsonModel.setCode(200);
		jsonModel.setCount((int)pageInfo.getTotal());
		return jsonModel;
	}

	@GetMapping("/add")
	public String add(){
		return "comment/save";
	}

	@PostMapping("")
	public String add(CommentsDTO commentsDTO) {
		commentsDTO.setDateComm(TimeUtil.getTime());
		commentService.add(commentsDTO);
		return "redirect:/comments";
	}

	@RequestMapping(value="/add",method= RequestMethod.POST)
	@ResponseBody
	public JsonModel<String> login(Integer userId, Integer videoId, String content) {
		JsonModel jsonModel = new JsonModel();
		if(StringUtil.isEmpty(content)){
			jsonModel.setCode(401);
			jsonModel.setMsg("请输入内容！");
		}else{
			CommentsDTO commentsDTO = new CommentsDTO();
			commentsDTO.setUserId(userId);
			commentsDTO.setVideoId(videoId);
			commentsDTO.setContent(content);
			commentsDTO.setDateComm(TimeUtil.getTime());
			int result = commentService.add(commentsDTO);
			if(result != 0){
				jsonModel.setCode(200);
				jsonModel.setMsg("评论成功！");
			}else{
				jsonModel.setCode(401);
				jsonModel.setMsg("评论失败！");
			}
		}
		return jsonModel;
	}

		@GetMapping("/{id}/edit")
		public String edit (@PathVariable("id") Integer id, Model model){
			CommentsVO commentsVO = commentService.findById(id);
			model.addAttribute("commentsVO", commentsVO);
			return "comment/save";
		}


		@PutMapping("/{id}")
		public String edit (@PathVariable("id") Integer id, CommentsDTO commentsDTO){
			commentsDTO.setId(id);
			commentService.update(commentsDTO);
			return "redirect:/comments";
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
			commentService.deleteByIds(idArray);
			return jsonModel;
		}

	@GetMapping("/{videoId}/{id}/delete")
	public String watch (@PathVariable("videoId") Integer videoId,@PathVariable("id") Integer id) {
		Integer[] idArray = new Integer[1];
		idArray[0] = id;
		commentService.deleteByIds(idArray);
		return "redirect:/videos/"+videoId+"/sp";
	}
}
