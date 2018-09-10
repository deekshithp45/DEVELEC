package com.inspire.user1.develec;

public class eleccontent_items {

    private String elecurl;
    private String electext;
    private String cat;


    public eleccontent_items(String elecurl, String electext, String electype, String cat) {
        this.elecurl = elecurl;
        this.electext = electext;
        this.cat = cat;
    }



    public String getElecurl() {
        return elecurl;
    }

    public void setElecurl(String elecurl) {
        this.elecurl = elecurl;
    }

    public String getElectext() {
        return electext;
    }

    public void setElectext(String electext) {
        this.electext = electext;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public eleccontent_items(){

    }



}

