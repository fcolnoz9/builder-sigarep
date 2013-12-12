package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.PreguntaBasica;

public interface IPreguntaBasicaDAO extends
		JpaRepository<PreguntaBasica, Integer> {
	@Query("select  pre from PreguntaBasica pre where pre.pregunta= :pregunta")
	public List<PreguntaBasica> buscarPregunta();
	@Query("select pr from PreguntaBasica pr where pr.pregunta= :pregunta")
    public PreguntaBasica findBynombre(Integer idPreguntaBasica);
	@Query("select pr from PreguntaBasica pr where pr.idPreguntaBasica= :idPreguntaBasica")
	public List<PreguntaBasica> buscarPreguntab(Integer idPreguntaBasica);

}
