package uz.pdp.app_spring_boot_fastfood_online.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uz.pdp.app_spring_boot_fastfood_online.entity.CodeEntity;
import uz.pdp.app_spring_boot_fastfood_online.exception.RestException;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.UserDTO;
import uz.pdp.app_spring_boot_fastfood_online.repository.CodeEntityRepository;

import java.util.Random;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
    private final CodeEntityRepository codeRepository;
    private final JavaMailSender mailSender;

    private final Random random = new Random();


    @Override
    @Transactional
    public ApiResult<UserDTO> sendVerificationCode(UserDTO userDTO){

        Integer code = random.nextInt(10_000, 99_999);

        MimeMessage message = mailSender.createMimeMessage();

        try {
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(userDTO.getEmail()));
            message.setSubject("Verification code");
            message.setText(code.toString());
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RestException(e.getMessage());
        }
        CodeEntity codeEntity = new CodeEntity(userDTO.getEmail(), code);

        codeRepository.save(codeEntity);

        return ApiResult.success(userDTO);
    }
}
