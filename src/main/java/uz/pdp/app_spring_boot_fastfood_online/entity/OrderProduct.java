package uz.pdp.app_spring_boot_fastfood_online.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.pdp.app_spring_boot_fastfood_online.entity.templates.AbsLongEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class OrderProduct extends AbsLongEntity {

    @ManyToOne
    private Product product;

    private String productName;

    private Double buyingPrice;

    private int quantity = 1;

}
