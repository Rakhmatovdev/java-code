package uz.pdp.app_spring_boot_fastfood_online.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_spring_boot_fastfood_online.payload.CategoryCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.CategoryDTO;
import uz.pdp.app_spring_boot_fastfood_online.service.CategoryService;
import uz.pdp.app_spring_boot_fastfood_online.utils.AppConstant;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstant.BASE_PATH_V1+"/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @PostMapping
    public ApiResult<CategoryDTO> create(@RequestBody CategoryCrudDTO crudDTO) {

        log.info("Request to CategoryController create; params: {}", crudDTO);
        return categoryService.create(crudDTO);
    }

    @GetMapping
    public ApiResult<List<CategoryDTO>> readAll() {

        log.info("Request to CategoryController readAll");
        return categoryService.read();
    }

    @GetMapping("{id}")
    public ApiResult<CategoryDTO> readOne(@PathVariable Long id){

        log.info("Request to CategoryController readOne; params: {}", id);
        return categoryService.readById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @PutMapping("{id}")
    public ApiResult<CategoryDTO> update(@RequestBody CategoryCrudDTO crudDTO, @PathVariable Long id) {

        log.info("Request to CategoryController update; params: {}, {}", id, crudDTO);

        return categoryService.update(id, crudDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @DeleteMapping("{id}")
    public ApiResult<String> delete(@PathVariable Long id) {

        log.info("Request to CategoryController delete; params: {}", id);
        return categoryService.delete(id);
    }


}
