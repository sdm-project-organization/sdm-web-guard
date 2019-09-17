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
import java.util.List;

@Entity
@Table(name = "G_USER_TB", uniqueConstraints = {
        @UniqueConstraint(
                name = "UNIQUE_OARAN_USERNAME",
                columnNames = {"organ_sq", "username"})
})
@EntityListeners(value = {AuditingEntityListener.class})
@Data
public class UserEntity {

    @Id
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE)*/
    @Column(name = "user_sq",
            columnDefinition = "user sequence (PK)")
    public int sequence;

    @Column(name = "organ_sq",
            insertable = false,
            updatable = false,
            columnDefinition = "organization sequence (FK) and (IDX01)")
    public int organSequence;

    @Column(name = "role_sq",
            insertable = false,
            updatable = false,
            columnDefinition = "role sequence (FK) and (IDX02)")
    public int roleSequence;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "G_R_USER_ROLE_TB",
            joinColumns = @JoinColumn(name = "user_sq", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_sq", nullable = false))*/
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_sq", nullable = false)
    public RoleEntity roles;

    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String password;

    @Column(name = "email")
    public String email;

    @Column(name = "name")
    public String name;

    @Column(name = "meta")
    public String meta;

    @Column(name = "disp_ord")
    public Integer displayOrder;

    @Column(name = "disp_nm")
    public String displayName;

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
