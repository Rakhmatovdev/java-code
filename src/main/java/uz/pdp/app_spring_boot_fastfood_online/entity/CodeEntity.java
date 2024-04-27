package uz.pdp.app_spring_boot_fastfood_online.entity;

import jakarta.persistence.Entity;
import lombok.*;
import uz.pdp.app_spring_boot_fastfood_online.entity.templates.AbsLongEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "codes")
public class CodeEntity extends AbsLongEntity {


    private String email;


    private Integer code;

}
