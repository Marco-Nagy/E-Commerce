package com.marco_nagy.e_commerce.ui.cart.getCartModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataItem implements Serializable {

	@SerializedName("quantity")
	private String quantity;

	@SerializedName("user_id")
	private UserId userId;

	@SerializedName("product_id")
	private ProductId productId;

	public void setQuantity(String quantity){
		this.quantity = quantity;
	}

	public String getQuantity(){
		return quantity;
	}

	public void setUserId(UserId userId){
		this.userId = userId;
	}

	public UserId getUserId(){
		return userId;
	}

	public void setProductId(ProductId productId){
		this.productId = productId;
	}

	public ProductId getProductId(){
		return productId;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"quantity = '" + quantity + '\'' + 
			",user_id = '" + userId + '\'' + 
			",product_id = '" + productId + '\'' + 
			"}";
		}
}