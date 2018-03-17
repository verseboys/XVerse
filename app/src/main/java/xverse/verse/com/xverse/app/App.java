package xverse.verse.com.xverse.app;

import android.app.Application;
import android.content.Context;

/**
 * 项目名称：${project_name}
 * 类名称：${type_name}
 * 类描述：
 * 创建人：verseboys
 * 创建时间：${date} ${time}
 * 修改人：${user}
 * 修改时间：2018-03-11  10:39
 * 修改备注：
 **/
public class App extends Application {
    private static Context mContext;
    @Override
    public void onCreate(){
        super.onCreate();
        mContext = getApplicationContext();
    }
    public static Context getGlobalContext(){
        return mContext;
    }
}
