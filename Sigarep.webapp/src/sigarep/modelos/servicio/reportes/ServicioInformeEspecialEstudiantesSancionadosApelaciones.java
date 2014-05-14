package sigarep.modelos.servicio.reportes;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.reportes.ListaEspecialEstudiantesSancionadosApelaciones;

/**
* Clase Servicio ServicioInformeEspecialEstudiantesSancionadosApelaciones consulta los 
* datos básicos del estudiante, así como el tipo de sanción, los motivos 
* por las que apelo y el veredicto obtenido
* en cada instancia apelada, todo ello en un lapso específico activo.
* @author Equipo Builder
* @version 1.11
* @since 05/01/2014 (Fecha de creación)
* @last 18/03/2014 (Ultima modificación)
*/
@Service("servicioestudianteasignaturasancion")
public class ServicioInformeEspecialEstudiantesSancionadosApelaciones {
	@PersistenceContext
	private EntityManager es;
	/** Consulta los datos básicos del estudiante, así como el tipo de sanción, los motivos por las que apelo y el veredicto obtenido
	 * en cada instancia apelada, todo ello en un lapso específico activo.
	 * @param String tiposancion,String instanciaApelada, String programaAcademico,String veredicto
	 * @return Listado del Resultado de la búsqueda 
	 * @throws No dispara ninguna excepcion.
	 */
	public List<ListaEspecialEstudiantesSancionadosApelaciones> buscarEstudianteAsignaturasSancion(String tiposancion,String instanciaApelada, String programaAcademico,String veredicto) {
		String queryStatement = 
		"SELECT sa.cedula_estudiante, e.primer_nombre,e.primer_apellido,p.nombre_programa,sm.nombre_sancion,sa.codigo_lapso,es.periodo_sacion,  " +
		"a.nombre_asignatura, ia.nombre_recurso_apelacion,sa.veredicto,sa.observacion, tm.nombre_tipo_motivo, m.descripcion,sa.codigo_sesion, sa.fecha_sesion " +
		"FROM sigarep.programa_academico as p,sigarep.estudiante as e,sigarep.sancion_maestro as sm,sigarep.lapso_academico as la, sigarep.solicitud_apelacion AS sa " +
		"LEFT JOIN sigarep.asignatura_estudiante_sancionado  AS aes ON (aes.codigo_lapso = sa.codigo_lapso " +
															  "AND aes.cedula_estudiante = sa.cedula_estudiante) " +
	    "LEFT JOIN sigarep.ESTUDIANTE_SANCIONADO AS es ON (es.cedula_estudiante=sa.cedula_estudiante " +
	    										  "AND es.codigo_lapso=sa.codigo_lapso)	" +  
	    "LEFT JOIN sigarep.asignatura	as a ON (a.codigo_asignatura=aes.codigo_asignatura) " +
	    "LEFT JOIN sigarep.motivo  as m ON (m.cedula_estudiante=sa.cedula_estudiante and m.codigo_lapso=sa.codigo_lapso) " +
	    "LEFT JOIN sigarep.tipo_motivo as tm ON (tm.id_tipo_motivo=m.id_tipo_motivo)" +
	    "LEFT JOIN sigarep.instancia_apelada as ia ON (ia.id_instancia_apelada=sa.id_instancia_apelada) " +
	    "WHERE es.codigo_lapso= la.codigo_lapso and la.estatus= 'TRUE' and p.id_programa=e.id_programa AND tm.id_tipo_motivo<>1 AND tm.id_tipo_motivo<>2 AND tm.id_tipo_motivo<>3 AND sm.id_sancion=es.id_sancion AND sa.cedula_estudiante=e.cedula_estudiante AND e.id_programa="+""+programaAcademico+" AND sa.id_instancia_apelada="+""+instanciaApelada+" AND sa.veredicto="+""+veredicto+" AND es.id_sancion="+""+tiposancion+" ORDER BY p.nombre_programa,ia.instancia_apelada,e.primer_nombre,sm.nombre_sancion desc " ;
	     
	   	Query query = es.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		List<ListaEspecialEstudiantesSancionadosApelaciones> results = new ArrayList<ListaEspecialEstudiantesSancionadosApelaciones>();
		for (Object[] resultRow : resultSet) {
			results.add(new ListaEspecialEstudiantesSancionadosApelaciones(
					(String)  resultRow[0],
					(String)  resultRow[1],
					(String)  resultRow[2],
					(String)  resultRow[3],
					(String)  resultRow[4],
					(String)  resultRow[5],
					(Integer) resultRow[6],
					(String)  resultRow[7],
					(String)  resultRow[8],
					(String)  resultRow[9],
					(String)  resultRow[10],
					(String)  resultRow[11],
					(String)  resultRow[12],
					(String)  resultRow[13],
					(Date)  resultRow[14]));			
		}
		return results;
	}
}
