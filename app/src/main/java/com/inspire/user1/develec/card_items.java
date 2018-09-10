package com.inspire.user1.develec;

public class card_items {

    private String Description;
    private String cardtitle;
    private String link;
    private String sshot;
    private String type;

    public card_items(String description, String cardtitle, String link, String sshot, String type) {
        Description = description;
        this.cardtitle = cardtitle;
        this.link = link;
        this.sshot = sshot;
        this.type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCardtitle() {
        return cardtitle;
    }

    public void setCardtitle(String cardtitle) {
        this.cardtitle = cardtitle;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSshot() {
        return sshot;
    }

    public void setSshot(String sshot) {
        this.sshot = sshot;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public card_items() {

    }
}
