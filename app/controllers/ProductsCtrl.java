package controllers;

import collections.ProductCollection;
import models.Product;
import views.html.products.list;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class ProductsCtrl extends Controller {
    public static Result list() {
        List<Product> products = ProductCollection.findAll();
        return ok(list.render(products));
    }

    public static Result newProduct() {
        return TODO;
    }

    public static Result view(String ean) {
        return TODO;
    }

    public static Result save() {
        return TODO;
    }
}
