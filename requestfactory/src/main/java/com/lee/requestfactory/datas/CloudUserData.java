package com.lee.requestfactory.datas;

import java.io.Serializable;

/**
 * Created by LinJK on 7/14/16.
 * 云平台用户数据类
 */
public class CloudUserData extends BaseData implements Serializable{
    private String Id;
    private String CloudUserName;
    private String Mobile;
    private String Email;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCloudUserName() {
        return CloudUserName;
    }

    public void setCloudUserName(String cloudUserName) {
        CloudUserName = cloudUserName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "CloudUserData{" +
                "Id='" + Id + '\'' +
                ", CloudUserName='" + CloudUserName + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
