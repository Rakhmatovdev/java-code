package uz.pdp.app_spring_boot_fastfood_online.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.app_spring_boot_fastfood_online.entity.Order;

import java.io.Serializable;

/**
 * DTO for {@link Order}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCrudDTO implements Serializable {

    private Long userId;
    private Long bonusId;
    private String deliveryAddress;

}