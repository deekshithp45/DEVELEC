package com.inspire.user1.develec;

public class Subparts_electronics {

    private String suburl;
    private String subtext;
    private String filter;
    private String cat1;

    public Subparts_electronics(String suburl, String subtext, String filter, String cat1) {
        this.suburl = suburl;
        this.subtext = subtext;
        this.filter = filter;
        this.cat1 = cat1;
    }

    public String getSuburl() {
        return suburl;
    }

    public void setSuburl(String suburl) {
        this.suburl = suburl;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getCat1() {
        return cat1;
    }

    public void setCat1(String cat1) {
        this.cat1 = cat1;
    }

    public Subparts_electronics(){

    }
}
