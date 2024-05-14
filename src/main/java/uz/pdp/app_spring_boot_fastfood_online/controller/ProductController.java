package uz.pdp.app_spring_boot_fastfood_online.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.ProductCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.ProductDTO;
import uz.pdp.app_spring_boot_fastfood_online.service.ProductService;
import uz.pdp.app_spring_boot_fastfood_online.utils.AppConstant;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstant.BASE_PATH_V1+"/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResult<ProductDTO> create(@RequestBody ProductCrudDTO crudDTO) {

        log.info("Request to ProductController create; params: {}", crudDTO);
        return productService.create(crudDTO);
    }


    @GetMapping
    public ApiResult<List<ProductDTO>> readAll() {

        log.info("Request to ProductController readAll");
        return productService.read();
    }


    @GetMapping("{id}")
    public ApiResult<ProductDTO> readOne(@PathVariable Long id){

        log.info("Request to ProductController readOne; params: {}", id);
        return productService.readById(id);
    }

    @PutMapping("{id}")
    public ApiResult<ProductDTO> update(@RequestBody ProductCrudDTO crudDTO, @PathVariable Long id) {

        log.info("Request to ProductController update; params: {}, {}", id, crudDTO);

        return productService.update(id, crudDTO);
    }

    @DeleteMapping("{id}")
    public ApiResult<String> delete(@PathVariable Long id) {

        log.info("Request to ProductController delete; params: {}", id);
        return productService.delete(id);
    }


}
