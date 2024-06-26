package uz.pdp.app_spring_boot_fastfood_online.payload;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.app_spring_boot_fastfood_online.enums.RegionEnum;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilialCrudDTO {

    @NotNull
    private String name;

    private String location;

    private String workingTime;

    @NotNull
    private String contactNumber;

    private RegionEnum regions;

}
