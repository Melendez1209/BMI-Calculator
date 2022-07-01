package com.melendez.bmi;


import static androidx.core.app.ActivityOptionsCompat.makeSceneTransitionAnimation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;

public class OpenningActivity extends AppCompatActivity {
    private static final String TAG = "Melendez-OpenningActivity";
    //全局变量
    private ImageView OpenningImage;
    private TextView OpenningText;
    private TextView AppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openning);
        //调取控件
        OpenningImage = findViewById(R.id.opennig_image);
        OpenningText = findViewById(R.id.click_to_start);
        AppName = findViewById(R.id.AppName);
        Log.e(TAG, "onCreate: 控件调取成功");

        startAnimation();
    }

    public void go_main(View view) {
        //设置跳转过度
        ViewCompat.setTransitionName(AppName, "CONTENT");
        ActivityOptionsCompat aop = makeSceneTransitionAnimation(this, new Pair<View, String>(OpenningImage, "CONTENT"));
        //跳转到主页面
        startActivity(new Intent(this, MainActivity.class), aop.toBundle());
        Log.e(TAG, "go_main: 跳转成功并销毁当前页面");
        finish();
    }

    /**
     * 开屏动画
     */
    public void startAnimation() {
        //动画1
        PropertyValuesHolder alphaAnimation = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);
        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("translationY", 300f, 0f);
        PropertyValuesHolder translationY_text = PropertyValuesHolder.ofFloat("translationY", -450f, 0f);
        //组合动画
        ObjectAnimator animator1_ToImage = ObjectAnimator.ofPropertyValuesHolder(OpenningImage,
                        alphaAnimation)
                .setDuration(1500);
        ObjectAnimator animator1_ToText = ObjectAnimator.ofPropertyValuesHolder(OpenningText,
                        alphaAnimation,
                        translationY_text)
                .setDuration(1500);
        ObjectAnimator animator1_ToAppName = ObjectAnimator.ofPropertyValuesHolder(AppName,
                        alphaAnimation,
                        translationY)
                .setDuration(1500);

        //动画2
        PropertyValuesHolder scaleYAnimation = PropertyValuesHolder.ofFloat("scaleY", 0.1f, 1.1f, 1f);
        PropertyValuesHolder scaleXAnimation = PropertyValuesHolder.ofFloat("scaleX", 0.1f, 1.1f, 1f);
        //组合动画
        ObjectAnimator animator2_ToImage = ObjectAnimator.ofPropertyValuesHolder(OpenningImage,
                        scaleYAnimation,
                        scaleXAnimation)
                .setDuration(2000);
        ObjectAnimator animator2_ToText = ObjectAnimator.ofPropertyValuesHolder(OpenningText,
                        scaleYAnimation,
                        scaleXAnimation)
                .setDuration(2000);
        ObjectAnimator animator2_ToAppName = ObjectAnimator.ofPropertyValuesHolder(AppName,
                        scaleYAnimation,
                        scaleXAnimation)
                .setDuration(2000);

        //启动动画
        animator1_ToAppName.start();
        animator2_ToAppName.start();
        animator1_ToImage.start();
        animator2_ToImage.start();
        animator2_ToText.start();
        animator1_ToText.start();
        Log.e(TAG, "onCreate: 动画播放成功");
    }
}