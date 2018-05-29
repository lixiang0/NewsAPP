package com.newsjd.http;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;


/**
 * Created by sjd on 2017/2/10.
 */

public class OkHttpUtils {
    private static final OkHttpClient client = new OkHttpClient();
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/xml; charset=utf-8");

    //    同步GET请求s
//     Response类的string()方法会把文档的所有内容加载到内存，适用于小文档，对应大于1M的文档，应   使用流()的方式获取。
//    response.body().byteStream()
    public void run_get_synchronization(String  url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }
        System.out.println(response.body().string());
    }

    //    异步GET请求
//    读取响应会阻塞当前线程，所以发起请求是在主线程，回调的内容在非主线程中。
    public void run_get_asynchronous() throws Exception {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                System.out.println(response.body().string());
            }
        });
    }

    //    POST方式提交字符串
    //因为整个请求体都在内存中，应避免提交1M以上的文件。
    public static void run_post_String(String url, String postBody) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
        System.out.println(response.body().string());
    }


    //POST方式提交流
    //使用Okio框架以流的形式将内容写入，这种方式不会出现内存溢出问题。
    public void run_post_byte(String url) throws Exception {
        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_MARKDOWN;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("Numbers\n");
                sink.writeUtf8("-------\n");
                for (int i = 2; i <= 997; i++) {
                    sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                }
            }

            private String factor(int n) {
                for (int i = 2; i < n; i++) {
                    int x = n / i;
                    if (x * i == n) return factor(x) + " × " + i;
                }
                return Integer.toString(n);
            }
        };
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
        System.out.println(response.body().string());
    }

    //POST方式提交文件
    public void run_post_file(String url, File file) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
        System.out.println(response.body().string());
    }

    //POST方式提交表单
    //表单的每个Names-Values都进行了URL编码。如果服务器端接口未进行URL编码，可定制个  FormBuilder。
    public void run_post_form(String url) throws Exception {
        RequestBody formBody = new FormBody.Builder()
                .add("search", "Jurassic Park")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);

        System.out.println(response.body().string());
    }


    //文件上传（兼容html文件上传）
    private static final String IMGUR_CLIENT_ID = "...";
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    public void run_upload_file(String url) throws Exception {
        // Use the imgur image upload API as documented at https://api.imgur.com/endpoints/image
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(Headers.of("Content-Disposition", "form-data; name=\"title\""),
                        RequestBody.create(null, "Square Logo"))
                .addPart(Headers.of("Content-Disposition", "form-data; name=\"image\""),
                        RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .url(url)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
        System.out.println(response.body().string());
    }

    //OkHttpResponseJsonStructure json 分析
    public static OkHttpResponseJsonStructure analysisJson(String s) {
        OkHttpResponseJsonStructure jsonStructure = new OkHttpResponseJsonStructure();
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.has("result")) {
                jsonStructure.setResult(jsonObject.optInt("result"));
            }
            if (jsonObject.has("msg")) {
                jsonStructure.setMsg(jsonObject.optString("msg"));
            }
            if (jsonObject.has("cards")) {
                jsonStructure.setCards(jsonObject.optString("cards"));
            }
            if (jsonObject.has("userNames")) {
                jsonStructure.setUserNames(jsonObject.optString("userNames"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonStructure;
    }
    /* //基本测试
    public void connectService() {
        //创建OkHttpClient对象，用于稍后发起请求
        OkHttpClient client = new OkHttpClient();
        //根据请求URL创建一个Request对象
        Request request = new Request.Builder().url(OkHttpContent.DoorServiceUrl + OkHttpContent.lockStatus).build();
        Call call = client.newCall(request);

        //根据Request对象发起Get同步Http请求
        try {
            Response response = call.execute();
            //验证是否请求成功
            if (response.isSuccessful()) {
                //The call was successful.print it to the log
                Log.v("OKHttp", response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //根据Request对象发起Get异步Http请求，并添加请求回调
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求成功，此处对请求结果进行处理
                //String result = response.body().string();
                //InputStream is = response.body().byteStream();
                //byte[] bytes = response.body().bytes();
            }
        });
    }
*/
}
