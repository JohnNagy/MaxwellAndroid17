package nicholasbenson.offuttairshowapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;



public class SplashActivity extends AppCompatActivity{

    private static int splashinterval = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent i = new Intent(SplashActivity.this, MainScreenActivity.class);
                startActivity(i);

                this.finish();
            }

            private void finish() {
                // TODO Auto-generated method stub

            }
        }, splashinterval);

    };

}
