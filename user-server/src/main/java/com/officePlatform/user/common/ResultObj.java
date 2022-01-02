package com.officePlatform.user.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultObj implements Serializable {
    private int code;
    private String msg;
    private Object obj;
    public static ResultObj succ(String msg,Object obj){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(200);
        resultObj.setMsg(msg);
        resultObj.setObj(obj);
        return resultObj;
    }
    public static ResultObj succ(String msg){
        return succ(msg,new Object());
    }
    public static ResultObj fail(int code,String msg,Object obj){
        ResultObj resultObj = new ResultObj();
        resultObj.setCode(code);
        resultObj.setMsg(msg);
        resultObj.setObj(obj);
        return resultObj;
    }
    public static ResultObj fail(int code,String msg){
        return fail(code,msg,new Object());
    }
    public static ResultObj fail(String msg){
        return fail(400,msg);
    }

}
