package com.marco_nagy.e_commerce.ui.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SizesItem implements Serializable {
	private boolean isChecked = false;
	@SerializedName("name")
	private String size;

	@SerializedName("id")
	private int id;

	public SizesItem(boolean isChecked, String size, int id) {
		this.isChecked = isChecked;
		this.size = size;
		this.id = id;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean checked) {
		isChecked = checked;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}