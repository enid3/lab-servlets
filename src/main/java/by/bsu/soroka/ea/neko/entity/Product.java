package by.bsu.soroka.ea.neko.entity;

import java.io.Serializable;
import java.util.Objects;


public class Product implements Serializable, Identifiable {
    private static final long serialVersionUID = -8157269109462864430L;
    public static final int BASE_RATING = 1200;

    private int id ;
    private String name;
    private int rating;
    private byte[] image;

    public Product(int id, String name, int rating, byte[] image) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.image = image;
    }

    public Product(){}

    public Product(String name, int rating, byte[] image) {
        this(0, name, rating, image);
    }

    public Product(String name, byte[] image) {
        this(0, name, BASE_RATING, image);
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void decRating(){
        --this.rating;
    }

    public void incRating(){
        ++this.rating;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && rating == product.rating && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rating);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}
