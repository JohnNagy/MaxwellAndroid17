package nicholasbenson.offuttairshowapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Nick on 5/1/2016.
 */
public class AttractionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attractions_screen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            case R.id.action_settings:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            case R.id.menu_qr_scanner:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void openPerformersActivity(View view) {
        Intent intent = new Intent(this, PerformersActivity.class);
        startActivity(intent);
    }

    public void openMapActivity(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    public void openStaticsActivity(View view) {
        Intent intent = new Intent(this, StaticsActivity.class);
        startActivity(intent);
    }

    public void openExhibitorsActivity(View view) {
        Intent intent = new Intent(this, ExhibitorsActivity.class);
        startActivity(intent);
    }

    public void openSponsorsActivity(View view) {
        Intent intent = new Intent(this, SponsorsActivity.class);
        startActivity(intent);
    }

    public void goToWebsite (View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://www.offuttairshow.com"));
        startActivity(intent);
    }


}
