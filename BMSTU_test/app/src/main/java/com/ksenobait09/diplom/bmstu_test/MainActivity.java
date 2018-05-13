package com.ksenobait09.diplom.bmstu_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aspose.cells.LibsLoadHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LibsLoadHelper.loadLibs(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // call this
    public void goToServerActivity(View view) {
        Intent intent = new Intent(this, DisplayServerActivity.class);
        startActivity(intent);
    }

    private static final int READ_REQUEST_CODE = 42;

    public void openFileToCreateTest(View view) {

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Filter to show only images, using the image MIME data type.
        // cIf one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.setType("application/excel");

        startActivityForResult(intent, READ_REQUEST_CODE);
    }
}
