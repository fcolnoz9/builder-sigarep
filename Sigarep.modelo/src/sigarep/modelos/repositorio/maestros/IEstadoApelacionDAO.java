
package sigarep.modelos.repositorio.maestros;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.EstadoApelacion;

/**
 * Repositorio IEstadoApelacionDAO: Repositorio relacionado con el Maestro EstadoApelacion. 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 12/12/2013
 * @last 08/05/2014
 */
public interface IEstadoApelacionDAO extends JpaRepository<EstadoApelacion, Integer> {

	/**
	 * Busca todos los Estados de Apelación que poseen estatus == true
	 * 
	 * @return List<EstadoApelacion> Lista de Estados de Apelación con estatus == true
	 */
	public List<EstadoApelacion> findByEstatusTrue();
	
	/**
	 * Busca un Estado de Apelación por su nombre
	 * 
	 * @param nombreEstado Nombre del estado de apelación que se pretende encontrar
	 * @return EstadoApelacion encontrado por su nombre
	 */
	public EstadoApelacion findByNombreEstado(String nombreEstado);
	
	/**
	 * Busca el último id insertado en la tabla EstadoApelacion
	 * 
	 * @return Último id insertado en la tabla EstadoApelacion
	 */
	@Query("SELECT COALESCE(MAX(ea.idEstadoApelacion),0) FROM EstadoApelacion AS ea")
	public int buscarUltimoID();
	
	/**
	 * Busca los estados de apelación de una instancia
	 * 
	 * @param instancia
	 * @return List<EstadoApelacion> Lista de estados de una instancia
	 */
	@Query("select e from EstadoApelacion AS e, InstanciaApelada AS i " +
			"where e.instanciaApelada.idInstanciaApelada = i.idInstanciaApelada " +
			"and e.instanciaApelada.idInstanciaApelada = :instancia " +
			"and e.idEstadoApelacion <> '4' " +
			"and e.idEstadoApelacion <> '8' " +
			"and e.idEstadoApelacion <> '12'" +
			"order by e.idEstadoApelacion asc ")
	public List<EstadoApelacion> buscarEstados(@Param("instancia") int instancia);

	public EstadoApelacion findByPrioridadEjecucion(Integer prioridadEjecucion);

}
