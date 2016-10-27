package com.lee.requestfactory.interfaces;

/**
 * Created by Ye on 2016/7/15.
 */
public interface ICloudUserSvc {

    /**
     * 登录
     * @param phoneNum
     * @param pw
     */
    void GetLogin(String phoneNum, String pw);

    /**
     * 手动添加绑定
     * @param cloudUserId
     * @param parkId
     * @param customerMobile
     * @param verifyId
     * @param verifyCode
     */
    void GetAddBindProperty(String cloudUserId, String parkId, String customerMobile, String verifyId, String verifyCode);

    /**
     * 自动添加绑定
     * @param cloudUserId
     */
    void GetAutoBindProperty(String cloudUserId);

    /**
     * 检查版本
     * @param version
     */
    void GetCheckAPPVersion(String version);

    /**
     * 忘记密码
     * @param phoneNum
     * @param pswd
     * @param verifyCode
     * @param VerifyCodeId
     */
    void GetForgotPassword(String phoneNum, String pswd, String verifyCode, String VerifyCodeId);

    /**
     * 获取单元
     * @param cloudUserId
     */
    void GetUnits(String cloudUserId);

    /**
     * 自动登录
     * @param phoneNum
     * @param encryptPw
     */
    void AutoLogin(String phoneNum, String encryptPw);

    /**
     * 注册
     * @param phoneNum
     * @param pswd
     * @param verifyCode
     * @param VerifyCodeId
     */
    void RegisterNewUser(String phoneNum, String pswd, String verifyCode, String VerifyCodeId);

    /**
     * 重置密码
     * @param mobileNumber
     * @param newPassword
     * @param verifyId
     * @param verifyCode
     */
    void ResetLoginPassword(String mobileNumber, String newPassword, String verifyId, String verifyCode);

    /**
     * 更换手机号
     * @param oldMobile
     * @param newMobile
     * @param cloudUserId
     * @param verifyId
     * @param verifyCode
     */
    void UpdateMobile(String oldMobile, String newMobile, String cloudUserId, String verifyId, String verifyCode);

    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     */
    void UpdatePassword(String id, String oldPassword, String newPassword);

    /**
     * 获取密码
     * @param cloudUserId
     * @param wxId
     * @param platform
     */
    void GetPassword(String cloudUserId, String wxId, String platform);

}
