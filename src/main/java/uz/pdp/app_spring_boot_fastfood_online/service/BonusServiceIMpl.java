package uz.pdp.app_spring_boot_fastfood_online.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.app_spring_boot_fastfood_online.entity.Bonus;
import uz.pdp.app_spring_boot_fastfood_online.exception.RestException;
import uz.pdp.app_spring_boot_fastfood_online.mapper.BonusMapper;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.BonusCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.BonusDTO;
import uz.pdp.app_spring_boot_fastfood_online.repository.BonusRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BonusServiceIMpl implements BonusService{

    private final BonusRepository bonusRepository;

    private final BonusMapper bonusMapper;

    @Override
    public ApiResult<BonusDTO> create(BonusCrudDTO crudDTO) {

        Optional<Bonus> bonusDB = bonusRepository.findByName(crudDTO.getName());

        if (bonusDB.isPresent()) {
            throw RestException.alreadyExist("Bonus with name: ".formatted(crudDTO.getName()));
        }

        Bonus bonus = bonusMapper.toEntity(crudDTO);
        bonusRepository.save(bonus);

        return ApiResult.success(bonusMapper.toDto(bonus));
    }

    @Override
    public ApiResult<BonusDTO> update(BonusCrudDTO bonusCrudDTO, Long id) {

        Bonus bonus = bonusRepository.findByName(bonusCrudDTO.getName()).orElseThrow(() -> RestException.notFound("Bonus with id: ".formatted(id)));

        bonusMapper.update(bonus, bonusCrudDTO);

        bonusRepository.save(bonus);

        return ApiResult.success(bonusMapper.toDto(bonus));
    }

    @Override
    public ApiResult<List<BonusDTO>> read() {

        List<Bonus> bonusAll = bonusRepository.findAll();

        return ApiResult.success(bonusMapper.toDto(bonusAll));

    }

    @Override
    public ApiResult<BonusDTO> readOne(Long id) {

        Bonus bonus = bonusRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Bonus with id: ".formatted(id)));

        return ApiResult.success(bonusMapper.toDto(bonus));
    }

    @Override
    public ApiResult<String> delete(Long id) {

        Bonus bonus = bonusRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Bonus with id: ".formatted(id)));

        bonusRepository.delete(bonus);


        return ApiResult.success("Bonus deleted");
    }
}
