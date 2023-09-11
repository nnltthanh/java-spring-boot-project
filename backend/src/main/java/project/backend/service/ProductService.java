package project.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.backend.entity.Product;
import project.backend.reposity.ProductDBReposity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductDBReposity repo ; //access database
    private ArrayList<Product> products; //products list from database
    public ProductService(ArrayList<Product> products) {
        this.products = products;
    }
    public ProductService() {
        this.products = new ArrayList<Product>();
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public Product findProductById(Integer id){
        System.out.println("Call find product by ID function");
        Optional<Product> product = repo.findById(id);
        //isPresent( ) for Optional<S> = isExist( )
        if (!product.isPresent()) {
            System.out.println("Can not found product has id " + id);
            return null;
        }
        else System.out.println(product.get().toString());

        return product.get(); //get ( ) -> Optional to Product
    }

    public ArrayList<Product> findAllProduct() {
        System.out.println("Call find all products function");
        this.products = (ArrayList<Product>) repo.findAll();
        for (Product a : products) {
            System.out.println(a.toString());
        }
        return products;
    }

    public Product addProduct(Product product) {
        if (repo.findOneByName(product.getName()) != null) {
            System.out.println("Product name has been exist");
            return null;
        } else {
            Product newProduct = repo.save(new Product(product)); //create new empty Product obj with id is generated
            this.products.add(newProduct);
            System.out.println("Call add Product function");
            System.out.println(newProduct.toString());

            repo.save(newProduct);
            return newProduct;
        }
    }

    public Product updateProduct(Integer id, Product product) {
        System.out.println("Call update product function");
        Product temp = (Product) findProductById(id);
        if (temp != null) {
            //equals to compare value
            if (findProductById(id).getName().equals(product.getName())) {
                //update


                System.out.println(temp.toString());
                repo.save(temp);
                return temp;
            } else {
                System.out.println("Wrong product name!!");
            }
        } else {
            System.out.println("Can not found product has id " + id);
        }
        return null;
    }

    public boolean deleteProduct(Integer id) {
        repo.delete(findProductById(id));
        if (findProductById(id) == null){
            return true;
        }
        return false;
    }
}
