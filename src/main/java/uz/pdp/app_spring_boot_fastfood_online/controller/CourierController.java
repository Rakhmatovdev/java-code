package uz.pdp.app_spring_boot_fastfood_online.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.CourierCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.CourierDTO;
import uz.pdp.app_spring_boot_fastfood_online.service.CourierService;
import uz.pdp.app_spring_boot_fastfood_online.utils.AppConstant;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(AppConstant.BASE_PATH_V1 + "/courier")
@RequiredArgsConstructor
public class CourierController {

    private final CourierService courierService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @PostMapping
    public ApiResult<CourierDTO> create(CourierCrudDTO crudDTO){
        log.info("Request to courier controller create; params {}", crudDTO);
        return  courierService.create(crudDTO);
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @GetMapping
    public ApiResult<List<CourierDTO>> read(){
        log.info("Request to courier controller read method");
        return courierService.read();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @GetMapping("/{id}")
    public ApiResult<CourierDTO> getOne(@PathVariable Long id){
        log.info("Request to courier controller getOne; params: {}", id);
       return courierService.readOne(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @PutMapping("/{id}")
    public ApiResult<CourierDTO> edit(@RequestBody CourierCrudDTO crudDTO, @PathVariable Long id){
        log.info("Request to courier controller update; params {}", crudDTO);
      return   courierService.update(id, crudDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @DeleteMapping("/{id}")
    public ApiResult<String> delete(@PathVariable Long id){
        log.info("Request to courier controller delete; params: {}", id);
        return courierService.delete(id);
    }
}
