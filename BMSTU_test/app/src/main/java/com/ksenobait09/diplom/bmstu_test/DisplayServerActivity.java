package com.ksenobait09.diplom.bmstu_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DisplayServerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_server);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

    }
}
