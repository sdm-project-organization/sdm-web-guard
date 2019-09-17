package com.mo.guard.model.entity.relation;

import com.mo.guard.constant.ActiveFlag;
import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.embedded.RelationAuthResourceId;
import com.mo.guard.model.entity.ResourceEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "G_R_AUTH_RESOURCE_TB")
@EntityListeners(value = {AuditingEntityListener.class})
@IdClass(RelationAuthResourceId.class)
@Data
@NoArgsConstructor
public class RelationAuthResource {

    @Id
    @Column(name = "auth_sq")
    public int authSequence;

     @Id
     @Column(name = "resource_sq")
     public int resourceSequence;

    @ManyToOne
    @JoinColumn(name = "resource_sq", insertable = false, updatable = false)
    public ResourceEntity resource;

    @Column(name = "disp_ord")
    public Integer displayOrder;

    @Column(name = "disp_nm")
    public String displayName;

    @Column(name = "`desc`")
    public String desc;

    @Column(name = "meta")
    public String meta;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "active_fl", nullable = false)
    public ActiveFlag activeFlag = ActiveFlag.YES;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "enable_fl", nullable = false)
    public EnableFlag enableFlag = EnableFlag.YES;

    @CreatedDate
    @Column(name = "created_dt", updatable = false)
    public LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_dt")
    public LocalDateTime updatedDate;

    @CreatedBy
    @Column(name = "writer", updatable = false)
    public String writer;

    @LastModifiedBy
    @Column(name = "editor")
    public String editor;

}
