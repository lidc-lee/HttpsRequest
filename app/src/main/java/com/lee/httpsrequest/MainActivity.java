package com.lee.httpsrequest;

import android.app.Dialog;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.ldc.networkservice.HttpRequestBase.OkHttpApi;
import com.lee.requestfactory.RequestFactory;
import com.lee.requestfactory.response.ResponseObject;
import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity implements OkHttpApi.OnRequestCallBack{

    private static final int REQUEST_LOGIN=100;
    private static final int REQUEST_HTTPS=101;
    private String RequestServerAddress="cloud.hodi.cn:6403";
    private String RequestServerAddressHttps="www.linjk.cn:3298";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestFactory.getInstance(this).getCloudUserSvc(REQUEST_LOGIN, RequestServerAddress).
                GetLogin("13650714309","123456");
        RequestFactory.getInstance(this).getHttpsTestc(REQUEST_HTTPS,RequestServerAddressHttps)
                .HttpTest();
    }

    @Override
    public void onBefore(int requestId) {

    }

    @Override
    public void onFailure(int requestId) {

    }

    @Override
    public void onResponse(final int requestId, final String result) {
        Logger.json(result);
        Logger.xml(result);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
//                    ResponseObject responseObject = JSON.parseObject(result,ResponseObject.class);
//                    switch (requestId){
//                        case REQUEST_LOGIN:
//                            if (responseObject.getCode()==0){
//
//                            }
//                            break;
//                    }
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
    }
}
