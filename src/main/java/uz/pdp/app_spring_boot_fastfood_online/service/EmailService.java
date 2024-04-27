package uz.pdp.app_spring_boot_fastfood_online.service;

import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.UserDTO;


public interface EmailService {

   ApiResult<UserDTO> sendVerificationCode(UserDTO userDTO);

}
