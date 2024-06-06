package iesmb.pp3.planillaProfesores;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {


        Long countCategoria = (Long) entityManager.createQuery("SELECT COUNT(c) FROM Categoria c").getSingleResult();
        if (countCategoria == 0) {
            // Define un array de categorías
            Object[][] categorias = {
                    {"TÍTULO", 16},
                    {"FORMACIÓN POSTERIOR AL TÍTULO BASE", 6},
                    {"ANTIGÜEDAD DOCENTE", 15},
                    {"PARTICIPACIÓN EN EVENTOS ACADÉMICOS TALES COMO CONGRESOS, JORNADAS, SIMPOSIOS, ENCUENTROS CIENTIFÍCOS, ETC.", 5},
                    {"ELABORACIÓN Y DICTADO DE CURSOS, JORNADAS, TALLERES, SEMINARIOS, ETC, AFINES AL NIVEL O FUNCION PARA LA QUE SE POSTULA, CON RECONOCIMIENTO DE ORGANISMOS OFICIALES", 12},
                    {"TRABAJOS DE INVESTIGACIÓN", 6},
                    {"PUBLICACIONES VINCULADAS CON LA EDUCACIÓN O CON LA FUNCIÓN A LA QUE SE POSTULA", 12},
                    {"ASISTENCIA A CURSOS, SEMINARIOS Y TALLERES AFINES AL NIVEL SUPERIOR O FUNCION PARA LA QUE SE POSTULA, CON RECONOCIMIENTO DE ORGANISMOS OFICIALES", 8},
                    {"OTROS DESEMPEÑOS LABORALES EN EL ÁMBITO PRIVADO", 5},
                    {"CARGOS DE CONDUCCIÓN Y/O GESTIÓN EN EL NIVEL SUPERIOR", 10},
                    {"OTRAS ACTIVIDADES Y/O CARGOS PERTINENTES A LA FUNCION PARA LA QUE SE POSTULA", 10},
                    {"DESEMPEÑOS LABORALES ESPECÍFICOS RELACIONADOS AL ESPACIO CURRICULAR", 30},
                    {"COLOQUIO", 100}
            };

            for (Object[] categoria : categorias) {
                String nombre = (String) categoria[0];
                Integer puntuacionMaxima = (Integer) categoria[1];
                entityManager.createNativeQuery("INSERT INTO categoria (nombre, puntuacion_maxima) VALUES (:nombre, :puntuacionMaxima)")
                        .setParameter("nombre", nombre)
                        .setParameter("puntuacionMaxima", puntuacionMaxima)
                        .executeUpdate();
            }

            Long countActividad = (Long) entityManager.createQuery("SELECT COUNT(a) FROM Actividad a").getSingleResult();
            Object[][] actividades = {
                    {"Profesor de grado universitario y/o Profesor de nivel Superior de 4 años o más(***)", 16, 1},
                    {"Profesional universitario de 4 años o más y certificación de formación pedagógica (*) (**)", 16, 1},
                    {"Profesional de 4 años SIN certificación de formación pedagógica", 12, 1},
                    {"Técnico de Nivel Superior y Formación docente (Técnico de 3 o mas años)", 10, 1},
                    {"Técnico de Nivel Superior (Técnico de 3 o mas años)", 8, 1},
                    {"Diplomatura / Postítulo / Actualización académica", 1, 2},
                    {"Especialización", 2, 2},
                    {"Licenciatura", 3, 2},
                    {"Maestría", 4, 2},
                    {"Doctorado", 6, 2},
                    {"Total en la docencia - 0,5 pts. por año", 0.5, 3},
                    {"En el Nivel Superior - 0,7 pts. por año o fracción no menor a 6 meses", 0.7, 3},
                    {"En la Institución - 1 pts. por año o fracción no menor a 6 meses", 1, 3},
                    {"Asistente", 0.05, 4},
                    {"Asistente con evaluación", 0.1, 4},
                    {"Moderador", 0.15, 4},
                    {"Miembro de jurado para feria de ciencias o similares, olimpiadas provinciales, regionales, nacionales o internacionales", 0.1, 4},
                    {"Coordinador general", 0.3, 4},
                    {"Miembro de comité académico", 0.2, 4},
                    {"Expositor", 0.5, 4},
                    {"Asesor o tutor de grupo para feria de ciencias o similares, olimpiadas provinciales, regionales, nacionales o internacionales para el nivel superior", 0.15, 4},
                    {"Curso de 6 a 12 hs cátedras - Elaboración", 0.25, 5},
                    {"Curso de 6 a 12 hs cátedras - Dictado", 0.25, 5},
                    {"Curso de 13 a 24 hs cátedra - Elaboración", 0.5, 5},
                    {"Curso de 13 a 24 hs cátedra - Dictado", 0.5, 5},
                    {"Curso de 25 a 40 hs cátedra - Elaboración", 0.6, 5},
                    {"Curso de 25 a 40 hs cátedra - Dictado", 0.6, 5},
                    {"Curso de 41 a 60 hs cátedra - Elaboración", 0.7, 5},
                    {"Curso de 41 a 60 hs cátedra - Dictado", 0.7, 5},
                    {"Curso de 61 a 100 hs cátedra - Elaboración", 0.7, 5},
                    {"Curso de 61 a 100 hs cátedra - Dictado", 0.7, 5},
                    {"Postítulo - Elaboración", 1.2, 5},
                    {"Postítulo - Dictado", 1.2, 5},
                    {"Director o Co-director", 0.5, 6},
                    {"Investigador", 0.3, 6},
                    {"Ayudante de Investigación", 0.1, 6},
                    {"Director de tesina o trabajos finales  de carrera de grado", 0.3, 6},
                    {"Director de tesis de postgrado", 0.5, 6},
                    {"Jurado de tesis o tesina", 0.2, 6},
                    {"Libro AUTOR", 2, 7},
                    {"Libro CO-AUTOR", 1.5, 7},
                    {"Libro COMPILADOR", 1, 7},
                    {"Capítulo de Libro y/o artículo en revista especializada con referato - AUTOR", 1, 7},
                    {"Capítulo de Libro y/o artículo en revista especializada con referato - CO-AUTOR", 0.5, 7},
                    {"Cuadernillo, fascículo curricular, separata - AUTOR", 0.5, 7},
                    {"Cuadernillo, fascículo curricular, separata - CO-AUTOR", 0.3, 7},
                    {"Artículo en Revista especializada sin referato - AUTOR", 0.3, 7},
                    {"Artículo en Revista especializada sin referato - CO-AUTOR", 0.2, 7},
                    {"Otros artículos - AUTOR", 0.1, 7},
                    {"Otros artículos - CO-AUTOR", 0.05, 7},
                    {"Entre 12 y 23 hs - Sin evaluación", 0.05, 8},
                    {"Entre 12 y 23 hs - Con evaluación", 0.1, 8},
                    {"Entre 24 y 40 hs - Sin evaluación", 0.1, 8},
                    {"Entre 24 y 40 hs - Con evaluación", 0.2, 8},
                    {"Entre 41 y 60 hs - Sin evaluación", 0.15, 8},
                    {"Entre 41 y 60 hs - Con evaluación", 0.3, 8},
                    {"Entre 61 y 80 hs - Sin evaluación", 0.2, 8},
                    {"Entre 61 y 80 hs - Con evaluación", 0.4, 8},
                    {"Entre 81 y 100 hs - Sin evaluación", 0.25, 8},
                    {"Entre 81 y 100 hs - Con evaluación", 0.5, 8},
                    {"Más de 100 hs - Sin evaluación", 0.3, 8},
                    {"Más de 100 hs - Con evaluación", 0.6, 8},
                    {"Solo para Coordinadores de Carreras Técnicas", 5, 9},
                    {"Rector", 3, 10},
                    {"Vicerrector/Director de nivel/Regente", 2.5, 10},
                    {"Jefatura", 2, 10},
                    {"Coordinación", 1.5, 10},
                    {"Miembro de Consejo Directivo", 1, 10},
                    {"Miembro de equipo técnico  de organismos e instituciones oficiales", 1.5, 11},
                    {"Cargo docente en el nivel superior universitario", 1.5, 11},
                    {"Miembro de equipo evaluador en organismos e instituciones oficiales", 1.5, 11},
                    {"Miembro de Consejo Provinciales, relacionados con la educación", 1.5, 11},
                    {"Cargo de Gestión Pública (gobierno nacional, provincial, municipal) relacionados con la Educación", 1.5, 11},
                    {"Trabajo en equipos Jurisdiccionales de desarrollo curricular", 1.5, 11},
                    {"Otros cargos o desempeños relacionados con la educación (considerar becas e intercambios internacionales)", 1, 11},
                    {"Desarrollador de software independiente últimos 5 años - Presentar los proyectos realizados  - (asignar hasta 5 pts., se calcula como máximo 2 por año y proyecto)", 10, 12},
                    {"Capacitador equipos de desarrollo en empresas del medio últimos 5 años (asignar 2 pts. por año)", 10, 12},
                    {"Desarrollador de software últimos 5 años (asignar 2 pts. por año)", 10, 12},
                    {"Coloquio frente a tribunal técnico/docente", 100, 13}
            };

            Map<Integer, Integer> actividadIds = new HashMap<>();
            int actividadId = 1;
            if (countActividad == 0) {
                for (Object[] actividad : actividades) {
                    String nombre = (String) actividad[0];
                    float puntuacion = ((Number) actividad[1]).floatValue();
                    Integer categoria_id = (Integer) actividad[2];
                    entityManager.createNativeQuery("INSERT INTO actividad (nombre, puntuacion, categoria_id) VALUES (:nombre, :puntuacion, :categoria_id )")
                            .setParameter("nombre", nombre)
                            .setParameter("puntuacion", puntuacion)
                            .setParameter("categoria_id", categoria_id)
                            .executeUpdate();
                    Integer generatedId = ((Number) entityManager.createNativeQuery("SELECT LAST_INSERT_ID()").getSingleResult()).intValue();
                    actividadIds.put(actividadId++, generatedId);
                }
            }

            Long countProfesor = (Long) entityManager.createQuery("SELECT COUNT(p) FROM Profesor p").getSingleResult();
            Object[][] profesores = {
                    {"Linus", "Torvalds", "Kernel Linux", 12345612, 123356},
                    {"Richard", "Stallman", "Sistema operativo GNU", 687789, 7854},
                    {"Bill", "Gates", "Cofundador de Microsoft", 7412852, 258147},
                    {"Paul", "Allen", "Cofundador de Microsoft", 369858, 29636},
                    {"Steven", "Jobs", "Cofundador y presidente ejecutivo de Apple", 875335, 86957},
                    {"Mark", "Zuckerberg", "Cofundador de Facebook", 152477, 152475},
                    {"Elon", "Musk", "Ingeniero en jefe de SpaceX", 1378264, 648297},
                    {"James", "Gosling", "Desarrollador de Java", 9885, 66985},
                    {"Andy", "Rubin", "Creador del sistema operativo Android", 745874, 587874},
                    {"Ken", "Thompson", "Desarrollador de UNIX", 456665, 654565}
            };
            if (countProfesor == 0) {
                for (Object[] profesor : profesores) {
                    String apellido = (String) profesor[0];
                    String nombre = (String) profesor[1];
                    String direccion = (String) profesor[2];
                    Integer documento = (Integer) profesor[3];
                    Integer telefono = (Integer) profesor[4];
                    entityManager.createNativeQuery("INSERT INTO profesor (apellido, nombre, direccion, documento, telefono) VALUES (:apellido, :nombre, :direccion, :documento, :telefono)")
                            .setParameter("apellido", apellido)
                            .setParameter("nombre", nombre)
                            .setParameter("direccion", direccion)
                            .setParameter("documento", documento)
                            .setParameter("telefono", telefono)
                            .executeUpdate();
                }
            }
            int contador = 0;
            for (int i = 0; i < profesores.length; i++) {
                Integer profesor_id = i + 1;
                Map<Integer, Double> categoriaSumador = new HashMap<>();
                for (int j = 0; j < actividades.length; j++) {
                    Integer categoria_id = (Integer) actividades[j][2];
                    Double puntaje = generateRandomValue();
                    int actividad_id = actividadIds.get(j + 1);
                    entityManager.createNativeQuery("INSERT INTO puntaje_actividad (puntaje, actividad_id, profesor_id) VALUES (:puntaje, :actividad_id, :profesor_id)")
                            .setParameter("puntaje", puntaje)
                            .setParameter("actividad_id", actividad_id)
                            .setParameter("profesor_id", profesor_id)
                            .executeUpdate();
                    categoriaSumador.put(categoria_id, categoriaSumador.getOrDefault(categoria_id, 0.0) + puntaje);
                    contador++;
                }

                for (Map.Entry<Integer, Double> entry : categoriaSumador.entrySet()) {
                    Integer categoria_id = entry.getKey();
                    Double puntaje_validado = entry.getValue();
                    entityManager.createNativeQuery("INSERT INTO puntajexcategoria_validado (puntaje_validado, categoria_id, profesor_id) VALUES (:puntaje_validado, :categoria_id, :profesor_id)")
                            .setParameter("puntaje_validado", puntaje_validado)
                            .setParameter("categoria_id", categoria_id)
                            .setParameter("profesor_id", profesor_id)
                            .executeUpdate();
                }
            }
        }
    }

    private static final Random random = new Random();

    public static double generateRandomValue() {
        double value = random.nextDouble() * 0.5;
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(3, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
