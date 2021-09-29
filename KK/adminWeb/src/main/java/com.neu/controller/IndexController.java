package com.neu.controller;

import com.neu.dto.VideoDTO;
import com.neu.his.utils.JsonModel;
import com.neu.service.*;
import com.neu.utils.ConstUtil;
import com.neu.utils.SpringContextUtil;
import com.neu.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;

    @Autowired
    VideoService videoService;

    @Autowired
    NoticeService noticeService;

    @Autowired
    HomePageService homePageService;

    @GetMapping("/{id}/ss")
    public String watch (@PathVariable("id") Integer id, Model model) {
        List<ComicVO> comic = null;
        if(id == 1){
            comic = videoService.findComic("电影");
            model.addAttribute("search",comic);
            return "search";
        }else if(id == 2){
            comic = videoService.findComic("动漫");
            model.addAttribute("search",comic);
            return "search";
        }else if(id == 3){
            comic = videoService.findComic("纪录片");
            model.addAttribute("search",comic);
            return "search";
        }else {
            return "index";
        }
    }

    @RequestMapping(value="/index",method= RequestMethod.GET)
    public String index(Model model){
        List<ComicVO> comic = videoService.findComic("动漫");
        List<NoticeVO> notices = noticeService.findNotices();
        List<ComicVO> films = videoService.findComic("电影");
        List<ComicVO> documentary = videoService.findComic("纪录片");
        List<ComicVO> hots = videoService.findHot();
        HomePageVO zxgg = homePageService.findHome("最新公告");
        HomePageVO rmtj = homePageService.findHome("热门推荐");
        HomePageVO dm = homePageService.findHome("动漫");
        HomePageVO dy = homePageService.findHome("电影");
        HomePageVO jlp = homePageService.findHome("纪录片");
        model.addAttribute("aaa",zxgg);
        model.addAttribute("bbb",rmtj);
        model.addAttribute("ccc",dm);
        model.addAttribute("ddd",dy);
        model.addAttribute("eee",jlp);
        model.addAttribute("hots",hots);
        model.addAttribute("comic",comic);
        model.addAttribute("film",films);
        model.addAttribute("documentary",documentary);
        model.addAttribute("notices",notices);
        return "index";
    }

    @RequestMapping(value="/core",method= RequestMethod.GET)
    public String core(){
        return "core";
    }

    @RequestMapping(value="/search",method= RequestMethod.GET)
    public String search(){
        return "search";
    }

    @PostMapping("/search")
    public String search(VideoDTO videoDTO,Model model){
        videoDTO.setState("已上架");
        List<VideoVO> videoVOS = videoService.find(videoDTO).getList();
        if(videoVOS != null){
            model.addAttribute("search",videoVOS);
        }
        return "search";
    }


    @RequestMapping(value="/echart",method= RequestMethod.GET)
    @ResponseBody
    public JsonModel<Map<String,Object[]>> echart(){

        JsonModel<Map<String,Object[]>> jsonModel = new JsonModel<>();

        jsonModel.setCode(200);

        Map<String,Object[]> data = new HashMap<String,Object[]>();

        String types [] = {"洗发水","沐浴露","牙膏"};
        data.put("type", types);

        Integer count [] = {100,20,1000};
        data.put("count",count);

        jsonModel.setData(data);

        return jsonModel;
    }


    /**
     * 转发到登录界面
     * @return
     */
    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value="/register",method= RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value="/invalidate",method= RequestMethod.GET)
    public String invalidate(HttpSession session){
        if(session!=null){
            session.invalidate();
        }
        return "redirect:/index";
    }

    /**
     * 处理登录请求
     * @param loginName
     * @param password
     * @return
     */
    @RequestMapping(value="/login",method= RequestMethod.POST)
    @ResponseBody
    public JsonModel<String> login(String loginName, String password, HttpSession session) {
        UserVO userVO = userService.login(loginName,password);
        JsonModel jsonModel = new JsonModel();
        //System.out.println("上下文环境测试打印：" + SpringContextUtil.getBean("springContextUtil").toString());
        //打印根目录
        //System.out.println("打印根目录" + (new File("")).getAbsolutePath());
        if(userVO==null){
            jsonModel.setCode(401);
            jsonModel.setMsg("账号或密码错误");
        }else if("注销".equals(userVO.getState())){
            jsonModel.setCode(401);
            jsonModel.setMsg("该账户已注销");
        }else{
            session.setAttribute(ConstUtil.SESSION_KEY,userVO);
            //获取当前用户的权限
                //角色-->权限
            int [] roleId = roleService.findByUserId(userVO.getId());
            List<MenuVO> menus = menuService.findByRole(roleId);
            session.setAttribute(ConstUtil.SESSION_MENU_KEY,menus);
            jsonModel.setCode(200);
            jsonModel.setMsg("登录成功");
        }
        return jsonModel;
    }
}
