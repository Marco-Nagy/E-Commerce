
package com.marco_nagy.e_commerce.home.latest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.marco_nagy.e_commerce.home.latest.models.DataItem;

import java.io.Serializable;
import java.util.List;

public class LatestResponse implements Serializable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<DataItem> data = null;
    private final static long serialVersionUID = 2749237969851386705L;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataItem> getData() {
        return data;
    }

    public void setData(List<DataItem> data) {
        this.data = data;
    }

}
