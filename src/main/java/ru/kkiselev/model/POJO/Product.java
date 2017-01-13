package ru.kkiselev.model.POJO;

/**
 * Created by kv on 04.01.17.
 */
public class Product {
    int id;
    String description;
    int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product(String description, int price) {
        this.description = description;
        this.price = price;
    }

    public Product() {
    }
}
