package com.example.demoecommerce.dto;

import com.example.demoecommerce.dto.baseDto.AbstractDTO;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class LoginDTO extends AbstractDTO {
    private String username;
    private String password;
}
