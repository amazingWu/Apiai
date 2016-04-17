package com.robot.example.helper;

import com.google.gson.Gson;
import com.robot.example.entity.json.RobotBackJson;
import com.robot.example.entity.json.TypeCollection;

public class RobotHelper
{
    private String ROBOT_URL = "http://apiaiservice.duapp.com/api/webhook";

    public String getRobotPost(String postBody)
    {
        String getback=HttpHelper.SendPost(ROBOT_URL,postBody,null);
        Gson gson=new Gson();
        RobotBackJson backJson=gson.fromJson(getback, RobotBackJson.class);
        if (backJson.status == "false")
        {
            return "";
        }
        else
        {
            //此处需要根据robot.type判断返回消息的类型，然后组织样式
            if (backJson.type == TypeCollection.Type_Back_String)
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
