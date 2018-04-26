package com.example.rdos.niisokb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.net.URL;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "ITEM_ID";
    private int mPosition;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mPosition = getIntent().getIntExtra(EXTRA_POSITION, -1);
        Log.i("DetailActivity", "mPosition=" + String.valueOf(mPosition));
        mImageView = (ImageView) findViewById(R.id.image_detail);
        DownloadImageTask downloadImageTask = new DownloadImageTask(mImageView);
        downloadImageTask.execute(App.restMan.getAndroidsImg(mPosition));
//        mNetworkImageView.setImageURI(Uri.parse(App.restMan.getAndroidsImg(mPosition)));
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView mImageView;

        public DownloadImageTask(ImageView imageView) {
            mImageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            Bitmap bmp = null;
            try {
                URL url = new URL(urls[0]);
                bmp = BitmapFactory.decodeStream(url.openStream());
            } catch (Exception e) {
                //TODO: )))
                e.printStackTrace();
            }
            return bmp;
        }

        protected void onPostExecute(Bitmap result) {
            mImageView.setImageBitmap(result);
        }
    }
}
