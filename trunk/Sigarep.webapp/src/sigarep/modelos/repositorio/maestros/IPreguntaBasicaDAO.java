package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.PreguntaBasica;

public interface IPreguntaBasicaDAO extends
		JpaRepository<PreguntaBasica, Integer> {

	/**
	 * Busca las todas las preguntas básicas que poseen estatus == true
	 * @return List<PreguntaBasica> Lista de preguntas basicas con estatus == true
	 */
	@Query("select pr from PreguntaBasica pr where pr.estatus='true'")
	public List<PreguntaBasica> buscarPreguntab();
	
	@Query("SELECT COALESCE(MAX(pb.idPreguntaBasica),0) FROM PreguntaBasica AS pb")
	public int buscarUltimoID();
}