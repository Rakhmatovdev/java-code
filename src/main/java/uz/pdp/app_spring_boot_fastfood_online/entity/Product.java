package uz.pdp.app_spring_boot_fastfood_online.entity;

import jakarta.persistence.Column;
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
public class Product extends AbsLongEntity {

    private String name;

    @Column(columnDefinition = "varchar(500)")
    private String description;

    private Double price;

    @ManyToOne
    private Attachment attachment;

    @ManyToOne
    private Category category;

    private Double priceWithStock;


}
