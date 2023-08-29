package ru.job4j.io.duplicates;


import java.util.Objects;

public class FileProperty {

    private long size;
    private String name;

    public FileProperty(long size, String name) {
        this.name = name;
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return false;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileProperty that = (FileProperty) o;
        return size == that.getSize() && Objects.equals(name, that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, name);
    }
}
