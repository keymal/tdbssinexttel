package com.example.tdbssinexttel.model;

import com.example.tdbssinexttel.utils.enums.EtatDepartement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Departement extends AbstractEntity implements Serializable {

    private String nom;

    private String description;

    private EtatDepartement etatDepartement;


}
