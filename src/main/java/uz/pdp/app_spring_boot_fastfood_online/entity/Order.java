package uz.pdp.app_spring_boot_fastfood_online.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Order extends AbsLongEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private Courier courier;

    @ManyToOne
    private Bonus bonus;

    private Double deliveryCost;

    @Column(columnDefinition = "varchar(1000)")
    private String deliveryAddress;

    private Date orderDate;

    private Double totalPrice;

    @OneToMany
    private List<OrderProduct> orderProducts;

}
