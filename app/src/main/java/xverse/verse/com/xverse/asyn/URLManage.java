package xverse.verse.com.xverse.asyn;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Url管理类这里只写了一个，实际开发中有很多请求
 * Created by Administrator on 2018/4/24 0024.
 */

public class URLManage {

    // 以百度搜索为例
    //https://www.baidu.com/s?wd=
    // https://www.baidu.com/s?wd=琴吹柚
    public final static String HOST = "https://www.baidu.com/";
    private static AsyncHttpClient client = new AsyncHttpClient(); // 实例化对象

    static {
        client.setTimeout(11000); // 设置链接超时，如果不设置，默认为10s
    }

    public static void showInfos(String string, JsonHttpResponseHandler res) {
        String urlString = HOST + "s";
        RequestParams params = new RequestParams(); // 绑定参数
        params.put("wd", string);// 前面的key就是连接中所对应的字段
        get(urlString, params, res);
    }

    /**
     * 拼接地址并请求
     *
     * @param urlString
     * @param params
     * @param res
     */
    private static void get(String urlString, RequestParams params, JsonHttpResponseHandler res) {
        System.out.println((urlString + "?" + params.toString()));//可以看下请求的地址
        client.get(urlString, params, res);
    }
}

