package by.bsu.soroka.ea.neko.entity;

import java.io.Serializable;
import java.util.Objects;

public class Storage implements Serializable, Identifiable {
    private static final long serialVersionUID = -3295499478815283724L;
    private int id;
    private String name;
    //private String location;


    public Storage(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Storage(){}

    public Storage(String name) {
        this(0, name);
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return id == storage.id && Objects.equals(name, storage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
