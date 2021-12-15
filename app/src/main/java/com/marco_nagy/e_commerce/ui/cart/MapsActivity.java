package com.marco_nagy.e_commerce.ui.cart;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.data.AppNetworkBuilder;
import com.marco_nagy.e_commerce.data.SharedPref;
import com.marco_nagy.e_commerce.databinding.ActivityMapsBinding;
import com.marco_nagy.e_commerce.ui.cart.placeOrderModel.PlaceOrderRequest;
import com.marco_nagy.e_commerce.ui.cart.placeOrderModel.PlaceOrderResponse;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    FusedLocationProviderClient fusedLocationProviderClient;
    private GoogleMap mMap;
    String myAddress;
    private Geocoder geocoder;
    private LatLng latLang;

    private double latitude;
    private double longitude;

    private static final String TAG = "MapsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMapsBinding binding;
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        geocoder = new Geocoder(this);
        binding.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (latLang == null) {
                    Toast.makeText(MapsActivity.this, "Please select Order Location", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("latLang", latLang);
                intent.putExtra("fullAddress", myAddress);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
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
    public void onMapReady(@NotNull GoogleMap googleMap) {
        mMap = googleMap;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        // Add a marker in Sydney and move the camera
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        LatLng latLng = getIntent().getParcelableExtra("latLang");
        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Location"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull @NotNull LatLng latLng) {
                mMap.clear();
                latLang = latLng;
                MarkerOptions markerOptions = new MarkerOptions().position(latLng);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.online_shopping_foreground));
                mMap.addMarker(markerOptions);
                getLatLngGeocoder(latLng);
            }
        });
    }

    private void getLatLngGeocoder(LatLng latLng) {
        latitude = latLng.latitude;
        longitude =latLng.longitude;
        try {
            List<Address> addressList = geocoder.getFromLocation

                    (latitude, longitude, 1);


            for (Address address : addressList) {
                myAddress = address.getAddressLine(0);
                Log.i(TAG, "getLatLngtGeocoder: " + myAddress);

            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "geLatLngGeocoder: " + e.getLocalizedMessage());
        }
        AppNetworkBuilder.getClient().addPlaceOrder(
                new PlaceOrderRequest(String.valueOf(latitude),String.valueOf(longitude)), SharedPref.read(SharedPref.Token,null))
                .enqueue(new Callback<PlaceOrderResponse>() {
                    @Override
                    public void onResponse(Call<PlaceOrderResponse> call, Response<PlaceOrderResponse> response) {
                        if(response.isSuccessful()){
                            SharedPref.write(SharedPref.LAT,String.valueOf(latitude));
                            SharedPref.write(SharedPref.LNG,String.valueOf(longitude));
                            SharedPref.write(SharedPref.MY_ADDRESS,String.valueOf(myAddress));
                            assert response.body() != null;
                            Toast.makeText(MapsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }else {
                            PlaceOrderResponse message = new Gson().fromJson(response.errorBody().charStream(), PlaceOrderResponse.class);
                            Toast.makeText(MapsActivity.this, "" + message.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PlaceOrderResponse> call, Throwable t) {

                    }
                });
    }


}


