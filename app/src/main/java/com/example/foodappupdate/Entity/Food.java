package com.example.foodappupdate.Entity;

public class Food {

    String title,description;
    float price;
    int imgResId;

    public Food(){}

    public Food(String title, String description, float price, int imgResId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imgResId = imgResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    @Override
    public String toString() {
        return "Food{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imgResId=" + imgResId +
                '}';
    }
}
