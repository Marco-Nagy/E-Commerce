package com.marco_nagy.e_commerce.ui.cart.addModel;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("product_id")
	private String productId;

	@SerializedName("product_size")
	private Object productSize;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("product_color")
	private Object productColor;

	@SerializedName("id")
	private int id;

	@SerializedName("status")
	private String status;

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setProductSize(Object productSize){
		this.productSize = productSize;
	}

	public Object getProductSize(){
		return productSize;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setProductColor(Object productColor){
		this.productColor = productColor;
	}

	public Object getProductColor(){
		return productColor;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"quantity = '" + quantity + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",user_id = '" + userId + '\'' + 
			",product_id = '" + productId + '\'' + 
			",product_size = '" + productSize + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",product_color = '" + productColor + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}