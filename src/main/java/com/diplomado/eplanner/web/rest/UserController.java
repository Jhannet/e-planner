package com.diplomado.eplanner.web.rest;

import com.diplomado.eplanner.dto.UserCreationDTO;
import com.diplomado.eplanner.dto.UserDTO;
import com.diplomado.eplanner.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> listStudents(@RequestParam(required = false, defaultValue = "false") boolean detailed) {
        if (detailed) {
            return ResponseEntity.ok().body(userService.listUsersDetailed());
        } else {
            return ResponseEntity.ok().body(userService.listUsers());
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody final UserCreationDTO dto) throws URISyntaxException {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("A new user cannot already have an id.");
        }
        dto.setCreatedAt(LocalDateTime.now());

        UserDTO userDTO = userService.save(dto);

        return ResponseEntity.created(new URI("/v1/users/" + userDTO.getId())).body(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> editCourse(@RequestBody final UserCreationDTO dto,
                                                @PathVariable final Long id) throws URISyntaxException {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Invalid user id, null value");
        }
        if (!Objects.equals(dto.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }

        return ResponseEntity
                .ok()
                .body(this.userService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
