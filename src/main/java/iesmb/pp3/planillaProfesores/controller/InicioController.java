package iesmb.pp3.planillaProfesores.controller;

import iesmb.pp3.planillaProfesores.entity.*;
import iesmb.pp3.planillaProfesores.service.ICategoriaService;
import iesmb.pp3.planillaProfesores.service.IProfesorService;
import iesmb.pp3.planillaProfesores.service.IPuntajeXCategoriaValidadoService;
import iesmb.pp3.planillaProfesores.service.jpa.PuntajeActividadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping("")
public class InicioController {

    @Autowired
    IProfesorService profesorService;
    @Autowired
    ICategoriaService categoriaService;

    @Autowired
    PuntajeActividadServiceImpl puntajeActividadService;
    @Autowired
    IPuntajeXCategoriaValidadoService puntajeXCategoriaValidadoService;

    @GetMapping("")
    public String cargarInicio(ModelMap model) {
        List<Profesor> profesores = profesorService.getAll();
        List<ProfesorTotalDePuntos> listaProfesoresTotalDePuntos = new ArrayList<>();
        DecimalFormat formato = new DecimalFormat("#,##0.###");
        
        //ITERADOR aaaaaa
        for (Profesor profesor : profesores) {
            ProfesorTotalDePuntos profesorTotalDePuntos = new ProfesorTotalDePuntos();

            // Obtener el total de puntos sin formatear
            double totalPuntos = puntajeXCategoriaValidadoService.obtenerTotalPuntosPorProfesor(profesor);

            // Formatear el total de puntos a tres decimales y reemplazar la coma por un punto
            String totalPuntosFormateado = formato.format(totalPuntos).replace(",", ".");

            profesorTotalDePuntos.setTotalAcumulado(Double.parseDouble(totalPuntosFormateado));
            profesorTotalDePuntos.setProfesor(profesor);

            listaProfesoresTotalDePuntos.add(profesorTotalDePuntos);
        }

        model.addAttribute("profesores", listaProfesoresTotalDePuntos);
        return "index";
    }


    @GetMapping("/profesor")
    public String mostrar_profesor(@RequestParam Integer id, ModelMap model) {
        Profesor profe = profesorService.getById(id);
        double total = puntajeXCategoriaValidadoService.obtenerTotalPuntosPorProfesor(profe);
        DecimalFormat formato = new DecimalFormat("#,##0.###");
        String totalPorCategoriaStr = (total != 0) ? formato.format(total) : "0";
        model.addAttribute("total_puntos", totalPorCategoriaStr);
        model.addAttribute("profesor", profe);
        return "profesor";
    }

    @PostMapping("/borrar/{profesorId}")
    public String borrarxdni(@PathVariable Integer profesorId, ModelMap model) {
        profesorService.delete(profesorId);
        return cargarInicio(model);
    }

    @GetMapping("/datos_profesor/{profesorId}")
    public String nuevo_profe(@PathVariable Integer profesorId, ModelMap model) {
        Profesor profe = profesorService.getById(profesorId);
        model.addAttribute("profesor", profe);
        return "datos_personales";
    }

    @PostMapping("/profesor")
    public String profesor (@RequestParam(name = "id", required = false)  Integer id,
                              @RequestParam(name = "dni", required = false)  String dni,
                              ModelMap model) {
        Profesor profe = new Profesor() ;
        if (id != null){
            profe = profesorService.getById(id);
        }
        if (dni != null){
            profe = profesorService.getByDni(dni);
        }
        if(profe != null){
            model.addAttribute("id", profe.getId());
            return mostrar_profesor(profe.getId(), model);
        }else {
            profe = new Profesor();
            model.addAttribute("profesor", profe);
            return "datos_personales";
        }
    }

    @PostMapping("/guardar_profe")
    public String guardar_profe(@RequestParam String nombre,
                                @RequestParam String apellido,
                                @RequestParam String direccion,
                                @RequestParam String telefono,
                                @RequestParam String documento,
                                @RequestParam(name = "id", required = false)  Integer id,
                                ModelMap model) {
        Profesor newProfe;
        if (id == null || id == 0){
            newProfe = new Profesor();
            if (profesorService.getByDni(documento) != null) {
                String mensaje = "El DNI ya existe en la base de datos, puede buscar en el inicio por DNI";
                newProfe.setNombre(nombre);
                newProfe.setApellido(apellido);
                newProfe.setDocumento(documento);
                newProfe.setDireccion(direccion);
                newProfe.setTelefono(telefono);
                model.addAttribute("profesor", newProfe);
                model.addAttribute("error", mensaje);
                return "datos_personales";
            }
        }else{
            newProfe = profesorService.getById(id);
        }
        newProfe.setNombre(nombre);
        newProfe.setApellido(apellido);
        newProfe.setDireccion(direccion);
        newProfe.setTelefono(telefono);
        newProfe.setDocumento(documento);
        profesorService.save(newProfe);
        return mostrar_profesor(newProfe.getId(), model);
    }

