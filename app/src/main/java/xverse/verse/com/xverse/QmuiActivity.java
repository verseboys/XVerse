package xverse.verse.com.xverse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import butterknife.ButterKnife;
import butterknife.OnClick;
import xverse.verse.com.xverse.common.BaseActivity;

public class QmuiActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qmui);
        ButterKnife.bind(this);
    }

    @Override
    @OnClick({R.id.bt_djsj})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_djsj:
                showMessagePositiveDialog();

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

    // ================================ 生成不同类型的对话框
    private void showMessagePositiveDialog() {

        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("标题")
                .setMessage("确定要发送吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        Toast.makeText(QmuiActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

}
