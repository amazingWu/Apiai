package com.robot.example.entity.json;

import java.util.List;

/**
 * 发送给api的json串对应的序列化的类，方便json字符串的构造
 * @author wuqi-pc
 *
 */
public class ApiSentJson
{
    //The natural language text to be processed.The request can have multiple query parameters.
    private List<String> query;//Required unless sound file/stream is submitted.
                              //The confidence of the corresponding query parameter having been correctly recognized by a speech recognition system. 0 represents no confidence and 1 represents the highest confidence. 
    private List<Integer> confidence;//Required when multiple query parameters are used.
                                //Array of additional input context objects.
    private List<Context> contexts;//Optional.
                                  //If true, all current contexts in a session will be reset before the new ones are set.False by default.
    private boolean resetContexts;//Optional
    private Location location;
    //Time zone from IANA Time Zone Database.
    private String timezone;//Optional
                           //Language tag, e.g.EN, ES, ... 
    private String lang;//Required.
                       //A string token up to 36 symbols long, used to identify the client and to manage sessions parameters (including contexts) per client.
    private String sessionid; //Required.
                             //Version of the protocol, e.g.v=20150910
    private String v; //Must be used as a URL parameter.
    
    public List<String> getQuery() {
		return query;
	}

	public void setQuery(List<String> query) {
		this.query = query;
	}

	public List<Integer> getConfidence() {
		return confidence;
	}

	public void setConfidence(List<Integer> confidence) {
		this.confidence = confidence;
	}

	public List<Context> getContexts() {
		return contexts;
	}

	public void setContexts(List<Context> contexts) {
		this.contexts = contexts;
	}

	public boolean isResetContexts() {
		return resetContexts;
	}

	public void setResetContexts(boolean resetContexts) {
		this.resetContexts = resetContexts;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public class Context
    {
        public String name;
        public int lifespan;
    }
    
    public class Location
    {
    	public double latitude;
    	public double longitude;
    }
}

