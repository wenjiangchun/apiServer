package com.dlyy.api.park.util;

public enum ParkBrand {

    Fuji("富士");

    private String brandName;

    ParkBrand(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandName() {
        return brandName;
    }
}
