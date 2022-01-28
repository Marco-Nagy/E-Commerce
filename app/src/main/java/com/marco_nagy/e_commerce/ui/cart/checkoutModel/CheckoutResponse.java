package com.marco_nagy.e_commerce.ui.cart.checkoutModel;

import com.google.gson.annotations.SerializedName;

public class CheckoutResponse{

	@SerializedName("data")
	private CheckoutData data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(CheckoutData data){
		this.data = data;
	}

	public CheckoutData getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"CheckoutResponse{" + 
			"data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}