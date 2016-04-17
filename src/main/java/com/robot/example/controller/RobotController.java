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
	
//	@RequestMapping(value="/chat",method=RequestMethod.GET)
//	public ModelAndView chat(){
//
//		//设置返回消息实体
//        ChatViewModel chatmessage1 = new ChatViewModel();
//        chatmessage1.setRole("you");
//        chatmessage1.setContent("欢迎使用智能助手");
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
//        chatmessage1.setTime(df.format(new Date()));
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.addObject(chatmessage1);
//        modelAndView.setViewName("/Robot");
//		return modelAndView;
//		
//	}
	@RequestMapping(value="/{page}")
	public ModelAndView ok(@PathVariable String page){
		//设置返回消息实体
      ChatViewModel chatmessage1 = new ChatViewModel();
      chatmessage1.setRole("you");
      chatmessage1.setContent("欢迎使用智能助手");
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
      chatmessage1.setTime(df.format(new Date()));
      ModelAndView modelAndView=new ModelAndView();
      modelAndView.addObject("model", chatmessage1);
      modelAndView.setViewName("/"+page+".jsp");
	  return modelAndView;
	}
	
	 @RequestMapping(value="/RobotAjaxBack",method=RequestMethod.POST,produces = {"text/plain;charset=UTF-8"})
	 public @ResponseBody String RobotAjaxBack(HttpServletRequest request)
     {
		 String content = "";
		 try {  
		       request.setCharacterEncoding("UTF-8");  
		       String body = request.getParameter("sendinput");  
		       if(body!=null||body.trim()!=""){
		        	 body=body.trim();
		        	//构造要提交的请求的参数
		             ApiJson apijson = new ApiJson();
		             List<String> list=new ArrayList<String>();
		             list.add(body);
		             apijson.setQuery(list);
		             apijson.setSessionid("12345678");
		             apijson.setV("20160415");
		             apijson.setLang("ZH-CN"); ;
		             apijson.setResetContexts(false);
		             //获取post请求
		             Gson gson=new Gson();
		             System.out.println(gson.toJson(apijson)+"\n");
		             ApiHelper apihelper = new ApiHelper();
		             content = apihelper.GetApiPost( gson.toJson(apijson));
		         }
		 }catch (Exception e) {  
		       e.printStackTrace();  
		 }
         return content;
     }
}
