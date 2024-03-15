package org.akov.animal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "animal")
@Data
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private Date dateNaissance;
    @Column(columnDefinition = "text")
    private String presentation;

    @ManyToOne
    private Users users;

    @OneToMany
    private List<Photos> photos;
}
