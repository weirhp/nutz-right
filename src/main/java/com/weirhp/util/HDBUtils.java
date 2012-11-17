package com.weirhp.util;

import org.nutz.lang.Strings;


public class HDBUtils {
    public static String getLikeSqlForMysql(String queryStr){
        if(Strings.isEmpty(queryStr)){
            return "%";
        }else{
            String re = queryStr.replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_");
            return "%" + re + "%";
        }
    }
    
    public static String join(Long[] ids, String split) {
        StringBuilder sb = new StringBuilder();
        if (ids != null && ids.length > 0) {
            boolean isFirst = true;
            for (Long id : ids) {
                if (!isFirst) {
                    sb.append(split);
                } else {
                    isFirst = false;
                }
                sb.append(id);
            }
        }
        return sb.toString();
    }
}
