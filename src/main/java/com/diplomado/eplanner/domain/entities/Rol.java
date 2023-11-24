package com.diplomado.eplanner.domain.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
public class Rol implements Serializable {
    private static final long serialVersionUID = 8799656478674714001L;

    @Id
    @SequenceGenerator(name = "role_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence")
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    private List<UserRol> users;

    public Rol() {
    }

    public Rol(String name) {
        this.name = name;
    }
    public Rol(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserRol> getUsers() {
        return users;
    }

    public void setUsers(List<UserRol> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
