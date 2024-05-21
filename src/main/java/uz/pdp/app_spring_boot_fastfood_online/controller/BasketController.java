package uz.pdp.app_spring_boot_fastfood_online.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.BasketProductCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.BasketProductDTO;
import uz.pdp.app_spring_boot_fastfood_online.service.BasketProductService;
import uz.pdp.app_spring_boot_fastfood_online.utils.AppConstant;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstant.BASE_PATH_V1+"/basket")
public class BasketController {

    private final BasketProductService basketProductService;

    @PostMapping("/add-to-basket")
    public ApiResult<BasketProductDTO> addToBasket(@RequestBody BasketProductCrudDTO crudDTO) {

        log.info("Request to basket-management/add-to-basket params: {}", crudDTO);
       return basketProductService.create(crudDTO);
    }

    @GetMapping("/show-basket/{userId}")
    public ApiResult<List<BasketProductDTO>> showBasket(@PathVariable Long userId) {

        log.info("Request to basket-management/show-basket params: {}", userId);
        return basketProductService.getByBasketId(userId) ;
    }

    @PutMapping("/change-quantity/{userId}")
    public ApiResult<BasketProductDTO> updateBasketProduct( @RequestBody BasketProductCrudDTO crudDTO) {

        log.info("Request to basket-management/update-basket-product: {}", crudDTO);
        return basketProductService.update(crudDTO);
    }

    @DeleteMapping("/clean-basket/{userId}")
    public ApiResult<String> cleanBasket(@PathVariable Long userId) {

        log.info("Request to basket-management/clean-basket");
        return basketProductService.deleteAll(userId);
    }

}
