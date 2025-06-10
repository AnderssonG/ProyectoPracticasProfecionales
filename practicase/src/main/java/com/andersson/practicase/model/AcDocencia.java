package com.andersson.practicase.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AcDocencia")
public class AcDocencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAcDocencia;

    private String codigo;
    private String grupo;
    private String asignatura;
    private Boolean bloque;
    private Integer estudiantes;
    @Column(name = "HorasTotales")
    private Integer horasTotales;
    private Integer semanas;
    private Integer semestral;

    @ManyToOne
    @JoinColumn(name = "IdPlanTrabajo")
    @ToString.Exclude
    private PlanTrabajo planTrabajo;

    @ManyToOne
    @JoinColumn(name = "IdPrograma")
    @ToString.Exclude
    private Programa programa;
}
