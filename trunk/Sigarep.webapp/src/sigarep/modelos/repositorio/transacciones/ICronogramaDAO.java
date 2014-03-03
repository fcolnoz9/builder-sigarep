package sigarep.modelos.repositorio.transacciones;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.transacciones.Cronograma;
import sigarep.modelos.data.transacciones.CronogramaPK;

/**Cronograma de Actividades - Planificar
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo : Builder-Sigarep Lapso 2013-2
 * @version 1.1
 * @since 10/02/14
 */

public interface ICronogramaDAO extends JpaRepository<Cronograma, CronogramaPK> {
	 
	public List<Cronograma> findByEstatusTrue();

	public List<Cronograma> findById_CodigoLapso(String codigoLapso);
	
	@Query("SELECT MAX(cr.fechaFin) from Cronograma cr, LapsoAcademico la WHERE cr.id.codigoLapso = la.codigoLapso AND la.estatus = TRUE")
	public Date buscarUltimaFechaCronogramaActual();

}
