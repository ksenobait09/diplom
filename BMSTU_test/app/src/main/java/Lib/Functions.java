package Lib;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by root on 13.05.18.
 */

public class Functions {
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE
    };

    /**
     * Checks if the app has permission to write to device storage
     * <p>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int p = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int p2 = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_WIFI_STATE);

        if (p != PackageManager.PERMISSION_GRANTED || p2 != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    public static void loadExcel(String filepath, Data data) {
        String TAG = "loadExcel";
        int QUESTION_COL = 0;
        int ANSWER_COL = 1;
        int ANSWER_TRUE_COL = 2;
        // Обработка excel-таблицы
        try {
            Workbook workbook = new Workbook(filepath);
            Worksheet worksheet = workbook.getWorksheets().get(0);
            Cells cells = worksheet.getCells();

            Cell testNameCell = cells.get("B1");
            String testName = testNameCell.getStringValue();
            if (testName.isEmpty()) {
                throw new IllegalAccessException("Неправильно составленный exсel");
            }
            data.setTestName(testName);
            int row = 3;
            int lastQuestionIndex = 0;
            while (true) {
                Cell questionCell = cells.get(row, QUESTION_COL);
                Cell answerCell = cells.get(row, ANSWER_COL);
                Cell isTrueCell = cells.get(row, ANSWER_TRUE_COL);
                String question = questionCell.getStringValue();
                String answer = answerCell.getStringValue();
                Boolean isTrue = !isTrueCell.getStringValue().isEmpty();
                if (question.isEmpty() && answer.isEmpty()) {
                    break;
                } else if (!question.isEmpty() && answer.isEmpty()) {
                    throw new IllegalAccessException("Неправильно составленный exсel");
                }

                if (!question.isEmpty()) {
                    lastQuestionIndex = data.addQuestion(question);
                }
                data.questions.get(lastQuestionIndex).addAnswer(answer, isTrue);
                row++;
            }

            row++;

        } catch (Exception e) {
            Log.e(TAG, e.toString() + filepath);
        }
    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static String getStringFromFile(String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }

    public static String getFileName(Context context, Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri     The Uri to query.
     * @author paulburke
     */
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static String dataToJSONString(Data data) {
        StringBuilder JSON = new StringBuilder("<script>let myTest=");
        JSON.append("{testName:\"").append(data.testName).append("\", questions: [");
        int questionsCount = data.questions.size();
        for (int questionId = 0; questionId < questionsCount; questionId++) {
            Data.Question currentQuestion = data.questions.get(questionId);
            JSON.append("{id:").append(Integer.toString(questionId)).append(", text:\"").append(currentQuestion.text).append("\",answers:[");
            int answersCount = currentQuestion.answers.size();
            for (int answerId = 0; answerId < answersCount; answerId++) {
                Data.Answer currentAnswer = currentQuestion.answers.get(answerId);
                JSON.append("{id:").append(Integer.toString(answerId)).append(", text:\"").append(currentAnswer.text).append("\"}");
                if (answerId != answersCount - 1) {
                    JSON.append(",");
                }
            }
            JSON.append("]}");
            if (questionId != questionsCount - 1) {
                JSON.append(",");
            }
        }
        JSON.append("]};renderTest(myTest);</script>");
        return JSON.toString();
    }

    public static void saveResultsExcel(String filepath, Data data) {
        String TAG = "saveResultsExcel";
        int QUESTION_COL = 0;
        int RESULTS_COL = 1;
        // Обработка excel-таблицы
        try {
            Workbook workbook = new Workbook(filepath);
            int sheetIndex = workbook.getWorksheets().add();
            Worksheet worksheet = workbook.getWorksheets().get(sheetIndex);
            Cells cells = worksheet.getCells();

            Cell testNameCell = cells.get("B1");
            testNameCell.setValue("Результаты " + data.testName);
            int row = 3;
            int lastQuestionIndex = 0;
            for (int i = 0; i < data.questions.size(); i++) {
                Cell questionCell = cells.get(row, QUESTION_COL);
                Cell ResultsCell = cells.get(row, RESULTS_COL);
                Data.Question q = data.questions.get(i);
                questionCell.setValue(q.text);
                String result = String.valueOf((int)(((double)q.rightAnswersCount) / q.answersCount* 100));
                ResultsCell.setValue(result + " %");
                row++;
            }
            workbook.save(filepath);
        } catch (Exception e) {
            Log.e(TAG, e.toString() + filepath);
        }
    }
}
