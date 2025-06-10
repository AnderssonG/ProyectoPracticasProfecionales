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
@Table(name = "AsTrabajoGrado")
public class AsTrabajoGrado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAsGrado;
    @Column(name = "NombreProyecto")
    private String nombreProyecto;
    @Column(name = "NombreEstudiante")
    private String nombreEstudiante;
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
