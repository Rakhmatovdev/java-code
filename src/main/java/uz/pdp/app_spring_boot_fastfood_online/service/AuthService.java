package uz.pdp.app_spring_boot_fastfood_online.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import uz.pdp.app_spring_boot_fastfood_online.payload.*;

public interface AuthService extends UserDetailsService {

    ApiResult<TokenDTO> signIn(SignInDTO signInDTO);

    ApiResult<UserDTO> signUp(SignUpDTO signUpDTO);

    ApiResult<String> emailVerification(CodeDTO codeDto);

    ApiResult<UserDTO> forgotPassword(String email);

    ApiResult<String> resetPassword(ResetPasswordDTO resetPasswordDTO);



}
