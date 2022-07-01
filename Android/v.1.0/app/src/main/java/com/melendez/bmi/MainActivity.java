package com.melendez.bmi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Melendez";
    private EditText hight_edit;
    private EditText weight_edit;
    private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //控件
        hight_edit = findViewById(R.id.hight);
        weight_edit = findViewById(R.id.weight);
        show = findViewById(R.id.show);
    }

    @SuppressLint("SetTextI18n")
    public void get_calculation(View view) {
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
        bmi = (float) bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        //在前端输出
        show.setText(getString(R.string.your_bmi) + bmi);
    }

}