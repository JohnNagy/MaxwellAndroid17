package nicholasbenson.offuttairshowapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A bike_park that launches other parts of the demo application.
 */
public class MapFragment extends Fragment {

    MapView mMapView;
    static CheckBox cbShuttle;
    static CheckBox cbOnBase;
    static CheckBox cbHandi;

    private GoogleMap mMap;
    LatLng SouthRoadsTechCenter = new LatLng(41.178011, -95.924852);
    LatLng BellevueWestHighSchool = new LatLng(41.1629921,-95.9361967);
    LatLng BellevueUniversity = new LatLng(41.150692, -95.917860);
    LatLng BellevueEastHighSchool = new LatLng(41.146561, -95.903344);
    LatLng AFWA = new LatLng(41.131373, -95.908265);
    LatLng STRATCOMGate = new LatLng(41.114452, -95.926526);
    LatLng HandiPark = new LatLng(41.116137, -95.916402);

    Marker SRT = null, BWHS = null, BU = null, BEHS = null, WeatherWing = null, STRAT = null, mHandi = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate and return the layout
        View v = inflater.inflate(R.layout.gmaps_fragment, container,
                false);
        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMap = mMapView.getMap();
        mMap.setPadding(0, 180, 0, 0);

        LatLng Center = new LatLng(41.150763, -95.921382);

        cbShuttle = (CheckBox) v.findViewById(R.id.Shuttle);
        cbOnBase = (CheckBox) v.findViewById(R.id.OnBase);
        cbHandi = (CheckBox) v.findViewById(R.id.Handi);

        cbShuttle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkers(cbShuttle);
            }
        });

        cbOnBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkers(cbOnBase);
            }
        });

        cbHandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkers(cbHandi);
            }
        });

        showMarkers(cbShuttle);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Center));
        mMap.moveCamera((CameraUpdateFactory.zoomTo(12)));
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 3000, null);


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public static MapFragment newInstance(int page, String title) {
        MapFragment mapFragment = new MapFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        mapFragment.setArguments(args);
        return mapFragment;
    }

    public void showMarkers(CheckBox view)
    {


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
            case R.id.Handi:
                if (cbHandi.isChecked())
                {
                    mHandi = mMap.addMarker(new MarkerOptions()
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.small_gmaps_marker_handi))
                            .position(HandiPark)
                            .title("Handicap Parking"));
                }
                else
                {
                    try{mHandi.remove();}catch(Exception e){}
                }
        }
    }
}
