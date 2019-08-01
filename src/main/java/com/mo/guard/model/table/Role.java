package com.mo.guard.model.table;

import com.mo.guard.constant.ActiveFlag;
import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.table.relation.RelationAuthResource;
import com.mo.guard.model.table.relation.RelationRoleAuth;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "G_ROLE_TB")
@EntityListeners(value = {AuditingEntityListener.class})
@Data
public class Role {

    @Id
    @Column(name = "role_sq")
    public int sequence;

    /*@OneToMany
    @JoinColumn(name = "role_sq")
    @Where(clause = "enable_fl = 1")
    public List<RelationRoleAuth> relationAuths;*/

    @ManyToMany
    @JoinTable(
            name = "G_R_ROLE_AUTH_TB",
            joinColumns = @JoinColumn(name = "role_sq"),
            inverseJoinColumns = @JoinColumn(name = "auth_sq"))
    public List<Auth> auths;

    @Column(name = "expired_period")
    public int expiredPeriod;

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
