package xverse.verse.com.xverse.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 发送广播之前的准备工作，定义一个广播接收器来准备接收此广播，并在XML中对这个广播接收器进行注册：
 MyBroadcastReceiver 广播接收器：
 * Created by Administrator on 2018/4/19 0019.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Woider 已经收到信息"+intent.getStringExtra("username"),Toast.LENGTH_SHORT).show();

    }
}
