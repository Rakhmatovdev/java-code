package uz.pdp.app_spring_boot_fastfood_online.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.app_spring_boot_fastfood_online.entity.Order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link Order}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {
    private Long id;
    private UserDTO userDTO;
    private CourierDTO courierDTO;
    private BonusDTO bonusDTO;
    private Double deliveryCost;
    private String deliveryAddress;
    private Date orderDate;
    private Double totalPrice;
    private List<OrderProductDTO> orderProducts;
}