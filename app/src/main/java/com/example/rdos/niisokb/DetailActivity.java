package com.example.rdos.niisokb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements View.OnTouchListener {

    public static final String EXTRA_POSITION = "ITEM_ID";
    private final String LOG_TAG = "DetailActivity";
    private int mPosition;
    private ImageView mImageView;
    private float mInitialX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mPosition = getIntent().getIntExtra(EXTRA_POSITION, -1);
        Log.i(LOG_TAG, "mPosition=" + String.valueOf(mPosition));
        mImageView = (ImageView) findViewById(R.id.image_detail);
        mImageView.setOnTouchListener(this);
//        DownloadImageTask downloadImageTask = new DownloadImageTask(mImageView);
//        downloadImageTask.execute(App.restMan.getAndroidsImgUrl(mPosition));
//        mNetworkImageView.setImageURI(Uri.parse(App.restMan.getAndroidsImgUrl(mPosition)));

        loadImage();
    }

    private void loadImage() {
        //TODO: redirect http to https not work??
        String newUrl = App.restMan.getAndroidsImgUrl(mPosition).replace("http", "https");
        Picasso.with(this)
                .load(newUrl)
                .placeholder(R.mipmap.ic_loading_image)
                .error(R.mipmap.ic_loading_image_error)
                .into(mImageView);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mInitialX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalX = event.getX();
                Log.i(LOG_TAG, "Action was UP");

                if (mInitialX < finalX) {
                    Log.i(LOG_TAG, "Left to Right swipe");
                    if (mPosition > 0) {
                        mPosition--;
                    }
                    loadImage();
                }

                if (mInitialX > finalX) {
                    Log.i(LOG_TAG, "Right to Left swipe");
                    if (mPosition < App.restMan.getAndroidsCount()) {
                        mPosition++;
                    }
                    loadImage();
                }
                break;
        }
        return true;
    }


//    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
//        private ImageView mImageView;
//
//        public DownloadImageTask(ImageView imageView) {
//            mImageView = imageView;
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            Bitmap bmp = null;
//            try {
//                URL url = new URL(urls[0]);
//                bmp = BitmapFactory.decodeStream(url.openStream());
//            } catch (Exception e) {
//                //TODO: :(
//                e.printStackTrace();
//            }
//            return bmp;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            mImageView.setImageBitmap(result);
//        }
//    }
}
