package com.lee.requestfactory;

import com.ldc.networkservice.HttpRequestBase.OkHttpApi;
import com.lee.requestfactory.implementations.CloudUserSvc;
import com.lee.requestfactory.implementations.HttpsTest;
import com.lee.requestfactory.interfaces.ICloudUserSvc;
import com.lee.requestfactory.interfaces.IHttpsTest;

/**
 * 服务请求工厂类
 *
 * @author LinJK
 * @version 2.0
 * <p>
 * 增加获取租赁方案接口(IMeterRentSvc)以及
 * 租户修改密码接口(ITenantSvc--ModifyLoginPassword)
 * ，获取验证码接口(IMsgManageSvc)
 */
public class RequestFactory {
    private static RequestFactory requestFactory = null;

    private OkHttpApi.OnRequestCallBack callBack = null;

    private RequestFactory(OkHttpApi.OnRequestCallBack callBack) {
        this.callBack = callBack;
    }

    public static RequestFactory getInstance(OkHttpApi.OnRequestCallBack callBackListener) {

        requestFactory = new RequestFactory(callBackListener);

        return requestFactory;
    }
    public ICloudUserSvc getCloudUserSvc(int requestID, String serverAddress){
        return new CloudUserSvc(this.callBack,requestID, serverAddress);
    }

    public IHttpsTest getHttpsTestc(int requestID, String serverAddress){
        return new HttpsTest(this.callBack,requestID, serverAddress);
    }
}
