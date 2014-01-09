package sigarep.modelos.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.PreguntaBasica;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacionPK;
import sigarep.modelos.data.transacciones.*;
import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.*;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.EstudianteSancionado;

public interface IApelacionEstadoApelacionDAO extends
		JpaRepository<ApelacionEstadoApelacion, ApelacionEstadoApelacionPK> {
	@Query("select es.cedulaEstudiante, es.primerNombre, es.primerApellido, " +
			"sm.nombreSancion, ea.idEstadoApelacion, ia.idInstanciaApelada from Estudiante es, EstudianteSancionado esa, SolicitudApelacion sa, " +
			"ApelacionMomento ap, EstadoApelacion ea, SancionMaestro sm, InstanciaApelada ia  where  es.cedulaEstudiante = " +
			"cedula_estudiante and cedula_estudiante = " +
			"sa.estudianteSancionado and sa.estudianteSancionado = " +
			"cedula_estudiante and id_estado_apelacion = ea.idEstadoApelacion and " +
			"ea.nombreEstado = 'veredicto' and id_sancion = sm.idSancion and " +
			"id_instancia_apelada = ia.idInstanciaApelada and codigo_lapso = codigo_lapso")
	public List<ApelacionEstadoApelacion> buscarApelaciones();


}