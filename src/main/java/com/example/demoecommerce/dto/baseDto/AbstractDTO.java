package com.example.demoecommerce.dto.baseDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbstractDTO {
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private Boolean activeFlag = Boolean.TRUE;
    private Boolean deleteFlag = Boolean.FALSE;
}
