package com.marco_nagy.e_commerce.ui.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DetailsItem implements Serializable {

	@SerializedName("name")
	private String name;

	@SerializedName("desc")
	private String desc;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
	}

	@Override
 	public String toString(){
		return 
			"DetailsItem{" + 
			"name = '" + name + '\'' + 
			",desc = '" + desc + '\'' + 
			"}";
		}
}