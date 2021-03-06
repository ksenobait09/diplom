package com.ksenobait09.diplom.bmstu_test;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Lib.Data;
import Lib.Functions;


public class DisplayServerActivity extends AppCompatActivity {
    String TAG = "DisplayServerActivity";
    Data data;
    String filepath;
    Context context;
    Intent intentServerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_server);
        context = getApplicationContext();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        intent.getData();
        filepath = intent.getStringExtra("filepath");
        Log.e(TAG, filepath.toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Прекращаем работу сервера
        if (intentServerService != null) {
            intentServerService.setAction("com.ksenobait09.diplom.bmstu_test.action.STOP");
            startService(intentServerService);
            intentServerService = null;
        }
    }

    public void startServer(View view) {
        Button startServerButton = (Button) findViewById(R.id.button4);
        startServerButton.setEnabled(false);
        TextView statusBar = (TextView) findViewById(R.id.textView3);

        //get local ip
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        String statusText;
        if (ipAddress == null || ipAddress.equals("0.0.0.0") || ipAddress.equals("")) {
            statusText = "Ошибка сети. Пожалуйста, подключитесь к Wi-Fi сети или проверьте настройки маршрутизатора.";
        } else {
            statusText ="Тестовая система запущена по адресу:\n http://" + ipAddress + ":5000/. Для окончания тестирования нажмите кнопку далее.";

            // Запустить тестовую систему
            intentServerService=new Intent(context, ServerService.class);
            intentServerService.setAction("com.ksenobait09.diplom.bmstu_test.action.START");
            intentServerService.putExtra("com.ksenobait09.diplom.bmstu_test.extra.FILEPATH", filepath);
            startService(intentServerService);

            // активировать кнопку "далее"
            Button goToNextPage  = (Button) findViewById(R.id.button3);
            goToNextPage.setEnabled(true);
        }
        statusBar.setText(statusText);
        statusBar.setVisibility(View.VISIBLE);
    }

    public void goToResultsActivity(View view) {
        Intent intent = new Intent(this, DisplayResults.class);
        intent.putExtra("filepath", filepath );
        startActivity(intent);
    }
}
