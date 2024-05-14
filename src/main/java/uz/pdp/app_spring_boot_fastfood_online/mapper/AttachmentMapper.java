package uz.pdp.app_spring_boot_fastfood_online.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import uz.pdp.app_spring_boot_fastfood_online.entity.Attachment;
import uz.pdp.app_spring_boot_fastfood_online.payload.AttachmentDTO;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AttachmentMapper {

    AttachmentDTO toDTO(Attachment attachment);


}