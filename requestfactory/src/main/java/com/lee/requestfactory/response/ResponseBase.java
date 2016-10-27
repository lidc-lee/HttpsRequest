package com.lee.requestfactory.response;

import java.io.Serializable;

/**
 * Created by LinJK on 6/21/16.
 */
public class ResponseBase implements Serializable {
    private int    Code;
    private String Message;
    private String Param;
    private String Param2;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getParam() {
        return Param;
    }

    public void setParam(String param) {
        Param = param;
    }

    public String getParam2() {
        return Param2;
    }

    public void setParam2(String param2) {
        Param2 = param2;
    }

    @Override
    public String toString() {
        return "ResponseBase{" +
                "Code=" + Code +
                ", Message='" + Message + '\'' +
                ", Param='" + Param + '\'' +
                ", Param2='" + Param2 + '\'' +
                '}';
    }
}
