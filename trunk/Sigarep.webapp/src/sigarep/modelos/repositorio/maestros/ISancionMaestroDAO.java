package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.maestros.SancionMaestro;

public interface ISancionMaestroDAO extends
		JpaRepository<SancionMaestro, Integer> {

	@Query("Select san FROM SancionMaestro AS san WHERE estatus = TRUE")
	public List<SancionMaestro> buscarSancionesActivas();
	
	@Query("SELECT COALESCE(MAX(sm.idSancion),0) FROM SancionMaestro AS sm")
	public int buscarUltimoID();
}
