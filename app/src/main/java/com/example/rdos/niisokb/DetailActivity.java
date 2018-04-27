package com.example.rdos.niisokb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements View.OnTouchListener {

    public static final String EXTRA_POSITION = "POSITION";
    private final String LOG_TAG = "DetailActivity";
    private int mPosition;
    private ImageView mImageView;
    private TextView mTitleTextView;
    private float mInitialX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mPosition = getIntent().getIntExtra(EXTRA_POSITION, 0);
        } else {
            mPosition = savedInstanceState.getInt(EXTRA_POSITION);
        }
        Log.i(LOG_TAG, "mPosition=" + String.valueOf(mPosition));

        setContentView(R.layout.activity_detail);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mImageView = (ImageView) findViewById(R.id.image_detail);
        mTitleTextView = (TextView) findViewById(R.id.text_title_detail);
        mImageView.setOnTouchListener(this);
//        DownloadImageTask downloadImageTask = new DownloadImageTask(mImageView);
//        downloadImageTask.execute(App.restMan.getAndroidsImgUrl(mPosition));
//        mNetworkImageView.setImageURI(Uri.parse(App.restMan.getAndroidsImgUrl(mPosition)));
        loadImage();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(EXTRA_POSITION, mPosition);
        super.onSaveInstanceState(outState);
    }

    private void loadImage() {
        //TODO: redirect http to https not work??
        String newUrl = App.restMan.getAndroidsImgUrl(mPosition).replace("http", "https");
        Picasso.with(this)
                .load(newUrl)
                .placeholder(R.mipmap.ic_loading_image)
                .error(R.mipmap.ic_loading_image_error)
                .into(mImageView);
        mTitleTextView.setText(App.restMan.getAndroidsTitle(mPosition));
    }

    private void loadImage(boolean isNextPosition) {
        if (isNextPosition) {
            mPosition++;
        } else {
            mPosition--;
        }

        if (mPosition < 0) {
            mPosition = App.restMan.getAndroidsCount() - 1;
        }
        if (mPosition >= App.restMan.getAndroidsCount()) {
            mPosition = 0;
        }
        loadImage();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mInitialX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalX = event.getX();
                if (mInitialX < finalX) {
                    Log.i(LOG_TAG, "Left to Right swipe");
                    loadImage(false);
                }

                if (mInitialX > finalX) {
                    Log.i(LOG_TAG, "Right to Left swipe");
                    loadImage(true);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
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
