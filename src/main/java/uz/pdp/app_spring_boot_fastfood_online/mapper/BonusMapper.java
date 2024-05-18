package uz.pdp.app_spring_boot_fastfood_online.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.app_spring_boot_fastfood_online.entity.Bonus;
import uz.pdp.app_spring_boot_fastfood_online.entity.Category;
import uz.pdp.app_spring_boot_fastfood_online.payload.BonusCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.BonusDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.CategoryCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.CategoryDTO;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BonusMapper {

    BonusDTO toDto(Bonus bonus);

    Bonus toEntity(BonusCrudDTO bonusCrudDTO);

    List<BonusDTO> toDto(List<Bonus> bonuses);

    void update(@MappingTarget Bonus bonus, BonusCrudDTO bonusCrudDTO);
}
