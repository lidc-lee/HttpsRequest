package com.ldc.networkservice.HttpRequestBase;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class OkHttpApi {

    public enum Protocol {
        HTTP, HTTPS
    }


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final String SESSION_ID    = "connectsid";
    private final String Authorization = "Authorization";
    private final String USER_AGENT    = "User-Agent";

    private String       sessionId     = "";
    private String       authorization = "";
    private String       userAgent     = "";

    private Response     mResponse;

    /* Set current service url */
    protected String     currentBaseUrl;
    protected Protocol   currentProtocol;

    private int requestId;


    public OkHttpApi() {
    }

    public void getPath(String path) {
        String protocolString = (currentProtocol == Protocol.HTTP ? "http://" : "https://");
        Request request = new Request.Builder()
                .url(protocolString + currentBaseUrl + "/" + path)
                .build();

        doTheCall(request);
    }

    public void getPath(String path, Param[] params) {

        StringBuilder paramsEndpoint = new StringBuilder();
        if (params != null) {
            for (Param p : params) {
                if (paramsEndpoint.length() == 0) {
                    paramsEndpoint.append('?');
                } else {
                    paramsEndpoint.append('&');
                }
                paramsEndpoint.append(p.key);
                paramsEndpoint.append('=');
                try {
                    paramsEndpoint.append(URLEncoder.encode(p.value, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            path += paramsEndpoint;
        }

        String protocolString = (currentProtocol == Protocol.HTTP ? "http://" : "https://");
        Request request = new Request.Builder()
                .url(protocolString + currentBaseUrl + "/" + path)
                .build();

        doTheCall(request);
    }

    public void postPath(String path, String json) {

        String protocolString = (currentProtocol == Protocol.HTTP ? "http://" : "https://");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(protocolString + currentBaseUrl + "/" + path)
                .post(body)
                .build();

        doTheCall(request);
    }

    public void postPath(String path, String fileKey, File file, Map<String, String> paramsMap) {

        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        MultipartBuilder mb = new MultipartBuilder().type(MultipartBuilder.FORM);

        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            String key = "form-data; name=\"" + entry.getKey() + "\"";
            mb.addPart(Headers.of("Content-Disposition", key),
                       RequestBody.create(null, entry.getValue()));
        }
        String fileNameKey = "form-data; name=\"" + fileKey + "\"; filename=\"" + file.getName() + "\"";
        mb.addPart(Headers.of("Content-Disposition", fileNameKey), fileBody);
        RequestBody requestBody = mb.build();
        String protocolString = (currentProtocol == Protocol.HTTP ? "http://" : "https://");

        Request request = new Request.Builder()
                .url(protocolString + currentBaseUrl + "/" + path)
                .post(requestBody)
                .build();

        doTheCall(request);
    }

    public void putJson(String path, String json) {
        String protocolString = (currentProtocol == Protocol.HTTP ? "http://" : "https://");

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(protocolString + currentBaseUrl + "/" + path)
                .put(body)
                .build();

        doTheCall(request);
    }

    public void deletePath(String path) {
        String protocolString = (currentProtocol == Protocol.HTTP ? "http://" : "https://");

        Request request = new Request.Builder()
                .url(protocolString + currentBaseUrl + "/" + path)
                .delete()
                .build();

        doTheCall(request);
    }

    protected void doTheCall(final Request request) {

        OkHttpClient client = OkHttpClientManager.getClinet();
        client.setConnectTimeout(30, TimeUnit.SECONDS);
        client.setWriteTimeout(7, TimeUnit.SECONDS);
        client.setReadTimeout(7, TimeUnit.SECONDS);

        onRequestCallBack.onBefore(requestId);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
                onRequestCallBack.onFailure(requestId);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                mResponse = response;
                onRequestCallBack.onResponse(requestId, response.body().string());
            }
        });

    }

    public static class Param {
        public Param() {
        }

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

        String key;
        String value;
    }

    /**
     *  Sample:
     *  Get status code -- response.code()
     */
    public Response getCallResponse() {
        return this.mResponse;
    }

    private OnRequestCallBack onRequestCallBack;

    public interface OnRequestCallBack {
        void onBefore(int requestId);
        void onFailure(int requestId);
        void onResponse(int requestId, String result);
    }

    public void addListener(OnRequestCallBack callBack) {
        this.onRequestCallBack = callBack;
    }

    public void setRequestId(int requsetId) {
        this.requestId = requsetId;
    }
}
