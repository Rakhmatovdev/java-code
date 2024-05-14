package uz.pdp.app_spring_boot_fastfood_online.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.app_spring_boot_fastfood_online.entity.Product}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCrudDTO implements Serializable {
    private String name;
    private String description;
    private Double price;
    private Long attachmentId;
    private Long categoryId;
}