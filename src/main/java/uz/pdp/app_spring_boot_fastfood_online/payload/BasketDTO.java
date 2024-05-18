package uz.pdp.app_spring_boot_fastfood_online.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BasketDTO {

    private Long id;

    private Double price;

    private Long userId;

    private Boolean isClosed;

}
