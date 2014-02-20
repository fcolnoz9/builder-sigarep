package sigarep.modelos.repositorio.maestros;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sigarep.modelos.data.maestros.Actividad;

public interface IActividadDAO extends JpaRepository<Actividad, Integer> {

	/**
	 * Busca las todas las actividades que poseen estatus == true
	 * @return List<Actividad> Lista de actividades con estatus == true
	 */
	public List<Actividad> findByEstatusTrue();

	@Query("SELECT COALESCE(MAX(a.idActividad),0) FROM Actividad AS a")
	public int buscarUltimoID();
}
