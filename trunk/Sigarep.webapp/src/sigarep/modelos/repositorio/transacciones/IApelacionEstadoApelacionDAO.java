package sigarep.modelos.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sigarep.modelos.data.maestros.PreguntaBasica;
import sigarep.modelos.data.transacciones.*;
import sigarep.modelos.data.maestros.*;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.EstudianteSancionado;

public interface IApelacionEstadoApelacionDAO extends
		JpaRepository<ApelacionEstadoApelacion, ApelacionEstadoApelacionPK> {
	@Query("select es.cedulaEstudiante, es.primerNombre, es.primerApellido, " +
			"sm.nombreSancion, ea.idEstadoApelacion, ia.idInstanciaApelada from Estudiante es, EstudianteSancionado esa, SolicitudApelacion sa, " +
			"ApelacionEstadoApelacion ap, EstadoApelacion ea, SancionMaestro sm, InstanciaApelada ia  where  es.cedulaEstudiante = " +
			"cedula_estudiante and cedula_estudiante = " +
			"sa.estudianteSancionado and sa.estudianteSancionado = " +
			"cedula_estudiante and id_estado_apelacion = ea.idEstadoApelacion and " +
			"ea.nombreEstado = 'veredicto' and id_sancion = sm.idSancion and " +
			"id_instancia_apelada = ia.idInstanciaApelada and codigo_lapso = codigo_lapso")
	public List<ApelacionEstadoApelacion> buscarApelaciones();
	
	@Query("select sa from ApelacionEstadoApelacion sa, SolicitudApelacion sap where sa.id.cedulaEstudiante = :cedula " +
			" and sap.id.codigoLapso = sa.id.codigoLapso and sap.id.cedulaEstudiante = sa.id.cedulaEstudiante and sa.id.codigoLapso = :codigoLapso " +
			"and sap.id.idInstanciaApelada = sa.id.idInstanciaApelada and sa.id.idInstanciaApelada =:instancia order by sa.id.idEstadoApelacion asc " )
	public List<ApelacionEstadoApelacion> buscarApelacionHistorial(@Param("cedula")String cedula, @Param("codigoLapso")String codigoLapso, @Param("instancia")Integer instancia);

}