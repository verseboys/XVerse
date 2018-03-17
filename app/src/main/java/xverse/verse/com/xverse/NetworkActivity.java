package xverse.verse.com.xverse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.model.LottieComposition;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 项目名称：${project_name}
 * 类名称：${type_name}
 * 类描述：
 * 创建人：verseboys
 * 创建时间：${date} ${time}
 * 修改人：${user}
 * 修改时间：2018-03-03  16:18
 * 修改备注：
 **/
public class NetworkActivity extends AppCompatActivity {
    LottieAnimationView animation_view_network;
    private OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        animation_view_network=(LottieAnimationView)findViewById(R.id.animation_view_network);
//        loadUrl("http://www.chenailing.cn/EmptyState.json");


        String uu= "https://raw.githubusercontent.com/panacena/LottieTest/master/app/src/main/assets/LottieLogo1.json";
        loadUrl(uu);

    }

    private void loadUrl(String url) {
        Request request;
        try {
            request = new Request.Builder()
                    .url(url)
                    .build();
        } catch (IllegalArgumentException e) {
            return;
        }


        if (client == null) {
            client = new OkHttpClient();
        }
        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {

            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                }

                try {
                    JSONObject json = new JSONObject(response.body().string());
                    LottieComposition
                            .fromJson(getResources(), json, new LottieComposition.OnCompositionLoadedListener() {
                                @Override
                                public void onCompositionLoaded(LottieComposition composition) {
                                    setComposition(composition);
                                }
                            });
                } catch (JSONException e) {
                }
            }
        });
    }

    private  void setComposition(LottieComposition composition){
        animation_view_network.setProgress(0);
        animation_view_network.loop(true);
        animation_view_network.setComposition(composition);
        animation_view_network.playAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        animation_view_network.cancelAnimation();
    }
}
