package com.lee.requestfactory.implementations;

import com.ldc.networkservice.HttpRequestBase.OkHttpApi;
import com.ldc.networkservice.NetworkBase;
import com.ldc.networkservice.utils.StringEncrypt;
import com.lee.requestfactory.interfaces.ICloudUserSvc;

/**
 * Created by Ye on 2016/7/15.
 */
public class CloudUserSvc extends NetworkBase implements ICloudUserSvc {

    private String PATH =this.getClass().getSimpleName()+ ".svc";
    public CloudUserSvc(OkHttpApi.OnRequestCallBack cb, int requestID, String serverAddress) {
        super(serverAddress,"http");

        this.addListener(cb);
        this.setRequestId(requestID);
    }
    @Override
    public void GetLogin(String phoneNum, String pw) {
        try {
            pw= StringEncrypt.Encrypt(pw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getPath(PATH + "/Login/" + phoneNum + "/" + pw);
    }

    @Override
    public void GetAddBindProperty(String cloudUserId, String parkId, String customerMobile, String verifyId, String verifyCode) {

        getPath(PATH + "/Bind?CloudUserId=" +cloudUserId + "&ParkId=" + parkId + "&CustomerMobile=" + customerMobile + "&VerifyId=" + verifyId + "&VerifyCode=" + verifyCode);
    }

    @Override
    public void GetAutoBindProperty(String cloudUserId) {

        getPath(PATH + "/AutoBind?CloudUserId=" +cloudUserId);
    }

    @Override
    public void GetCheckAPPVersion(String version) {

        getPath(PATH + "/CheckAppVersion?AppVersion=" +version);
    }

    @Override
    public void GetForgotPassword(String phoneNum, String pswd, String verifyCode, String VerifyCodeId) {

        try {
            getPath(PATH + "/Register?Mobile=" +phoneNum
                    + "&Password=" + StringEncrypt.Encrypt(pswd) +"&VerifyCode=" + verifyCode
                    + "&VerifyId=" + VerifyCodeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void GetUnits(String cloudUserId) {
        getPath(PATH + "/GetUnits?CloudUserId=" +cloudUserId);

    }

    @Override
    public void AutoLogin(String phoneNum, String encryptPw) {
        getPath(PATH + "/Login/" +phoneNum +"/" +encryptPw);

    }

    @Override
    public void RegisterNewUser(String phoneNum, String pswd, String verifyCode, String VerifyCodeId) {

        getPath(PATH + "/Register?Mobile=" +phoneNum + "&Password=" + pswd +"&VerifyCode=" + verifyCode
                + "&VerifyId=" + VerifyCodeId);
    }

    @Override
    public void ResetLoginPassword(String mobileNumber, String newPassword, String verifyId, String verifyCode) {

        try {
            getPath(PATH + "/ResetLoginPassword?MobileNumber=" +mobileNumber
                    + "&NewPassword=" + StringEncrypt.Encrypt(newPassword) +"&VerifyID=" + verifyId
                    + "&VerifyCode=" + verifyCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void UpdateMobile(String oldMobile, String newMobile, String cloudUserId, String verifyId, String verifyCode) {

        getPath(PATH + "/ChangeCloudUserMobile?oldMobile=" +oldMobile
                + "&newMobile=" + newMobile +"&cloudUserId=" + cloudUserId
                + "&verifyId=" + verifyId + "&verifyCode=" + verifyCode);
    }

    @Override
    public void UpdatePassword(String id, String oldPassword, String newPassword) {

        try {
            getPath(PATH + "/ModifyLoginPassword?cloudUserId=" +id
                    + "&oldPassword=" + StringEncrypt.Encrypt(oldPassword)
                    + "&newPassword=" + StringEncrypt.Encrypt(newPassword));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void GetPassword(String cloudUserId, String wxId, String platform) {

        getPath(PATH + "/ReturnCloudUserPassWord?CloudUserId=" +cloudUserId
                + "&Id=" + wxId + "&Platform=" + platform);
    }
}
