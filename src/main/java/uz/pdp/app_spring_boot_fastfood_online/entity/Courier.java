package uz.pdp.app_spring_boot_fastfood_online.entity;


import jakarta.persistence.Entity;
import lombok.*;
import uz.pdp.app_spring_boot_fastfood_online.entity.templates.AbsLongEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Courier extends AbsLongEntity {

    private String name;

    private String email;

    private String contactNumber;

    private String password;

}
