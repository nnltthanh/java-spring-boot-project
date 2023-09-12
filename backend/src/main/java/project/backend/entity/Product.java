package project.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.Date;

@Entity
@Component
@Data
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product { //electronic product
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;
    private int price; //original price
    private int officialPrice; //official price with promotion
    private int quantity;

    @OneToOne (cascade = CascadeType.ALL) //auto update when changing
    @JoinColumn(name = "productCode")
    @Autowired
    private ProductConfiguration configuration;

    private String producer;
    private String type;
    private Date updateDate;
    @Lob //save in database with large object binary type
    private byte[] images;
    private String productDescribe;

    public Product(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.officialPrice = product.getOfficialPrice();
        this.quantity = product.getQuantity();
        this.configuration = product.getConfiguration();
        this.producer = product.getProducer();
        this.type = product.getType();
        this.updateDate = product.getUpdateDate();
        this.images = product.getImages();
        this.productDescribe = product.getProductDescribe();
    }

    public void printUpdateDate(Date JSONformatDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(JSONformatDate);

        System.out.printf("%02d-%02d-%4d  %02d:%02d:%02d",
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.HOUR) ,
                calendar.get(Calendar.MINUTE) ,
                calendar.get(Calendar.SECOND));
    }
}
