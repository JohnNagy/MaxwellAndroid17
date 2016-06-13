package nicholasbenson.offuttairshowapp;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;


public class FAQActivity extends AppCompatActivity {
    private String[] faq;
    private String[] faqCut;
    private ExpandableListView mExpandableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq_screen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        faq = getResources().getStringArray(R.array.faq);
        mExpandableList = (ExpandableListView)findViewById(R.id.expandable_list);
        ArrayList<Parent> arrayParents = new ArrayList<Parent>();

        for (int i = 0; i < faq.length; i++){
            //for each "i" create a new Parent object to set the title and the children
            faqCut = faq[i].split("[|]");

            Parent parent = new Parent();
            parent.setTitle(faqCut[0]);
            parent.setChildBody(faqCut[1]);

            arrayParents.add(parent);
        }

        mExpandableList.setAdapter(new CustomExpandListAdapter(this,arrayParents));

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

}
