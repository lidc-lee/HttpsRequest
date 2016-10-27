package com.ldc.networkservice;

import android.util.Log;

import com.ldc.networkservice.HttpRequestBase.OkHttpApi;
import com.ldc.networkservice.utils.StringEncrypt;
import com.ldc.networkservice.utils.TimeUtils;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

/**
 * Created by LinJK on 6/17/16.
 */
public class NetworkBase extends OkHttpApi {
    private final String HEADER_AUTHORIZATION = "Authorization";

    public NetworkBase(String serverAddress,String type) {
        currentBaseUrl  = serverAddress;
        //
        if (type.equals("http")){
            currentProtocol = Protocol.HTTP;
        }else {
            currentProtocol = Protocol.HTTPS;
        }

    }

    @Override
    public void getPath(String path) {
        String protocolString = (currentProtocol == Protocol.HTTP ? "http://" : "https://");

        Request request = new Request.Builder()
                .header(HEADER_AUTHORIZATION, getEncryptHeaderString())
                .url(protocolString + currentBaseUrl + "/" + path)
                .build();
        Log.i("request",request.toString());
        doTheCall(request);
    }

    @Override
    public void postPath(String path, String json) {
        String protocolString = (currentProtocol == Protocol.HTTP ? "http://" : "https://");
        RequestBody body = RequestBody.create(JSON, json);
        Log.d("NetworkBase", "data will post: " + json);
        Request request = new Request.Builder()
                .header(HEADER_AUTHORIZATION, getEncryptHeaderString())
                .url(protocolString + currentBaseUrl + "/" + path)
                .post(body)
                .build();
        Log.i("request",request.toString());
        doTheCall(request);
    }

    /**
     * 获取HTTP请求头加密串
     * */
    private String getEncryptHeaderString() {
        String header = "HDACS access_token=epcloudtokenkey20150508;request_time=" + TimeUtils.getUTCTime();
        String enHeader = "";
        try {
            enHeader = StringEncrypt.Encrypt(header);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enHeader;
    }
}
