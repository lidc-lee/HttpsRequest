package com.lee.requestfactory.implementations;

import com.ldc.networkservice.HttpRequestBase.OkHttpApi;
import com.ldc.networkservice.NetworkBase;
import com.lee.requestfactory.interfaces.IHttpsTest;

/**
 * Created by AA on 2016/10/27.
 */

public class HttpsTest extends NetworkBase implements IHttpsTest{
    public HttpsTest(OkHttpApi.OnRequestCallBack cb, int requestID,String serverAddress) {
        super(serverAddress, "https");
        this.addListener(cb);
        this.setRequestId(requestID);
    }

    @Override
    public void HttpTest() {
        getPath("");
    }
}
