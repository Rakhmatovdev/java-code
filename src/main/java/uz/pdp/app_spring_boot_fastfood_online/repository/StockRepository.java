package uz.pdp.app_spring_boot_fastfood_online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.app_spring_boot_fastfood_online.entity.Stock;

import java.util.Date;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT s FROM Stock s WHERE :now BETWEEN s.beginsAt AND s.endsAt")
    List<Stock> findAllActiveStocks(@Param("now") Date now);

}