package uz.pdp.app_spring_boot_fastfood_online.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.app_spring_boot_fastfood_online.entity.Product;
import uz.pdp.app_spring_boot_fastfood_online.entity.Stock;
import uz.pdp.app_spring_boot_fastfood_online.exception.RestException;
import uz.pdp.app_spring_boot_fastfood_online.mapper.StockMapper;
import uz.pdp.app_spring_boot_fastfood_online.payload.ApiResult;
import uz.pdp.app_spring_boot_fastfood_online.payload.StockCrudDTO;
import uz.pdp.app_spring_boot_fastfood_online.payload.StockDTO;
import uz.pdp.app_spring_boot_fastfood_online.repository.ProductRepository;
import uz.pdp.app_spring_boot_fastfood_online.repository.StockRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    private final StockMapper stockMapper;
    private final ProductRepository productRepository;

    @Override
    public ApiResult<StockDTO> create(StockCrudDTO crudDTO) {

        Stock entity = stockMapper.toEntity(crudDTO);

        List<Product> products = productRepository.findAllById(crudDTO.getProductIds());

        entity.setProducts(products);

        Stock save = stockRepository.save(entity);

        return ApiResult.success(stockMapper.toDto(save));
    }

    @Override
    public ApiResult<StockDTO> readById(Long id) {

        Stock entity = stockRepository.findById(id)
                .orElseThrow(()-> RestException.notFound("Stock with id: "+id));

        return ApiResult.success(stockMapper.toDto(entity));
    }

    @Override
    public ApiResult<List<StockDTO>> read() {

        List<Stock> stocks = stockRepository.findAll();

        return ApiResult.success(stockMapper.toDto(stocks));
    }

    @Override
    public ApiResult<StockDTO> update(Long id, StockCrudDTO crudDTO) {

        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> RestException.notFound("Stock with id: " + id));

        stockMapper.update(stock,crudDTO);

        List<Product> products = productRepository.findAllById(crudDTO.getProductIds());

        stock.setProducts(products);

        return ApiResult.success(stockMapper.toDto(stock));
    }

    @Override
    public ApiResult<String> delete(Long id) {

        stockRepository.deleteById(id);

        return ApiResult.success("Stock is deleted ");
    }
}
