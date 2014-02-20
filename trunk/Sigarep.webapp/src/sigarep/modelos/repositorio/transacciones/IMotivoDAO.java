package sigarep.modelos.repositorio.transacciones;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.MotivoPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;

public interface IMotivoDAO extends JpaRepository<Motivo, MotivoPK> {

	public List<Motivo> findBySolicitudApelacion(
			SolicitudApelacion solicitudApelacion);

	@Query("Select m FROM Motivo AS m WHERE m.id.cedulaEstudiante = :cedula AND m.id.codigoLapso = :codigoLapso "
			+ "and m.id.idTipoMotivo <> 1 and m.id.idTipoMotivo <> 2 and m.id.idTipoMotivo <> 3")
	public List<Motivo> buscarMotivos(@Param("cedula") String cedula,
			@Param("codigoLapso") String codigoLapso);

	// eliecer
	@Query("Select distinct(tm.nombreTipoMotivo) FROM Motivo AS m, TipoMotivo AS tm WHERE m.id.cedulaEstudiante = :cedula AND m.id.codigoLapso = :codigoLapso "
			+ "and m.id.idTipoMotivo = tm.idTipoMotivo and m.id.idTipoMotivo <> 1 and m.id.idTipoMotivo <> 2 and m.id.idTipoMotivo <> 3")
	public List<String> buscarMotivosApelacion(@Param("cedula") String cedula,
			@Param("codigoLapso") String codigoLapso);
}
