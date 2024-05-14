package uz.pdp.app_spring_boot_fastfood_online.payload;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.SecondaryRow;
import org.hibernate.validator.constraints.UniqueElements;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourierCrudDTO {

    @NotNull(message = "username_must_not_be_null")
    @NotBlank
    private String name;

    @NotNull
    @Email(message = "Email_should_be_valid")
    @Column(unique = true)
    private String email;

    private String contactNumber;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[@$!%*#?&])[A-Za-z\\\\d@$!%*#?&]{8,}$")
    private String password;

}
