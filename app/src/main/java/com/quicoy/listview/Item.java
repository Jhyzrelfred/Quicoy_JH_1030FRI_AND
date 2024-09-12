package com.quicoy.listview;

public class Item {
    private String text;
    private boolean isChecked;
    private int imageResId;

    public Item(String text, boolean isChecked, int imageResId) {
        this.text = text;
        this.isChecked = isChecked;
        this.imageResId = imageResId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getImageResId() {
        return imageResId;
    }
}