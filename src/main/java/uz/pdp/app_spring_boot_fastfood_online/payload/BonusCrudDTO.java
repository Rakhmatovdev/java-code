package uz.pdp.app_spring_boot_fastfood_online.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BonusCrudDTO {

    private String name;

    private String description;
}
