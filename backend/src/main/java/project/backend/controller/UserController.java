package project.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.backend.entity.UserCart;
import project.backend.entity.Product;

import project.backend.entity.User;
import project.backend.service.ProductService;
import project.backend.service.UserService;

@RestController
@RequestMapping("/u")
public class UserController {
    @Autowired
    private UserService userService = new UserService();
    @Autowired
    private ProductService productService = new ProductService();
    @Autowired
    private ProductController productController = new ProductController();
   
    @GetMapping({"/all", "/"})
    public String getAllUser() {
        userService.findAllUser();
        String userList = "";
        for (User user : userService.getUsers()) {
            userList += "\n" + user.toString();
        }
        return "Call find all users function " + userList;
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Integer id) {
        if (userService.findUserById(id) == null) {
            return "Call find user by ID " + id + " function\nCan not found user has id " + id;
        }
        return "Call find user by ID " + id + " function\n" + userService.findUserById(id).toString();
    }

    @DeleteMapping("{id}")
    public String deleteUserById(@PathVariable Integer id) {
        if (userService.deleteUser(id)) {
            return "Call delete user by ID " + id + " function\n" + "id " + id + " User has been deleted!";
        } else if (userService.findUserById(id) == null) {
            return "Call delete user by ID " + id + " function\n" + "Can not found user has id " + id;
        }
        return "Call delete user by ID " + id + " function\n" + "Can not delete user has id " + id;
    }

    @PutMapping("{id}")
    public String updateUserById(@PathVariable Integer id, @RequestBody User user) {
        if (userService.updateUser(id, user) != null) {
            return "Call update user by ID " + id + " function\n" +  userService.updateUser(id, user).toString();
        }
        return "Call update user by ID " + id + " function\n" + "Wrong username!";
    }
    
    @PostMapping("add")
    public String addUser(@RequestBody User user){
        user = userService.addUser(user);
        if (user == null) {
            return "Call add user function \n" + "Username has been exist";
        }
        return "Call add user function \n" +  user.toString();
    }

    //save into cart database { userId, productId, quantity }
    @PostMapping("add-to-cart")
    public String addProductToCart(@RequestBody UserCart cart) {
        if (userService.findUserById(cart.getUserId()) != null && productService.findProductById(cart.getProductId()) != null) {
            return "Call add product to cart function\n" +
                    "Cart: " + userService.addToCart(cart);
        }
        return "Call add product to cart function\n" + "Can not add to cart";
    }

    @GetMapping("/cart")
    public String getAllCartInfo() {
        String cartList = "";
        for (UserCart cart : userService.findAllCart()) {
            cartList += "\n" + cart;
        }
        return "Call find all cart information\n" + cartList;
    }

    // list products in cart by userId ( haven't sorted )
    @GetMapping("/cart/{id}")
    public String getUserCartById(@PathVariable Integer id) {
        String cartList = "";
        for (UserCart cart : userService.getUserCart(id)) {
            cartList += "\n" + cart;
        }
        return "Call get user cart has ID " + id + cartList;
    }
}
