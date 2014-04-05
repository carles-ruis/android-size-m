package com.carles.sizematters.model;


public class MenSizesConversion {

    private String size;
    private String neck;
    private String chest;
    private String sleeve;
    private String waist;

    public MenSizesConversion() {}

    public MenSizesConversion(String size, String neck, String chest, String sleeve, String waist) {
        super();
        this.size = size;
        this.neck = neck;
        this.chest = chest;
        this.sleeve = sleeve;
        this.waist = waist;
    }

    /*- ************************************************************************************************************** */
    /*- ************************************************************************************************************** */

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = neck;
    }

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getSleeve() {
        return sleeve;
    }

    public void setSleeve(String sleeve) {
        this.sleeve = sleeve;
    }
}
