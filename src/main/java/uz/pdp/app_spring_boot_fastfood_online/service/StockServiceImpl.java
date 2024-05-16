package uz.pdp.app_spring_boot_fastfood_online.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
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

import java.util.ArrayList;
import java.util.Date;
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

        Stock save = stockRepository.save(stock);

        return ApiResult.success(stockMapper.toDto(save));
    }

    @Override
    public ApiResult<String> delete(Long id) {

        stockRepository.deleteById(id);

        return ApiResult.success("Stock is deleted ");
    }


    public void setProductStockPrice( Stock stock) {

        List<Product> updatedProducts = new ArrayList<>();

        List<Product> products = stock.getProducts();

        for (Product product : products) {
            product.setPriceWithStock(
                    product.getPrice() - stock.getDiscount_amount()
            );
            updatedProducts.add(product);
        }

        productRepository.saveAll(updatedProducts);

    }

    public void removeProductStockPrice( Stock stock) {

        List<Product> updatedProducts = new ArrayList<>();

        List<Product> products = stock.getProducts();

        for (Product product : products) {
            product.setPriceWithStock(null);
            updatedProducts.add(product);
        }

        productRepository.saveAll(updatedProducts);
    }

    public List<Stock> findActiveStocks(Date now) {

        return stockRepository.findAllActiveStocks(now);
    }

    // This method runs every hour
    @Scheduled(fixedRate = 360000)
    public void checkAndApplyDiscounts() {
        Date now = new Date();

        List<Stock> activeStocks = findActiveStocks(now);

        for (Stock stock : activeStocks) {
            if (now.after(stock.getBeginsAt()) && now.before(stock.getEndsAt())) {
                setProductStockPrice(stock);
            } else if (now.after(stock.getEndsAt())) {
                removeProductStockPrice(stock);
            }
        }
    }

}
