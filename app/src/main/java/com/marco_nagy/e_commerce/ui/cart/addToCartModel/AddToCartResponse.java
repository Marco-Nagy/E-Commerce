package com.marco_nagy.e_commerce.ui.cart.addToCartModel;

import com.google.gson.annotations.SerializedName;

public class AddToCartResponse{

	@SerializedName("addToCartRequest")
	private AddToCartRequest addToCartRequest;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(AddToCartRequest addToCartRequest){
		this.addToCartRequest = addToCartRequest;
	}

	public AddToCartRequest getData(){
		return addToCartRequest;
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
			"AddToCartResponse{" + 
			"addToCartRequest = '" + addToCartRequest + '\'' +
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}