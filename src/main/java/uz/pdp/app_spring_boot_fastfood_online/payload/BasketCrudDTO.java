package uz.pdp.app_spring_boot_fastfood_online.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BasketCrudDTO {

    private Double price;

    private Long userId;

}
