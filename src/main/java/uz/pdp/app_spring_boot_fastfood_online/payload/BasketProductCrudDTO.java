package uz.pdp.app_spring_boot_fastfood_online.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.app_spring_boot_fastfood_online.entity.BasketProduct;

import java.io.Serializable;

/**
 * DTO for {@link BasketProduct}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketProductCrudDTO implements Serializable {

    private Long productId;
    private Long userId;
    private int quantity = 1;

}