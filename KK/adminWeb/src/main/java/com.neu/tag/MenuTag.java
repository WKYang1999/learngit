package com.neu.tag;

import com.neu.utils.ConstUtil;
import com.neu.vo.MenuVO;
import com.neu.vo.UserVO;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;

public class MenuTag extends TagSupport {

    private String menuName;

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    //eval执行
        //EVAL_BODY_INCLUDE  执行标签里面的内容
        //EVAL_PAGE          执行标签后面的内容
        //SKIP_BODY          不执行标签里面的内容


    //开始执行标签该如何处理
    @Override
    public int doStartTag() throws JspException {

        //判断有没有对menuName的权限
             //有   EVAL_BODY_INCLUDE  执行标签里面的内容
             //无   SKIP_BODY          不执行标签里面的内容

        HttpSession session = pageContext.getSession();
        UserVO userVO = (UserVO) session.getAttribute(ConstUtil.SESSION_KEY);
        if(userVO.getLoginName().equals("admin")){
            return TagSupport.EVAL_BODY_INCLUDE;
        }

        List<MenuVO> menus = (List<MenuVO>) session.getAttribute(ConstUtil.SESSION_MENU_KEY);

        if(menus!=null){
            for(MenuVO menuVO : menus){
                if(menuVO.getName().equals(menuName)){
                    return TagSupport.EVAL_BODY_INCLUDE;
                }
            }
        }
        return TagSupport.SKIP_BODY;
    }

    //标签执行后该如何处理
    @Override
    public int doEndTag() throws JspException {
        return TagSupport.EVAL_PAGE;
    }
}
