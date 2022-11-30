package cn.young.oj.controller;

import cn.young.oj.entity.UserInfo;
import cn.young.oj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/sign")
    @ResponseBody
    public int insert(HttpServletRequest req, UserInfo userInfo){
        //判断输入数据是否为空
        if(!StringUtils.hasLength(userInfo.getUsername())||
                !StringUtils.hasLength(userInfo.getLoginName())||
                !StringUtils.hasLength(userInfo.getPassword())){
            return 0;
        }
        //判断loginname是否唯一
        UserInfo userInfo1 = userService.selectByLoginName(userInfo.getLoginName());
        if(userInfo1!=null){
            return 0;
        }

        //添加用户
        userInfo.setPassword(userInfo.getPassword());

        int insert = userService.insert(userInfo);
        UserInfo dbUserInfo = userService.selectByLoginName(userInfo.getLoginName());
        //存入session
        HttpSession session = req.getSession(true);
        session.setAttribute("userinfo",dbUserInfo);

        return 1;
    }

    @ResponseBody
    @RequestMapping("/login")
    public boolean login(HttpServletRequest req,String loginName, String password){
        if(!StringUtils.hasLength(loginName)||!StringUtils.hasLength(password)){
            return false;
        }
        UserInfo userInfo = userService.selectByLoginName(loginName);
        //查找数据库中的密码，与用户输入密码比对
        if(userInfo==null){
            return false;
        }
        String dbPassword = userInfo.getPassword();
        if(!dbPassword.equals(password)){
            return false;
        }
        //存入session
        HttpSession session = req.getSession(true);
        session.setAttribute("userinfo",userInfo);
        return true;
    }

    @ResponseBody
    @RequestMapping("person")
    //根据session获取个人uid
    public Integer getLoginName(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if(session==null){
            return null;
        }
        UserInfo userinfo = (UserInfo) session.getAttribute("userinfo");
        System.out.println(userinfo);
        //注册后从数据库取uid
        if(userinfo!=null&&userinfo.getUid()!=null){
            return userinfo.getUid();
        }
        return null;

    }
}
