package com.diplomado.eplanner.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRolDTO {
    private Integer id;
    @NotNull
    private Boolean active;
    @NotNull
    private LocalDateTime createdAt;
    @NotNull
    private UserDTO user;
    @NotNull
    private RolDTO rol;
}
