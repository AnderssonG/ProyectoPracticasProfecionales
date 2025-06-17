package com.andersson.practicase.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.andersson.practicase.excel_logic.ExcelImportService;
import com.andersson.practicase.model.Docente;
import com.andersson.practicase.model.Facultad;
import com.andersson.practicase.model.PlanTrabajo;
import com.andersson.practicase.model.Programa;
import com.andersson.practicase.model.Usuario;
import com.andersson.practicase.repository.DocenteRepository;
import com.andersson.practicase.repository.FacultadRepository;
import com.andersson.practicase.repository.PlanTrabajoRepository;
import com.andersson.practicase.repository.ProgramaRepository;
import com.andersson.practicase.repository.UsuarioRepository;
import com.andersson.practicase.service.PlanTrabajoService;
import com.andersson.practicase.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UsuarioService usuarioService;
    private final ExcelImportService excelService;
    private final ProgramaRepository programaRepository;
    private final PlanTrabajoService planTrabajoService;
    private final DocenteRepository docenteRepository;
    private final PlanTrabajoRepository planTrabajoRepository;
    private final FacultadRepository facultadRepository;
    private final UsuarioRepository usuarioRepository;

    /*------------------------------------Inicio y registro ---------------------------------------------------- */
    @GetMapping("/index")
    public String getIndex(Model model, Authentication auth) {
        Usuario usuario = auth.getPrincipal() instanceof Usuario ? (Usuario) auth.getPrincipal() : null;
        model.addAttribute("usuario", usuario);
        return "index";
    }

    @GetMapping("/regis")
    public String getRegis(Model model, Authentication auth) {
        Usuario user = new Usuario();
        user.setDocente(new Docente());
        model.addAttribute("usuario", user);
        model.addAttribute("programas", programaRepository.findAll());
        return "regis";
    }

    @PostMapping("/regis")
    public String postRegis(@ModelAttribute("usuario") Usuario usuario) {
        docenteRepository.save(usuario.getDocente());

        usuarioService.save(usuario);
        return "login";

    }

    @GetMapping("/login")
    public String getlogin(Model model, Authentication auth) {
        return "login";
    }

    // Página de inicio
    @GetMapping("/")
    public String index() {
        return "login";
    }

    /*------------------------------------ importar Plan trabajo ---------------------------------------------------- */
    @PostMapping("/importar")
    public String importarExcel(@RequestParam("archivoExcel") MultipartFile archivo, Authentication auth, Model model) {
        Usuario usuario = auth.getPrincipal() instanceof Usuario ? (Usuario) auth.getPrincipal() : null;

        PlanTrabajo planTrabajo = excelService.leerTodasLasActividades(archivo, usuario.getDocente());
        planTrabajoService.guardar(planTrabajo);
        return "redirect:/index";
    }

    @GetMapping("/importar")
    public String getImport(Model model, Authentication auth) {
        Usuario usuario = auth.getPrincipal() instanceof Usuario ? (Usuario) auth.getPrincipal() : null;
        model.addAttribute("usuario", usuario);

        return "importar";
    }

    /*------------------------------------ visualizar Plan trabajo ---------------------------------------------------- */

    @GetMapping("/ultimoPlanTrabajo")
    public String verPlanTrabajo(Model model, Authentication auth) {

        Usuario usuario = auth.getPrincipal() instanceof Usuario ? (Usuario) auth.getPrincipal() : null;
        model.addAttribute("usuario", usuario);
        Docente docente = usuario.getDocente();
        PlanTrabajo ultimoPlan = planTrabajoRepository.findTopByDocenteOrderBySemestreFechaFinDesc(docente);
        model.addAttribute("planTrabajo", ultimoPlan);

        return "ultimoPlan";
    }

    @GetMapping("/listadoP")
    public String verListadoPlanTrabajo(Model model, Authentication auth) {

        Usuario usuario = auth.getPrincipal() instanceof Usuario ? (Usuario) auth.getPrincipal() : null;
        model.addAttribute("usuario", usuario);
        Docente docente = usuario.getDocente();
        List<PlanTrabajo> planes = planTrabajoService.buscarPorDocente(docente.getId());
        Collections.reverse(planes); // Invertir el orden para mostrar el más reciente primero
        model.addAttribute("planes", planes);

        return "listadoPlanes";
    }

    @GetMapping("/PlanTrabajoS/{id}")
    public String verPlanTrabajoS(Model model, Authentication auth, @PathVariable("id") Integer id) {

        Usuario usuario = auth.getPrincipal() instanceof Usuario ? (Usuario) auth.getPrincipal() : null;
        model.addAttribute("usuario", usuario);
        Optional<PlanTrabajo> ultimoPlan = planTrabajoRepository.findById(id);
        System.out.println("Ultimo plan: " + ultimoPlan.get());

        model.addAttribute("planTrabajo", ultimoPlan.get());

        return "ultimoPlan";
    }

    @PostMapping("/eliminarP/{id}")
    public String eliminarPlan(Authentication auth, Model model, @PathVariable("id") Integer id) {
        Usuario usuario = auth.getPrincipal() instanceof Usuario ? (Usuario) auth.getPrincipal() : null;
        model.addAttribute("usuario", usuario);
        planTrabajoService.eliminar(id);
        return "redirect:/listadoP";
    }

    /*------------------------------------ Vistas de admin ---------------------------------------------------- */

    @GetMapping("/ultimoPlanTrabajoDoc/{id}")
    public String verPlanTrabajoDoc(Model model, Authentication auth, @PathVariable("id") Integer id) {

        Usuario usuario = auth.getPrincipal() instanceof Usuario ? (Usuario) auth.getPrincipal() : null;
        model.addAttribute("usuario", usuario);

        PlanTrabajo ultimoPlan = planTrabajoRepository
                .findTopByDocenteOrderBySemestreFechaFinDesc(docenteRepository.findById(id).orElse(null));
        model.addAttribute("planTrabajo", ultimoPlan);

        return "ultimoPlan";
    }

    @GetMapping("/docentes")
    public String verDocentes(Model model, Authentication auth) {
        Usuario usuario = auth.getPrincipal() instanceof Usuario ? (Usuario) auth.getPrincipal() : null;
        model.addAttribute("usuario", usuario);
        List<Usuario> docentes = usuarioRepository.findAll();

        model.addAttribute("docentes", docentes);
        return "listadoDo";
    }

    @GetMapping("/listadoP/{id}")
    public String verListadoPlanTrabajoDoc(Model model, Authentication auth, @PathVariable("id") Integer id) {

        Usuario usuario = auth.getPrincipal() instanceof Usuario ? (Usuario) auth.getPrincipal() : null;
        model.addAttribute("usuario", usuario);
        Docente docente = docenteRepository.findById(id).orElse(null);
        List<PlanTrabajo> planes = planTrabajoService.buscarPorDocente(docente.getId());
        Collections.reverse(planes); // Invertir el orden para mostrar el más reciente primero
        model.addAttribute("planes", planes);

        return "listadoPlanes";
    }

    // Mostrar formulario para crear una nueva facultad
    @GetMapping("/nuevaFacultad")
    public String mostrarFormularioFacultad(Model model, Authentication auth) {
        model.addAttribute("facultad", new Facultad());
        Usuario usuario = auth.getPrincipal() instanceof Usuario ? (Usuario) auth.getPrincipal() : null;
        model.addAttribute("usuario", usuario);
        return "CreateFacultad"; // nombre del HTML con el formulario
    }

    // Procesar el formulario de creación de facultad
    @PostMapping("/guardarFacultad")
    public String guardarFacultad(@ModelAttribute Facultad facultad) {
        facultadRepository.save(facultad);
        return "redirect:/index";
    }

    // Mostrar formulario para crear un nuevo programa
    @GetMapping("/nuevoPrograma")
    public String mostrarFormularioPrograma(Model model, Authentication auth) {
        Usuario usuario = auth.getPrincipal() instanceof Usuario ? (Usuario) auth.getPrincipal() : null;
        model.addAttribute("usuario", usuario);
        model.addAttribute("programa", new Programa());
        model.addAttribute("facultades", facultadRepository.findAll()); // para el <select>
        return "CreatePrograma"; // nombre del HTML con el formulario
    }

    // Procesar el formulario de creación de programa
    @PostMapping("/guardarPrograma")
    public String guardarPrograma(@ModelAttribute Programa programa,
            @RequestParam("facultad.idFacultad") Integer idFacultad) {

        programa.setFacultad(facultadRepository.findById(idFacultad).orElse(null));
        programaRepository.save(programa);

        return "redirect:/index";
    }

    @PostMapping("/cambiarRol")
    public String cambiarRolUsuario(@RequestParam("usuarioId") Integer usuarioId,
            @RequestParam("nuevoRol") boolean nuevoRol) {

        Optional<Usuario> optUsuario = usuarioRepository.findByDocente(docenteRepository.findById(usuarioId).get());
        if (optUsuario.isPresent()) {
            Usuario usuario = optUsuario.get();
            usuario.setRol(nuevoRol);
            usuarioRepository.save(usuario);
        }
        return "redirect:/docentes"; // Ajusta según la ruta a tu listado
    }

}