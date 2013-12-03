package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.PreguntaBasica;

public interface IPreguntaBasicaDAO extends
		JpaRepository<PreguntaBasica, Integer> {

}
