package collections;

import models.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductCollection {
    private static List<Product> products;

    static {
        products = new ArrayList<Product>();
        products.add(new Product("11111", "Paperclips 1", "Paperclips Description 1"));
        products.add(new Product("22222", "Paperclips 2", "Paperclips Description 2"));
        products.add(new Product("33333", "Paperclips 3", "Paperclips Description 3"));
        products.add(new Product("44444", "Paperclips 4", "Paperclips Description 4"));
        products.add(new Product("55555", "Paperclips 5", "Paperclips Description 5"));
    }

    public static Product findByEan(String ean) {
        for (Product p : products) {
            if (p.ean.equals(ean)) {
                return p;
            }
        }
        return null;
    }

    public static List<Product> findByName(String term) {
        final List<Product> results = new ArrayList<Product>();
        for (Product p : products) {
            if (p.name.toLowerCase().contains(term.toLowerCase())) {
                results.add(p);
            }
        }
        return results;
    }

    public static boolean remove(Product product) {
        return products.remove(product);
    }

    public static void save(Product product) {
        products.remove(product);
        products.add(product);
    }
}

