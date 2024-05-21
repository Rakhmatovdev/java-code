package uz.pdp.app_spring_boot_fastfood_online.payload;

import lombok.*;

import java.io.Serializable;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderHistoryDTO implements Serializable {

    private Long id;

    private Date date;

    private Double totalPrice;

}
