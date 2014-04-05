package com.carles.sizematters.model;


public class SizeCountriesConversion {

    private String size;
    private String eu;
    private String us;
    private String uk;
    private String jp;

    /*- ************************************************************************************************************** */
    /*- ************************************************************************************************************** */
    public SizeCountriesConversion() {}

    public SizeCountriesConversion(String size, String eu, String us, String uk, String jp) {
        super();
        this.size = size;
        this.eu = eu;
        this.us = us;
        this.uk = uk;
        this.jp = jp;
    }

    /*- ************************************************************************************************************** */
    /*- ************************************************************************************************************** */
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getEu() {
        return eu;
    }

    public void setEu(String eu) {
        this.eu = eu;
    }

    public String getUs() {
        return us;
    }

    public void setUs(String us) {
        this.us = us;
    }

    public String getUk() {
        return uk;
    }

    public void setUk(String uk) {
        this.uk = uk;
    }

    public String getJp() {
        return jp;
    }

    public void setJp(String jp) {
        this.jp = jp;
    }

}
