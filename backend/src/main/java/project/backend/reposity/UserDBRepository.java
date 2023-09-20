package project.backend.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backend.entity.User;

import java.util.Optional;

//communicate with database
@Repository
public interface UserDBRepository extends JpaRepository<User, Integer> {
    Optional<User> findOneByUsername(String username) ;
}
