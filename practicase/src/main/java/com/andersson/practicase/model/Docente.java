package com.andersson.practicase.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Docente")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombres;
    private String apellidos;
    private String escalafon;

    @ManyToOne
    @JoinColumn(name = "IdPrograma")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Programa programa;

    @OneToOne(mappedBy = "docente")
    @ToString.Exclude
    private Usuario usuario;

    @OneToMany(mappedBy = "docente", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<PlanTrabajo> planes;
}

