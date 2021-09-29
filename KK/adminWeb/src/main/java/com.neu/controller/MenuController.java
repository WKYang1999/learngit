package com.neu.controller;

import com.neu.his.utils.JsonModel;
import com.neu.service.MenuService;
import com.neu.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping(value="")
    @ResponseBody
    public JsonModel<String> find(Integer parentId) {

        List<MenuVO> menuVO = menuService.findByParent(parentId);

        JsonModel jsonModel = new JsonModel();
        jsonModel.setCode(200);
        jsonModel.setMsg("查询成功");

        jsonModel.setData(menuVO);
        return jsonModel;
    }
}
