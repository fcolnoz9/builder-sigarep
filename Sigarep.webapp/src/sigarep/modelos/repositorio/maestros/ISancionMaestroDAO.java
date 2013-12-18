package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.SancionMaestro;

public interface ISancionMaestroDAO extends
		JpaRepository<SancionMaestro, Integer> {

	@Query("SELECT san FROM SancionMaestro  san WHERE san.nombreSancion= :nombre")
	public List<SancionMaestro> buscarSancion();

	@Query("SELECT san FROM SancionMaestro san WHERE san.nombreSancion= :nombre")
	public SancionMaestro findBynombre(String nombre);

	@Query("SELECT san FROM SancionMaestro san WHERE san.idSancion= :id_sancion")
	public List<SancionMaestro> buscarSancionId(Integer id_sancion);
}
