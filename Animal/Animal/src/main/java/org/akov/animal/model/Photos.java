package org.akov.animal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "photos")
@Data
public class Photos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom; // nom de la photo
    private String lien; // lien de la photo dans le dossier upload
}
