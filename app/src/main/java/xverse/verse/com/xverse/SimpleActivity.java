package xverse.verse.com.xverse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 项目名称：${project_name}
 * 类名称：${type_name}
 * 类描述：
 * 创建人：verseboys
 * 创建时间：${date} ${time}
 * 修改人：${user}
 * 修改时间：2018-03-03  16:19
 * 修改备注：
 **/
public class SimpleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // activity_simple.xml中 lottie_fileName="data.json"
        // 所以只需要在 app/src/main/assets 中添加AE 生成的 json文件，重命名为data.json就可以显示动画
        setContentView(R.layout.activity_simple);
    }
}
