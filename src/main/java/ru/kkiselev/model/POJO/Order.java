package ru.kkiselev.model.POJO;

/**
 * Created by kv on 04.01.17.
 */
public class Order {
    int id;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
