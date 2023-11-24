package com.example.graduationbe.enums;

public enum ProductEnum {

    ACCESSORY, LAPTOP, SMARTPHONE;

    public String getSelection() {
        String selected = "";
        switch (this) {
            case LAPTOP -> selected = "LAPTOP";
            case ACCESSORY -> selected = "ACCESSORY";
            case SMARTPHONE -> selected = "SMARTPHONE";
            default -> {
            }
        }
        return selected;
    }

    @Override
    public String toString() {
        return this.getSelection();
    }
}
