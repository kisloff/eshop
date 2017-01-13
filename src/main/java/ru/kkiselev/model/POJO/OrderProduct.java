package ru.kkiselev.model.POJO;

/**
 * Created by kv on 07.01.17.
 */
public class OrderProduct {
    int productId;
    int orderId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
