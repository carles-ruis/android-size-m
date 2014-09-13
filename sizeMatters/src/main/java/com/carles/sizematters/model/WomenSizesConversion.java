package com.carles.sizematters.model;


public class WomenSizesConversion {

    private String size;
    private String bust;
    private String waist;
    private String hip;

    public WomenSizesConversion() {}

    public WomenSizesConversion(String size, String bust, String waist, String hip) {
        super();
        this.size = size;
        this.bust = bust;
        this.waist = waist;
        this.hip = hip;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getBust() {
        return bust;
    }

    public void setBust(String bust) {
        this.bust = bust;
    }

    public String getHip() {
        return hip;
    }

    public void setHip(String hip) {
        this.hip = hip;
    }

}
