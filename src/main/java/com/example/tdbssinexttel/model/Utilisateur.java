package com.example.tdbssinexttel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Utilisateur extends AbstractEntity implements Serializable {


    private String email;

    private String password;

    private Boolean status;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "compte_roles",
            joinColumns = @JoinColumn(name = "utiliateur_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
