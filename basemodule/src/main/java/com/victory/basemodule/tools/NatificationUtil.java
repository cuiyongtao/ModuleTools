package com.victory.basemodule.tools;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.victory.basemodule.R;

/**
 * @author Victory
 * @date 2019/6/19
 * NatificationUtil :
 */
public class NatificationUtil {

    /**
     * TODO 通知栏
     */

    private static Context mContext;
    private static NatificationUtil natificationUtil;

    static NotificationManager notificationManager;
    static Notification.Builder mBuilder;
    static RemoteViews remoteViews;
    static NotificationChannel channel;

    //ID
    static String id = "testNotification";
    //名称
    static String name = "notification";

    public static NatificationUtil getNatificationUtil(Context context) {
        if (natificationUtil == null) {
            mContext = context;
            natificationUtil = new NatificationUtil();
            notificationManager = (NotificationManager) context.getSystemService
                    (Context.NOTIFICATION_SERVICE);
            mBuilder = new Notification.Builder(context);
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_notification);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                channel = new NotificationChannel(id, name, NotificationManager
                        .IMPORTANCE_DEFAULT);

                mBuilder.setChannelId(id);
                notificationManager.createNotificationChannel(channel);

                mBuilder.setSmallIcon(R.mipmap.timg);
                mBuilder.setContent(remoteViews);
//            if (progress == 1) {
//                mBuilder.setDefaults(Notification.DEFAULT_SOUND);
//            }
                remoteViews.setImageViewResource(R.id.image, R.mipmap.timg);
                remoteViews.setTextViewText(R.id.title, "我是标题");
                remoteViews.setTextViewText(R.id.content, "我是内容");
//            remoteViews.setProgressBar(R.id.pBar, 100, progress, false);
//            remoteViews.setTextViewText(R.id.proNum, progress+"");
            }
        }
        return natificationUtil;
    }


    /**
     * 默认通知栏
     * 用不同手机会有不同效果显示，需注意
     */
    private void setNotficationDemo() {
        /**
         *  创建通知栏管理工具
         */

        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        /**
         *  实例化通知栏构造器
         */

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);

        /**
         *  设置Builder
         */
        //设置标题
        mBuilder.setContentTitle("我是标题")
                //设置内容
                .setContentText("我是内容")
                //设置大图标
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher))
                //设置小图标
                .setSmallIcon(R.mipmap.ic_launcher_round)
                //设置通知时间
                .setWhen(System.currentTimeMillis())
                //首次进入时显示效果
                .setTicker("我是测试内容")
                //设置通知方式，声音，震动，呼吸灯等效果，这里通知方式为声音
                .setDefaults(Notification.DEFAULT_SOUND);

        notificationManager.notify(10, mBuilder.build());
    }


    /**
     * 自定义通知栏
     */
    private void setNotificationDemoSecond(int progress) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.layout_notification);
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService
                (Context.NOTIFICATION_SERVICE);
        mBuilder.setSmallIcon(R.mipmap.timg);
//        Intent intent = new Intent(this, SecondeActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setContent(remoteViews);
        if (progress == 1) {
            mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        }
        remoteViews.setImageViewResource(R.id.image, R.mipmap.timg);
        remoteViews.setTextViewText(R.id.title, "我是标题");
        remoteViews.setTextViewText(R.id.content, "我是内容");
        remoteViews.setProgressBar(R.id.pBar, 10, progress, false);
        remoteViews.setTextViewText(R.id.proNum, progress + "/10");
        notificationManager.notify(10, mBuilder.build());
    }

    /**
     *
     */
    public void setNotificationDemoForAndroid(int p) {
        remoteViews.setProgressBar(com.victory.basemodule.R.id.pBar, 100, p, false);
        remoteViews.setTextViewText(com.victory.basemodule.R.id.proNum, p + "");
        notificationManager.notify(10, mBuilder.build());
    }


}
