package uz.pdp.app_spring_boot_fastfood_online.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_spring_boot_fastfood_online.payload.*;
import uz.pdp.app_spring_boot_fastfood_online.service.AuthService;
import uz.pdp.app_spring_boot_fastfood_online.utils.AppConstant;

@Slf4j
@RestController
@RequestMapping(AppConstant.BASE_PATH_V1 + "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ApiResult<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO) {

        log.info("Request to sign-up params {}", signUpDTO);
        return authService.signUp(signUpDTO);
    }

    @PostMapping("/sign-in")
    public ApiResult<TokenDTO> signIn(@RequestBody SignInDTO signInDTO) {

        log.info("Request to sign-in params {}", signInDTO);
        return authService.signIn(signInDTO);
    }


    @PostMapping("/email-verification")
    public ApiResult<String> emailVerification(@RequestBody CodeDTO codeDTO){

        log.info("Request to email-verification params {}", codeDTO);
        return authService.emailVerification(codeDTO);
    }

    @PostMapping("/forgot-password")
    public ApiResult<UserDTO> forgotPassword(@RequestParam String email) {

        log.info("Request to forgot-password params {}", email);
        return authService.forgotPassword(email);
    }

    @PostMapping("reset-password")
    public ApiResult<String> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {

        log.info("Request to reset-password params {}", resetPasswordDTO);
        return authService.resetPassword(resetPasswordDTO);
    }

}
