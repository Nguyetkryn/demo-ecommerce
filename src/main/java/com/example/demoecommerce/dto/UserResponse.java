package com.example.demoecommerce.dto;

import com.example.demoecommerce.dto.baseDto.AbstractDTO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponse extends AbstractDTO {
    private String id;
    private String fullName;
    private String accessToken;
    private List<String> role;


}
