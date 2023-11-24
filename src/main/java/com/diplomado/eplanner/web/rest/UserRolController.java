package com.diplomado.eplanner.web.rest;

import com.diplomado.eplanner.dto.UserRolDTO;
import com.diplomado.eplanner.services.UserRolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/users/{userId}/roles")
public class UserRolController {
    private final UserRolService userRolService;

    public UserRolController(UserRolService userRolService) {
        this.userRolService = userRolService;
    }

    @PostMapping
    public ResponseEntity<UserRolDTO> create(@RequestBody final UserRolDTO dto) throws URISyntaxException {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("A new user rol cannot already have an id.");
        }
        dto.setCreatedAt(LocalDateTime.now());

        UserRolDTO userRolDTO = userRolService.save(dto);

        return ResponseEntity
                .created(new URI("/v1/users/" + userRolDTO.getUser().getId() + "/roles/" + userRolDTO.getId()))
                .body(userRolDTO);
    }

    @PatchMapping("/{roleId}")
    public ResponseEntity<Void> logicalDeleteRolForUser(@PathVariable Long userId, @PathVariable Integer roleId) {
        userRolService.logicalDelete(userId, roleId);
        return ResponseEntity.noContent().build();
    }
}
