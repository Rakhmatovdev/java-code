package uz.pdp.app_spring_boot_fastfood_online.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import uz.pdp.app_spring_boot_fastfood_online.entity.templates.AbsLongEntity;

import java.util.List;

//toDo isClosed attributi kerak yoki kerak emasligi tekshirib chiqish kerak

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Basket extends AbsLongEntity {

    @ManyToOne
    private User user;

    private Double totalPrice;

    private Boolean isClosed;

    @OneToMany(mappedBy = "basket")
    private List<BasketProduct> products;

}
