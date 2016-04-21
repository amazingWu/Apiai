package com.robot.example.entity.json;

/**
 * 图灵回复消息的父类
 * @author wuqi-pc
 *
 */
public class TulingBackJson
{
    public int code;
    public String text;
    
    public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	/**
	 * 文本类
	 * @author wuqi-pc
	 *
	 */
    public class TextMessage extends TulingBackJson
    {
    }
    
    /**
     * 新闻集合类
     * @author wuqi-pc
     *
     */
    public class NewsMessage extends TulingBackJson
    {
        public News[] list;//新闻列表

		public News[] getList() {
			return list;
		}

		public void setList(News[] list) {
			this.list = list;
		}
        
    }
    /**
     * 新闻消息
     * @author wuqi-pc
     *
     */
    public class News
    {
        public String article ;//标题
        public String source ;//来源：如：网易新闻
        public String icon ;//缩略图
        public String detailurl;//详细列表
		public String getArticle() {
			return article;
		}
		public void setArticle(String article) {
			this.article = article;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getDetailurl() {
			return detailurl;
		}
		public void setDetailurl(String detailurl) {
			this.detailurl = detailurl;
		}
        
    }

    /**
     * 链接类
     * @author wuqi-pc
     *
     */
    public class LinkMessage extends TulingBackJson
    {
        public String url;//链接地址

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
        
    }

    /**
     * 菜谱集合类
     * @author wuqi-pc
     *
     */
    public class FoodMessage extends TulingBackJson
    {
        public Food[] list;//菜列表

		public Food[] getList() {
			return list;
		}

		public void setList(Food[] list) {
			this.list = list;
		}
        
    }
    /**
     * 菜谱食物类
     * @author wuqi-pc
     *
     */
    public class Food
    {
        public String name ;//菜名
        public String icon ;//缩略图
        public String info ;//做法
        public String detailurl ;//详细地址
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		public String getDetailurl() {
			return detailurl;
		}
		public void setDetailurl(String detailurl) {
			this.detailurl = detailurl;
		}
        
    }
}



