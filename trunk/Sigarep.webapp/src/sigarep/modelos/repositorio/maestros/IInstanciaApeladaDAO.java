package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.InstanciaApelada;

public interface IInstanciaApeladaDAO extends
		JpaRepository<InstanciaApelada, Integer> {

}
