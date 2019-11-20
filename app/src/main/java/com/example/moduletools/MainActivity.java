package com.example.moduletools;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RemoteViews;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.NotificationCompat;

import com.victory.basemodule.activity.BaseActivity;
import com.victory.basemodule.download.DownLoaderHelper;
import com.victory.basemodule.network.httphelper.NetWorkRequestServer;
import com.victory.basemodule.network.model.BaseModel;
import com.victory.basemodule.network.presenter.BasePresenter;
import com.victory.basemodule.network.view.BaseView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Time 2019/5/23
 * Explain 主页
 *
 * @author Victory
 */
public class MainActivity extends BaseActivity {

    private AppCompatButton button;
    ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        logUtil.getLogE(constantUtils.getPackgeName());


    }



}

