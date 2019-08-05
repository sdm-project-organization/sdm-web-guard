package com.mo.guard.model.table;

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
@Table(name = "G_USER_TB")
@EntityListeners(value = {AuditingEntityListener.class})
@Data
public class User {

    @Id
    @Column(name = "user_sq")
    public int sequence;

    @ManyToMany
    @JoinTable(
            name = "G_R_USER_ROLE_TB",
            joinColumns = @JoinColumn(name = "user_sq"),
            inverseJoinColumns = @JoinColumn(name = "role_sq"))
    public List<Role> roles;

    /*@ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "G_R_USER_ROLE_TB", joinColumns = @JoinColumn(name = "user_sq"))
    @Column(name="disp_nm")
    public List<String> roles;*/

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
