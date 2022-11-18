package com.melendez.bmi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private static final String TAG = "ResultActivity-Melendez";
    private float Bmi;
    private String Link = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //获取传参
        Intent intent = getIntent();
        Bmi = intent.getFloatExtra("Bmi", 20.5F);
        String Level = intent.getStringExtra("Level");
        String BMI = Float.toString(Bmi);
        Log.d(TAG, "onCreate: BMI:" + BMI + "水平：" + Level);

        //查找控件
        TextView TV_BMI = findViewById(R.id.TV_BMI);
        TextView TV_Level = findViewById(R.id.TV_Level);
        TextView TV_Advice = findViewById(R.id.Advice_View);
        TextView TV_Link = findViewById(R.id.Link_View);
        //设置建议
        String Advice;
        if (Bmi < 19) {
            Advice = getString(R.string.Advice_Thin);
            Link = "https://zhidao.baidu.com/question/1309158929255721019.html";
        } else if (19 <= Bmi && Bmi < 25) {
            Advice = getString(R.string.Advice_Normal);
        } else if (25 <= Bmi && Bmi < 30) {
            Advice = getString(R.string.Advice_OverWeight1);
            Link = "https://www.who.int/zh/news-room/fact-sheets/detail/obesity-and-overweight";
        } else if (30 <= Bmi && Bmi < 39) {
            Advice = getString(R.string.Advice_OverWeight2);
            Link = "https://www.who.int/zh/news-room/fact-sheets/detail/obesity-and-overweight";
        } else {
            Advice = getString(R.string.Advice_OverWeight2);
            Link = "https://www.who.int/zh/news-room/fact-sheets/detail/obesity-and-overweight";
        }
        //在前端输出
        TV_Advice.setText(Advice);
        TV_Link.setText(Link);
        //设置表格
        TV_BMI.setText(BMI);
        TV_Level.setText(Level);
        Log.e(TAG, "onCreate: 表格设置完成");

    }

    public void Go_Link(View view) {
        if (Link != null) {
            Uri uri = Uri.parse(Link);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

    }
}