package com.robot.example.controller;

import java.text.Normalizer.Form;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.robot.example.entity.json.ApiJson;
import com.robot.example.entity.model.ChatViewModel;
import com.robot.example.helper.ApiHelper;

@Controller
public class RobotController {
	
	//页面控制器
	@RequestMapping(value="/{page}")
	public ModelAndView ok(@PathVariable String page){
		//设置返回消息实体
      ChatViewModel chatmessage1 = new ChatViewModel();
      chatmessage1.setRole("you");//设置该信息是哪个角色发出来的，为对方
      chatmessage1.setContent("欢迎使用智能助手");//设置正文
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
      chatmessage1.setTime(df.format(new Date()));//设置日期
      ModelAndView modelAndView=new ModelAndView();
      //将chatmessage1 传递到jsp界面，用来初始化第一次进入页面的欢迎导语
      modelAndView.addObject("model", chatmessage1);
      //设置返回哪个界面
      modelAndView.setViewName("/"+page+".jsp");
	  return modelAndView;
	}
	
	
	/**
	 * robot聊天界面ajax请求入口
	 * @return
	 */
	 @RequestMapping(value="/RobotAjaxBack",method=RequestMethod.POST,produces = {"text/plain;charset=UTF-8"})
	 public @ResponseBody String RobotAjaxBack(HttpServletRequest request)
     {
		 String content = "";
		 try {  
			 //设置request中的内容的编码为utf-8,防止乱码
		       request.setCharacterEncoding("UTF-8");  
		       //获取传递过来的参数
		       String body = request.getParameter("sendinput");  
		       //如果参数不为空
		       if(body!=null||body.trim()!=""){
		        	 body=body.trim();
		        	//构造要提交的请求的参数
		             ApiJson apijson = new ApiJson();
		             List<String> list=new ArrayList<String>();
		             list.add(body);
		             //给将要进行反序列化成json字符串的对象赋值
		             apijson.setQuery(list);
		             //获取sessionid
		             String sessionid=request.getSession().getId();
		             //将用户唯一的sessionid传给apijson
		             apijson.setSessionid(sessionid);
		             //版本
		             apijson.setV("20160415");
		             //语言
		             apijson.setLang("ZH-CN"); 
		             //是否重置上下文，设为否
		             apijson.setResetContexts(false);
		             //将对象反序列化，并获取post请求
		             Gson gson=new Gson();
		            // System.out.println(gson.toJson(apijson)+"\n");
		             ApiHelper apihelper = new ApiHelper(body,sessionid);
		             content = apihelper.GetApiPost( gson.toJson(apijson));
		         }
		 }catch (Exception e) {  
		       e.printStackTrace();  
		 }
         return content;
     }
}
