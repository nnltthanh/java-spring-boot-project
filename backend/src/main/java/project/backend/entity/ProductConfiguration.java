package project.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@Entity
public class ProductConfiguration {
    @Column
    @Id
    private String productCode;
    private String screenConfiguration;
    private String cameraConfiguration;
    private String cpuConfiguration;
    private String memoryConfiguration;
    private String connectionConfiguration;
    private String batteryConfiguration;
    private String extensionConfiguration;
    private String generalConfiguration;
}
