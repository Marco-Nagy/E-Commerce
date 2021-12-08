package com.marco_nagy.e_commerce.ui.cart.addToCartModel;

import com.google.gson.annotations.SerializedName;

public class AddToCartRequest {


	@SerializedName("product_id")
	private int productId;

	@SerializedName("size")
	private int productSize;

	@SerializedName("color")
	private String productColor;

	public AddToCartRequest(int productId, int productSize, String productColor) {
		this.productId = productId;
		this.productSize = productSize;
		this.productColor = productColor;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductSize() {
		return productSize;
	}

	public void setProductSize(int productSize) {
		this.productSize = productSize;
	}

	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}
}