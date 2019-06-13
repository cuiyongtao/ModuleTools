package com.example.moduletools;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import com.victory.basemodule.activity.BaseActivity;
import com.victory.basemodule.network.httphelper.NetWorkRequestServer;
import com.victory.basemodule.network.model.BaseModel;
import com.victory.basemodule.network.presenter.BasePresenter;
import com.victory.basemodule.network.view.BaseView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Time 2019/5/23
 * Explain 主页
 *
 * @author Victory
 */
public class MainActivity extends BaseActivity {

    private AppCompatButton button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });


    }

    private void init(){
        BaseView baseView = new BaseView() {
            @Override
            public void requestSuccess(Object data) {
                logUtil.getLogD("aaass"+data.toString());
            }

            @Override
            public void requestError(@NotNull String errorMessage) {
                logUtil.getLogD(errorMessage);
            }
        };
        BaseModel baseModel = new BaseModel(NetWorkRequestServer.class);

        BasePresenter basePresenter = new BasePresenter();
        basePresenter.onRequestStart(baseView);
        Map<String, Object> map = new HashMap<>();
        map.put("username", "victory");
        map.put("password", "123456");
        basePresenter.getObjectData(compositeDisposable,baseModel.postFormRegisterObject("http://192.168.2.117:8000/logins", map));
    }
}
