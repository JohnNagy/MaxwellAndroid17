package nicholasbenson.offuttairshowapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class SponsorsActivity extends AppCompatActivity {

    ImageView[] imgArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsors_screen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayout ll = (LinearLayout) findViewById(R.id.sponsorLL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ll.getLayoutParams().WRAP_CONTENT, ll.getLayoutParams().WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_HORIZONTAL;

        int dpH = (int) (getResources().getDimension(R.dimen.activity_horizontal_margin) / getResources().getDisplayMetrics().density);
        int dpV = (int) (getResources().getDimension(R.dimen.activity_vertical_margin) / getResources().getDisplayMetrics().density);

        lp.setMargins(dpH, dpV,dpH, dpV);

        int i = 1;
        String imgTitle = "";
        while (i<40) {
            ImageView imgVw = new ImageView(this);
            imgVw.setLayoutParams(lp);
            imgVw.setBackgroundColor(getResources().getColor(android.R.color.white));
            imgTitle = "sponsor_" + i;

            Context context = imgVw.getContext();
            int id = context.getResources().getIdentifier(imgTitle, "drawable", context.getPackageName());
            if (id == 0)
            {
                break;
            }
            imgVw.setImageResource(id);
            //imgVw.setImageResource(imgVw.getContext().getResources().getIdentifier(imgTitle, "drawable", imgVw.getContext().getPackageName()));

            ll.addView(imgVw);
            i+=1;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
