package com.example.demoecommerce.dto;

import com.example.demoecommerce.dto.baseDto.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDTO extends AbstractDTO {
    private String discountId;
    private String discountName;
    private String description;
    private Double discount;
    private Date startTime;
    private Date endTime;
    private List<String> products;
}
