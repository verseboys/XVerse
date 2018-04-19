package xverse.verse.com.xverse.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import xverse.verse.com.xverse.R;

/**
 * 广播类
 */
public class BroadcastReceiverActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);


        intentFilter = new IntentFilter();
        //为过滤器添加处理规则
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        //注册广播接收器
        registerReceiver(networkChangeReceiver, intentFilter);



        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把要发送的广播值传入Intent对象
                Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
                intent.putExtra("username","吴磊");
                //调用Context的 sendBroadcast()方法发送广播
                sendBroadcast(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //动态的广播接收器最后一定要取消注册
        unregisterReceiver(networkChangeReceiver);
    }

    //自定义内部类，继承自BroadcastReceiver
    public class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {//connectivityManger是一个系统服务类，专门用于管理网络连接
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            //调用NetworkInfo的isAvailable()方法判断是否联网
            if(networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context,"网络已连接",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"网络不可用",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
