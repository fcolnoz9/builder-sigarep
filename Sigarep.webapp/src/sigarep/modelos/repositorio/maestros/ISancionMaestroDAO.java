package sigarep.modelos.repositorio.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import sigarep.modelos.data.maestros.SancionMaestro;

public interface ISancionMaestroDAO extends
		JpaRepository<SancionMaestro, Integer> {

}
