package com.marco_nagy.e_commerce.product;

import java.io.Serializable;

public class ColorItem implements Serializable {
    private boolean isChecked = false;
    private int color;

    public ColorItem(boolean isChecked, int color) {
        this.isChecked = isChecked;
        this.color = color;
    }

    public ColorItem(int color) {
        this.color = color;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
