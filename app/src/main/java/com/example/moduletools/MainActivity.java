package com.example.moduletools;

import android.os.Bundle;

import com.victory.basemodule.activity.BaseActivity;
import com.victory.basemodule.tools.TitleBar;

/**
 * Time 2019/5/23
 * Explain 主页
 *
 * @author Victory
 */
public class MainActivity extends BaseActivity {
    private TitleBar test;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test=findViewById(R.id.title);
    }
}
