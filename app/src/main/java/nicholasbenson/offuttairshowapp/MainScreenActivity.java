package nicholasbenson.offuttairshowapp;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainScreenActivity extends AppCompatActivity{

    private DrawerLayout mDrawerLayout;
    MenuExpandListAdapter mMenuAdapter;
    ExpandableListView expandableList;
    List<ExpandedMenuModel> listDataHeader;
    HashMap<ExpandedMenuModel, List<ExpandedMenuModelChild>> listDataChild;
    List<ExpandedMenuModelChild> childObj;
    private ScrollTextView scrolltext;
    public int width;
    public Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);
        ctx = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                scrolltext.pauseScroll();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                scrolltext.resumeScroll();
            }
        };
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        expandableList = (ExpandableListView) findViewById(R.id.navigationmenu);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        prepareListData();

        mMenuAdapter = new MenuExpandListAdapter(this, listDataHeader, listDataChild, expandableList);
        expandableList.setAdapter(mMenuAdapter);

        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Intent intent;
                switch (i1)
                {
                    case 0:
                        intent = new Intent(ctx, SocialMediaActivity.class);
                        startActivity(intent);
                        return true;
                    case 1:
                        intent = new Intent(ctx, MapActivity.class);
                        startActivity(intent);
                        return true;
                    case 2:
                        intent = new Intent(ctx, PerformersActivity.class);
                        startActivity(intent);
                        return true;
                    case 3:
                        intent = new Intent(ctx, SponsorsActivity.class);
                        startActivity(intent);
                        return true;
                    case 4:
                        intent = new Intent(ctx, StaticsActivity.class);
                        startActivity(intent);
                        return true;
                    case 5:
                        intent = new Intent(ctx, ExhibitorsActivity.class);
                        startActivity(intent);
                        return true;
                    case 6:
                        intent = new Intent(ctx, SocialMediaActivity.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
        expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        ImageView imgIndic = (ImageView) findViewById(R.id.indicatorImage);
                        if(!expandableListView.isGroupExpanded(i)) {
                            imgIndic.setImageResource(R.drawable.ic_menu_white_24dp);
                            imgIndic.setColorFilter(getResources().getColor(R.color.colorAccent));
                            expandableListView.expandGroup(i);
                        }
                        else
                        {
                            imgIndic.setImageResource(R.drawable.about_offutt_notext_nobg);
                            imgIndic.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
                            expandableListView.collapseGroup(i);
                        }
                        return true;
                    case 1:
                        openBPTab();
                        return true;
                    case 2:
                        //openKidZoneActivity();
                        return true;
                    case 3:
                        openSponsorsActivity();
                        return true;
                    case 4:
                        openDirectionsTab();
                        return true;
                    case 5:
                        openSocialMediaActivity();
                        return true;
                    case 6:
                        openAboutOffuttActivity();
                        return true;
                    case 7:
                        openFAQActivity();
                        return true;
                    case 8:
                        openContactActivity();
                        return true;
                }
                return false;
            }
        });



        scrolltext=(ScrollTextView) findViewById(R.id.marquee);
        scrolltext.setText(R.string.Main_Default_Marquee);
        scrolltext.setTextColor(Color.WHITE);

        scrolltext.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (left == 0 && top == 0 && right == 0 && bottom == 0) {
                    return;
                }
                width = scrolltext.getRight();
                scrolltext.startScroll(width);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.action_settings:
                return true;

            case R.id.menu_qr_scanner:
                scanQR();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        ExpandedMenuModel item1 = new ExpandedMenuModel(ctx);
        item1.setIconName(getString(R.string.Attractions_Activity_Label));
        item1.setIconImg(R.drawable.attractions_notext_nobg);
        item1.setIndicatorImg(R.drawable.ic_arrow_drop_down_black_24dp);
        item1.setIndicatorTint(getResources().getColor(R.color.colorPrimary));
        listDataHeader.add(item1);

        ExpandedMenuModel item2 = new ExpandedMenuModel(ctx);
        item2.setIconName(getString(R.string.Bike_Park_Activity_Label));
        item2.setIconImg(R.drawable.bike_and_park_notext_nobg);
        listDataHeader.add(item2);

        ExpandedMenuModel item3 = new ExpandedMenuModel(ctx);
        item3.setIconName(getString(R.string.Kid_Zone_Activity_Label));
        item3.setIconImg(R.drawable.kids_zone_notext_nobg);
        listDataHeader.add(item3);

        ExpandedMenuModel item4 = new ExpandedMenuModel(ctx);
        item4.setIconName(getString(R.string.Sponsors_Activity_Label));
        item4.setIconImg(R.drawable.sponsors_notext_nobg);
        listDataHeader.add(item4);

        ExpandedMenuModel item5 = new ExpandedMenuModel(ctx);
        item5.setIconName(getString(R.string.Directions_Activity_Label));
        item5.setIconImg(R.drawable.directions_notext_nobg);
        listDataHeader.add(item5);

        ExpandedMenuModel item6 = new ExpandedMenuModel(ctx);
        item6.setIconName(getString(R.string.Social_Media_Activity_Label));
        item6.setIconImg(R.drawable.social_media_notext_nobg);
        listDataHeader.add(item6);

        ExpandedMenuModel item7 = new ExpandedMenuModel(ctx);
        item7.setIconName(getString(R.string.About_Offutt_Activity_Label));
        item7.setIconImg(R.drawable.about_offutt_notext_nobg);
        listDataHeader.add(item7);

        ExpandedMenuModel item8 = new ExpandedMenuModel(ctx);
        item8.setIconName(getString(R.string.FAQ_Activity_Label));
        item8.setIconImg(R.drawable.faq_notext_nobg);
        listDataHeader.add(item8);

        ExpandedMenuModel item9 = new ExpandedMenuModel(ctx);
        item9.setIconName(getString(R.string.Contact_Activity_Label));
        item9.setIconImg(R.drawable.contact_notext_nobg);
        listDataHeader.add(item9);



        ExpandedMenuModelChild cld1 = new ExpandedMenuModelChild();
        cld1.setIconImg(R.drawable.schedule_notext_nobg);
        cld1.setChildName("Schedule");

        ExpandedMenuModelChild cld2 = new ExpandedMenuModelChild();
        cld2.setIconImg(R.drawable.map_notext_nobg);
        cld2.setChildName("Map");

        ExpandedMenuModelChild cld3 = new ExpandedMenuModelChild();
        cld3.setIconImg(R.drawable.performers_notext_nobg);
        cld3.setChildName("Performers");

        ExpandedMenuModelChild cld4 = new ExpandedMenuModelChild();
        cld4.setIconImg(R.drawable.sponsors_notext_nobg);
        cld4.setChildName("Sponsors");

        ExpandedMenuModelChild cld5 = new ExpandedMenuModelChild();
        cld5.setIconImg(R.drawable.statics_notext_nobg);
        cld5.setChildName("Statics");

        ExpandedMenuModelChild cld6 = new ExpandedMenuModelChild();
        cld6.setIconImg(R.drawable.exhibitors_notext_nobg);
        cld6.setChildName("Exhibitors");

        ExpandedMenuModelChild cld7 = new ExpandedMenuModelChild();
        cld7.setIconImg(R.drawable.food_notext_nobg);
        cld7.setChildName("Food");

        childObj = new ArrayList<>();
        childObj.add(cld1);
        childObj.add(cld2);
        childObj.add(cld3);
        childObj.add(cld4);
        childObj.add(cld5);
        childObj.add(cld6);
        childObj.add(cld7);

        listDataChild.put(listDataHeader.get(0), childObj);// Header, Child data


    }

    public void goToWebsite (View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://www.offuttairshow.com"));
        startActivity(intent);
        }

    public void openContactActivity(View view) {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }
    public void openContactActivity() {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

    public void openSocialMediaActivity(View view) {
        Intent intent = new Intent(this, SocialMediaActivity.class);
        startActivity(intent);
    }
    public void openSocialMediaActivity() {
        Intent intent = new Intent(this, SocialMediaActivity.class);
        startActivity(intent);
    }

    public void openAttractionsActivity(View view) {
        Intent intent = new Intent(this, AttractionsActivity.class);
        startActivity(intent);
    }
    public void openAttractionsActivity() {
        Intent intent = new Intent(this, AttractionsActivity.class);
        startActivity(intent);
    }

    public void openSponsorsActivity(View view) {
        Intent intent = new Intent(this, SponsorsActivity.class);
        startActivity(intent);
    }
    public void openSponsorsActivity() {
        Intent intent = new Intent(this, SponsorsActivity.class);
        startActivity(intent);
    }

    public void openFAQActivity(View view) {
        Intent intent = new Intent(this, FAQActivity.class);
        startActivity(intent);
    }
    public void openFAQActivity() {
        Intent intent = new Intent(this, FAQActivity.class);
        startActivity(intent);
    }

    public void openAboutOffuttActivity(View view) {
        Intent intent = new Intent(this, AboutOffuttActivity.class);
        startActivity(intent);
    }
    public void openAboutOffuttActivity() {
        Intent intent = new Intent(this, AboutOffuttActivity.class);
        startActivity(intent);
    }

    public void openBPTab(View view){
        Intent intent = new Intent(this, DirectsAndBikePark.class);
        intent.putExtra("bike_park", 1);
        startActivity(intent);
    }
    public void openBPTab(){
        Intent intent = new Intent(this, DirectsAndBikePark.class);
        intent.putExtra("bike_park", 1);
        startActivity(intent);
    }

    public void openDirectionsTab(View view){
        Intent intent = new Intent(this, DirectsAndBikePark.class);
        intent.putExtra("bike_park", 0);
        startActivity(intent);
    }
    public void openDirectionsTab(){
        Intent intent = new Intent(this, DirectsAndBikePark.class);
        intent.putExtra("bike_park", 0);
        startActivity(intent);
    }


    /**
     * Opens a supported QR scanning app and handles scanning.
     * If a supported app is not installed, the user will be redirected to the Play Store.
     */
    public void scanQR() {
        try {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (Exception e) {
            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
            startActivity(marketIntent);
        }
    }

    /**
     * Handles QR-scan result and builds an intent.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Uri result = Uri.parse(data.getStringExtra("SCAN_RESULT"));
                String activity = result.getPathSegments().get(0);
                String sub_activity = result.getPathSegments().get(1);
                String item = result.getPathSegments().get(2);
                if(activity.equals("attractions")){ //TODO Better handling and activity lookup
                    if(sub_activity.equals("performers")){
                        Intent intent = new Intent(this, PerformersActivity.class);
                        intent.putExtra("offuttairshowapp.highlightitem", item);
                        startActivity(intent);
                    }
                }
            }
            if(resultCode == RESULT_CANCELED){
                return;
            }
        }
    }

}
