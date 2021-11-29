package com.marco_nagy.e_commerce.ui.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReviewItem implements Serializable {

	@SerializedName("comment")
	private String comment;

	@SerializedName("creat_date")
	private String creatDate;

	@SerializedName("user")
	private User user;

	@SerializedName("rate_no")
	private String rateNo;

	public void setComment(String comment){
		this.comment = comment;
	}

	public String getComment(){
		return comment;
	}

	public void setCreatDate(String creatDate){
		this.creatDate = creatDate;
	}

	public String getCreatDate(){
		return creatDate;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setRateNo(String rateNo){
		this.rateNo = rateNo;
	}

	public String getRateNo(){
		return rateNo;
	}

	@Override
 	public String toString(){
		return 
			"ReviewItem{" + 
			"comment = '" + comment + '\'' + 
			",creat_date = '" + creatDate + '\'' + 
			",user = '" + user + '\'' + 
			",rate_no = '" + rateNo + '\'' + 
			"}";
		}
}