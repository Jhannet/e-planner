package com.diplomado.eplanner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDetailDTO {
    private Long id;
    @NotBlank
    @Size( min = 2, max = 100)
    private String firstName;
    @NotBlank
    @Size( min = 2, max = 100)
    private String lastName;
    private Integer age;
    private LocalDate birthDay;
}
