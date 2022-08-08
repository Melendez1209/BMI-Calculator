package com.melendez.bmi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingActivity extends AppCompatActivity {
    private static final String TAG = "SettingActivity-Melendez";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //查找控件
        TextView AutoNote = findViewById(R.id.Auto_Note);
        Button btn_sys = findViewById(R.id.btn_sys);
        Button btn_time = findViewById(R.id.btn_time);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch AutoSwitch = findViewById(R.id.Auto_Swich);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch DarkSwitch = findViewById(R.id.Dark_Switch);
        //自动
        AutoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    DarkSwitch.setEnabled(false);
                    btn_sys.setEnabled(true);
                    btn_time.setEnabled(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                    AutoNote.setText(getString(R.string.on_Auto));
                    Log.e(TAG, "onCheckedChanged: 打开了- 自动深色模式");
                } else {
                    DarkSwitch.setEnabled(true);
                    btn_sys.setEnabled(false);
                    btn_time.setEnabled(false);
                    Log.e(TAG, "onCheckedChanged: 关闭了- 自动深色模式");
                }
            }
        });
        //深色
        DarkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Log.e(TAG, "onCheckedChanged: 打开了- 深色模式");
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    Log.e(TAG, "onCheckedChanged: 关闭了- 深色模式");
                }
            }
        });
    }

    public void With_Time(View view) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_TIME);
        Log.e(TAG, "With_Sys: 与时间同步");
    }

    public void With_Sys(View view) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        Log.e(TAG, "With_Sys: 与系统同步");
    }
}