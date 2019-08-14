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
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "G_AUTH_TB")
@EntityListeners(value = {AuditingEntityListener.class})
@Data
public class AuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Throw to MySQL
    @Column(name = "auth_sq")
    public int sequence;

    /*@OneToMany
    @JoinColumn(name = "auth_sq")
    @Where(clause = "enable_fl = 1")
    public List<RelationAuthResource> relationResources;*/

    @ManyToMany(fetch = FetchType.LAZY) // 단방향
    @JoinTable(
            name = "G_R_AUTH_RESOURCE_TB",
            joinColumns = @JoinColumn(name = "auth_sq"),
            inverseJoinColumns = @JoinColumn(name = "resource_sq"))
    public List<ResourceEntity> resources = new ArrayList<>();

    @Column(name = "disp_ord")
    public Integer displayOrder;

    @Column(name = "disp_nm")
    public String displayName;

    @Column(name = "`desc`")
    public String desc;

    @Column(name = "active_fl", nullable = false)
    public Byte activeFlag = ActiveFlag.Y.getValue();

    @Column(name = "enable_fl", nullable = false)
    public Byte enableFlag = EnableFlag.Y.getValue();

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