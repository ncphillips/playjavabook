package controllers;

// Utils
import java.util.List;

// Play Toys
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;

// Collections
import collections.ProductCollection;

// Modelsj
import models.Product;

// Views
import views.html.products.list;
import views.html.products.view;

public class ProductsCtrl extends Controller {
    private static final Form<Product> productForm = Form.form(Product.class);

    public static Result list() {
        List<Product> products = ProductCollection.findAll();
        return ok(list.render(products));
    }

    public static Result newProduct() {

        return ok(view.render(productForm));
    }

    public static Result view(String ean) {
        return TODO;
    }

    public static Result save() {
        return TODO;
    }
}
