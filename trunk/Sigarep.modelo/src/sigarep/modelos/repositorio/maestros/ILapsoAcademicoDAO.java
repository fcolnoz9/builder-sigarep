package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import sigarep.modelos.data.maestros.LapsoAcademico;


/**
 * Repositorio ILapsoAcademicoDAO: Repositorio relacionado con el Maestro LapsoAcademico.
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 12/12/2013
 * @last 08/05/2014
 */
public interface ILapsoAcademicoDAO extends JpaRepository<LapsoAcademico, String> {

	/**
	 * Busca el lapso académico ACTIVO, es decir, donde su estatus es == true
	 * 
	 * @return Lapso académico activo encontrado 
	 */
	public LapsoAcademico findByEstatusTrue();

	/**
	 * Busca los lapsos académico INACTIVOS, es decir, donde su estatus es == false
	 * 
	 * @return List<LapsoAcademico> Lapsos académicos inactivos
	 */
	public List<LapsoAcademico> findByEstatusFalse();
}
