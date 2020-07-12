package com.example.pinch2zoom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //We use an ImageView with the application icon (you can use any image of choice) to provide
    //a visual representation of the scaling by setting the ImageView scale
    //using the scale factor returned from ScaleGestureDetector.
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView=(ImageView)findViewById(R.id.imageView);
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        mScaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    //The ScaleGestureDetector does all the work by analyzing the gesture data and reporting
    // the final scale factor through the onScale() callback.
    // source: https://medium.com/quick-code/pinch-to-zoom-with-multi-touch-gestures-in-android-d6392e4bf52d
    // source-backup: https://archive.vn/neCtd
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor(); //We get the actual scale factor by calling getScaleFactor() on ScaleGestureDetector .
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f)); //To prevent the scaling from becoming too large or too small.
            mImageView.setScaleX(mScaleFactor);
            mImageView.setScaleY(mScaleFactor);
            return true;
        }
    }
}