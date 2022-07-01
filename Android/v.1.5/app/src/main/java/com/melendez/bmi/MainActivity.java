package com.melendez.bmi;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {
    //全局变量
    private static final String TAG = "Melendez-MainActivity";
    private EditText hight_edit;
    private EditText weight_edit;
    private TextView show;
    private CardView edit_card;
    private CardView showCard;
    private Button more;
    private Button enter;
    private Button settings;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //控件
        hight_edit = findViewById(R.id.hight);
        weight_edit = findViewById(R.id.weight);
        show = findViewById(R.id.show);
        edit_card = findViewById(R.id.edit_card);
        showCard = findViewById(R.id.showCard);
        enter = findViewById(R.id.enter);
        more = findViewById(R.id.more_button);
        settings = findViewById(R.id.settings);
        findViewById(R.id.toolbar);
        Log.d(TAG, "onCreate: 控件调取完成");

        //判断竖屏
        if (getResources().getConfiguration().orientation == 1) {
            //执行动画
            start_animation_notland();
        } else {
            //执行动画
            start_animation_land();
        }
    }

    private void start_animation_land() {
        PropertyValuesHolder Bottom_here = PropertyValuesHolder.ofFloat("translationY", 200, -50, 0);
        PropertyValuesHolder top_here = PropertyValuesHolder.ofFloat("translationY", -200, 50, 0);
        PropertyValuesHolder left_right = PropertyValuesHolder.ofFloat("translationX", -350, 75, 0);
        PropertyValuesHolder right_left = PropertyValuesHolder.ofFloat("translationX", 200, 30, 0);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0, 1);
        //组合动画
        ObjectAnimator animator_showCard = ObjectAnimator.ofPropertyValuesHolder(showCard, left_right, alpha).setDuration(2250);
        ObjectAnimator animator_settings = ObjectAnimator.ofPropertyValuesHolder(settings, top_here, alpha).setDuration(2000);
        ObjectAnimator animator_enter = ObjectAnimator.ofPropertyValuesHolder(enter, Bottom_here, alpha).setDuration(2000);
        ObjectAnimator animator_more = ObjectAnimator.ofPropertyValuesHolder(more, right_left, alpha).setDuration(2000);
        //执行动画
        animator_more.start();
        animator_enter.start();
        animator_settings.start();
        animator_showCard.start();
        Log.e(TAG, "start_animation_land: 动画执行完成");

    }

    public void start_animation_notland() {
        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("translationY", 500, -100, 0);
        PropertyValuesHolder right_left = PropertyValuesHolder.ofFloat("translationX", 350, -50, 0);
        PropertyValuesHolder left_right = PropertyValuesHolder.ofFloat("translationX", -350, 50, 0);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0, 1);
        //组合动画
        ObjectAnimator animator_showCard = ObjectAnimator.ofPropertyValuesHolder(showCard, translationY, alpha).setDuration(2500);
        ObjectAnimator animator_editCard = ObjectAnimator.ofPropertyValuesHolder(edit_card, translationY, alpha).setDuration(2250);
        ObjectAnimator animator_enter = ObjectAnimator.ofPropertyValuesHolder(enter, left_right, alpha).setDuration(2250);
        ObjectAnimator animator_more = ObjectAnimator.ofPropertyValuesHolder(more, right_left, alpha).setDuration(2250);
        //执行动画
        animator_editCard.start();
        animator_showCard.start();
        animator_enter.start();
        animator_more.start();
        Log.e(TAG, "start_animation_notland: 动画执行完成");
    }

    @SuppressLint("SetTextI18n")
    public void get_calculation(View view) {
        String level;//水平
        //未输入身高体重
        if (TextUtils.isEmpty(hight_edit.getText()) || TextUtils.isEmpty(weight_edit.getText())) {

            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(350);

            Toast.makeText(this, "请输入正确的身高和体重", Toast.LENGTH_SHORT).show();
            //输入卡片抖动动画
            PropertyValuesHolder translationYanimator = PropertyValuesHolder.ofFloat("translationX", 0, 30, -30, 30, -30, 0);
            PropertyValuesHolder rotatoranimator = PropertyValuesHolder.ofFloat("rotation", 0f, 2.5f, -2.5f, 0f);
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(edit_card, translationYanimator, rotatoranimator).setDuration(400);
            animator.start();

            Log.e(TAG, "get_calculation: 身高体重为空");
        } else {
            //输入框读取
            String hight_text = hight_edit.getText().toString();
            String weight_text = weight_edit.getText().toString();
            //转float
            float hight = Float.parseFloat(hight_text);
            float weight = Float.parseFloat(weight_text);
            //计算
            float bmi = weight / (hight * hight);
            //保留两位小数
            BigDecimal bd = new BigDecimal(bmi);
            bmi = (float) bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
            if (bmi > 100) {
                show.setText("请输入正确的身高体重！！！");
                Log.e(TAG, "get_calculation: 身高过低/体重过大");
            }
            //身体水平判断
            else {
                if (bmi < 19) {
                    level = "”偏瘦“";
                } else if (19 <= bmi && bmi < 25) {
                    level = "“健康”";
                } else if (25 <= bmi && bmi < 30) {
                    level = "”超重“";
                } else if (30 <= bmi && bmi < 39) {
                    level = "“严重超重”";
                } else {
                    level = "”极度超重“";
                }
                //在前端输出
                show.setText(getString(R.string.your_bmi) + bmi + "处于" + level + "水平");
            }
        }
    }

    public void Settings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
        Log.d(TAG, "Settings: 跳转至设置界面");
    }

    public ActivityOptionsCompat go_more(View view) {
        ViewCompat.setTransitionName(showCard, "MoreContent");
        ActivityOptionsCompat aop = ActivityOptionsCompat.makeSceneTransitionAnimation(this, showCard, "MoreContent");
        startActivity(new Intent(this, MoreActivity.class), aop.toBundle());
        Log.e(TAG, "go_more:前往更多界面");
        return null;
    }

    public void go_proposal(View view) {
        startActivity(new Intent(this, ProposalActivity.class));
        Log.e(TAG, "go_proposal:前往建议界面");
    }
}