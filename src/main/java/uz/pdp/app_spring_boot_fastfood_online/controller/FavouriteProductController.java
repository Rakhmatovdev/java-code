package uz.pdp.app_spring_boot_fastfood_online.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.FavouriteProductCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.FavouriteProductDTO;
import uz.pdp.app_spring_boot_fastfood_online.service.FavouriteProductService;
import uz.pdp.app_spring_boot_fastfood_online.utils.AppConstant;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstant.BASE_PATH_V1+"/favourite-product")
public class FavouriteProductController {

    private final FavouriteProductService favouriteProductService;

    @PostMapping
    public ApiResult<FavouriteProductDTO> create(@RequestBody FavouriteProductCrudDTO crudDTO) {

        log.info("Request to FavouriteProductController create; params: {}", crudDTO);
        return favouriteProductService.create(crudDTO);
    }

    @GetMapping
    public ApiResult<List<FavouriteProductDTO>> readAll() {

        log.info("Request to FavouriteProductController readAll");
        return favouriteProductService.read();
    }

    @GetMapping("{id}")
    public ApiResult<FavouriteProductDTO> readOne(@PathVariable Long id){

        log.info("Request to FavouriteProductController readOne; params: {}", id);
        return favouriteProductService.readById(id);
    }


    @PutMapping("{id}")
    public ApiResult<FavouriteProductDTO> update(@RequestBody FavouriteProductCrudDTO crudDTO, @PathVariable Long id) {

        log.info("Request to FavouriteProductController update; params: {}, {}", id, crudDTO);

        return favouriteProductService.update(id, crudDTO);
    }


    @DeleteMapping("{id}")
    public ApiResult<String> delete(@PathVariable Long id) {

        log.info("Request to FavouriteProductController delete; params: {}", id);
        return favouriteProductService.delete(id);
    }


    @DeleteMapping
    public ApiResult<String> deleteAll(){

        log.info("Request to FavouriteProductController deleteAll");
        return favouriteProductService.deleteAll();
    }


}
