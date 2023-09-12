package project.backend.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import project.backend.entity.UserCart;

import java.util.ArrayList;

public interface CartDBRepository extends JpaRepository<UserCart, Integer>  {
    ArrayList<UserCart> findUserCartByUserId(Integer id);
}
