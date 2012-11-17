package com.weirhp.util;

import java.lang.reflect.Method;

public class HReflects {
    
    /**
     * 生成一个方法的唯一标识
     * 
     * @param method
     * @return
     */
    public static String getMethodIdentity(Method method) {
        StringBuilder sb = new StringBuilder();
        sb.append(method.getDeclaringClass().getName());
        sb.append("@");
        sb.append(method.getName());
        Class<?>[] types = method.getParameterTypes();
        for (Class<?> clazz : types) {
            sb.append("@" + clazz.getName());
        }
        return sb.toString();
    }
    
    
}
