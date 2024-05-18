package uz.pdp.app_spring_boot_fastfood_online.payload;

import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BonusDTO {

    private Long id;

    private String name;

    private String description;

}
