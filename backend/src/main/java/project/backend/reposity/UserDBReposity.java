package project.backend.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backend.entity.User;

//communicate with database
@Repository
public interface UserDBReposity extends JpaRepository<User, Integer> {
    public User findOneByUsername(String username) ;
}
