package com.marco_nagy.e_commerce.ui.cart.getCartModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserId  implements Serializable {

	@SerializedName("name")
	private String name;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"UserId{" + 
			"name = '" + name + '\'' + 
			"}";
		}
}