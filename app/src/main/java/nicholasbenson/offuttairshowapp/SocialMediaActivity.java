package nicholasbenson.offuttairshowapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SocialMediaActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.social_media_screen);
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

    public void goToSocialMedia (View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        Integer btnText = view.getId();
        switch (btnText)
        {
            case R.id.btnFacebook: intent.setData(Uri.parse(getString(R.string.SM_facebook_link)));;
                break;
            case R.id.btnFlickr: intent.setData(Uri.parse(getString(R.string.SM_flickr_link)));;
                break;
            case R.id.btnPinterest: intent.setData(Uri.parse(getString(R.string.SM_pinterest_link)));;
                break;
            case R.id.btnWhatsapp: intent.setData(Uri.parse(getString(R.string.SM_whatsapp_link)));;
                break;
            case R.id.btnTwitter: intent.setData(Uri.parse(getString(R.string.SM_twitter_link)));
                break;
        }
        startActivity(intent);
    }

}
