package com.example.tdbssinexttel.model;

import com.example.tdbssinexttel.utils.enums.EtatUtilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Utilisateur extends AbstractEntity implements Serializable {


    @NotBlank
    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String email;

    private String password;

    private Boolean status;

    private String photos;

    @Column(name = "verification_code", unique = true, length = 64)
    private String verificationCode;

    @Column(name = "reset_password_token", unique = true)
    private String resetPasswordToken;

    @Enumerated(EnumType.STRING)
    private EtatUtilisateur etatUtilisateur;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "compte_roles",
            joinColumns = @JoinColumn(name = "utiliateur_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    public void addRole(Role role) {
        this.roles.add(role);
    }

    //Pour dire que cet attribut ne mappe avec rien de la base de donneés

    private transient String y;

    public void setY(String y) {
        this.y = y;
    }

    public String getY() {
        if (id == null || photos == null) return "/images/Nexttel-Cameroon-Logo.png";

        return "/user-photos/" + this.id + "/" + this.photos;
    }

    @Transient
    public String getPhotosImagePath() {
        if (id == null || photos == null) return "/images/Nexttel-Cameroon-Logo.png";

        return "/user-photos/" + this.id + "/" + this.photos;
    }
}
