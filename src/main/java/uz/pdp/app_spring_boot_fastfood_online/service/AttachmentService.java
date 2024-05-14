package uz.pdp.app_spring_boot_fastfood_online.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.AttachmentDTO;


public interface AttachmentService {


    ApiResult<AttachmentDTO> upload(HttpServletRequest request);

    void downloadOne(Long id, HttpServletResponse response);

}
