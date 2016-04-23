package com.robot.example.helper;

import com.google.gson.Gson;
import com.robot.example.entity.json.RobotBackJson;
import com.robot.example.entity.utility.TypeUtility;
import com.robot.example.entity.utility.UriUnility;

/**
 *  封装了与ApiController中抛出接口（即自己的服务）的交互
 * @author wuqi-pc
 *
 */
public class RobotHelper
{
    /**
     * 返回post请求的结果
     * @param postBody json字符串，参数一般为从api.ai返回的json，不需要做任何的改变
     * @return
     */
    public String getRobotPost(String postBody)
    {
    	//得到请求返回的json串
        String getback=HttpHelper.SendPost(UriUnility.ROBOT_URI,postBody,null);
        System.out.println("robotback:"+getback);
        //得到json串中的status为success或是false，做出相应的判断
        Gson gson=new Gson();
        RobotBackJson backJson=gson.fromJson(getback, RobotBackJson.class);
        if (backJson.status == "false")
        {
            return "";
        }
        else
        {
            //此处需要根据robot.type判断返回消息的类型，然后组织样式
        	//这里只做了string类型，后面若是系统进行拓展，可以在这里进行组织更多的样式
        	//由于前台是html形式的聊天器，支持html标签，所以直接封装成了html的消息内容
            if (backJson.type == TypeUtility.Type_Back_String)
            {
                return backJson.content+"<br/>来源："+backJson.source;
            }
            else
            {
                return backJson.content + "<br/>来源：" + backJson.source;
            }
        }
    }
}
