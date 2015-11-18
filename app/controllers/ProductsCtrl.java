package controllers;

// Utils
import java.util.ArrayList;
import java.util.List;

// Play Toys
import models.Tag;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;

// Collections
import collections.ProductCollection;

// Models
import models.Product;

// Views
import views.html.products.list;
import views.html.products.view;

public class ProductsCtrl extends Controller {
    private static final Form<Product> productForm = Form.form(Product.class);

    public static Result index() {
        return redirect(routes.ProductsCtrl.list(0));
    }

    public static Result list(Integer page) {
        List<Product> products = ProductCollection.findAll();
        return ok(list.render(products));
    }

    public static Result newProduct() {
        return ok(view.render(productForm));
    }

    public static Result view(Product product) {
        if (product == null) {
            return notFound(String.format("Product does not exist."));
        }
        Form<Product> filledForm = productForm.fill(product);
        return ok(view.render(filledForm));
    }

    public static Result save() {
        Form<Product> boundForm = productForm.bindFromRequest();

        if (boundForm.hasErrors()) {
            flash("error", "Please correct the form below.");
            return badRequest(view.render(boundForm));
        }

        Product product = boundForm.get();
        List<Tag> tags = new ArrayList<Tag>();

        product.tags.stream()
                .filter(tag -> tag != null)
                .map(tag -> Tag.findById(tag.id))
                .forEach(tags::add);

        product.tags = tags;
        ProductCollection.save(product);
        flash("success", String.format("Successfully added product %s.", product));
        return redirect(routes.ProductsCtrl.list(0));
    }

    public static Result delete(String ean) {
        Product product = ProductCollection.findByEan(ean);
        if (product != null) {
            ProductCollection.remove(product);
            return redirect(routes.ProductsCtrl.list(0));
        }
        return notFound();
    }
}
