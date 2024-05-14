package uz.pdp.app_spring_boot_fastfood_online.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.app_spring_boot_fastfood_online.enums.RegionEnum;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilialDTO {

    private Integer id;

    private String name;

    private String location;

    private String workingTime;

    private String contactNumber;

    private RegionEnum regions;

}
