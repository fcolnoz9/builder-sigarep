package sigarep.modelos.repositorio.maestros;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sigarep.modelos.data.maestros.Actividad;

/**
 * Repositorio Actividad-IActividadDAO
 * 
 * @author BUILDER
 * @version 1.0
 * @since 12/12/2013
 */

public interface IActividadDAO extends JpaRepository<Actividad, Integer> {

	/**
	 * Busca las todas las actividades que poseen estatus == true
	 * @return List<Actividad> Lista de actividades con estatus == true
	 */
	public List<Actividad> findByEstatusTrue();

	/**
	 * Busca el último id insertado en la tabla Actividad
	 * @return Último id insertado en la tabla Actividad
	 */
	@Query("SELECT COALESCE(MAX(a.idActividad),0) FROM Actividad AS a")
	public int buscarUltimoID();
}
