package com.marco_nagy.e_commerce.ui.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ColorItem implements Serializable {

	@SerializedName("colorid")
	private String colorId;
	private boolean isChecked = false;

	public ColorItem(String colorId, boolean isChecked) {
		this.colorId = colorId;
		this.isChecked = isChecked;
	}

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean checked) {
		isChecked = checked;
	}
}