package uz.pdp.app_spring_boot_fastfood_online.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.app_spring_boot_fastfood_online.entity.OrderProduct;

import java.io.Serializable;

/**
 * DTO for {@link OrderProduct}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDTO implements Serializable {
    private Long id;
    private ProductDTO product;
    private String productName;
    private Double buyingPrice;
    private int quantity = 1;
}