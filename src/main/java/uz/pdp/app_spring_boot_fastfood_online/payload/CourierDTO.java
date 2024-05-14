package uz.pdp.app_spring_boot_fastfood_online.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourierDTO {

    private Long id;

    private String name;

    private String email;

    private String contactNumber;

    private String password;

}
