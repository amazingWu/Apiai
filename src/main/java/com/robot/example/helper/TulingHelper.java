package com.robot.example.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.robot.example.entity.json.TulingBackJson;
import com.robot.example.entity.json.TulingBackJson.FoodMessage;
import com.robot.example.entity.json.TulingBackJson.LinkMessage;
import com.robot.example.entity.json.TulingBackJson.NewsMessage;
import com.robot.example.entity.json.TulingBackJson.TextMessage;


public class TulingHelper
{
    //图铃机器人api地址
    private final String TUPING_URL = "http://www.tuling123.com/openapi/api";
    //产品ak
    private final String AK = "a05e266389bc8d5f03f8e055da1ea825";
    private String postbody;
    //初始化构造,用来传递进info,和userid
    public TulingHelper(String info,String sessionid)
    {
        this.postbody="key="+AK+"&info="+info+"&userid="+sessionid;
    }

    /// <summary>
    /// 构造post请求，并得到返回值
    /// </summary>
    /// <returns></returns>
    private String getHttp()
    {
    	
       /* List<NameValuePair> parms=new ArrayList<NameValuePair>();
        parms.add(new BasicNameValuePair("Content-Type", "application/x-www-form-urlencoded"));*/
        //模拟请求建立
        return HttpHelper.SendGet(TUPING_URL, postbody);
    }

    /// <summary>
    /// 根据图灵Api的返回结果将结果封装成html的文本内容，以方便添加进网页中
    /// </summary>
    /// <returns></returns>
    public String GetMessage()
    {
    	Gson gson=new Gson();
        String content = getHttp();
        TulingBackJson tulingback = gson.fromJson(content, TulingBackJson.class);
        //java中的int取值范围足够大
        switch (tulingback.getCode())
        {
            //文本类
            case 100000:
                TextMessage textmessage=gson.fromJson(content,TextMessage.class);
                return textmessage.text;
            //链接类
            case 200000:
                LinkMessage linkmessage = gson.fromJson(content,LinkMessage.class);
                return linkmessage.text + "<a href="+linkmessage.url+">" + linkmessage.url + "</a>";
            //新闻类
            case 302000:
                NewsMessage newsmessage = gson.fromJson(content,NewsMessage.class);
                //想要组成的新闻类样式
                /*
                <div class="news">
			        <img src="news.jpg"/>
			        <section>
				        <p>大苏打撒大飒飒撒撒旦飒飒阿斯达克拉都拉多久啊了巨大倒萨大大阿瓦蒂</p>
				        <p>网易新闻</p>
			        </section>
			        <section>
				        <p>大苏打撒大飒飒撒撒旦飒飒阿斯达克拉都拉多久啊了巨大倒萨大大阿瓦蒂</p>
				        <p>网易新闻</p>
			        </section>
		        </div>
                */
                String newsback = "<div class='news'><img src='./images/news.jpg'/>";
                for(int i = 0; i < newsmessage.list.length; i++)
                {
                    newsback = newsback + "<section onclick=\"location.href =\'" + newsmessage.list[i].detailurl + "\';\"><p>" + newsmessage.list[i].article + "</p>";
                    newsback=newsback+ "<p>"+newsmessage.list[i].source+"</p></section>";
                }
                newsback += "</div>";
                return newsback;
            //菜谱类
            case 308000:
                //目标html样式
                /*
                <div class="food">
			        <img src="http://www.sinaimg.cn/dy/slidenews/1_img/2016_04/2841_658254_844711.jpg"/>
			        <section>
				        <img src="http://www.sinaimg.cn/dy/slidenews/1_img/2016_04/2841_658254_844711.jpg"/>
				        <p>大苏打撒大飒飒</p>
				        <p>大苏打撒大飒飒撒撒旦飒飒阿斯达克拉都拉多久啊了巨大倒萨大大阿瓦蒂</p>
			        </section>
			        <section>
				        <img src="http://www.sinaimg.cn/dy/slidenews/1_img/2016_04/2841_658254_844711.jpg"/>
				        <p>大苏打撒大飒飒撒撒旦飒飒阿斯达克拉都拉多久啊了巨大倒萨大大阿瓦蒂</p>
			        </section>
		        </div>
                */
                FoodMessage foodmessage = gson.fromJson(content,FoodMessage.class);
                String foodsback = "<div class='food'><img src=";
                if (foodmessage.list.length != 0) {
                    foodsback =foodsback+ "'./images/food.jpg'/>";
                    for(int i = 0; i < foodmessage.list.length; i++)
                    {
                        foodsback = foodsback + "<section onclick=\"location.href=\'" + foodmessage.list[i].detailurl + "\';\"><img src='" + foodmessage.list[i].icon + "'/>";
                        foodsback = foodsback + "<p>"+foodmessage.list[i].name+ "</p>";
                        foodsback = foodsback + "<p>" + foodmessage.list[i].info + "</p>";
                        foodsback = foodsback + "</section>";
                    }
                }
                else
                {
                    foodsback = foodsback+"'/>";
                }
                foodsback+= "</div>";
                return foodsback;
            default:break;
        }
        return null;
    }
}