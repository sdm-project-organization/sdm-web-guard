package com.mo.guard.model.table.relation;

import com.mo.guard.constant.ActiveFlag;
import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.embedded.RelationAuthResourceId;
import com.mo.guard.model.table.Auth;
import com.mo.guard.model.table.Resource;
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
    public Resource resource;

    @Column(name = "disp_ord")
    public Integer displayOrder;

    @Column(name = "disp_nm")
    public String displayName;

    @Column(name = "`desc`")
    public String desc;

    @Column(name = "meta")
    public String meta;

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
