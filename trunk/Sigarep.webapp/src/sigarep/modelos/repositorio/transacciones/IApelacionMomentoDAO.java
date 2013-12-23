package sigarep.modelos.repositorio.transacciones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sigarep.modelos.data.maestros.PreguntaBasica;
import sigarep.modelos.data.transacciones.ApelacionMomento;
import sigarep.modelos.data.transacciones.ApelacionMomentoPK;
import sigarep.modelos.data.transacciones.*;
import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.maestros.*;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.EstudianteSancionado;

public interface IApelacionMomentoDAO extends
		JpaRepository<ApelacionMomento, ApelacionMomentoPK> {
	@Query("select es.cedulaEstudiante, es.primerNombre, es.primerApellido, " +
			"sm.nombreSancion, m.idMomento, ia.idInstanciaApelada from Estudiante es, EstudianteSancionado esa, SolicitudApelacion sa, " +
			"ApelacionMomento ap, Momento m, SancionMaestro sm, InstanciaApelada ia  where  es.cedulaEstudiante = " +
			"cedula_estudiante and cedula_estudiante = " +
			"sa.estudianteSancionado and sa.estudianteSancionado = " +
			"cedula_estudiante and id_momento = m.idMomento and " +
			"m.nombreMomento = 'veredicto' and id_sancion = sm.idSancion and " +
			"id_instancia_apelada = ia.idInstanciaApelada and codigo_lapso = codigo_lapso")
	public List<ApelacionMomento> buscarApelaciones();


}