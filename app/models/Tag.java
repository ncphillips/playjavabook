package models;

import collections.ProductCollection;
import play.data.validation.Constraints;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Tag {
    public Long id;

    @Constraints.Required
    public String name;

    public List<Product> products;

    public Tag() {
        // Left empty
    }

    public Tag(Long id, String name, Collection<Product> products) {
        this.id = id;
        this.name = name;
        this.products = new LinkedList<Product>(products);
        for (Product product : products) {
            product.tags.add(this);
        }

    }

    private static List<Tag> tags = new LinkedList<Tag>();

    static {
        tags.add(new Tag(1l, "lightweight", ProductCollection.findByName("paperclips")));
        tags.add(new Tag(2l, "metal", ProductCollection.findByName("paperclips 1")));
        tags.add(new Tag(3l, "plastic", ProductCollection.findByName("paperclips 2")));
    }

    public static Tag findById(Long id) {
        for (Tag tag : tags) {
            if (tag.id.equals(id)) {
                return tag;
            }
        }
        return null;
    }

}