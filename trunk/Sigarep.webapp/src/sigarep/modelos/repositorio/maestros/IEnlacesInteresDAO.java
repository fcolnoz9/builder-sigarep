package sigarep.modelos.repositorio.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.EnlacesInteres;


public interface IEnlacesInteresDAO extends JpaRepository<EnlacesInteres,Integer> {
	
	@Query("select  ei from EnlacesInteres ei where ei.id_enlace= :id_enlace")
	public List<EnlacesInteres> buscar();
	@Query("select  ein from EnlacesInteres ein where ein.nombre_enlace= :nombre_enlace")
	public List<EnlacesInteres> buscarNombre();
	@Query("select ei from EnlacesInteres ei where ei.id_enlace= :id_enlace")
    public EnlacesInteres findByid_enlace(Integer id_enlace);
	@Query("select ein from EnlacesInteres ein where ein.nombre_enlace= :nombre_enlace")
    public EnlacesInteres findBynombre_enlace(String nombre_enlace);
	@Query("select ei from EnlacesInteres ei where ei.id_enlace= :id_enlace")
	public List<EnlacesInteres> buscarEnlace(Integer id_enlace);
	@Query("select ein from EnlacesInteres ein where ein.nombre_enlace= :nombre_enlace")
	public List<EnlacesInteres> buscarEnlaceNombre(String nombre_enlace);
	
}

