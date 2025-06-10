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
@Table(name = "Investigacion")
public class Investigacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInvestigacion;
    
    private String actividad;
    private String descripcion;
    private String responsabilidad;
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
}

