package com.example.demoecommerce.dto;

import com.example.demoecommerce.dto.baseDto.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO extends AbstractDTO {
    private String categoryId;
    private String categoryName;
    private List<String> products;
}
