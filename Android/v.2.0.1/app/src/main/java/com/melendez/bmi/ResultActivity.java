package com.melendez.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private static final String TAG = "ResultActivity-Melendez";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //获取传参
        Intent intent = getIntent();
        float Bmi = intent.getFloatExtra("Bmi", 20.5F);
        String Level = intent.getStringExtra("Level");
        String BMI = Float.toString(Bmi);
        Log.d(TAG, "onCreate: BMI:" + BMI + "水平：" + Level);

        //查找控件
        TextView TV_BMI = findViewById(R.id.TV_BMI);
        TextView TV_Level = findViewById(R.id.TV_Level);
        TV_BMI.setText(BMI);
        TV_Level.setText(Level);
        Log.e(TAG, "onCreate: 表格设置完成");

    }
}