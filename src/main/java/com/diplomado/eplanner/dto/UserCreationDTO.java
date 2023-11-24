package com.diplomado.eplanner.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserCreationDTO {
    private Long id;
    @NotBlank
    @Size( min = 2, max = 150)
    private String username;
    @NotBlank
    @Size(max = 150)
    private String password;
    @NotBlank
    @Email
    @Size(max = 150)
    private String email;
    private LocalDateTime createdAt;

    private UserDetailDTO userDetail;
}
