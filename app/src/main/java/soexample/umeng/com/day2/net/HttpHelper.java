package soexample.umeng.com.day2.net;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpHelper {

    private final int FAILURE_CODE = 1001;//失败

    private final int SUCCESS_CODE = 1000;//成功
    private HttpListener listener;

    public interface HttpListener {
        void success(String data);
        void fail(String error);
    }

    //get请求
    public HttpHelper get(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            doHttp(okHttpClient, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }


    private void doHttp(OkHttpClient okHttpClient, Request request) throws IOException {
        final Message message = Message.obtain();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                message.what = FAILURE_CODE;
                message.obj = e.getMessage();//失败的信息
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                message.what = SUCCESS_CODE;
                message.obj = data;
                handler.sendMessage(message);
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS_CODE://成功
                    String data = (String) msg.obj;
                    listener.success(data);
                    break;
                case FAILURE_CODE://失败
                    String error = (String) msg.obj;
                    listener.fail(error);
                    break;

            }
        }
    };

    //传递接口
    public void result(HttpListener listener) {
        this.listener = listener;
    }

    //post请求
    public HttpHelper post(String url, RequestBody body) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            doHttp(client, request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

}
