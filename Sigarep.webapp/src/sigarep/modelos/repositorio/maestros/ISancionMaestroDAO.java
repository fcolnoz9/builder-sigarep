package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.SancionMaestro;

public interface ISancionMaestroDAO extends JpaRepository<SancionMaestro, Integer> {

//	@Query("select san from SancionMaestro  san where san.idSancion= :idSancion")
//	public List<SancionMaestro> buscarSancion();
//
//	@Query("SELECT san FROM SancionMaestro san where san.idSancion= :idSancion")
//	public SancionMaestro findBynombre(String nombre);
//
//	@Query("SELECT san FROM SancionMaestro san where san.idSancion= :idSancion")
//	public List<SancionMaestro> buscarSancionId(Integer id_sancion);
}
