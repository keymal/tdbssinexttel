package com.example.tdbssinexttel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role extends AbstractEntity implements Serializable {

    @NotBlank
    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String nom;
    private String description;

    @Override
    public String toString() {
        return
                '['+nom+
                ']';
    }
}

