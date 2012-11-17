package com.weirhp.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
public @interface Allow {
    /**
     * true表示允许任何人访问,默认为false不允许别人访问
     * 
     * @return
     */
    boolean value() default true;
    
    /**
     * 允许某些角色访问
     * 
     * @return
     */
    String role() default "";
    
    /**
     * 允许某用户访问
     * 
     * @return
     */
    String user() default "";
    
}