package com.marco_nagy.e_commerce.ui.search.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SearchRequest implements Serializable {
    @SerializedName("search-name")
    String search;

    public SearchRequest(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
