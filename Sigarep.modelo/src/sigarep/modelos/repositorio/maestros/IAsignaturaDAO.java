package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.transacciones.EstudianteSancionado;

/**
 * Repositorio Asignatura-IAsignaturaDAO 
 * 
 * @author BUILDER
 * @version 1.0
 * @since 12/12/2013
 */

public interface IAsignaturaDAO extends JpaRepository<Asignatura, String> {

	/**
	 * Busca las todas las asignaturas que poseen estatus == true
	 * @return List<Asignatura> Lista de asignaturas con estatus == true
	 */
	public List<Asignatura> findByEstatusTrue();

	/**
	 * Busca asignaturas por programa que poseen estatus == true
	 * @param programa
	 *            identificador único de Programa académico
	 * @return List<Asignatura> Lista de asignaturas por programa con estatus ==
	 *         true
	 */
	public List<Asignatura> findByProgramaAcademicoAndEstatusTrue(
			ProgramaAcademico programa);

	/**
	 * Busca una Asignatura por su nombre 
	 * @param nombreAsignatura
	 *            Nombre de la asignatura que se pretende encontrar
	 * @return Asignatura encontrada por su nombre
	 */
	public Asignatura findByNombreAsignatura(String nombreAsignatura);
	
	/**
	 * Busca una Asignatura por su nombre y ProgramaAcademico
	 * @param nombreAsignatura
	 *            Nombre de la asignatura que se pretende encontrar y Programa Academico del estudiante
	 * @return Asignatura encontrada por su nombre y programa academico
	 */
	public Asignatura findByNombreAsignaturaAndProgramaAcademico(String nombreAsignatura, ProgramaAcademico programaAcademico);
	
	/**
	 * Busca las asignaturas que NO posee un estudiante sancionado especifico con sanción RR
	 * @param estudianteSancionado EstudianteSancionado al cual se le buscaran las asignaturas que no posee
	 * @return  List<Asignatura> Lista de asignaruras que NO posee el estudiante sancionado con RR
	 */
	
	@Query("SELECT DISTINCT a FROM AsignaturaEstudianteSancionado AS aesa, EstudianteSancionado AS esa, Asignatura AS a WHERE a.programaAcademico = esa.estudiante.programaAcademico AND esa.estudiante.programaAcademico = :programaAcademico AND esa = :estudianteSancionado AND a.codigoAsignatura NOT IN (SELECT DISTINCT a.codigoAsignatura FROM AsignaturaEstudianteSancionado AS aesa, EstudianteSancionado AS esa, Asignatura AS a WHERE esa = aesa.estudianteSancionado AND a.programaAcademico = esa.estudiante.programaAcademico AND esa.estudiante.programaAcademico = :programaAcademico AND aesa.asignatura = a AND esa = :estudianteSancionado)") 
	public List<Asignatura> buscarAsignaturaNoPerteneceEstudiante(@Param("estudianteSancionado") EstudianteSancionado estudianteSancionado, @Param("programaAcademico") ProgramaAcademico programaAcademico);
}
