package com.officePlatform.user.common;

import java.util.HashMap;
import java.util.Map;

public class ResultUtil {
    public static Map<String,Object> createMap(int code,String msg){
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        map.put("msg",msg);
        return map;
    }
    public static Map<String,Object> succ(String msg){
        return createMap(200,msg);
    }
    public static Map<String,Object> fail(int code,String msg){
        return createMap(code,msg);
    }
}
