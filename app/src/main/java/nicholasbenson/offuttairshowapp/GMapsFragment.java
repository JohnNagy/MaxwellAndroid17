package nicholasbenson.offuttairshowapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GMapsFragment extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng SouthRoadsTechCenter = new LatLng(41.178011, -95.924852);
    LatLng BellevueWestHighSchool = new LatLng(41.1629921,-95.9361967);
    LatLng BellevueUniversity = new LatLng(41.150692, -95.917860);
    LatLng BellevueEastHighSchool = new LatLng(41.146561, -95.903344);
    LatLng AFWA = new LatLng(41.131373, -95.908265);
    LatLng STRATCOMGate = new LatLng(41.114452, -95.926526);

    Marker SRT = null, BWHS = null, BU = null, BEHS = null, WeatherWing = null, STRAT = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gmaps_fragment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng Center = new LatLng(41.150763, -95.921382);
        showMarkers(findViewById(R.id.Shuttle));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Center));
        mMap.moveCamera((CameraUpdateFactory.zoomTo(12)));
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 3000, null);
    }

    public void showMarkers(View view)
    {
        CheckBox cbShuttle = (CheckBox) findViewById(R.id.Shuttle);
        CheckBox cbOnBase = (CheckBox) findViewById(R.id.OnBase);
        CheckBox cbHandi = (CheckBox) findViewById(R.id.Handi);



        switch (view.getId())
        {
            case R.id.Shuttle:
                if (cbShuttle.isChecked()) {
                    SRT = mMap.addMarker(new MarkerOptions().position(SouthRoadsTechCenter).title("Southroads Technology Center (Shuttle Pickup)"));
                    BWHS = mMap.addMarker(new MarkerOptions().position(BellevueWestHighSchool).title("Bellevue West High School (Shuttle Pickup)"));
                    BU = mMap.addMarker(new MarkerOptions().position(BellevueUniversity).title("Bellevue University (Shuttle Pickup)"));
                    BEHS = mMap.addMarker(new MarkerOptions().position(BellevueEastHighSchool).title("Bellevue East High School (Shuttle Pickup)"));
                    WeatherWing = mMap.addMarker(new MarkerOptions().position(AFWA).title("557th Weather Wing (Shuttle Pickup)").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                }
                else
                {
                    try {
                        SRT.remove();
                        BWHS.remove();
                        BU.remove();
                        BEHS.remove();
                        WeatherWing.remove();
                    }
                    catch (Exception e){}
                }
                break;
            case R.id.OnBase:
                if (cbOnBase.isChecked()) {
                    STRAT = mMap.addMarker(new MarkerOptions().position(STRATCOMGate).title("STRATCOM Gate (On-base Parking"));
                }
                else
                {
                    try{STRAT.remove();}catch(Exception e){}
                }
                break;
        }
    }
}
