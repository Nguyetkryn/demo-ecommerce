package com.example.demoecommerce.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntity implements Serializable {
    @Column(name = "createdBy")
    private String createdBy;
    @Column(name = "createdDate")
    private Date createdDate;
    @Column(name = "modifiedBy")
    private String modifiedBy;
    @Column(name = "modifiedDate")
    private Date modifiedDate;
    @Column(name = "activeFlag")
    private Boolean activeFlag = Boolean.TRUE;
    @Column(name = "deleteFlag")
    private Boolean deleteFlag = Boolean.FALSE;

}
