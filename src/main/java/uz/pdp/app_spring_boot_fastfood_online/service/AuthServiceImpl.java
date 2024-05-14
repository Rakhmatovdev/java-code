package uz.pdp.app_spring_boot_fastfood_online.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.app_spring_boot_fastfood_online.entity.CodeEntity;
import uz.pdp.app_spring_boot_fastfood_online.entity.User;
import uz.pdp.app_spring_boot_fastfood_online.enums.RoleEnum;
import uz.pdp.app_spring_boot_fastfood_online.exception.RestException;
import uz.pdp.app_spring_boot_fastfood_online.mapper.UserMapper;
import uz.pdp.app_spring_boot_fastfood_online.payload.*;
import uz.pdp.app_spring_boot_fastfood_online.repository.CodeEntityRepository;
import uz.pdp.app_spring_boot_fastfood_online.repository.UserRepository;
import uz.pdp.app_spring_boot_fastfood_online.security.JwtProvider;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationProvider authenticationProvider;
    private final EmailService emailService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CodeEntityRepository codeRepository;


    @Override
    public ApiResult<TokenDTO> signIn(SignInDTO signInDTO) {

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(signInDTO.getEmail(),signInDTO.getPassword());

        authenticationProvider.authenticate(authentication);

        User user = userRepository.findByEmail(signInDTO.getEmail())
                .orElseThrow(() -> RestException.notFound("Any user with email %s ".formatted(signInDTO.getEmail())));

        boolean matches = passwordEncoder.matches(signInDTO.getPassword(), user.getPassword());

        if(!matches)
            throw new RestException("Bad credentials");

        String token = jwtProvider.generateToken(signInDTO.getEmail());

        return ApiResult.success(new TokenDTO(token));
    }

    @Override
    public ApiResult<UserDTO> signUp(SignUpDTO signUpDTO) {

       checkUnique(signUpDTO.getEmail());

        String encodedPassword = passwordEncoder.encode(signUpDTO.getPassword());

        User user = new User(
               signUpDTO.getUsername(),
               signUpDTO.getContactNumber(),
               signUpDTO.getEmail(),
               encodedPassword,
               RoleEnum.USER,
               signUpDTO.getRegion(),
               signUpDTO.getAddress(),
               signUpDTO.getBirthDate(),
               false
       );

        User save = userRepository.save(user);

        return  emailService.sendVerificationCode(userMapper.toDto(save)) ;
    }

    @Override
    public ApiResult<String> emailVerification(CodeDTO codeDTO) {

        CodeEntity codeEntity = codeRepository.findByEmail(codeDTO.getEmail())
                .orElseThrow(() -> RestException.notFound("Any user with email %s ".formatted(codeDTO.getEmail())));

        if(!Objects.equals(codeDTO.getCode(), codeEntity.getCode()))
            throw new RestException("Wrong code");

        User user = userRepository.findByEmail(codeDTO.getEmail())
                .orElseThrow(() -> RestException.notFound("Any user with email %s ".formatted(codeDTO.getEmail())));

        user.setIsEnabled(true);

        userRepository.save(user);

        codeRepository.delete(codeEntity);

        return ApiResult.success("Email verified");
    }

    @Override
    public ApiResult<UserDTO> forgotPassword(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> RestException.notFound("Any user with email %s ".formatted(email)));

        return emailService.sendVerificationCode(userMapper.toDto(user));
    }

    @Override
    public ApiResult<String> resetPassword(ResetPasswordDTO resetPasswordDTO) {

        CodeEntity codeEntity = codeRepository.findByEmail(resetPasswordDTO.getEmail())
                .orElseThrow(() -> RestException.notFound("Any user with email %s ".formatted(resetPasswordDTO.getEmail())));

        User user = userRepository.findByEmail(resetPasswordDTO.getEmail())
                .orElseThrow(() -> RestException.notFound("Any user with email %s ".formatted(resetPasswordDTO.getEmail())));

        if(!Objects.equals(codeEntity.getCode(),resetPasswordDTO.getVerificationCode()))
             throw new RestException("Wrong code");

        String encodedPassword = passwordEncoder.encode(resetPasswordDTO.getNewPassword());

        codeRepository.delete(codeEntity);

        user.setPassword(encodedPassword);

        return ApiResult.success("Password is changed");
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByEmail(username)
                .orElseThrow(() -> RestException
                        .notFound("Any user with email %s".formatted(username)));
    }








    private void checkUnique(String email) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isPresent())
            throw RestException.alreadyExist("This user");

    }
}
