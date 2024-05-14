package uz.pdp.app_spring_boot_fastfood_online.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;
import uz.pdp.app_spring_boot_fastfood_online.entity.templates.AbsLongEntity;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Stock extends AbsLongEntity {

    @ManyToMany
    private List<Product> products;

    private Double discount_amount;

    private Date beginsAt;

    private Date endsAt;


}
