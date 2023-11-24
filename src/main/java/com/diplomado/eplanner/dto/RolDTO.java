package com.diplomado.eplanner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RolDTO {
    private Integer id;
    @NotBlank
    @Size( min = 2, max = 100)
    private String name;
}
