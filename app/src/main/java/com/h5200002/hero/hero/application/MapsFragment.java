package com.h5200002.hero.hero.application;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            /*LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));

*/
            getDataFromFirebase(googleMap);
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            googleMap.setMyLocationEnabled(true);


//            double latitude = 42;
//            double longitude = 28;
//            LatLng sydney = new LatLng(latitude,longitude);
//            MarkerOptions a = new MarkerOptions();
//            a.position(sydney);
//            a.title("Marker in Anywhere");
//            googleMap.addMarker(a);
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        return view;
    }
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    MyAdapterAcil myAdapterAcil;
    ArrayList<Places> list;
    RecyclerView recyclerView;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
    List<Places> placesList = new ArrayList<>();
    Places places;
    void getDataFromFirebase(GoogleMap googleMap){

        databaseReference = FirebaseDatabase.getInstance().getReference("Places");
        placesList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Map<String, String> map = (Map<String , String>) snapshot.getValue();
                places = new Places();
                places.setKonumAdresi(map.get("konumAdresi"));
                places.setKonumIlce(map.get("konumIlce"));
                places.setKonumIl(map.get("konumIl"));
                places.setKonumGorsel(map.get("konumGorsel"));

                placesList.add(places);
                googleMap.getUiSettings().setZoomControlsEnabled(true);
                googleMap.setPadding(20,20,20,200);

                LatLng address=null;
                for(int i=0; i<placesList.size(); i++){
                    try {
                        String addr=placesList.get(i).getKonumAdresi()+","+
                                placesList.get(i).getKonumIlce()+","+
                                placesList.get(i).getKonumIl()+",";
                        address=getLatLongFromAddress(getActivity(),addr);
                        byte[] imageBytes = Base64.decode(placesList.get(i).getKonumGorsel().getBytes(),Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                        googleMap.addMarker(new MarkerOptions().position(address)
                                .title(placesList.get(i).getKonumAdresi()))
                                .setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(address));

                    }catch (Exception e){}
                }



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    LatLng getLatLongFromAddress(Context context, String strAddress){
        Geocoder geocoder= new Geocoder(context);
        List<Address> address;
        LatLng latLng= null;
        try {
            address=geocoder.getFromLocationName(strAddress, 2);
            if(address==null){

                 return null;
            }
            Address loc= address.get(0);
            loc.getLatitude();

            latLng= new LatLng(loc.getLatitude(), loc.getLongitude());

        }catch (Exception e){}
        return latLng;


    }

}