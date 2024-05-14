package uz.pdp.app_spring_boot_fastfood_online.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.pdp.app_spring_boot_fastfood_online.entity.templates.AbsLongEntity;
import uz.pdp.app_spring_boot_fastfood_online.enums.RegionEnum;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Filial extends AbsLongEntity {

    private String name;

    private String location;

    private String workingTime;

    private String contactNumber;

    @Enumerated(EnumType.STRING)
    private RegionEnum regions;

}
