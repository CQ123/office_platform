package com.officePlatform.user.common;

import lombok.Data;

@Data
public class ResultMsg {
    private int code;
    private String msg;
    private Object obj;

    public ResultMsg succ(int code,String msg,Object obj){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(code);
        resultMsg.setMsg(msg);
        resultMsg.setObj(obj);
        return resultMsg;
    }
    public ResultMsg succ(String msg,Object obj){
        return succ(200,msg,obj);
    }
    public ResultMsg succ(String msg){
        return succ(msg,new Object());
    }
    public ResultMsg fail(int code,String msg,Object obj){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(code);
        resultMsg.setMsg(msg);
        resultMsg.setObj(obj);
        return resultMsg;
    }
    public ResultMsg fail(int code,String msg){
        return fail(code,msg,new Object());
    }
}
