package project.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.backend.entity.Product;
import project.backend.service.ProductService;
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService = new ProductService();
    @GetMapping({"/all", "/"})
    public String getAllProduct() {
        productService.findAllProduct();
        String productList = "";
        for (Product product : productService.getProducts()) {
            productList += "\n" + product.toString();
        }
        return "Call find all products function " + productList;
    }
    @GetMapping("/{id}")
    public String getUserById(@PathVariable Integer id) {
        if (productService.findProductById(id) == null) {
            return "Call find product by ID " + id + " function\nCan not found product has id " + id;
        }
        return "Call find product by ID " + id + " function\n" + productService.findProductById(id).toString();
    }
    @DeleteMapping("{id}")
    public String deleteProductById(@PathVariable Integer id) {
        if (productService.deleteProduct(id)) {
            return "Call delete product by ID " + id + " function\n" + "id " + id + " Product has been deleted!";
        } else if (productService.findProductById(id) == null) {
            return "Call delete product by ID " + id + " function\n" + "Can not found product has id " + id;
        }
        return "Call delete product by ID " + id + " function\n" + "Can not delete product has id " + id;

    }
    @PutMapping("{id}")
    public String updateProductById(@PathVariable Integer id, @RequestBody Product product) {
        if (productService.updateProduct(id, product) != null) {
            return "Call update product by ID " + id + " function\n" +  productService.updateProduct(id, product).toString();
        }
        return "Call update product by ID " + id + " function\n" + "Wrong product name!";
    }
    @PostMapping("add")
    public String addProduct(@RequestBody Product product){
        product = productService.addProduct(product);
        if (product == null) {
            return "Call add product function \n" + "Product name has been exist";
        }
        return "Call add product function \n" +  product.toString();
    }
}

