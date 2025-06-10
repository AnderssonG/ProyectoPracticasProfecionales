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
@Table(name = "Academica")
public class Academica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAcademica;

    private String actividad;
    private String descripcion;
    @Column(name = "HorasSemanales")
    private Integer horasSemanales;
    private String entregable;
    private Integer semanas;
    @Column(name = "HorasSemestre")
    private Integer horasSemestre;
    

    @ManyToOne
    @JoinColumn(name = "IdPlanTrabajo")
    @ToString.Exclude
    private PlanTrabajo planTrabajo;

    @ManyToOne
    @JoinColumn(name = "IdPrograma")
    @ToString.Exclude
    private Programa programa;
}

