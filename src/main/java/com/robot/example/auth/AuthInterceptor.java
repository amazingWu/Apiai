package com.robot.example.auth;

import java.net.HttpCookie;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.robot.example.entity.RobotUser;
import com.robot.example.service.UserService;

/**
 * 权限验证拦截器
 * @author wuqi-pc
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	private UserService userService;
	
    @Resource
    public void setUserService(UserService userService) {
		this.userService = userService;
	}
    
    
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("inter");
		if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
            
            //没有声明需要权限,或者声明不验证权限
            if(authPassport == null || authPassport.validate() == false)
                return true;
            else{                
                //在这里实现自己的权限验证逻辑
            	boolean auth;
            	HttpSession session=request.getSession();
            	if(session.isNew()){//服务器内存中没有该session
            		auth=false;
            	}else{
            		//获得用户名和密码
            		try{
            			String userName=session.getAttribute("userName").toString();
                		String userPwd=session.getAttribute("userPwd").toString();
                		//如果用户名或密码为空，则认证失败
                		if(userName==null||userPwd==null){
                			auth=false;
                		}else{
                			//查询数据库，验证用户名和密码
                			RobotUser user=userService.GetUser(userName, userPwd);
                			if(user!=null){
                				request.setAttribute("icon", "images/logo.png");
                				auth=true;
                			}else {
								auth=false;
							}
                		}
            		}catch(NullPointerException e){
            			auth=false;
            		}
            		
            	}          	
                if(auth)//如果验证成功返回true
                    return true;
                else//如果验证失败
                {
                    //返回到登录界面  
                	if(authPassport.redirect()==true){
                		response.sendRedirect(request.getContextPath()+"/user/login.jsp");
                		//response.sendError(HttpServletResponse., "用户还没有登录");
                	}
                	else{
                		return true;
                	}
                    return false;
                }       
            }
        }
        else
            return true;   
     }
}