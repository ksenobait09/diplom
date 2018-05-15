package com.ksenobait09.diplom.bmstu_test;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import Lib.Data;
import Lib.Functions;
import Lib.MyApp;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class ServerService extends IntentService {

    private static int FOREGROUND_ID=1338;
    private String TAG = "IntentService";
    public static final String ACTION_START = "com.ksenobait09.diplom.bmstu_test.action.START";
    public static final String ACTION_PROCESS = "com.ksenobait09.diplom.bmstu_test.action.PROCESS";

    public static final String EXTRA_FILEPATH = "com.ksenobait09.diplom.bmstu_test.extra.FILEPATH";

    public ServerService() {
        super("ServerService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            startForeground(FOREGROUND_ID,
                    buildForegroundNotification("hui"));

            try {
                final String action = intent.getAction();
                if (ACTION_START.equals(action)) {
                    final String filepath = intent.getStringExtra(EXTRA_FILEPATH);
                    Data data = new Data();
                    Log.w(TAG, "Tачата загрузка из excel");
                    Functions.loadExcel(filepath, data);
                    Log.w(TAG, "Завершена загрузка из excel");
                    ((MyApp) this.getApplication()).setTestData(data);

                } else if (ACTION_PROCESS.equals(action)) {
                }
            } finally {
                stopForeground(true);
            }
        }
    }


    private Notification buildForegroundNotification(String filename) {
        NotificationCompat.Builder b=new NotificationCompat.Builder(this);

        b.setOngoing(true)
                .setContentTitle("ContentTitle")
                .setContentText("ContentText");

        return(b.build());
    }
}
