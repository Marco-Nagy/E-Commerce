package com.marco_nagy.e_commerce.ui.cart.placeOrderModel;

public class PlaceOrderRequest {
    private String latitude;
    private String longitude;

    public PlaceOrderRequest(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
