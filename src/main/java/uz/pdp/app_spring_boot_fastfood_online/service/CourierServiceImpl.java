package uz.pdp.app_spring_boot_fastfood_online.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.app_spring_boot_fastfood_online.entity.Courier;
import uz.pdp.app_spring_boot_fastfood_online.exception.RestException;
import uz.pdp.app_spring_boot_fastfood_online.mapper.CourierMapper;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.CourierCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.CourierDTO;
import uz.pdp.app_spring_boot_fastfood_online.repository.CourierRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService{

    private final CourierRepository courierRepository;

    private final CourierMapper courierMapper;


    @Override
    public ApiResult<CourierDTO> create(CourierCrudDTO crudDTO) {
        Optional<Courier> courierDB = courierRepository.findByEmail(crudDTO.getEmail());
        if (courierDB.isPresent())
            throw RestException.alreadyExist("User");

        Courier courier = courierMapper.toEntity(crudDTO);
        courierRepository.save(courier);

        return ApiResult.success(courierMapper.toDTO(courier));
    }

    @Override
    public ApiResult<CourierDTO> update(Long id, CourierCrudDTO crudDTO) {

        Optional<Courier> courierDB = courierRepository.findById(id);
        if (courierDB.isEmpty())
            throw  RestException.notFound("Id user");

        Courier courier = courierDB.get();
        courierMapper.update(courier, crudDTO);
        courierRepository.save(courier);

        return ApiResult.success(courierMapper.toDTO(courier));
    }

    @Override
    public ApiResult<List<CourierDTO>> read() {

        List<Courier> couriers = courierRepository.findAll();
        List<CourierDTO> courierDTOS = couriers.stream()
                .map(courierMapper::toDTO)
                .collect(Collectors.toList());

        return ApiResult.success(courierDTOS);
    }

    @Override
    public ApiResult<String> delete(Long id) {

        Courier courier = courierRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Courier with id " + id));

        courierRepository.delete(courier);
        return ApiResult.success("Courier is deleted");

    }

    @Override
    public ApiResult<CourierDTO> readOne(Long id) {
        Courier courier = courierRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Courier with id " + id));

       return ApiResult.success(courierMapper.toDTO(courier));

    }
}
