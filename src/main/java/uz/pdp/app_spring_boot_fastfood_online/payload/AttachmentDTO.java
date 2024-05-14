package uz.pdp.app_spring_boot_fastfood_online.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttachmentDTO implements Serializable {

    private Long id;
    private String submittedName;
    private String name;
    private String contentType;
    private Long size;
    private String path;

}
