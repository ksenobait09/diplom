package com.ksenobait09.diplom.bmstu_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import Lib.Data;
import Lib.Functions;
import Lib.MyApp;

public class DisplayResults extends AppCompatActivity {

    private String TAG = "DisplayResultsActivity";
    private Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        intent.getData();
        String filepath = intent.getStringExtra("filepath");
        data = ((MyApp) getApplication()).data;
        Functions.saveResultsExcel(filepath, data);

        TableLayout table = (TableLayout) findViewById(R.id.displayLinear);
        TableRow row;
        TextView t1, t2;

        int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (float) 1, getResources().getDisplayMetrics());
        for (int current = 0; current < data.questions.size(); current++) {
            row = new TableRow(this);

            t1 = new TextView(this);
            t2 = new TextView(this);
            Data.Question q = data.questions.get(current);
            String result = String.valueOf((int)(((double)q.rightAnswersCount) / q.answersCount* 100));
            t1.setText(q.text);
            t2.setText(result + " %");

            t1.setTypeface(null, 1);
            t2.setTypeface(null, 1);

            t1.setTextSize(15);
            t2.setTextSize(15);

            t1.setWidth(300*dip);
            t2.setWidth(50*dip);
            t1.setPadding(10*dip, 0, 10*dip, 0);
            row.addView(t1);
            row.addView(t2);

            table.addView(row, new TableLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        }
    }
}
