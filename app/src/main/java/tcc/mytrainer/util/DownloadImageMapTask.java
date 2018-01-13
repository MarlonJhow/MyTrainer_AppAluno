package tcc.mytrainer.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by Marlon on 23/09/2017.
 */

public class DownloadImageMapTask extends AsyncTask<String, Void, Bitmap> {
    HashMap<String, Bitmap> map;
    String url;

    public DownloadImageMapTask(HashMap<String, Bitmap> map) {
        this.map = map;
    }

    protected Bitmap doInBackground(String... urls) {
        url = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        map.put(url, result);
    }
}
