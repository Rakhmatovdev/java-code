package uz.pdp.app_spring_boot_fastfood_online.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_spring_boot_fastfood_online.config.AppProperties;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.BonusCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.BonusDTO;
import uz.pdp.app_spring_boot_fastfood_online.service.BonusService;
import uz.pdp.app_spring_boot_fastfood_online.utils.AppConstant;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(AppConstant.BASE_PATH_V1 + "/bonus")
@RequiredArgsConstructor
public class BonusConroller {

    private final BonusService bonusService;

    @PostMapping("/create")
    public ApiResult<BonusDTO> create(@RequestBody BonusCrudDTO crudDTO) {
        log.info("Create bonus : {}", crudDTO);
        return bonusService.create(crudDTO);
    }

    @GetMapping("/read/{id}")
    public ApiResult<BonusDTO> readOne(@PathVariable Long id) {
        log.info("Read bonus : {}", id);
        return bonusService.readOne(id);
    }

    @GetMapping("/read")
    public ApiResult<List<BonusDTO>> read(){
        log.info("Read all bonus");
        return bonusService.read();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<String> delete(@PathVariable Long id) {
        log.info("Delete bonus : {}", id);
        return bonusService.delete(id);
    }

    @PutMapping("/update/{id}")
    public ApiResult<BonusDTO> update(@PathVariable Long id, @RequestBody BonusCrudDTO crudDTO) {
        log.info("Update bonus : {}", id);
        return bonusService.update(crudDTO, id);
    }

}


