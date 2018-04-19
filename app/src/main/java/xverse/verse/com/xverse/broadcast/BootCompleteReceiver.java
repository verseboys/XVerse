package xverse.verse.com.xverse.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;



/**
 * 静态注册实现开机启动：
 动态注册广播接收器可以自由地控制注册与注销，在灵活性方面有很大优势。但是它也存在一个缺点，即必须要在程序启动之后才能接收到广播，
 因为注册的逻辑是写在onCreate()方法中的。而静态注册可以让程序在未启动的情况下就能接收到广播。
 这里我们准备让程序接收一条开机广播，当收到这条广播的时候就可以在onReceive()方法里执行相应的逻辑，从而实现开机启动的功能。
 新建一个 BootCompleteReceiver 继承自 BroadcastReceiver，代码如下所示：
 * Created by Administrator on 2018/4/19 0019.
 */

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"程序已启动",Toast.LENGTH_SHORT).show();
    }
}
