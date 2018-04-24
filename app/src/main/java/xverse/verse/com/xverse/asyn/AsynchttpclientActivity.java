package xverse.verse.com.xverse.asyn;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.HttpGet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;
import xverse.verse.com.xverse.R;

public class AsynchttpclientActivity extends Activity {

    private TextView main_tv;


    private ImageView imageView ;
    private Button button ;
    private ProgressDialog dialog ;
    //来自网络的图片
    private String image_path = "http://imgsrc.baidu.com/forum/pic/item/7c1ed21b0ef41bd51a5ac36451da81cb39db3d10.jpg" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynchttpclient);
        main_tv = (TextView) findViewById(R.id.main_tv);
        //网络请求 new JsonHttpResponseHandler并重写里面的两个方法
        URLManage.showInfos("琴吹柚", new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                // super.onSuccess(statusCode, headers, responseString);
               // System.out.println(responseString.toString());//请求的网页内容文字形式
                main_tv.setText(responseString.toString());//设置内容
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
              //  super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(AsynchttpclientActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }


        });



        //添加弹出的对话框
        dialog = new ProgressDialog(this) ;
        dialog.setTitle("提示") ;
        dialog.setMessage("正在下载图片，请稍后···") ;
        //将进度条设置为水平风格，让其能够显示具体的进度值
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL) ;
        dialog.setCancelable(false) ; //用了这个方法之后，直到图片下载完成，进度条才会消失（即使在这之前点击了屏幕）

        imageView = (ImageView)findViewById(R.id.imageView1) ;
        button = (Button)findViewById(R.id.button1) ;
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //点击按钮时，执行异步任务的操作
                new DownTask().execute(image_path) ;
            }
        }) ;   //注意，这个地方的分号容易遗忘
    }

    /*
     * 异步任务执行网络下载图片
     * */
    public class DownTask extends AsyncTask<String, Integer, byte[]> {
        //上面的方法中，第一个参数：网络图片的路径，第二个参数的包装类：进度的刻度，第三个参数：任务执行的返回结果
        @Override
        //在界面上显示进度条
        protected void onPreExecute() {
            dialog.show() ;
        };

        protected byte[] doInBackground(String... params) {  //三个点，代表可变参数
            //使用网络链接类HttpClient类完成对网络数据的提取，即完成对图片的下载功能
            HttpClient httpClient = new DefaultHttpClient() ;
            HttpGet httpget = new HttpGet(params[0]) ;
            byte[] result = null ;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream() ;
            InputStream inputStream = null ;

            try {
                HttpResponse httpResponse = httpClient.execute(httpget) ;
                if(httpResponse.getStatusLine().getStatusCode()==200){

                    HttpEntity httpEntiry = httpResponse.getEntity();
                    inputStream = httpEntiry.getContent();
                    //    先要获得文件的总长度
                    long file_length = httpResponse.getEntity().getContentLength() ;
                    int len = 0 ;
                    //    每次读取1024个字节
                    byte[] data = new byte[1024] ;
                    //    每次读取后累加的长度
                    int total_length = 0 ;
                    while ((len = inputStream.read(data))!=-1) {
                        //    每读一次，就将total_length累加起来
                        total_length+=len ;
                        //    得到当前图片下载的进度
                        int progress_value = (int) ((total_length / (float)file_length)*100);
                        //    时刻将当前进度更新给onProgressUpdate方法
                        publishProgress(progress_value) ;
                        outputStream.write(data, 0, len);
                    }
                    //    边读边写到ByteArrayOutputStream当中
                    result = outputStream.toByteArray();
                    //bitmap = BitmapFactory.decodeByteArray(result, 0, result.length) ;
                }
            }  catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                httpClient.getConnectionManager().shutdown();
            }
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
            //    更新ProgressDialog的进度条
            dialog.setProgress(values[0]);
        }

        //主要是更新UI
        @Override
        protected void onPostExecute(byte[] result) {
            super.onPostExecute(result);
            //    将doInBackground方法返回的byte[]解码成要给Bitmap
            Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0, result.length) ;
            //    更新我们的ImageView控件
            imageView.setImageBitmap(bitmap) ;//更新UI
            //    使ProgressDialog框消失
            dialog.dismiss() ;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}
