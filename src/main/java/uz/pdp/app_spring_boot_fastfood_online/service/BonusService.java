package uz.pdp.app_spring_boot_fastfood_online.service;

import uz.pdp.app_spring_boot_fastfood_online.entity.Bonus;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.BonusCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.BonusDTO;

import java.util.List;

public interface BonusService {

    ApiResult<BonusDTO> create(BonusCrudDTO crudDTO);

    ApiResult<BonusDTO> update(BonusCrudDTO bonusCrudDTO, Long id);

    ApiResult<List<BonusDTO>> read();

    ApiResult<BonusDTO> readOne(Long id);

    ApiResult<String> delete(Long id);

}
