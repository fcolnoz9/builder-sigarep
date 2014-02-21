package sigarep.modelos.repositorio.maestros;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.maestros.ProgramaAcademico;

public interface IProgramaAcademicoDAO extends
		JpaRepository<ProgramaAcademico, Integer> {

	/**
	 * Busca las todas los Programas Academicos que poseen estatus true
	 * @return List<ProgramaAcademico> Lista de programas academicos con estatus true
	 */
	public List<ProgramaAcademico> findByEstatusTrue();
	
	/**
	 * Busca el ultimo id insertado en la tabla ProgramaAcademico
	 * @return Ultimo id insertado en la tabla ProgramaAcademico
	 */
	@Query("SELECT COALESCE(MAX(pa.idPrograma),0) FROM ProgramaAcademico AS pa")
	public int buscarUltimoID();
}
