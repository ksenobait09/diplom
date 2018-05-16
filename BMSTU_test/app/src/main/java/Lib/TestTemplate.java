package Lib;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by root on 15.05.18.
 */


public class TestTemplate{
    public static String createTemplateFile(Context context) {
        String path = Environment.getExternalStorageDirectory()+"/bauman_test/";
        File file = new File(path+ "test_template.html");
        if (!file.exists()) {
            file.mkdirs();
            //copy file
            AssetManager assetManager = context.getAssets();
            try {
                InputStream in = assetManager.open("test_template.html");
                OutputStream out = new FileOutputStream(path + "test_template.html");
                byte[] buffer = new byte[1024];
                int read = in.read(buffer);
                while (read != -1) {
                    out.write(buffer, 0, read);
                    read = in.read(buffer);
                }
            } catch (Exception e) {
                e.getMessage();
            }
            // end of file copy
        }
        return file.getAbsolutePath();
    }
}
