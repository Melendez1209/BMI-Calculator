package com.melendez.bmi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lsp.RulerView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity-Melendez";
    private RulerView Height_Ruler;
    private RulerView Weight_Ruler;
    private TextView Show_Text;
    private CardView Show_Card;
    private FloatingActionButton Fab;
    private float bmi;
    private String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //查找控件
        Height_Ruler = findViewById(R.id.Height_Ruler);
        Weight_Ruler = findViewById(R.id.Weight_Ruler);
        Show_Text = findViewById(R.id.Show_Text);
        Show_Card = findViewById(R.id.ShowCard);
        Log.e(TAG, "onCreate: 控件查找完成");
    }

    @SuppressLint("SetTextI18n")
    public void Get_Calculation(View view) {
        Log.e(TAG, "Get_Calculation: 确定按钮被按下");
        //RulerView返回值读取
        float Height = Height_Ruler.currentScale;
        float Weight = Weight_Ruler.currentScale;
        Log.d(TAG, "Get_Calculation: 身高：" + Height + "体重；" + Weight);
        Height = Height / 100;
        Log.d(TAG, "Get_Calculation: 身高(m)：" + Height);
        //计算
        bmi = Weight / (Height * Height);
        Log.d(TAG, "Get_Calculation: 保留前的BMI：" + bmi);
        //保留两位小数
        BigDecimal bd = new BigDecimal(bmi);
        bmi = (float) bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
        Log.d(TAG, "Get_Calculation: 保留后的BMI" + bmi);
        if (bmi > 100) {
            Show_Text.setText("请输入正确的身高体重！！！");
            Log.e(TAG, "get_calculation: 身高过低/体重过大");
        }
        //身体水平判断
        else {
            //水平划分
            if (bmi < 19) {
                level = getString(R.string.Thin);
            } else if (19 <= bmi && bmi < 25) {
                level = getString(R.string.Normal);
            } else if (25 <= bmi && bmi < 30) {
                level = getString(R.string.Overweight);
            } else if (30 <= bmi && bmi < 39) {
                level = getString(R.string.Seriously_overweight);
            } else {
                level = getString(R.string.Extremely_overweight);
            }
            //在前端输出
            Show_Text.setText(getString(R.string.Yours_Bmi) + bmi + "\n" + getString(R.string.In) + level + getString(R.string.Stage));
        }
    }

    public void Go_More(View view) {
        Log.e(TAG, "Go_More: 更多按钮被点击");
        ViewCompat.setTransitionName(Show_Card, "MoreContent");
        ActivityOptionsCompat aop = ActivityOptionsCompat.makeSceneTransitionAnimation(this, Show_Card, "MoreContent");
        startActivity(new Intent(this, MoreActivity.class), aop.toBundle());
        Log.e(TAG, "Go_More: 跳转至更多页面");
    }

    public void Go_Result(View view) {
        if (Show_Text.getText() == getString(R.string.None)) {
            Toast.makeText(this, getString(R.string.ResultToast), Toast.LENGTH_LONG).show();
            Log.e(TAG, "Go_Result: 没有计算");
        } else {
            ViewCompat.setTransitionName(Show_Card, "ResultContent");
            ActivityOptionsCompat aop = ActivityOptionsCompat.makeSceneTransitionAnimation(this, Show_Card, "ResultContent");
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("Bmi", bmi);
            intent.putExtra("Level", level);
            startActivity(intent, aop.toBundle());
            Log.e(TAG, "Go_More: 跳转至结果页面");
        }
    }
}