package uz.pdp.app_spring_boot_fastfood_online.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.app_spring_boot_fastfood_online.entity.Filial;
import uz.pdp.app_spring_boot_fastfood_online.exception.RestException;
import uz.pdp.app_spring_boot_fastfood_online.mapper.FilialMapper;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.FilialCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.FilialDTO;
import uz.pdp.app_spring_boot_fastfood_online.repository.FilialRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilialServiceImpl implements FilialService {

    private final FilialRepository filialRepository;

    private final FilialMapper filialMapper;

    @Override
    public ApiResult<FilialDTO> create(FilialCrudDTO crudDTO) {

        Optional<Filial> filialDB = filialRepository.findByContactNumber(crudDTO.getContactNumber());
        if (filialDB.isPresent())
            throw RestException.alreadyExist("Filial with contact number " + crudDTO.getContactNumber());

        Filial filial = filialMapper.toEntity(crudDTO);
        filialRepository.save(filial);

        return ApiResult.success(filialMapper.toDTO(filial));
    }

    @Override
    public ApiResult<FilialDTO> edit(Long id, FilialCrudDTO crudDTO) {

        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Filial with id " + id));

        filialMapper.updateEntity(filial, crudDTO);
        filialRepository.save(filial);

        return ApiResult.success(filialMapper.toDTO(filial));
    }

    @Override
    public ApiResult<List<FilialDTO>> read() {
        List<Filial> filials = filialRepository.findAll();
        List<FilialDTO> filialsDTO = filials.stream()
                .map(filial -> filialMapper.toDTO(filial))
                .collect(Collectors.toList());

        return ApiResult.success(filialsDTO);
    }

    @Override
    public ApiResult<FilialDTO> readOne(Long id) {

        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Filial with id " + id));

        FilialDTO filialDTO = filialMapper.toDTO(filial);

        return ApiResult.success(filialDTO);
    }

    @Override
    public ApiResult<String> delete(Long id) {

        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Filial with id " + id));

        filialRepository.delete(filial);

        return ApiResult.success("Filial deleted");
    }
}
