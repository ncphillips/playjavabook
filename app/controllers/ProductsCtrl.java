package controllers;

// Utils
import java.util.List;

// Play Toys
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

    public static Result list() {
        List<Product> products = ProductCollection.findAll();
        return ok(list.render(products));
    }

    public static Result newProduct() {
        return ok(view.render(productForm));
    }

    public static Result view(String ean) {
        final Product product = ProductCollection.findByEan(ean);
        if (product == null) {
            return notFound(String.format("Product %s does not exist.", ean));
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
        ProductCollection.save(product);
        flash("success", String.format("Successfully added product %s.", product));
        return redirect(routes.ProductsCtrl.list());
    }

    public static Result delete(String ean) {
        Product product = ProductCollection.findByEan(ean);
        if (product != null) {
            ProductCollection.remove(product);
            return redirect(routes.ProductsCtrl.list());
        }
        return notFound();
    }
}
