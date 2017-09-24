package com.liukevin.em;

/**
 * Created by liukai on 2017/9/24.
 */
public enum Status {

    SUCCESS("S","SUCCESS"),
    FAIL("F","异常");

    private Status(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;
    private Object o;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setContext(Object o) {
        this.o = o;
    }

    public Object getContext() {
        return o;
    }
}
