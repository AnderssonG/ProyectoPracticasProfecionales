package com.andersson.practicase.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PlanTrabajo")
public class PlanTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlantrabajo;
    private String resolucionAcademica;

    @Column(name = "HorasTotales")
    private Short horasTotales ;
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "IdDocente")
    private Docente docente;

    @ManyToOne
    @JoinColumn(name = "IdSemestre")
    private Semestre semestre;

    @OneToMany(mappedBy = "planTrabajo", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<AcDocencia> docencias= new ArrayList<>();

    @OneToMany(mappedBy = "planTrabajo", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<AsTrabajoGrado> trabajosGrado= new ArrayList<>();

    @OneToMany(mappedBy = "planTrabajo", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Investigacion> investigaciones= new ArrayList<>();

    @OneToMany(mappedBy = "planTrabajo", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Extension> extensiones= new ArrayList<>();

    @OneToMany(mappedBy = "planTrabajo", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Academica> academicas= new ArrayList<>();

    @OneToMany(mappedBy = "planTrabajo", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<OtrasAc> otrasActividades= new ArrayList<>();

    @OneToMany(mappedBy = "planTrabajo", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Seguimiento> seguimientos = new ArrayList<>();



    public void getSumaActividades(){
        int totalHoras = 0;
        if (docencias != null) {
            for (AcDocencia d : docencias) {
                totalHoras += d.getSemestral();
            }
        }
        if (trabajosGrado != null) {
            for (AsTrabajoGrado t : trabajosGrado) {
                totalHoras += t.getSemestral();
            }
        }
        if (investigaciones != null) {
            for (Investigacion i : investigaciones) {
                totalHoras += i.getHorasSemestre();
            }
        }
        if (extensiones != null) {
            for (Extension e : extensiones) {
                totalHoras += e.getHorasSemestre();
            }
        }
        if (academicas != null) {
            for (Academica a : academicas) {
                totalHoras += a.getHorasSemestre();
            }
        }
        if (otrasActividades != null) {
            for (OtrasAc o : otrasActividades) {
                totalHoras += o.getHorasSemestre();
            }
        }
        this.horasTotales= (short) totalHoras;
    }

    public void vincularActividadesHijas() {
        if (docencias != null) {
            for (AcDocencia d : docencias) {
                d.setPlanTrabajo(this);
            }
        }
        if (trabajosGrado != null) {
            for (AsTrabajoGrado t : trabajosGrado) {
                t.setPlanTrabajo(this);
            }
        }
        if (investigaciones != null) {
            for (Investigacion i : investigaciones) {
                i.setPlanTrabajo(this);
            }
        }
        if (extensiones != null) {
            for (Extension e : extensiones) {
                e.setPlanTrabajo(this);
            }
        }
        if (academicas != null) {
            for (Academica a : academicas) {
                a.setPlanTrabajo(this);
            }
        }
        if (otrasActividades != null) {
            for (OtrasAc o : otrasActividades) {
                o.setPlanTrabajo(this);
            }
        }
        if (seguimientos != null) {
            for (Seguimiento s : seguimientos) {
                s.setPlanTrabajo(this);
            }
        }
    }

}
