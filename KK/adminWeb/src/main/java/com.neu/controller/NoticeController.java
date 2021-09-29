package com.neu.controller;

import com.github.pagehelper.PageInfo;
import com.neu.dto.NoticeDTO;
import com.neu.his.utils.JsonModel;
import com.neu.his.utils.TimeUtil;
import com.neu.service.NoticeService;
import com.neu.vo.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notices")
public class NoticeController {

	@Autowired
	NoticeService noticeService;


	@GetMapping("")
	public String list(){
		return "notice/list";
	}

	@GetMapping("/xx")
	@ResponseBody
	public JsonModel<List<NoticeVO>> list(NoticeDTO noticeDTO){
		PageInfo<NoticeVO> pageInfo = noticeService.find(noticeDTO);
		JsonModel<List<NoticeVO>> jsonModel = new JsonModel<>();
		jsonModel.setData(pageInfo.getList());
		jsonModel.setCode(200);
		jsonModel.setCount((int)pageInfo.getTotal());
		return jsonModel;
	}

	@GetMapping("/add")
	public String add(){
		return "notice/save";
	}

	@PostMapping("")
	public String add(NoticeDTO noticeDTO) {
		noticeDTO.setTime(TimeUtil.getTime());
		noticeService.add(noticeDTO);
		return "redirect:/notices";
	}


		@GetMapping("/{id}/edit")
		public String edit (@PathVariable("id") Integer id, Model model){
			NoticeVO noticeVO = noticeService.findById(id);
			model.addAttribute("noticeVO", noticeVO);
			return "notice/save";
		}


		@PutMapping("/{id}")
		public String edit (@PathVariable("id") Integer id, NoticeDTO noticeDTO){
			noticeDTO.setId(id);
			noticeService.update(noticeDTO);
			return "redirect:/notices";
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
			noticeService.deleteByIds(idArray);
			return jsonModel;
		}
}
