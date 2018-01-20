package com.goldenthread.infopetapp.general;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

import com.goldenthread.infopetapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PhotoUtilsImpl implements PhotoUtils {
    private Context mContext;
    private BitmapFactory.Options generalOptions;
    private Bitmap downloadedImage;

    public PhotoUtilsImpl(Context context) {
        this.mContext = context;
    }

    public static File createTemporaryFile(String part, String ext,
                                           Context myContext) throws Exception {
        String path = myContext.getExternalCacheDir().getAbsolutePath()
                + "/temp/";
        File tempDir = new File(path);
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }
        return File.createTempFile(part, ext, tempDir);
    }

    public void getImage(Uri uri, PhotoSetter photoSetter) {
        Object[] object = new Object[2];
        object[0] = (Object) uri;
        object[1] = (Object) photoSetter;
        new DownloadTask().execute(object);
    }

    private class DownloadTask extends AsyncTask<Object,Integer, Bitmap> {
        private PhotoSetter photoSetter;

        @Override
        protected Bitmap doInBackground(Object...objects) {
            Uri uri = (Uri) objects[0];
            this.photoSetter = (PhotoSetter) objects[1];
            Bitmap result = null;
            BitmapFactory.Options options = new BitmapFactory.Options();
            InputStream is = null;
            try {
                is = mContext.getContentResolver().openInputStream(uri);
                result = BitmapFactory.decodeStream(is, null, options);
                is.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                cancel(true);
            } catch (IOException e) {
                e.printStackTrace();
                cancel(true);
            }

            return result;
        }

        @Override
        protected void onPostExecute(Bitmap result){
            photoSetter.onPhotoDownloaded(result);
        }

        @Override
        protected void onCancelled(){
            Toast.makeText(mContext, mContext.getString(R.string.error_cargar_foto), Toast.LENGTH_LONG).show();
        }
    }

    public static int nearest2pow(int value) {
        return value == 0 ? 0
                : (32 - Integer.numberOfLeadingZeros(value - 1)) / 2;
    }

    public Bitmap scaleImage(BitmapFactory.Options options, Uri uri,
                             int targetWidth) {
        if (options == null)
            options = generalOptions;
        Bitmap bitmap = null;
        double ratioWidth = ((float) targetWidth) / (float) options.outWidth;
        double ratioHeight = ((float) targetWidth) / (float) options.outHeight;
        double ratio = Math.min(ratioWidth, ratioHeight);
        int dstWidth = (int) Math.round(ratio * options.outWidth);
        int dstHeight = (int) Math.round(ratio * options.outHeight);
        ratio = Math.floor(1.0 / ratio);
        int sample = nearest2pow((int) ratio);

        options.inJustDecodeBounds = false;
        if (sample <= 0) {
            sample = 1;
        }
        options.inSampleSize = (int) sample;
        options.inPurgeable = true;
        try {
            InputStream is;
            is = mContext.getContentResolver().openInputStream(uri);
            bitmap = BitmapFactory.decodeStream(is, null, options);
            if (sample > 1)
                bitmap = Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight,
                        true);
            is.close();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return bitmap;
    }

}
