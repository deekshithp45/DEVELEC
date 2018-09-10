package com.inspire.user1.develec;

public class Ratings {

    private String username,comments;
    private float ratings;

    public Ratings(String username, String comments, float ratings) {
        this.username = username;
        this.comments = comments;
        this.ratings = ratings;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }

    public Ratings(){

    }
}