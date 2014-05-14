package sigarep.modelos.servicio.reportes;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.reportes.ProcedentesComision;
import sigarep.modelos.data.reportes.Sancionados;

/**
* Clase Servicio ServicioReportesEstructurados busca
* a los estudiante con los resultados de sugerencias por comisión.
* @author Equipo Builder
* @version 1.5
* @since 05/01/2014 (Fecha de creación)
* @last 09/05/2014 (Ultima modificación)
*/
@Service("servicioreportesestructurados")
public class ServicioReportesEstructurados {

	@PersistenceContext
	private EntityManager em;

	
	/**
	 * Buscar Estudiantes por Comisión
	 * 
	 * @param Integer
	 *            programa
	 * @return Lista de estudiante con los resultados de sugerencias por
	 *         Comisión
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public List<ProcedentesComision> buscarProcedentesComision(String codigo_lapso) {
		String queryStatement = 
				"select v.programa as programa,sum(v.procedentes) procedentes, sum(v.noprocedentes) noprocedentes " +
				"from " +
				"((SELECT count(es.cedula_estudiante) as procedentes, 0 as noprocedentes, prog.nombre_programa as programa " +
				"FROM sigarep.apelacion_estado_apelacion as aea, sigarep.estudiante es " +
				"INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = es.cedula_estudiante " +
				"INNER JOIN sigarep.programa_academico as prog on es.id_programa = prog.id_programa " +
				"INNER JOIN sigarep.solicitud_apelacion as sa on sa.cedula_estudiante = essa.cedula_estudiante " +
				"and sa.codigo_lapso = essa.codigo_lapso " +
				"WHERE sa.cedula_estudiante = aea.cedula_estudiante " +
				"and sa.codigo_lapso = aea.codigo_lapso " +
				"and sa.id_instancia_apelada = aea.id_instancia_apelada " +
				"and aea.id_instancia_apelada = 1 " +
				"and aea.sugerencia = 'PROCEDENTE' " +
				"and sa.codigo_lapso = '" + codigo_lapso + "' " +
				"and aea.id_estado_apelacion = 3 " +
				"group by programa) " +
				"union all " +
				"SELECT 0 as procedentes, count(es.cedula_estudiante) as noprocedentes, prog.nombre_programa as programa " +
				"FROM sigarep.apelacion_estado_apelacion as aea, sigarep.estudiante es " +
				"INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = es.cedula_estudiante " +
				"INNER JOIN sigarep.programa_academico as prog on es.id_programa = prog.id_programa " +
				"INNER JOIN sigarep.solicitud_apelacion as sa on sa.cedula_estudiante = essa.cedula_estudiante " +
				"and sa.codigo_lapso = essa.codigo_lapso " +
				"WHERE sa.cedula_estudiante = aea.cedula_estudiante " +
				"and sa.codigo_lapso = aea.codigo_lapso " +
				"and sa.id_instancia_apelada = aea.id_instancia_apelada " +
				"and aea.id_instancia_apelada = 1 " +
				"and aea.sugerencia = 'NO PROCEDENTE' " +
				"and sa.codigo_lapso = '" + codigo_lapso + "' " +
				"and aea.id_estado_apelacion = 3 " +
				"group by programa) as v " +
				"group by v.programa " +
				"order by v.programa";

		Query query = em.createNativeQuery(queryStatement);

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();

		List<ProcedentesComision> results = new ArrayList<ProcedentesComision>();
		for (Object[] resultRow : resultSet) {
			results.add(new ProcedentesComision
					( (String) resultRow[0],
					( (Number) resultRow[1]).intValue(),
					( (Number) resultRow[2]).intValue()));
		}
		return results;
	}
	
	
	/**
	 * Buscar Estudiantes por Comisión
	 * 
	 * @param Integer
	 *            programa
	 * @return Lista de estudiante con los resultados de sugerencias por
	 *         Comisión
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public List<Sancionados> buscarEstudiantesComision(String codigo_lapso) {
		String queryStatement = 
				"SELECT DISTINCT es.cedula_estudiante as cedula, es.primer_nombre as nombre, " +
				"es.primer_apellido as apellido, aea.sugerencia as veredicto , prog.nombre_programa as programa " +
				"FROM sigarep.apelacion_estado_apelacion as aea, sigarep.estudiante es " +
				"INNER JOIN sigarep.estudiante_sancionado as essa " +
				"on essa.cedula_estudiante = es.cedula_estudiante " +
				"INNER JOIN sigarep.programa_academico as prog " +
				"on es.id_programa = prog.id_programa " +
				"INNER JOIN sigarep.solicitud_apelacion as sa " +
				"on sa.cedula_estudiante = essa.cedula_estudiante " +
				"and sa.codigo_lapso = essa.codigo_lapso " +
				"WHERE sa.cedula_estudiante = aea.cedula_estudiante " +
				"and sa.codigo_lapso = aea.codigo_lapso " +
				"and sa.id_instancia_apelada = aea.id_instancia_apelada " +
				"and aea.id_instancia_apelada = 1 " +
				"and aea.sugerencia is not null " +
				"and aea.id_estado_apelacion = 3 " +
				"and sa.codigo_lapso = '2012-1' order by programa, nombre";
		
		Query query = em.createNativeQuery(queryStatement);

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();

		List<Sancionados> results = new ArrayList<Sancionados>();
		for (Object[] resultRow : resultSet) {
			results.add(new Sancionados((String) resultRow[0],
					(String) resultRow[1], (String) resultRow[2],
					(String) resultRow[3], (String) resultRow[4]));
		}
		return results;
	}
}