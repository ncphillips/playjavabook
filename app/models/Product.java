package models;

import play.data.validation.Constraints;

import java.util.LinkedList;
import java.util.List;

public class Product {
    @Constraints.Required
    public String ean;
    @Constraints.Required
    public String name;
    public String description;
    public List<Tag> tags = new LinkedList<Tag>();

    public Product() {}

    public Product(String ean, String name, String description) {
        this.ean = ean;
        this.name = name;
        this.description = description;
    }

    public String toString() {
        return String.format("%s - %s", ean, name);
    }
}
