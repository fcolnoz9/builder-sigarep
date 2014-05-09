package sigarep.modelos.repositorio.transacciones;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.transacciones.Cronograma;
import sigarep.modelos.data.transacciones.CronogramaPK;

/** Repositorio ICronogramaDAO
 * @author Equipo Builder
 * @version 1.0
 * @since 10/02/2014
 * @last 09/05/2014
 */

public interface ICronogramaDAO extends JpaRepository<Cronograma, CronogramaPK> {
	 
	public List<Cronograma> findByEstatusTrue();

	public List<Cronograma> findById_CodigoLapso(String codigoLapso);
	
	/**
	 * Busca la ultima fecha en el cronograma actual
	 * @return la ultima fecha del cronograma actual
	 */
	@Query("SELECT MAX(cr.fechaFin) from Cronograma cr, LapsoAcademico la WHERE cr.id.codigoLapso = la.codigoLapso AND la.estatus = TRUE")
	public Date buscarUltimaFechaCronogramaActual();

}
