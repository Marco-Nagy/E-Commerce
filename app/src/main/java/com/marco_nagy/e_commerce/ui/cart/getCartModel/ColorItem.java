package com.marco_nagy.e_commerce.ui.cart.getCartModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ColorItem implements Serializable {

	@SerializedName("colorid")
	private String colorid;

	public void setColorid(String colorid){
		this.colorid = colorid;
	}

	public String getColorid(){
		return colorid;
	}

	@Override
 	public String toString(){
		return 
			"ColorItem{" + 
			"colorid = '" + colorid + '\'' + 
			"}";
		}
}