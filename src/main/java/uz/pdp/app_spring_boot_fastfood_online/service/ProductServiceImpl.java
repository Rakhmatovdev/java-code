package uz.pdp.app_spring_boot_fastfood_online.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.app_spring_boot_fastfood_online.entity.Attachment;
import uz.pdp.app_spring_boot_fastfood_online.entity.Category;
import uz.pdp.app_spring_boot_fastfood_online.entity.Product;
import uz.pdp.app_spring_boot_fastfood_online.entity.Stock;
import uz.pdp.app_spring_boot_fastfood_online.exception.RestException;
import uz.pdp.app_spring_boot_fastfood_online.mapper.ProductMapper;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.ProductCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.ProductDTO;
import uz.pdp.app_spring_boot_fastfood_online.repository.AttachmentRepository;
import uz.pdp.app_spring_boot_fastfood_online.repository.CategoryRepository;
import uz.pdp.app_spring_boot_fastfood_online.repository.ProductRepository;
import uz.pdp.app_spring_boot_fastfood_online.repository.StockRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;
    private final AttachmentRepository attachmentRepository;
    private final CategoryRepository categoryRepository;
    private final StockRepository stockRepository;

    @Override
    public ApiResult<ProductDTO> create(ProductCrudDTO crudDTO) {

        Product entity = productMapper.toEntity(crudDTO);

        Attachment attachment = attachmentRepository.findById(crudDTO.getAttachmentId())
                .orElseThrow(() -> RestException.notFound("Attachment with id " + crudDTO.getAttachmentId()));

        Category category = categoryRepository.findById(crudDTO.getCategoryId())
                .orElseThrow(() -> RestException.notFound("Category with id " + crudDTO.getCategoryId()));

        entity.setCategory(category);
        entity.setAttachment(attachment);
        entity.setPriceWithStock(null);

        Product save = productRepository.save(entity);

        return ApiResult.success(productMapper.toDto(save));
    }

    @Override
    public ApiResult<ProductDTO> readById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Product with id " + id));

        return ApiResult.success(productMapper.toDto(product));
    }

    @Override
    public ApiResult<List<ProductDTO>> read() {
        return ApiResult.success(productMapper.toDto(productRepository.findAll()));
    }

    @Override
    public ApiResult<ProductDTO> update(Long id, ProductCrudDTO crudDTO) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Product with id " + id));

        productMapper.update(product,crudDTO);

        return ApiResult.success(productMapper.toDto(product));
    }

    @Override
    public ApiResult<String> delete(Long id) {

        productRepository.deleteById(id);
        return ApiResult.success("Product is deleted ");
    }

    @Override
    public ApiResult<List<ProductDTO>> readAllProductsWithStock() {

        Date now = new Date();

        List<Stock> allActiveStocks = stockRepository.findAllActiveStocks(now);

        if(allActiveStocks.isEmpty()){
            throw RestException.restThrow("There is no any active stocks right now");
        }

        List<Product> productsWithStuck = productRepository.findAllByPriceWithStockIsNotNull();

        return ApiResult.success(productMapper.toDto(productsWithStuck));
    }
}
