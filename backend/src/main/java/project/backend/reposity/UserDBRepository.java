package project.backend.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backend.entity.User;

//communicate with database
@Repository
public interface UserDBRepository extends JpaRepository<User, Integer> {
    User findOneByUsername(String username) ;
}
