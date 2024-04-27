package uz.pdp.app_spring_boot_fastfood_online.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.SignInDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.SignUpDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.TokenDTO;

public interface AuthService extends UserDetailsService {

    ApiResult<TokenDTO> signIn(SignInDTO signInDTO);

    ApiResult<String> signUp(SignUpDTO signUpDTO);

    ApiResult<String> emailVerification(String email, String code);

}
