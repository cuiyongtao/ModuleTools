package com.example.moduletools;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.victory.basemodule.activity.BaseActivity;

/**
 * Time 2019/5/23
 * Explain 主页
 *
 * @author Victory
 */
public class MainActivity extends BaseActivity {
    private TextView test;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test=findViewById(R.id.test);


    }

    @Override
    protected void onResume() {
        super.onResume();
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getToastUtil().showToastShort("fadsfsdf");
            }
        });
    }
}
