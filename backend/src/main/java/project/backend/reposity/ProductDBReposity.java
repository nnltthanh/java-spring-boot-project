package project.backend.reposity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backend.entity.Product;


@Repository
public interface ProductDBReposity extends JpaRepository<Product, Integer> {
    public Product findOneByName(String name);
}
