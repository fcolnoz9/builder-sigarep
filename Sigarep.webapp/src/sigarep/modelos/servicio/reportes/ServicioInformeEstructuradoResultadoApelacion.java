package sigarep.modelos.servicio.reportes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.reportes.ListaInformeEstructuradoResultadosApelacion;

/**
* Clase Servicio ServicioApelacionesMotivosPorAsignatura se encarga de buscar 
* y filtar los estudiantes según la instancia apelada y el programa académico
* @author Equipo Builder
* @version x.11
* @since 05/01/2014 (Fecha de creación)
* @last 18/03/2014 (Ultima modificación)
*/
@Service("servicioInformeEstructuradoResultadoApelacion")
public class ServicioInformeEstructuradoResultadoApelacion {
	@PersistenceContext
	private EntityManager es;
	/** Buscar Estudiante Resultado Apelacion
	 * @param String instanciaApelada, String programaAcademico,String sesion
	 * @return Listado del Resultado de la búsqueda 
	 * @throws No dispara ninguna excepcion.
	 */
	public List<ListaInformeEstructuradoResultadosApelacion> buscarEstudianteResultadoApelacion(String instanciaApelada, String programaAcademico,String sesion) {
		String queryStatement = 
		"SELECT sa.cedula_estudiante, e.primer_nombre,e.primer_apellido,p.nombre_programa,sa.veredicto,sa.codigo_sesion, sa.fecha_sesion, sa.tipo_sesion FROM sigarep.programa_academico as p,sigarep.estudiante as e,sigarep.lapso_academico as la, sigarep.solicitud_apelacion AS sa LEFT JOIN sigarep.instancia_apelada as ia ON (ia.id_instancia_apelada=sa.id_instancia_apelada) " +
	    "WHERE sa.codigo_lapso= la.codigo_lapso and la.estatus= 'TRUE' and p.id_programa=e.id_programa AND sa.codigo_sesion= ? AND sa.cedula_estudiante=e.cedula_estudiante AND e.id_programa="+""+programaAcademico+" AND sa.id_instancia_apelada="+""+instanciaApelada+"  ORDER BY p.nombre_programa,ia.instancia_apelada,e.primer_nombre desc " ;
	     
	   	Query query = es.createNativeQuery(queryStatement);
	   	query.setParameter(1, sesion);

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		List<ListaInformeEstructuradoResultadosApelacion> results = new ArrayList<ListaInformeEstructuradoResultadosApelacion>();
		for (Object[] resultRow : resultSet) {
			results.add(new ListaInformeEstructuradoResultadosApelacion(
					(String)  resultRow[0],
					(String)  resultRow[1],
					(String)  resultRow[2],
					(String)  resultRow[3],
					(String)  resultRow[4],
					(String)  resultRow[5],
					(Date) resultRow[6],
					(String)  resultRow[7]));			
		}
		return results;
	}
}