    @GetMapping("/categorias_t/{profesorId}" )
    public String listarCategorias(@PathVariable Integer profesorId, ModelMap model) {
        List<CategoriaConTotal> categoriaConTotal = new ArrayList<>();
        Profesor profesor = profesorService.getById(profesorId);
        List<Categoria> categorias = categoriaService.getAll();

        for (Categoria categoria : categorias) {
            PuntajeXCategoriaValidado puntajeXCategoriaValidado = puntajeXCategoriaValidadoService.getByProfesorAndCategoria(profesor, categoria);
            double totalPorCategoria = (puntajeXCategoriaValidado != null) ? puntajeXCategoriaValidado.getPuntajeValidado() : 0;

            DecimalFormat formato = new DecimalFormat("#,##0.###");
            String totalPorCategoriaStr = (totalPorCategoria != 0) ? formato.format(totalPorCategoria) : "0";

            CategoriaConTotal categoriaConTotalItem = new CategoriaConTotal();
            categoriaConTotalItem.setCategoria(categoria);
            categoriaConTotalItem.setTotalPorCategoria(totalPorCategoriaStr);
            categoriaConTotal.add(categoriaConTotalItem);
        }
        double total = puntajeXCategoriaValidadoService.obtenerTotalPuntosPorProfesor(profesor);
        DecimalFormat formato = new DecimalFormat("#,##0.###");
        String totalFormateado = formato.format(total);

        model.addAttribute("total_puntos", totalFormateado);
        model.addAttribute("profesor", profesor);
        model.addAttribute("lCategorias", categoriaConTotal);
        model.addAttribute("profesorId", profesorId);
        return "categorias";
    }
    @PostMapping("/categorias_p/{profesorId}")
    public String cargarNotaPorCategoria(@PathVariable Integer profesorId,
                                         @RequestParam(name = "categoriasSeleccionadas", required = false) List<String> categoriasSeleccionadas,
                                         @RequestParam(name = "strCategoriasSeleccionadas", required = false) String strCategoriasSeleccionadas,
                                         ModelMap model) {
        Profesor profesor = profesorService.getById(profesorId);
        String profesor_nombre = profesor.getNombre();
        String strNameCategoria = "";

        if (categoriasSeleccionadas == null || categoriasSeleccionadas.isEmpty()) {
            categoriasSeleccionadas = Arrays.asList(strCategoriasSeleccionadas.split(",\\s*"));
        }
        if (categoriasSeleccionadas != null && !categoriasSeleccionadas.isEmpty()) {
            String categoriaId = categoriasSeleccionadas.get(0).trim(); // Obtén el primer elemento
            Categoria categoria = categoriaService.getById(Integer.valueOf(categoriaId));
            strNameCategoria = categoria.getNombre();
            // Obtener actividades y puntajes
            List<Actividad> actividades = categoria.getActividades();
            List<PuntajeActividad> puntajes = puntajeActividadService.obtenerPuntajesPorProfesorYCategoria(profesor, categoria);
            // Combinar las listas
            List<ActividadConPuntaje> actividadesConPuntajes = new ArrayList<>();

            for (int i = 0; i < actividades.size(); i++) {
                ActividadConPuntaje actividadConPuntaje = new ActividadConPuntaje();
                actividadConPuntaje.setActividad(actividades.get(i));
                if (i < puntajes.size()) {
                    actividadConPuntaje.setPuntajeActividad(puntajes.get(i));
                }else{
                    PuntajeActividad puntajeDefault = new PuntajeActividad();
                    puntajeDefault.setPuntaje(0);
                    actividadConPuntaje.setPuntajeActividad(puntajeDefault);
                }
                actividadesConPuntajes.add(actividadConPuntaje);
            }
            strCategoriasSeleccionadas = String.join(", ", categoriasSeleccionadas);

            model.addAttribute("profesor", profesor);
            model.addAttribute("categoriaId", categoriaId);
            model.addAttribute("categoria", categoria);
            model.addAttribute("nameCategoria", strNameCategoria);
            model.addAttribute("profesorId", profesorId);
            model.addAttribute("profesor_nombre", profesor_nombre);
            model.addAttribute("actividadesConPuntajes", actividadesConPuntajes);
            model.addAttribute("strCategoriasSeleccionadas", strCategoriasSeleccionadas);
            model.addAttribute("categoriasSeleccionadas", categoriasSeleccionadas);
            return "categoria";
        }
        return "error";
    }

