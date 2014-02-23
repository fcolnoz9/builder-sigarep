package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.maestros.ProgramaAcademico;

public interface IAsignaturaDAO extends JpaRepository<Asignatura, String> {
	
	/**
	 * Busca las todas las asignaturas que poseen estatus == true
	 * @return List<Asignatura> Lista de asignaturas con estatus == true
	 */
	public List<Asignatura> findByEstatusTrue();

	/**
	 * Busca asignaturas por programa que poseen estatus == true
	 * @param Programa identificador unico de Asignaturas
	 * @return List<Asignatura> Lista de asignaturas por programa con estatus == true
	 */
	public List<Asignatura> findByProgramaAcademicoAndEstatusTrueS(ProgramaAcademico programa);
	
	/**
	 * Busca asignaturas por programa que poseen estatus == true
	 * @param idPrograma identificador unico de Asignaturas
	 * @return List<Asignatura> Lista de asignaturas por programa con estatus == true
	 */
	public List<Asignatura> findByProgramaAcademicoAndEstatusTrue(Integer idPrograma);
	
	
	/**
	 * Busca una Asignatura por su nombre
	 * @param nombreAsignatura Nombre de la asignatura que se pretende encontrar
	 * @return Asignatura encontrada por su nombre
	 */
	public Asignatura findByNombreAsignatura(String nombreAsignatura);

	
	/**
	 * ¡HEY TU!, RESPONSABLE COMENTAME
	 * @param 
	 * @return 
	 */
	@Query("SELECT DISTINCT a FROM Asignatura As a, Estudiante AS e "
			+ "WHERE e.programaAcademico.idPrograma= a.programaAcademico.idPrograma "
			+ "AND a.codigoAsignatura NOT IN "
				+ "(SELECT a.codigoAsignatura FROM Estudiante AS e, EstudianteSancionado AS es,"
					+ "AsignaturaEstudianteSancionado AS aes, Asignatura AS a, ProgramaAcademico AS pa "
					+ "WHERE e.cedulaEstudiante = es.id.cedulaEstudiante "
					+ "AND es.id.cedulaEstudiante = aes.id.cedulaEstudiante "
					+ "AND aes.id.codigoAsignatura = a.codigoAsignatura "
					+ "AND a.programaAcademico.idPrograma= pa.idPrograma "
					+ "AND e.programaAcademico.idPrograma = pa.idPrograma "
					+ "AND e.programaAcademico.idPrograma= a.programaAcademico.idPrograma "
					+ "AND aes.id.cedulaEstudiante = :cedula )")
	 public List<Asignatura> BuscarAsignaturasNoSeleccionadas(@Param("cedula") String cedula);
	
}
