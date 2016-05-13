package com.robot.example.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Session;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.example.auth.AuthPassport;
import com.robot.example.entity.RobotUser;
import com.robot.example.service.UserService;

/**
 * 用户控制器
 * @author wuqi-pc
 *
 */
@Controller
@RequestMapping(value="user")
public class UserController {

	private UserService userService;

	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * 查询用户名是否已经使用
	 * @param userName
	 * @return
	 */
	@RequestMapping(value="checkname/{userName}")
	public boolean CheckName(@PathVariable String userName){
		return userService.CheckUserName(userName);
	}
	
	/**
	 * 用户登录
	 * @param userName
	 * @param userPwd
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String GetUser(HttpServletRequest request){
		try{
			String userName=request.getParameter("userName");
			String userPwd=request.getParameter("userPwd");
			if(userName!=null&&userPwd!=null){
				//查询用户
				RobotUser user=userService.GetUser(userName, userPwd);
				if(user!=null){
					HttpSession session=request.getSession();
					session.setAttribute("userName", userName);
					session.setAttribute("userPwd", userPwd);
					//设置登录后的用户的session有效期为3天，如果1天没有操作就需要重新登录
					session.setMaxInactiveInterval(3600*24);
					return "redirect:/robot/robot";
				}
			}
		}catch(NullPointerException e){
		}
		return "/user/login.jsp";
	}
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String Login(){
		return "/user/login.jsp";
	}
	
	
	
	/**
	 * 用户注册
	 * @return
	 */
	@RequestMapping(value = "register", method = {RequestMethod.GET})  
    public String register(){
        return "/user/register.jsp";
    }
	@RequestMapping(value = "register",method=RequestMethod.POST)
	 public String registerCheck(Model model, @ModelAttribute("robotUser") @Valid RobotUser robotUser,  
	            BindingResult result) {
	    if (result.hasErrors())
	        return "/user/register.jsp";
	    else{
	    	//保存用户数据
	    	if(this.userService.Save(robotUser)){
	    		model.addAttribute("userName", robotUser.getUserName());
	 	        return "forward:/user/success.jsp";
	    	}else{
	    		return "redirect:/user/register.jsp";
	    	}
	       
	    }
	 }
	/**
	 * 退出登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value="out")
	@AuthPassport
	public String Logout(HttpServletRequest request){
		request.getSession().setAttribute("userName", null);
		request.getSession().setAttribute("userPwd", null);
		request.getSession().setMaxInactiveInterval(0);
		return "forward:/user/login";
	}
}
