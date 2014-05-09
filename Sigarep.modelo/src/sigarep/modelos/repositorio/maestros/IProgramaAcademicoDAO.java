package sigarep.modelos.repositorio.maestros;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.maestros.ProgramaAcademico;

/**
 * Repositorio IProgramaAcademicoDAO: Repositorio relacionado con el Maestro ProgramaAcademico.
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 12/12/2013
 * @last 08/05/2014
 */
public interface IProgramaAcademicoDAO extends JpaRepository<ProgramaAcademico, Integer> {

	/**
	 * Busca  todos los Programas Académicos que poseen estatus == true
	 * 
	 * @return List<ProgramaAcademico> Lista de programas académicos con estatus == true
	 */
	public List<ProgramaAcademico> findByEstatusProgramaTrue();
	
	/**
	 * Busca el último id insertado en la tabla ProgramaAcademico
	 * 
	 * @return Último id insertado en la tabla ProgramaAcademico
	 */
	@Query("SELECT COALESCE(MAX(pa.idPrograma),0) FROM ProgramaAcademico AS pa")
	public int buscarUltimoID();
}
