
package sigarep.modelos.repositorio.maestros;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.EstadoApelacion;

/** Clase EstadoApelacion
 * Registra y Modifica el Estado de Apelación asociado a Instancia Apelada
 * @author BUILDER
 * @version 1
 * @since 15/12/2013 
 */


public interface IEstadoApelacionDAO extends JpaRepository<EstadoApelacion, Integer> {

	@Query("Select ea FROM EstadoApelacion AS ea WHERE ea.nombreEstado = :nombreEstado")
	public EstadoApelacion buscarEstadoPorNombre(@Param("nombreEstado") String nombreEstado);
	
	@Query("Select ea FROM EstadoApelacion AS ea WHERE estatus = TRUE")
	public List<EstadoApelacion> buscarEstadoApelacionActivas();
	
}
