package com.marco_nagy.e_commerce.ui.cart;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.marco_nagy.e_commerce.R;
import com.marco_nagy.e_commerce.data.AppNetworkBuilder;
import com.marco_nagy.e_commerce.data.SharedPref;
import com.marco_nagy.e_commerce.databinding.ActivityCheckoutBinding;
import com.marco_nagy.e_commerce.ui.cart.getCartModel.DataItem;
import com.marco_nagy.e_commerce.ui.cart.getCartModel.GetCartResponse;
import com.marco_nagy.e_commerce.ui.cart.placeOrderModel.PlaceOrderRequest;
import com.marco_nagy.e_commerce.ui.cart.placeOrderModel.PlaceOrderResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {
    ActivityCheckoutBinding binding;
    FusedLocationProviderClient fusedLocationProviderClient;
    LatLng latLng;
    private String latitude;
    private String longitude;
    private static final String TAG = "CheckoutActivity";
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    String myAddress = SharedPref.read(SharedPref.MY_ADDRESS,null);
    String token = SharedPref.read(SharedPref.Token, null);
    List<DataItem> dataItemList;
    CartAdapter cartAdapter;
    double totalAmount = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_checkout);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
        initLocationRequest();
        getCart();
//        binding.placeOrderBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), SuccessActivity.class));
//            }
//        });
        binding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (myAddress!=null){
            binding.shippingTexET.setText(myAddress);
        }else {
            binding.shippingTexET.setText("Select Your Address");
        }
        binding.shippingTexET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (latLng == null) {
                    Toast.makeText(CheckoutActivity.this, "Please Select Order Location", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(CheckoutActivity.this, MapsActivity.class);
                intent.putExtra("latLang", latLng);
                Log.i(TAG, "onClick: " + latLng);
                setResult(RESULT_OK, intent);
                startActivityForResult(intent, 1);
            }
        });
        binding.placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppNetworkBuilder.getClient().addPlaceOrder(
                        new PlaceOrderRequest(String.valueOf(latitude),String.valueOf(longitude)), SharedPref.read(SharedPref.Token,null))
                        .enqueue(new Callback<PlaceOrderResponse>() {
                            @Override
                            public void onResponse(@NotNull Call<PlaceOrderResponse> call, Response<PlaceOrderResponse> response) {
                                if(response.isSuccessful()){
                                    latitude =SharedPref.read(SharedPref.LAT,null);
                                    longitude =SharedPref.read(SharedPref.LNG,null);
                                    assert response.body() != null;
                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }else {
                                    assert response.errorBody() != null;
                                    PlaceOrderResponse message = new Gson().fromJson(response.errorBody().charStream(), PlaceOrderResponse.class);
                                    Toast.makeText(getApplicationContext(), "" + message.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(@NotNull Call<PlaceOrderResponse> call, Throwable t) {

                            }
                        });
            }
        });
    }

    private void askLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            getLastLocation();

            getUpdatesLocation();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLastLocation();
            getUpdatesLocation();
        }
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }
        fusedLocationProviderClient.getLastLocation()
                .addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        latitude =SharedPref.read(SharedPref.LAT,null);
                        longitude =SharedPref.read(SharedPref.LNG,null);
                        if (longitude != null ){
                           latLng = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));
                        }else if (task.isSuccessful() && task.getResult() != null) {

                            latLng = new LatLng(task.getResult().getLatitude(), task.getResult().getLongitude());


                            Log.i(TAG, "onComplete: " + task.getResult().getLatitude() + "," + task.getResult().getLongitude());
                            Log.i(TAG, "onComplete: " + task.getResult().getLatitude());
                            Log.i(TAG, "onComplete: " + task.getResult().getLongitude());

                        } else {

                            String errorMessage = task.getException().getLocalizedMessage();
                            Toast.makeText(CheckoutActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onComplete: null");
                        }
                    }
                });


    }

    @Override
    protected void onStart() {
        super.onStart();
        getLocationDialog();
        askLocationPermission();
    }
    // Location updates
    LocationRequest locationRequest;

    private void initLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(4000);
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK&&data!=null){
            String fullAddress = data.getStringExtra("fullAddress");
            LatLng latLng = data.getParcelableExtra("latLang");
            binding.shippingTexET.setText(fullAddress);
        }else if (resultCode==RESULT_CANCELED){
            Toast.makeText(this, "Order Location Is Required ", Toast.LENGTH_SHORT).show();
        }


        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        // All required changes were successfully made

                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to

                        break;
                    default:
                        break;
                }
                break;
        }
    }

    private void getLocationDialog() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true); //this is the key ingredient

        Task<LocationSettingsResponse> result =
                LocationServices.getSettingsClient(this).checkLocationSettings(builder.build());


        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    // All location settings are satisfied. The client can initialize location
                    // requests here.

                } catch (ApiException exception) {
                    switch (exception.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied. But could be fixed by showing the
                            // user a dialog.
                            try {
                                // Cast to a resolvable exception.
                                ResolvableApiException resolvable = (ResolvableApiException) exception;
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().


                                resolvable.startResolutionForResult(CheckoutActivity.this,
                                        REQUEST_CHECK_SETTINGS);

                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            } catch (ClassCastException e) {
                                // Ignore, should be an impossible error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way to fix the
                            // settings so we won't show the dialog.

                            break;
                    }
                }
            }
        });
    }

    private void getUpdatesLocation() {
        LocationSettingsRequest locationSettingsRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
                .build();
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest)
                .addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i(TAG, "onSuccess: ");
                        startLocationUpdates();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        stopLocationUpdates();
                        Log.i(TAG, "onFailure: " + e.getLocalizedMessage());
                    }
                });
    }
    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        Log.i(TAG, "startLocationUpdates: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }
    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if (locationResult == null) {
                Log.i(TAG, "onLocationResult: null");
                return;
            }
            for (Location location : locationResult.getLocations()) {

                Log.i(TAG, "onLocationResult: " + location.getLatitude());
                Log.i(TAG, "onLocationResult: " + location.getLongitude());
            }
        }
    };


    public void getCart() {

        AppNetworkBuilder.getClient().getCart(token).enqueue(new Callback<GetCartResponse>() {

            @Override
            public void onResponse(@NotNull Call<GetCartResponse> call, @NotNull Response<GetCartResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Log.i(TAG, "onResponse: getCart =>> " + response.body().getData().toString());
                    dataItemList = response.body().getData();
                    countAmount();

                    setCartRecyclerView();

                }
            }

            @Override
            public void onFailure(@NotNull Call<GetCartResponse> call, @NotNull Throwable t) {


            }
        });

    }

    public void setCartRecyclerView() {

        cartAdapter = new CartAdapter(dataItemList, getApplicationContext(), cartInterface);
        binding.cartRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        binding.cartRV.setAdapter(cartAdapter);


    }

    public void countAmount() {
        totalAmount = 0.0;
        for (int i = 0; i < dataItemList.size(); i++) {
            double amount = Double.parseDouble(dataItemList.get(i).getQuantity())
                    * Double.parseDouble(dataItemList.get(i).getProductId().getPrice());
            totalAmount = totalAmount + amount;


        }
        binding.amountTextV.setText(String.valueOf(totalAmount));

    }

    CartInterface cartInterface = new CartInterface() {

        @Override
        public void onUpdateCart() {
            countAmount();

        }

    };
}