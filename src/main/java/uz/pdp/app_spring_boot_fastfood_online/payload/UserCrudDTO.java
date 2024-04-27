package uz.pdp.app_spring_boot_fastfood_online.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.app_spring_boot_fastfood_online.enums.RegionEnum;

import java.io.Serializable;
import java.sql.Date;

/**
 * DTO for {@link uz.pdp.app_spring_boot_fastfood_online.entity.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCrudDTO implements Serializable {
    @NotNull(message = "username_must_not_be_null")
    @NotBlank
    private String username;
    @NotNull(message = "contact_number_must_not_be_null")
    @NotBlank
    private String contactNumber;
    @Email(message = "Email_should_be_valid")
    private String email;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[@$!%*#?&])[A-Za-z\\\\d@$!%*#?&]{8,}$")
    private String password;
    private RegionEnum region;
    private String address;
    private Date birthDate;
}