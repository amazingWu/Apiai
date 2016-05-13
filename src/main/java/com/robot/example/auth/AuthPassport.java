package com.robot.example.auth;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 自定义注解
 * @author wuqi-pc
 *
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthPassport {
	/**
	 * 是否进行拦截验证，默认为true
	 * @return
	 */
    boolean validate() default true;
    /**
     * 验证失败后是否进行登录导航，默认为true
     * @return
     */
    boolean redirect() default true;
}