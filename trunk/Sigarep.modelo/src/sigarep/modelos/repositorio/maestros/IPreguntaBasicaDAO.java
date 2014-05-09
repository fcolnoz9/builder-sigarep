package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.PreguntaBasica;

/**
 * Repositorio IPreguntaBasicaDAO: Repositorio relacionado con el Maestro PreguntaBasica. 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 12/12/2013
 * @last 08/05/2014
 */
public interface IPreguntaBasicaDAO extends JpaRepository<PreguntaBasica, Integer> {

	/**
	 * Busca todas las preguntas básicas que poseen estatus == true
	 * 
	 * @return List<PreguntaBasica> Lista de preguntas básicas con estatus == true
	 */
	public List<PreguntaBasica> findByEstatusTrue();
	
	/**
	 * Busca el último id insertado en la tabla PreguntaBasica
	 * 
	 * @return Último id insertado en la tabla PreguntaBasica
	 */
	@Query("SELECT COALESCE(MAX(pb.idPreguntaBasica),0) FROM PreguntaBasica AS pb")
	public int buscarUltimoID();
}