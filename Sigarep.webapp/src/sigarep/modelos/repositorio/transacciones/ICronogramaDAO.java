package sigarep.modelos.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.transacciones.Cronograma;
import sigarep.modelos.data.transacciones.CronogramaPK;

public interface ICronogramaDAO extends JpaRepository<Cronograma, CronogramaPK> {
	 
	@Query("select c from Cronograma c where c.estatus = true")
	public List<Cronograma> buscar();

	@Query("SELECT cro FROM Cronograma cro WHERE cro.id.codigoLapso = :codigoLapso")
	public List<Cronograma> buscarCronogramas(@Param("codigoLapso")String codigoLapso);

}
