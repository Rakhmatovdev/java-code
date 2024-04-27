package uz.pdp.app_spring_boot_fastfood_online.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.app_spring_boot_fastfood_online.entity.User;
import uz.pdp.app_spring_boot_fastfood_online.enums.RegionEnum;
import uz.pdp.app_spring_boot_fastfood_online.enums.RoleEnum;

import java.io.Serializable;
import java.sql.Date;

/**
 * DTO for {@link User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private Long id;
    private String username;
    private String contactNumber;
    private String email;
    private RoleEnum role;
    private RegionEnum region;
    private String address;
    private Date birthDate;
}