package com.example.tdbssinexttel.model;

import com.example.tdbssinexttel.utils.enums.Etat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Direction extends AbstractEntity implements Serializable {
    @Column(unique = true)
    private String nom;

    private String description;

    private boolean status;

    @Enumerated(EnumType.STRING)
    private Etat etat;

    public Direction(String nom) {
        this.nom = nom;
    }

    private String libellé;




}
