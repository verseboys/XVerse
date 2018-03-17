package xverse.verse.com.xverse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;
import xverse.verse.com.xverse.common.BaseActivity;

public class DynamicGraphActivity extends BaseActivity {

    private Button button1,button2,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_graph);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        ButterKnife.bind(this);



    }

    @Override
    @OnClick({R.id.button1,R.id.button2,R.id.button3})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1 :
                Intent intent=new Intent(DynamicGraphActivity.this,SimpleActivity.class);
                startActivity(intent);
                break;
            case R.id.button2 :
               intent=new Intent(DynamicGraphActivity.this,ClickActivity.class);
                startActivity(intent);
                break;
            case R.id.button3 :
                 intent=new Intent(DynamicGraphActivity.this,NetworkActivity.class);
                startActivity(intent);

                break;
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void processClick(View view) {

    }
}
