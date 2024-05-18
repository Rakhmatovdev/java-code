package uz.pdp.app_spring_boot_fastfood_online.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.app_spring_boot_fastfood_online.entity.Basket;
import uz.pdp.app_spring_boot_fastfood_online.entity.User;
import uz.pdp.app_spring_boot_fastfood_online.exception.RestException;
import uz.pdp.app_spring_boot_fastfood_online.mapper.BasketMapper;
import uz.pdp.app_spring_boot_fastfood_online.mapper.FilialMapper;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.BasketCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.BasketDTO;
import uz.pdp.app_spring_boot_fastfood_online.repository.BasketRepository;
import uz.pdp.app_spring_boot_fastfood_online.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    private final BasketMapper basketMapper;


    @Override
    public ApiResult<BasketDTO> create(User user) {

        Basket basket = new Basket();

        basket.setIsClosed(false);

        basket.setUser(user);

        basketRepository.save(basket);

        return ApiResult.success(basketMapper.toDto(basket));
    }

    @Override
    public ApiResult<BasketDTO> update(BasketCrudDTO crudDTO) {

        Optional<Basket> basketDB = basketRepository.findByUserIdAndIsClosedFalse(crudDTO.getUserId());
        if (basketDB.isEmpty()) {
            throw RestException.restThrow("You don't have an open basket!");
        }
        Basket basket = basketDB.get();
        basketMapper.update(basket, crudDTO);
        basketRepository.save(basket);

        return ApiResult.success(basketMapper.toDto(basket));
    }

    @Override
    public ApiResult<String> delete(Long id) {
        Basket basket = basketRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Basket with id: " + id));
        basketRepository.delete(basket);

        return ApiResult.success("Basket deleted");
    }

    @Override
    public ApiResult<List<BasketDTO>> read() {
        List<Basket> baskets = basketRepository.findAll();
        List<BasketDTO> basketDTOS = basketMapper.toDto(baskets);

        return ApiResult.success(basketDTOS);
    }

    @Override
    public ApiResult<BasketDTO> readOne(Long id) {

        Basket basket = basketRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Basket with id: " + id));

        return ApiResult.success(basketMapper.toDto(basket));
    }


}
