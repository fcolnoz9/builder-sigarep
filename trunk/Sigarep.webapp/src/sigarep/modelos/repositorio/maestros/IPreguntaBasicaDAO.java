package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.PreguntaBasica;

public interface IPreguntaBasicaDAO extends
		JpaRepository<PreguntaBasica, Integer> {
//	@Query("select  pre from PreguntaBasica pre where pre.estatus = 'true'")
//	public List<PreguntaBasica> buscarPregunta();

// Metodo que busca las preguntas basicas cuando su estatus es true 
	@Query("select pr from PreguntaBasica pr where pr.estatus='true'")
	public List<PreguntaBasica> buscarPreguntab();
}