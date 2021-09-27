package com.marco_nagy.e_commerce.product;

public class SizeItem {
    private boolean isChecked = false;
    private String size;

    public SizeItem(boolean isChecked, String size) {
        this.isChecked = isChecked;
        this.size = size;
    }

    public SizeItem(String size) {
        this.size = size;
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
}
