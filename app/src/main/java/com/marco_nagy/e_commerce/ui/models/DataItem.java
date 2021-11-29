package com.marco_nagy.e_commerce.ui.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DataItem implements Serializable {

	@SerializedName("images")
	private List<ImagesItem> images = null;

	@SerializedName("item_image_url")
	private String itemImageUrl = null;

	@SerializedName("sizes")
	private List<SizesItem> sizes = null;

	@SerializedName("color")
	private List<ColorItem> color = null;

	@SerializedName("item_id")
	private int itemId;

	@SerializedName("price")
	private String price = null;

	@SerializedName("review")
	private List<ReviewItem> review = null;

	@SerializedName("description")
	private String description = null;

	@SerializedName("discount")
	private String discount = null;

	@SerializedName("item_name")
	private String itemName = null;

	@SerializedName("details")
	private List<DetailsItem> details = null;

	@SerializedName("price_after_discount")
	private String priceAfterDiscount = null;

	public void setImages(List<ImagesItem> images){
		this.images = images;
	}

	public List<ImagesItem> getImages(){
		return images;
	}

	public void setItemImageUrl(String itemImageUrl){
		this.itemImageUrl = itemImageUrl;
	}

	public String getItemImageUrl(){
		return itemImageUrl;
	}

	public void setSizes(List<SizesItem> sizes){
		this.sizes = sizes;
	}

	public List<SizesItem> getSizes(){
		return sizes;
	}

	public void setColor(List<ColorItem> color){
		this.color = color;
	}

	public List<ColorItem> getColor(){
		return color;
	}

	public void setItemId(int itemId){
		this.itemId = itemId;
	}

	public int getItemId(){
		return itemId;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setReview(List<ReviewItem> review){
		this.review = review;
	}

	public List<ReviewItem> getReview(){
		return review;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setDiscount(String discount){
		this.discount = discount;
	}

	public String getDiscount(){
		return discount;
	}

	public void setItemName(String itemName){
		this.itemName = itemName;
	}

	public String getItemName(){
		return itemName;
	}

	public void setDetails(List<DetailsItem> details){
		this.details = details;
	}

	public List<DetailsItem> getDetails(){
		return details;
	}

	public void setPriceAfterDiscount(String priceAfterDiscount){
		this.priceAfterDiscount = priceAfterDiscount;
	}

	public String getPriceAfterDiscount(){
		return priceAfterDiscount;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"images = '" + images + '\'' + 
			",item_image_url = '" + itemImageUrl + '\'' + 
			",sizes = '" + sizes + '\'' + 
			",color = '" + color + '\'' + 
			",item_id = '" + itemId + '\'' + 
			",price = '" + price + '\'' + 
			",review = '" + review + '\'' + 
			",description = '" + description + '\'' + 
			",discount = '" + discount + '\'' + 
			",item_name = '" + itemName + '\'' + 
			",details = '" + details + '\'' + 
			",price_after_discount = '" + priceAfterDiscount + '\'' + 
			"}";
		}
}