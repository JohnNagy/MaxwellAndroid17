package nicholasbenson.offuttairshowapp;


import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Nick on 5/9/2016.
 */
public class BikeParkMap  extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bike_park_map);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        TouchImageView img = new TouchImageView(this);
        img.setImageResource(R.drawable.border_bike_map_lastyear);
        img.setMaxZoom(4f);
        setContentView(img);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(this, DirectsAndBikePark.class);
                intent.putExtra("bike_park", 1);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
