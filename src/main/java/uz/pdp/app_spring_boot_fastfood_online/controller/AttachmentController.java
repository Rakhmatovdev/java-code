package uz.pdp.app_spring_boot_fastfood_online.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.AttachmentDTO;
import uz.pdp.app_spring_boot_fastfood_online.service.AttachmentService;
import uz.pdp.app_spring_boot_fastfood_online.utils.AppConstant;

@Slf4j
@RestController
@RequestMapping(AppConstant.BASE_PATH_V1+"/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @PostMapping("/upload")
    public ApiResult<AttachmentDTO> upload(HttpServletRequest request) {

        log.info("Request to attachment upload; params {}", request.getParameterMap());
        return attachmentService.upload(request);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @PostMapping("/download{id}")
    public void download(HttpServletResponse response, @PathVariable Long id) {

        log.info("Request to attachment download ");
        attachmentService.downloadOne(id,response);
    }

}
