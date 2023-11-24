package com.diplomado.eplanner.web.rest;

import com.diplomado.eplanner.dto.RolDTO;
import com.diplomado.eplanner.dto.UserDTO;
import com.diplomado.eplanner.services.RolService;
import com.diplomado.eplanner.services.UserRolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/roles")
public class RolController {
    private final RolService rolService;
    private UserRolService userRolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @PostMapping
    public ResponseEntity<RolDTO> create(@RequestBody @Valid final RolDTO dto) throws URISyntaxException {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("A new rol cannot already have an id.");
        }

        RolDTO rolDTO = this.rolService.save(dto);

        return ResponseEntity
                .created(new URI("/v1/roles/" + rolDTO.getId()))
                .body(rolDTO);
    }

    @GetMapping
    public ResponseEntity<List<RolDTO>> listAllRoles() {
        return ResponseEntity.ok().body(rolService.listRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> getRolById(@PathVariable final Integer id) {
        return ResponseEntity.ok().body(rolService.getRolById(id).orElseThrow(IllegalArgumentException::new));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> editRol(@RequestBody @Valid final RolDTO dto,
                                                @PathVariable final Integer id) throws URISyntaxException {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Invalid rol id, null value");
        }
        if (!Objects.equals(dto.getId(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }

        return ResponseEntity
                .ok()
                .body(this.rolService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        rolService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<UserDTO>> listAllUsersByRol(@PathVariable final Integer id) {
        return ResponseEntity.ok().body(userRolService.listUsersByRol(id));
    }

    @Autowired
    public void setUserRolService(UserRolService userRolService){
        this.userRolService = userRolService;
    }
}
