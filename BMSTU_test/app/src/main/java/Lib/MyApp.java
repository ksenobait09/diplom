package Lib;

import android.app.Application;

/**
 * Created by root on 15.05.18.
 */

public class MyApp extends Application {

    Data data;
    public Data getTestData() {
        return data;
    }

    public void setTestData(Data data) {
        this.data = data;
    }
}