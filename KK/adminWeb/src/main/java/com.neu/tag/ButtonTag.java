package com.neu.tag;

import com.neu.domain.Menu;
import com.neu.utils.ConstUtil;
import com.neu.vo.MenuVO;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

public class ButtonTag extends SimpleTagSupport {

    private String group;

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public void doTag() throws JspException, IOException {

        //逻辑
            //查找group下面的所有菜单并且有权限的


        PageContext pageContext = (PageContext) getJspContext();
        JspWriter jspWriter = pageContext.getOut();
        List<MenuVO> leafMenu = (List<MenuVO>) pageContext.getSession().getAttribute(ConstUtil.SESSION_MENU_KEY);

        if(leafMenu!=null){
            for(MenuVO child:leafMenu){
                //users!list
                //users!add
                if(child.getAccessToken().startsWith("/users!")){
                    jspWriter.print("<a href='' class='btn bg-maroon btn-flat'>"+child.getName()+"</a>");
                }
            }
        }
        jspWriter.flush();
    }
}
