package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.Asignatura;
import sigarep.modelos.data.maestros.ProgramaAcademico;

public interface IAsignaturaDAO extends JpaRepository<Asignatura, String> {

	/**
	 * Busca las todas las asignaturas que poseen estatus == true
	 * 
	 * @return List<Asignatura> Lista de asignaturas con estatus == true
	 */
	public List<Asignatura> findByEstatusTrue();

	/**
	 * Busca asignaturas por programa que poseen estatus == true
	 * 
	 * @param programa
	 *            identificador unico de Programa academico
	 * @return List<Asignatura> Lista de asignaturas por programa con estatus ==
	 *         true
	 */
	public List<Asignatura> findByProgramaAcademicoAndEstatusTrue(
			ProgramaAcademico programa);

	/**
	 * Busca una Asignatura por su nombre
	 * 
	 * @param nombreAsignatura
	 *            Nombre de la asignatura que se pretende encontrar
	 * @return Asignatura encontrada por su nombre
	 */
	public Asignatura findByNombreAsignatura(String nombreAsignatura);
}
