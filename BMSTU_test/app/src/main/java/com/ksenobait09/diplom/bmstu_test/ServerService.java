package com.ksenobait09.diplom.bmstu_test;

import android.app.IntentService;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.text.format.Formatter;
import android.util.Log;

import com.koushikdutta.async.http.WebSocket;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;

import java.io.File;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import Lib.Data;
import Lib.Functions;
import Lib.MyApp;
import Lib.TestTemplate;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class ServerService extends IntentService {

    private AsyncHttpServer server;
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
                    Context appContext = getApplicationContext();
                    final String filepath = intent.getStringExtra(EXTRA_FILEPATH);
                    Data data = new Data();
                    Log.w(TAG, "Tачата загрузка из excel");
                    Functions.loadExcel(filepath, data);
                    Log.w(TAG, "Завершена загрузка из excel");
                    ((MyApp) this.getApplication()).setTestData(data);


                    //get local ip
                    WifiManager wifiManager = (WifiManager) appContext.getSystemService(WIFI_SERVICE);
                    String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
                    Log.w(TAG, ipAddress);

                    //save file in internal storage:
                    Context context = getApplicationContext();
                    TestTemplate.createTemplateFile(context);

                    /// server
                    server = new AsyncHttpServer();

                    List<WebSocket> _sockets = new ArrayList<WebSocket>();

                    final String page = Functions.convertStreamToString(appContext.getAssets().open("test_template.html"));
                    server.get("/", new HttpServerRequestCallback() {
                        @Override
                        public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {
                            response.send(page);
                        }
                    });

// listen on port 5000
                    server.listen(5000);
// browsing http://localhost:5000 will return Hello!!!

                } else if (ACTION_PROCESS.equals(action)) {
                }
            }
            catch (Exception e) {
                Log.e(TAG, e.toString());
                server.stop();
            }finally {
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
