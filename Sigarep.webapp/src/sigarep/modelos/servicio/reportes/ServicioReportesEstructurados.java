package sigarep.modelos.servicio.reportes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
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
	public List<Sancionados> buscarEstudiantesComision(int programa,
			String codigo_lapso) {
		String queryStatement = "SELECT DISTINCT es.cedula_estudiante as cedula, es.primer_nombre as nombre, es.primer_apellido as apellido, aea.sugerencia as veredicto, "
				+ "(SELECT count(es.cedula_estudiante) FROM sigarep.apelacion_estado_apelacion as aea, sigarep.estudiante es "
				+ "INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = es.cedula_estudiante "
				+ "INNER JOIN sigarep.programa_academico as prog on es.id_programa = prog.id_programa "
				+ "INNER JOIN sigarep.solicitud_apelacion as sa on sa.cedula_estudiante = essa.cedula_estudiante "
				+ "and sa.codigo_lapso = essa.codigo_lapso "
				+ "WHERE sa.cedula_estudiante = aea.cedula_estudiante "
				+ "and sa.codigo_lapso = aea.codigo_lapso "
				+ "and sa.id_instancia_apelada = aea.id_instancia_apelada "
				+ "and aea.id_instancia_apelada = 1 "
				+ "and aea.sugerencia = 'PROCEDENTE' "
				+ "and prog.id_programa = " + "'"
				+ programa
				+ "' "
				+ "and sa.codigo_lapso = '"
				+ codigo_lapso
				+ "' "
				+ "and aea.id_estado_apelacion = 4) as procedentes, "
				+ "(SELECT count(es.cedula_estudiante) FROM sigarep.apelacion_estado_apelacion as aea, sigarep.estudiante es "
				+ "INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = es.cedula_estudiante "
				+ "INNER JOIN sigarep.programa_academico as prog on es.id_programa = prog.id_programa "
				+ "INNER JOIN sigarep.solicitud_apelacion as sa on sa.cedula_estudiante = essa.cedula_estudiante "
				+ "and sa.codigo_lapso = essa.codigo_lapso "
				+ "WHERE sa.cedula_estudiante = aea.cedula_estudiante "
				+ "and sa.codigo_lapso = aea.codigo_lapso "
				+ "and sa.id_instancia_apelada = aea.id_instancia_apelada "
				+ "and aea.id_instancia_apelada = 1 "
				+ "and aea.sugerencia = 'NO PROCEDENTE' "
				+ "and prog.id_programa = "
				+ "'"
				+ programa
				+ "' "
				+ "and sa.codigo_lapso = '"
				+ codigo_lapso
				+ "' "
				+ "and aea.id_estado_apelacion = 4) as noprocedentes "
				+ "FROM sigarep.apelacion_estado_apelacion as aea, sigarep.estudiante es "
				+ "INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = es.cedula_estudiante "
				+ "INNER JOIN sigarep.programa_academico as prog on es.id_programa = prog.id_programa "
				+ "INNER JOIN sigarep.solicitud_apelacion as sa on sa.cedula_estudiante = essa.cedula_estudiante "
				+ "and sa.codigo_lapso = essa.codigo_lapso "
				+ "WHERE sa.cedula_estudiante = aea.cedula_estudiante "
				+ "and sa.codigo_lapso = aea.codigo_lapso "
				+ "and sa.id_instancia_apelada = aea.id_instancia_apelada "
				+ "and aea.id_instancia_apelada = 1 "
				+ "and aea.sugerencia is not null "
				+ " and aea.id_estado_apelacion = 4 "
				+ "and prog.id_programa = "
				+ "'"
				+ programa
				+ "' "
				+ "and sa.codigo_lapso = '"
				+ codigo_lapso
				+ "' "
				+ "order by nombre";

		Query query = em.createNativeQuery(queryStatement);

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();

		List<Sancionados> results = new ArrayList<Sancionados>();
		for (Object[] resultRow : resultSet) {
			results.add(new Sancionados((String) resultRow[0],
					(String) resultRow[1], (String) resultRow[2],
					(String) resultRow[3], ((BigInteger) resultRow[4])
							.intValue(), ((BigInteger) resultRow[5]).intValue()));
		}
		return results;
	}
}