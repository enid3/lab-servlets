package by.bsu.soroka.ea.neko.entity;

import java.io.Serializable;
import java.util.Objects;

public class Stock implements Serializable, Identifiable {
    private static final long serialVersionUID = -2844985797010674471L;
    private int id;
    private int productID;
    private int storageID;
    private int count;

    public Stock(int id, int productID, int storageID, int count) {
        this.id = id;
        this.productID = productID;
        this.storageID = storageID;
        this.count = count;
    }

    public Stock(){}

    public Stock(int productID, int storageID, int count) {
        this(0, productID, storageID, count);
    }

    @Override
    public int getId() {
        return id;
    }

    public int getProductID() {
        return productID;
    }

    public int getStorageID() {
        return storageID;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setStorageID(int storageID) {
        this.storageID = storageID;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return id == stock.id && productID == stock.productID && storageID == stock.storageID && count == stock.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productID, storageID, count);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", productID=" + productID +
                ", storageID=" + storageID +
                ", count=" + count +
                '}';
    }
}
