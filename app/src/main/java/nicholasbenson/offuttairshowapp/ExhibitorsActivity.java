package nicholasbenson.offuttairshowapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class ExhibitorsActivity extends AppCompatActivity
{
    private ExpandableListView mExpandableList;

    private static ExhibitorsActivity ma = null;

    private String[] Exhibitors;
    private String[] ExhibitorsCut;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exhibitors_screen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ma = this;

        Exhibitors = getResources().getStringArray(R.array.exhibitors);

        mExpandableList = (ExpandableListView)findViewById(R.id.expandable_list);

        ArrayList<Parent> arrayParents = new ArrayList<Parent>();

        //here we set the parents and the children
        for (int i = 0; i < Exhibitors.length; i++){
            //for each "i" create a new Parent object to set the title and the children
            ExhibitorsCut = Exhibitors[i].split("[|]");

            Parent parent = new Parent();
            parent.setTitle(ExhibitorsCut[0]);
            parent.setChildBody(ExhibitorsCut[1]);
            parent.setChildLink(ExhibitorsCut[2]);

            if ((i % 2) != 0)
            {
                parent.setColor(R.color.colorPrimaryDark);
            }
            else
            {
                parent.setColor(R.color.colorAccent);
            }



            //in this array we add the Parent object. We will use the arrayParents at the setAdapter
            arrayParents.add(parent);
        }

        //sets the adapter that provides data to the list.
        mExpandableList.setAdapter(new CustomExpandListAdapter(ExhibitorsActivity.this,arrayParents));

        try {
            handleQRIntent(getIntent());
        }
        catch(NullPointerException ex){
            // Fail-soft.
            // NPE thrown if there is no intent; there will be no intent if the QR scanner was not used.
            // There's probably a better way to handle this.
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

    }

    /**
     * Expands the correct item during handling of QR-initiated intent.
     * @param intent
     */
    private void handleQRIntent(Intent intent) {
        super.onNewIntent(intent);
        String item = intent.getStringExtra("offuttairshowapp.highlightitem");
        for(int i = 0; i< Exhibitors.length; i++){
            if(Exhibitors[i].toLowerCase().contains(item)){
                mExpandableList.expandGroup(i);
                return;
            }
        }
        //If not found, will leave user at main exhibitors page.
    }

    public static Context getContext()
    {
        return ma.getApplicationContext();
    }
}
