package com.neu.listener;

import com.neu.domain.Menu;
import com.neu.service.MenuService;
import com.neu.service.NoticeService;
import com.neu.service.VideoService;
import com.neu.utils.SpringUtil;
import com.neu.vo.ComicVO;
import com.neu.vo.NoticeVO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

public class MyContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {

        SpringUtil.init(sce.getServletContext());

        MenuService menuService = SpringUtil.getBean("menuServiceImpl");

        List<Menu> leftMenus = menuService.findLeft();

        sce.getServletContext().setAttribute("leftMenus",leftMenus);

        //查找所有的叶子菜单
        List<Menu> leafMenus =  menuService.findLeafMenu();
        sce.getServletContext().setAttribute("leafMenu",leafMenus);
    }
}
