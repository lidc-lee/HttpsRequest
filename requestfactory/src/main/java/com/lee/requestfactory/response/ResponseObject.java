package com.lee.requestfactory.response;

import com.ldc.networkservice.utils.JSONHellper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by LinJK on 7/3/16.
 */
public class ResponseObject implements Serializable {
    private int    Code;
    private String List;
    private String ListTypeName;
    private String Message;
    private String Param;
    private String Param2;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    @SuppressWarnings("unchecked")
    public <T> java.util.List<T> getList() {
        java.util.List<Object> mList = new ArrayList<Object>();
        try {
            Class clazz = Class.forName("com.lee.requestfactory.datas." + getListTypeName());
            JSONArray jsonArray = new JSONArray(List);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject info = (JSONObject)jsonArray.opt(i);

                mList.add(JSONHellper.parseObject(info, clazz));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return (java.util.List<T>)mList;
    }


    public void setList(String list) {
        List = list;
    }

    public String getListTypeName() {
        return ListTypeName;
    }

    public void setListTypeName(String listTypeName) {
        ListTypeName = listTypeName;
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
        return "ResponseObject{" +
                "Code=" + Code +
                ", List='" + List + '\'' +
                ", ListTypeName='" + ListTypeName + '\'' +
                ", Param='" + Param + '\'' +
                ", Param2='" + Param2 + '\'' +
                '}';
    }
}
