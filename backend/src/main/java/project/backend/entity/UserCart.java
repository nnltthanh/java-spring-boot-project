package project.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Data
@Table(name = "cart")
@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserCart {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Integer productId;
    private int quantity;

    public UserCart(Integer userId, Integer productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public UserCart(UserCart cart) {
        this.userId = cart.getUserId();
        this.productId = cart.getProductId();
        this.quantity = cart.getQuantity();
    }
}