    @PostMapping("/categoria_t/{profesorId}")
    public String iterarCategoria(@PathVariable Integer profesorId,
                                  @RequestParam(name = "strCategoriasSeleccionadas") String strCategoriasSeleccionadas,
                                  @RequestParam(name = "asignados") List<String> asignados,
                                  @RequestParam(name = "totalValidado") String totalValidado, ModelMap model) {


        int idCategoria = Integer.parseInt(strCategoriasSeleccionadas.split(",")[0].trim());
        Profesor profesor = profesorService.getById(profesorId);
        Categoria categoria = categoriaService.getById(idCategoria);

        // Verificar si ya existe un registro para esa combinación de profesor y categoría
        PuntajeXCategoriaValidado existingRecord = puntajeXCategoriaValidadoService.getByProfesorAndCategoria(profesor, categoria);

        if (existingRecord != null) {
            // Actualizar el registro existente
            existingRecord.setPuntajeValidado(Double.parseDouble(totalValidado));
            puntajeXCategoriaValidadoService.save(existingRecord);
        } else {
            // Crear un nuevo registro
            PuntajeXCategoriaValidado nuevoRegistro = new PuntajeXCategoriaValidado();
            nuevoRegistro.setPuntajeValidado(Double.parseDouble(totalValidado));
            nuevoRegistro.setCategoria(categoria);
            nuevoRegistro.setProfesor(profesor);
            puntajeXCategoriaValidadoService.save(nuevoRegistro);
        }
        // Obtener las actividades de la categoría seleccionada
        List<Actividad> actividades = categoria.getActividades();

        // Obtener o inicializar los puntajesActividad del profesor para esta categoría
        List<PuntajeActividad> puntajesActividad = puntajeActividadService.obtenerPuntajesActividad(profesor, actividades);

        // Iterar sobre los puntajes y asignar valores
        double sumador = 0;
        for (int i = 0; i < asignados.size(); i++) {
            PuntajeActividad puntajeActividad = puntajeActividadService.obtenerPuntajeActividad(puntajesActividad, actividades.get(i));
            puntajeActividad.setPuntaje((asignados.get(i)).isEmpty() ? 0 : Double.parseDouble(asignados.get(i).trim()));
            puntajeActividad.setProfesor(profesor);
            puntajeActividad.setActividad(actividades.get(i));
            sumador = sumador + puntajeActividad.getPuntaje();
            puntajeActividadService.save(puntajeActividad);
        }
        String respaldo = "";
        Categoria cateRespaldo;
        Integer idRespaldo;
        // esto es para obteenr los datos que recien se adjudicaron
        // => generar la lista de categorias por iterar
        List<String> nombres = new ArrayList<>();
        if (strCategoriasSeleccionadas.split(",").length > 1) {
            for (int i = 1; i < strCategoriasSeleccionadas.split(",").length; i++) {
                idRespaldo = Integer.parseInt(strCategoriasSeleccionadas.split(",")[i].trim());
                cateRespaldo = categoriaService.getById(idRespaldo);

                // obtener los nombres de las categorias que faltan por modificar
                nombres.add(cateRespaldo.getNombre());
                respaldo += strCategoriasSeleccionadas.split(",")[i] + ", ";
            }
            strCategoriasSeleccionadas = respaldo;
        } else {
            model.addAttribute("id", profesor.getId());
            return mostrar_profesor(profesor.getId(), model);
        }
        model.addAttribute("model", model);
        model.addAttribute("porModificar", nombres);
        model.addAttribute("profesor", profesor);
        model.addAttribute("profesorId", profesorId);
        model.addAttribute("strCategoriasSeleccionadas", strCategoriasSeleccionadas);
        return "continuar";
    }
    
    @GetMapping("/error")
    public String redirigirError(ModelMap model) {
        return "error";
    }
}
