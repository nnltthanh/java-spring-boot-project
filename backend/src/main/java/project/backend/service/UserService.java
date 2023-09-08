package project.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.backend.entity.User;
import project.backend.reposity.UserDBReposity;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDBReposity repo ; //access database
    private ArrayList<User> users; //users list from database
    public UserService(ArrayList<User> users) {
        this.users = users;
    }
    public UserService() {
        this.users = new ArrayList<User>();
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
        Optional<User> user = repo.findById(id);
        //isPresent( ) for Optional<S> = isExist( )
        if (!user.isPresent()) {
            System.out.println("Can not found user has id " + id);
            return null;
        }
        else System.out.println(user.get().toString());

        return user.get(); //get ( ) -> Optional to User
    }
    //done
    public ArrayList<User> findAllUser() {
        System.out.println("Call find all users function");
        this.users = (ArrayList<User>) repo.findAll();
        for (User a : users) {
            System.out.println(a.toString());
        }
        return users;
    }
    //done - cant add new user has existed
    public User addUser(User user) {
        if (repo.findOneByUsername(user.getUsername()) != null) {
            System.out.println("Username has been exist");
            return null;
        } else {
            User newUser = repo.save(new User(user)); //create new empty User obj with id is generated
            this.users.add(newUser);
            System.out.println("Call add User function");
            System.out.println(newUser.toString());

            repo.save(newUser);
            return newUser;
        }
    }
    //done - only change password
    public User updateUser(Integer id, User user) {
        System.out.println("Call update user function");
        User temp = (User) findUserById(id);
        if (temp != null) {
            //equals to compare value
            if (findUserById(id).getUsername().equals(user.getUsername())) {
                temp.setPassword(user.getPassword());
                System.out.println("Reset new password successfully");
                System.out.println(temp.toString());
                repo.save(temp);
                return temp;
            } else {
                System.out.println("Wrong username!!");
            }
        } else {
            System.out.println("Can not found user has id " + id);
        }
        return null;
    }

    public boolean deleteUser(Integer id) {
        repo.delete(findUserById(id));
        if (findUserById(id) == null){
            return true;
        }
        return false;
    }
}
