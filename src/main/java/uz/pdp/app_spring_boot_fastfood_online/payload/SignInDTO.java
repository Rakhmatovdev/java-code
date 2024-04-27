package uz.pdp.app_spring_boot_fastfood_online.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignInDTO {

    private String contactNumber;

    private String password;

}
