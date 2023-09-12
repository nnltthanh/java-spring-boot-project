package project.backend.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.backend.entity.User;
import project.backend.entity.UserCart;
import project.backend.reposity.CartDBRepository;
import project.backend.reposity.UserDBRepository;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDBRepository userRepo ; //access user database

    @Autowired
    CartDBRepository cartRepo; //access cart database
    private ArrayList<User> users; //users list from database

    public UserService(ArrayList<User> users) {
        this.users = users;
    }

    public UserService() {
        this.users = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    //done
    public User findUserById(Integer id){
        System.out.println("Call find user by ID function");
        Optional<User> user = userRepo.findById(id);
        //isPresent( ) for Optional<S> = isExist( )
        if (!user.isPresent()) {
            System.out.println("Can not found user has id " + id);
            return null;
        }
        else System.out.println(user.get());

        return user.get(); //get ( ) -> Optional to User
    }

    //done
    public ArrayList<User> findAllUser() {
        System.out.println("Call find all users function");
        this.users = (ArrayList<User>) userRepo.findAll();
        for (User a : users) {
            System.out.println(a.toString());
        }
        return users;
    }

    //done - cant add new user has existed
    @Transactional
    public User addUser(User user) {
        if (userRepo.findOneByUsername(user.getUsername()) != null) {
            System.out.println("Username has been exist");
            return null;
        } else {
            User newUser = userRepo.save(new User(user)); //create new empty User obj with id is generated
            this.users.add(newUser);
            System.out.println("Call add User function");
            System.out.println(newUser);

            userRepo.save(newUser);
            return newUser;
        }
    }

    //done - only change password
    @Transactional
    public User updateUser(Integer id, User user) {
        System.out.println("Call update user function");
        User temp = findUserById(id);
        if (temp != null) {
            //equals to compare value
            if (findUserById(id).getUsername().equals(user.getUsername())) {
                temp.setPassword(user.getPassword());
                System.out.println("Reset new password successfully");
                System.out.println(temp);
                userRepo.save(temp);
                return temp;
            } else {
                System.out.println("Wrong username!!");
            }
        } else {
            System.out.println("Can not found user has id " + id);
        }
        return null;
    }

    //done - delete exist user
    @Transactional
    public boolean deleteUser(Integer id) {
        userRepo.delete(findUserById(id));
        return findUserById(id) == null;
    }

    @Transactional
    public UserCart addToCart(UserCart cart) {
        System.out.println("Call add product to cart function");
        UserCart newCart = new UserCart();

        newCart.setUserId(cart.getUserId());
        newCart.setProductId(cart.getProductId());
        newCart.setQuantity(cart.getQuantity());

        cartRepo.save(newCart);
        System.out.println(newCart.getId());

        return newCart;
    }

    public ArrayList<UserCart> findAllCart() {
        return (ArrayList<UserCart>) cartRepo.findAll();
    }

    public ArrayList<UserCart> getUserCart(Integer userId) {
        ArrayList<UserCart> cartList = new ArrayList<>();
        cartList = cartRepo.findUserCartByUserId(userId);

        return cartList;
    }
}
