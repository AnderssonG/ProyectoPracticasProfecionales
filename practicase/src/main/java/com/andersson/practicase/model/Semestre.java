package com.andersson.practicase.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Semestre")
public class Semestre {
    
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NombreSemestre")
    private String numeroSemestre;
    @Column(name = "FechaInicio")
    private Date fechaInicio;
    @Column(name = "FechaFin")
    private Date fechaFin;
    private Float semanas;
    private Float horas;
}
