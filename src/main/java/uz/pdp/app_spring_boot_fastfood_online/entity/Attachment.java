package uz.pdp.app_spring_boot_fastfood_online.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import uz.pdp.app_spring_boot_fastfood_online.entity.templates.AbsLongEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Attachment extends AbsLongEntity {

    @Column(columnDefinition = "varchar(500)")
    private String submittedName;

    @Column(columnDefinition = "varchar(500)")
    private String name;

    private String contentType;

    private Long size;

    @Column(columnDefinition = "varchar(500)")
    private String path;


}
