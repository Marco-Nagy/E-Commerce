package com.marco_nagy.e_commerce.ui.cart.checkoutModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CheckoutData{

	@SerializedName("user")
	private User user;

	@SerializedName("items")
	private List<ItemsItem> items;

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setItems(List<ItemsItem> items){
		this.items = items;
	}

	public List<ItemsItem> getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"user = '" + user + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}