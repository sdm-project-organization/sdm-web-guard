package com.mo.guard.model.entity;

import com.mo.guard.constant.ActiveFlag;
import com.mo.guard.constant.EnableFlag;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "G_RESOURCE_TB", uniqueConstraints = {
        @UniqueConstraint(
                name = "UNIQUE_APP_RESOURCE",
                columnNames = {"app_sq", "http_method", "http_path"})
})
@EntityListeners(value = {AuditingEntityListener.class})
@Data
public class ResourceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Throw to MySQL
    @Column(name = "resource_sq")
    public int sequence;

    @Column(name = "app_sq")
    public int appSequence;

    @Column(name= "http_method")
    public String httpMethod;

    @Column(name= "http_path")
    public String httpPath;

    @Column(name = "disp_ord")
    public Integer displayOrder;

    @Column(name = "disp_nm")
    public String displayName;

    @Column(name = "meta")
    public String meta;

    @Column(name = "`desc`")
    public String desc;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "active_fl", nullable = false)
    public ActiveFlag activeFlag = ActiveFlag.YES;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "enable_fl", nullable = false)
    public EnableFlag enableFlag = EnableFlag.YES;

    @CreatedDate
    @Column(name = "created_dt", nullable = false, updatable = false)
    public LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_dt")
    public LocalDateTime updatedDate;

    @CreatedBy
    @Column(name = "writer", nullable = false, updatable = false)
    public String writer;

    @LastModifiedBy
    @Column(name = "editor")
    public String editor;

}