package uz.pdp.app_spring_boot_fastfood_online.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.app_spring_boot_fastfood_online.entity.Product;

import java.io.Serializable;

/**
 * DTO for {@link Product}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long attachmentId;
    private Long categoryId;
    private Double priceWithStock;

}