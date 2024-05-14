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
public class Category extends AbsLongEntity {


    private String name;

    @ManyToOne
    private Category parentCategory;

}
