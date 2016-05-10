package nicholasbenson.offuttairshowapp;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

/**
 * Created by Nick on 5/9/2016.
 */
public class MapActivity  extends AppCompatActivity{

    private ImageView iv;
    private ScaleGestureDetector sGD;
    private Matrix matrix = new Matrix();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_screen);

        iv = (ImageView)findViewById(R.id.ivPinchToZoom);
        sGD = new ScaleGestureDetector(this, new ScaleListener());
    }

    public boolean onTouchEvent(MotionEvent ev)
    {
        sGD.onTouchEvent(ev);
        return true;
    }

    public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener
    {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));

            matrix.setScale(scaleFactor, scaleFactor);
            iv.setImageMatrix(matrix);
            return true;
        }
    }
}
