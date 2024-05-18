package uz.pdp.app_spring_boot_fastfood_online.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.StockCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.StockDTO;
import uz.pdp.app_spring_boot_fastfood_online.service.StockService;
import uz.pdp.app_spring_boot_fastfood_online.utils.AppConstant;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(AppConstant.BASE_PATH_V1+"/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @PostMapping
    public ApiResult<StockDTO> create(@RequestBody StockCrudDTO crudDTO) {

        log.info("Request to StockController create; params {}", crudDTO);
        return stockService.create(crudDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @GetMapping
    public ApiResult<List<StockDTO>> read() {

        log.info("Request to StockController read");
        return stockService.read();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @GetMapping("/{id}")
    public ApiResult<StockDTO> readOne(@PathVariable Long id) {

        log.info("Request to StockController readOne; params {}", id);
        return stockService.readById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @PutMapping("/{id}")
    public ApiResult<StockDTO> update(@PathVariable Long id, @RequestBody StockCrudDTO crudDTO) {

        log.info("Request to StockController update; params {},{}",id, crudDTO);
        return stockService.update(id,crudDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @DeleteMapping("/{id}")
    public ApiResult<String> delete(@PathVariable Long id) {

        log.info("Request to StockController delete; params {}",id);
        return stockService.delete(id);
    }


}
