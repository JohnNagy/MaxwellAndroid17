package nicholasbenson.offuttairshowapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;


public class DirectsAndBikePark extends AppCompatActivity {

    private CustomViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directions_bp_screen_main);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = (CustomViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);

        Intent i = getIntent();
        int tabToOpen = i.getIntExtra("bike_park", -1);
        if (tabToOpen != -1){mViewPager.setCurrentItem(tabToOpen);}
    }

    /*public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment bike_park = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            bike_park.setArguments(args);
            return bike_park;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = null;
            if (count < 1) {

                rootView = inflater.inflate(R.layout.contact_screen, container, false);
                count = count + 1;
            } else {
                setContentView(R.layout.gmaps_fragment);
                addMapFragment();
                rootView = inflater.inflate(R.layout.gmaps_fragment, container, false);
            }

            return rootView;
        }
    }*/

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show BikeParkFragment
                    return nicholasbenson.offuttairshowapp.MapFragment.newInstance(0, "Page # 1");
                case 1: // Fragment # 0 - This will show BikeParkFragment different title
                    return BikeParkFragment.newInstance(1, "Page # 2");
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "DIRECTIONS";
                case 1:
                    return "BIKE & PARK";
            }
            return null;
        }
    }

    /*private void addMapFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        GMapsFragment bike_park = new GMapsFragment();
        transaction.add(R.id.map, bike_park);
        transaction.commit();
    }*/

}


