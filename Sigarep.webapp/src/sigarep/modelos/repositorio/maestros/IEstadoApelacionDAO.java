
package sigarep.modelos.repositorio.maestros;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.EstadoApelacion;

/**
 * Repositorio EstadoApelacion-IEstadoApelacionDAO 
 * 
 * @author BUILDER
 * @version 1.0
 * @since 12/12/2013
 */
public interface IEstadoApelacionDAO extends JpaRepository<EstadoApelacion, Integer> {

	/**
	 * Busca las todas los Estados de Apelación que poseen estatus == true
	 * @return List<EstadoApelacion> Lista de Estados de Apelación con estatus == true
	 */
	public List<EstadoApelacion> findByEstatusTrue();
	
	/**
	 * Busca un Estado de Apelación por su nombre
	 * @param nombreEstado Nombre del estado de apelación que se pretende encontrar
	 * @return EstadoApelacion encontrado por su nombre
	 */
	public EstadoApelacion findByNombreEstado(String nombreEstado);
	
	/**
	 * Busca el último id insertado en la tabla EstadoApelacion
	 * @return Último id insertado en la tabla EstadoApelacion
	 */
	@Query("SELECT COALESCE(MAX(ea.idEstadoApelacion),0) FROM EstadoApelacion AS ea")
	public int buscarUltimoID();
	
	/**
	 * Busca los estados de apelación de una instancia
	 * @param el id de la instancia
	 * @return lista de estados de una instancia
	 */
	@Query("select e from EstadoApelacion AS e, InstanciaApelada AS i " +
			"where e.instanciaApelada.idInstanciaApelada = i.idInstanciaApelada " +
			"and e.instanciaApelada.idInstanciaApelada = :instancia " +
			"order by e.idEstadoApelacion asc ")
	public List<EstadoApelacion> buscarEstados(@Param("instancia") int instancia);

}
