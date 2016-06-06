package nicholasbenson.offuttairshowapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Nick on 5/9/2016.
 */
public class MapActivity  extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_screen);


        TouchImageView img = new TouchImageView(this);
        img.setImageResource(R.drawable.air_show_poster_half_res);
        img.setMaxZoom(4f);
        setContentView(img);
    }

}
