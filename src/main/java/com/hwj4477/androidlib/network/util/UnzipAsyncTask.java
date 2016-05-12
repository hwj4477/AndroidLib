package com.hwj4477.androidlib.network.util;

import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author wjhong
 * @since 15. 5. 4..
 */
public class UnzipAsyncTask extends AsyncTask<Void, Integer, Integer> {

    private String zipPath;
    private String toPath;
    private UnzipListener listener;
    private int per;
    private int total;

    private static String TAG = "UNZIP";

    public UnzipAsyncTask(String zipPath, String toPath, UnzipListener listener)
    {
        this.zipPath = zipPath;
        this.toPath = toPath;
        this.listener = listener;
        this.per = 0;
        this.total = 0;
    }

    @Override
    protected Integer doInBackground(Void... params) {

        try {
            File f = new File(toPath);
            if(!f.isDirectory()) {
                f.mkdirs();
            }

            File zipFile = new File(zipPath);

            // get count
            FileInputStream tfin = new FileInputStream(zipFile);
            ZipInputStream tzin = new ZipInputStream(tfin);

            while(tzin.getNextEntry() != null){

                total++;
            }

            // unzip
            FileInputStream fin = new FileInputStream(zipFile);
            ZipInputStream zin = new ZipInputStream(fin);

            try {
                ZipEntry ze = null;

                while ((ze = zin.getNextEntry()) != null) {

                    per++;

                    String path = toPath + ze.getName();

                    if (ze.isDirectory()) {
                        File unzipFile = new File(path);
                        if(!unzipFile.isDirectory()) {
                            unzipFile.mkdirs();
                        }
                    }
                    else {

                        FileOutputStream fout = new FileOutputStream(path, false);
                        try {
                            for (int c = zin.read(); c != -1; c = zin.read()) {
                                fout.write(c);
                            }
                            zin.closeEntry();
                        }
                        finally {
                            fout.close();
                        }
                    }

                    publishProgress(per, total);
                }
            }
            finally {
                zin.close();
            }
        }
        catch (FileNotFoundException e)
        {
            Log.d(TAG, "FileNotFoundException");
        }
        catch (Exception e) {
            Log.e(TAG, "Unzip exception", e);

            listener.failure();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        listener.onProgress(values[0], values[1]);
    }

    @Override
    protected void onPostExecute(Integer integer) {

        listener.success();
    }
}