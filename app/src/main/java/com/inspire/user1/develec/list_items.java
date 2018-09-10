package com.inspire.user1.develec;

public class list_items {

    private String url;
    private String title;
    private String Category;
    private String desc;
    private String head;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public list_items(String desc, String head) {
        this.desc = desc;
        this.head = head;
    }




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public void setCategory(String category) {
        Category = category;
    }



    public list_items(String url,String Category,String title) {
        this.url = url;
        this.Category = Category;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return Category;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public list_items(){

    }
}
