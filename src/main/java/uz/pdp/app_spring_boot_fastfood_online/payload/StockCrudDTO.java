package uz.pdp.app_spring_boot_fastfood_online.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.app_spring_boot_fastfood_online.entity.Stock;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link Stock}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockCrudDTO implements Serializable {
    private List<Long> productIds;
    private Double discount_amount;
    private Date beginsAt;
    private Date endsAt;
}