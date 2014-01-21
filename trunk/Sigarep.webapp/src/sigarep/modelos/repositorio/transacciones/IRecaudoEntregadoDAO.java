package sigarep.modelos.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.MotivoPK;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;

public interface IRecaudoEntregadoDAO extends
		JpaRepository<RecaudoEntregado, RecaudoEntregadoPK> {

	@Query("SELECT re FROM RecaudoEntregado AS re, LapsoAcademico la " +
		   "WHERE re.id.cedulaEstudiante = :cedula AND re.id.codigoLapso = la.codigoLapso " +
		   "AND la.estatus = 'TRUE'")
	public List<RecaudoEntregado> buscarRecaudosEntregados(@Param("cedula") String cedula);
}