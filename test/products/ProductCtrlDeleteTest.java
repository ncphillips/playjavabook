package products;

import collections.ProductCollection;
import models.Product;
import play.api.Routes;
import play.test.Helpers;
import controllers.ProductsCtrl;
import org.junit.Test;
import play.mvc.Result;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProductCtrlDeleteTest {
    @Test
    public void testNotFoundIfProductDoesNotExist() throws Exception {
        Result result = ProductsCtrl.delete("dne");
        assertEquals(Helpers.status(result), Helpers.NOT_FOUND);
    }

    @Test
    public void testRedirectIfProductExists() throws Exception {
        ProductCollection.save(new Product("test", "n", "d"));
        Result result = ProductsCtrl.delete("test");
        assertEquals(Helpers.SEE_OTHER, Helpers.status(result));
        assertEquals("/products/", Helpers.redirectLocation(result));
    }

    @Test
    public void testExistingProductIsDeleted() throws Exception {
        ProductCollection.save(new Product("test", "n", "d"));
        ProductsCtrl.delete("test");

        Product product = ProductCollection.findByEan("test");
        assertNull(product);

    }
}
