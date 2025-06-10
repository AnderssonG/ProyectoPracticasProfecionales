package com.andersson.practicase.excel_logic;

import java.io.IOException;
import java.sql.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.andersson.practicase.model.AcDocencia;
import com.andersson.practicase.model.Academica;
import com.andersson.practicase.model.AsTrabajoGrado;
import com.andersson.practicase.model.Docente;
import com.andersson.practicase.model.Extension;
import com.andersson.practicase.model.Investigacion;
import com.andersson.practicase.model.OtrasAc;
import com.andersson.practicase.model.PlanTrabajo;
import com.andersson.practicase.model.Seguimiento;
import com.andersson.practicase.model.Semestre;
import com.andersson.practicase.service.ProgramaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExcelImportService {

    private final ProgramaService programaService;

    public PlanTrabajo leerTodasLasActividades(MultipartFile archivo, Docente doc) {
        
        PlanTrabajo pt = new PlanTrabajo();
        Semestre semestre= new Semestre();

        try (Workbook workbook = new XSSFWorkbook(archivo.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            String seccionActual = null;
            
            pt.setDocente(doc);
            Row x = sheet.getRow(4);
            pt.setResolucionAcademica(getString(x.getCell(1)));

            Date fechaS = null;
            fechaS = fech(x.getCell(10));
            semestre.setFechaInicio(fechaS);
            fechaS = fech(x.getCell(12));
            semestre.setFechaFin(fechaS);

            semestre.setNumeroSemestre(getString(x.getCell(7)) + "-" + getString(x.getCell(8)));

            x = sheet.getRow(6);
            semestre.setSemanas(getFloat(x.getCell(11)));
            x = sheet.getRow(7);
            semestre.setHoras(getFloat(x.getCell(11)));

            x = sheet.getRow(87);
            pt.setObservaciones(getString(x.getCell(6)));

            for (Row row : sheet) {
                String primeraCelda = getString(row.getCell(0));
                if (primeraCelda == null)
                    continue;

                // Detección de sección
                if (primeraCelda.startsWith("2.     Actividades de Docencia")) {
                    seccionActual = "docencia";
                    continue;
                } else if (primeraCelda.startsWith("2.1")) {
                    seccionActual = "asesorias";
                    continue;
                } else if (primeraCelda.startsWith("3.")) {
                    seccionActual = "investigacion";
                    continue;
                } else if (primeraCelda.startsWith("4.")) {
                    seccionActual = "extension";
                    continue;
                } else if (primeraCelda.contains("Administración Académica") || primeraCelda.startsWith("5.")) {
                    seccionActual = "academica";
                    continue;
                } else if (primeraCelda.startsWith("6.")) {
                    seccionActual = "otras";
                    continue;
                } else if (primeraCelda.startsWith("7.")) {
                    seccionActual = "seguimiento";
                    continue;
                } else if (primeraCelda.toUpperCase().startsWith("TOTAL")) {
                    seccionActual = null;
                    continue;
                }

                // Si estamos en una sección válida y fila con datos
                if (seccionActual != null && isNumericRow(row)) {
                    switch (seccionActual) {
                        case "docencia" -> {
                            AcDocencia d = new AcDocencia();
                            d.setCodigo(getString(row.getCell(1)));
                            d.setGrupo(getString(row.getCell(2)));
                            d.setAsignatura(getString(row.getCell(3)));
                            d.setPrograma(programaService.buscarPorSiglas(getString(row.getCell(5))).orElse(null));
                            d.setEstudiantes(getInt(row.getCell(6)));
                            d.setHorasTotales(getInt(row.getCell(10)));
                            d.setSemanas(getInt(row.getCell(11)));
                            d.setSemestral(getInt(row.getCell(12)));
                            
                            if(getInt(row.getCell(4)) == 1){
                                d.setBloque(true);
                            } else {
                                d.setBloque(false);
                            }

                            pt.getDocencias().add(d);
                        }
                        case "asesorias" -> {
                            AsTrabajoGrado a = new AsTrabajoGrado();
                            a.setNombreProyecto(getString(row.getCell(1)));
                            a.setNombreEstudiante(getString(row.getCell(3)));
                            a.setPrograma(programaService.buscarPorSiglas(getString(row.getCell(5))).orElse(null));
                            a.setHorasTotales(getInt(row.getCell(10)));
                            a.setSemanas(getInt(row.getCell(11)));
                            a.setSemestral(getInt(row.getCell(12)));
                            pt.getTrabajosGrado().add(a);
                        }
                        case "investigacion" -> {
                            Investigacion i = new Investigacion();
                            i.setActividad(getString(row.getCell(1)));
                            i.setDescripcion(getString(row.getCell(3)));
                            i.setResponsabilidad(getString(row.getCell(6)));
                            i.setHorasSemanales(getInt(row.getCell(8)));
                            i.setEntregable(getString(row.getCell(9)));
                            i.setSemanas(getInt(row.getCell(11)));
                            i.setHorasSemestre(getInt(row.getCell(12)));
                            pt.getInvestigaciones().add(i);
                        }
                        case "extension" -> {
                            Extension e = new Extension();
                            e.setActividad(getString(row.getCell(1)));
                            e.setDescripcion(getString(row.getCell(3)));
                            e.setResponsabilidad(getString(row.getCell(7)));
                            e.setHorasSemanales(getInt(row.getCell(8)));
                            e.setPrograma(programaService.buscarPorSiglas(getString(row.getCell(9))).orElse(null));
                            e.setEntregable(getString(row.getCell(10)));
                            e.setSemanas(getInt(row.getCell(11)));
                            e.setHorasSemestre(getInt(row.getCell(12)));
                            pt.getExtensiones().add(e);
                        }
                        case "academica" -> {
                            Academica ac = new Academica();
                            ac.setActividad(getString(row.getCell(1)));
                            ac.setDescripcion(getString(row.getCell(3)));
                            ac.setHorasSemanales(getInt(row.getCell(8)));
                            ac.setPrograma(programaService.buscarPorSiglas(getString(row.getCell(9))).orElse(null));
                            ac.setEntregable(getString(row.getCell(10)));
                            ac.setSemanas(getInt(row.getCell(11)));
                            ac.setHorasSemestre(getInt(row.getCell(12)));
                            pt.getAcademicas().add(ac);
                        }
                        case "otras" -> {
                            OtrasAc o = new OtrasAc();
                            o.setActividad(getString(row.getCell(1)));
                            o.setDescripcion(getString(row.getCell(3)));
                            o.setHorasSemanales(getInt(row.getCell(8)));
                            o.setPrograma(programaService.buscarPorSiglas(getString(row.getCell(9))).orElse(null));
                            o.setEntregable(getString(row.getCell(10)));
                            o.setSemanas(getInt(row.getCell(11)));
                            o.setHorasSemestre(getInt(row.getCell(12)));
                            pt.getOtrasActividades().add(o);
                        }
                        case "seguimiento" -> {
                            Seguimiento s = new Seguimiento();
                            s.setDescripcion(getString(row.getCell(1)));
                            Date fechaSql = null;

                            if (getInt(row.getCell(6)) != 0) {
                                fechaSql = fech(row.getCell(6));
                            } else if (getInt(row.getCell(9)) != 0) {
                                fechaSql = fech(row.getCell(9));
                            } else if (getInt(row.getCell(12)) != 0) {
                                fechaSql = fech(row.getCell(12));
                            }
                            
                            s.setFecha(fechaSql);
                            pt.getSeguimientos().add(s);
                        }

                    }

                }

            }
            
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo Excel: " + e.getMessage(), e);
        }

        pt.setSemestre(semestre);
        pt.vincularActividadesHijas();
        pt.getSumaActividades();
        return pt;
    }

    private String getString(Cell cell) {
        if (cell == null)
            return null;
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }

    private Integer getInt(Cell cell) {
        if (cell == null)
            return 0;
        try {
            return (int) cell.getNumericCellValue();
        } catch (Exception e) {
            try {
                return Integer.parseInt(cell.getStringCellValue().trim());
            } catch (Exception ex) {
                return 0;
            }
        }
    }

    private float getFloat(Cell cell) {
        if (cell == null)
            return 0;
        try {
            return (float) cell.getNumericCellValue();
        } catch (Exception e) {
            try {
                return Integer.parseInt(cell.getStringCellValue().trim());
            } catch (Exception ex) {
                return 0;
            }
        }
    }

    private boolean isNumericRow(Row row) {
        try {
            Cell cell = row.getCell(0);
            if (cell == null)
                return false;
            cell.setCellType(CellType.STRING);
            String val = cell.getStringCellValue().trim();
            return val.matches("^\\d+(\\.\\d+)?$");
        } catch (Exception e) {
            return false;
        }
    }

    private java.sql.Date fech(Cell cellFecha) {
        if (cellFecha != null) {
            try {
                if (cellFecha.getCellType() == CellType.NUMERIC) {
                    if (DateUtil.isCellDateFormatted(cellFecha)) {
                        java.util.Date fecha = cellFecha.getDateCellValue();
                        return new java.sql.Date(fecha.getTime());
                    }
                } else if (cellFecha.getCellType() == CellType.STRING) {
                    String fechaStr = cellFecha.getStringCellValue().trim();
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM/dd/yyyy");
                    sdf.setLenient(false); // Validación estricta
                    java.util.Date fecha = sdf.parse(fechaStr);
                    return new java.sql.Date(fecha.getTime());
                }
            } catch (Exception e) {
                System.out.println("Error procesando la fecha: " + e.getMessage());
            }
        }
        // Siempre retornar algo
        return null;
    }
}
