package uz.pdp.app_spring_boot_fastfood_online.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.app_spring_boot_fastfood_online.entity.FavouriteProduct;

import java.io.Serializable;

/**
 * DTO for {@link FavouriteProduct}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteProductDTO implements Serializable {
    private Long id;
    private Long userId;
    private Long productId;
}