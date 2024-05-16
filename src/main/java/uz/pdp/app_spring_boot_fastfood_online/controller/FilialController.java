package uz.pdp.app_spring_boot_fastfood_online.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.FilialCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.FilialDTO;
import uz.pdp.app_spring_boot_fastfood_online.service.FilialService;
import uz.pdp.app_spring_boot_fastfood_online.utils.AppConstant;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(AppConstant.BASE_PATH_V1 + "/filial")
@RequiredArgsConstructor
public class FilialController {

    private final FilialService filialService;

    @GetMapping
    public ApiResult<List<FilialDTO>> read(){
        log.info("Request to courier controller read");
        return filialService.read();
    }

    @GetMapping("{id}")
    public ApiResult<FilialDTO> readOne(@PathVariable Long id){
        log.info("Request to courier controller readOne; id: {}", id);
        return filialService.readOne(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @PostMapping
    public ApiResult<FilialDTO> create(@Valid @RequestBody FilialCrudDTO crudDTO){
        log.info("Request to courier controller {}", crudDTO);
        return filialService.create(crudDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @PutMapping("{id}")
    public ApiResult<FilialDTO> update(@PathVariable Long id, @RequestBody FilialCrudDTO crudDTO){
        log.info("Request to courier controller with id: {}", id);
        return filialService.edit(id, crudDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @DeleteMapping("{id}")
    public ApiResult<String> delete(@PathVariable Long id){
        log.info("Request to courier controller with id: {}", id);
        return filialService.delete(id);
    }







}
