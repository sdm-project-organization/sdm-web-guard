package com.mo.guard.model.entity;

import com.mo.guard.constant.ActiveFlag;
import com.mo.guard.constant.EnableFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Table(name = "G_APP_TB")
@EntityListeners(value = {AuditingEntityListener.class})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppEntity {

    public AppEntity(Integer organSequence,
                     Integer displayOrder,
                     String displayName,
                     String desc) {
        this.organSequence = organSequence;
        this.displayOrder = displayOrder;
        this.displayName = displayName;
        this.desc = desc;
    }

    public AppEntity(Integer organSequence,
                     Integer displayOrder,
                     String displayName,
                     String desc,
                     ActiveFlag activeFlag,
                     EnableFlag enableFlag) {
        this.organSequence = organSequence;
        this.displayOrder = displayOrder;
        this.displayName = displayName;
        this.desc = desc;
        this.activeFlag = activeFlag;
        this.enableFlag = enableFlag;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Throw to MySQL
    @Column(name = "app_sq")
    public Integer sequence;

    @Column(name = "organ_sq",
            insertable = false,
            updatable = false,
            nullable = false)
    public Integer organSequence;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organ_sq")
    public OrganEntity organ;*/

    @Column(name = "disp_ord")
    public Integer displayOrder = -1;

    @Column(name = "disp_nm")
    public String displayName = "";

    @Column(name = "`desc`")
    public String desc = "";

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

    public static AppEntity getSample() {
        return new AppEntity(
                1,
                1,
                "guard",
                "description...");
    }

}