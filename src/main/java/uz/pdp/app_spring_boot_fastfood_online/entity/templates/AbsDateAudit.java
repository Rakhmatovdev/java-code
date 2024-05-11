package uz.pdp.app_spring_boot_fastfood_online.entity.templates;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class AbsDateAudit extends AbsUserAudit {

    @CreatedDate
    @Column(updatable = false)
    private Timestamp createdAt;

    @LastModifiedDate
    private Timestamp updatedAt;



}
