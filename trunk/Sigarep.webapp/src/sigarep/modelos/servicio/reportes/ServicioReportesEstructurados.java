package sigarep.modelos.servicio.reportes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.reportes.Sancionados;

@Service("servicioreportescomparativos")
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
	public List<Sancionados> buscarEstudiantesComision(int programa) {
		String queryStatement = "SELECT DISTINCT es.cedula_estudiante as cedula, es.primer_nombre as nombre, es.primer_apellido as apellido, aea.sugerencia as veredicto, "
				+ "(SELECT count(es.cedula_estudiante) FROM apelacion_estado_apelacion as aea, estudiante es "
				+ "INNER JOIN estudiante_sancionado as essa on essa.cedula_estudiante = es.cedula_estudiante "
				+ "INNER JOIN programa_academico as prog on es.id_programa = prog.id_programa "
				+ "INNER JOIN solicitud_apelacion as sa on sa.cedula_estudiante = essa.cedula_estudiante "
				+ "and sa.codigo_lapso = essa.codigo_lapso "
				+ "WHERE sa.cedula_estudiante = aea.cedula_estudiante "
				+ "and sa.codigo_lapso = aea.codigo_lapso "
				+ "and sa.id_instancia_apelada = aea.id_instancia_apelada "
				+ "and aea.id_instancia_apelada = 1 "
				+ "and aea.sugerencia = 'PROCEDENTE' "
				+ "and prog.id_programa = " + "'"
				+ programa
				+ "' "
				+ "and sa.codigo_lapso = '2013-2' "
				+ "and aea.id_estado_apelacion = 3) as procedentes, "
				+ "(SELECT count(es.cedula_estudiante) FROM apelacion_estado_apelacion as aea, estudiante es "
				+ "INNER JOIN estudiante_sancionado as essa on essa.cedula_estudiante = es.cedula_estudiante "
				+ "INNER JOIN programa_academico as prog on es.id_programa = prog.id_programa "
				+ "INNER JOIN solicitud_apelacion as sa on sa.cedula_estudiante = essa.cedula_estudiante "
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
				+ "and sa.codigo_lapso = '2013-2' "
				+ "and aea.id_estado_apelacion = 3) as noprocedentes "
				+ "FROM apelacion_estado_apelacion as aea, estudiante es "
				+ "INNER JOIN estudiante_sancionado as essa on essa.cedula_estudiante = es.cedula_estudiante "
				+ "INNER JOIN programa_academico as prog on es.id_programa = prog.id_programa "
				+ "INNER JOIN solicitud_apelacion as sa on sa.cedula_estudiante = essa.cedula_estudiante "
				+ "and sa.codigo_lapso = essa.codigo_lapso "
				+ "WHERE sa.cedula_estudiante = aea.cedula_estudiante "
				+ "and sa.codigo_lapso = aea.codigo_lapso "
				+ "and sa.id_instancia_apelada = aea.id_instancia_apelada "
				+ "and aea.id_instancia_apelada = 1 "
				+ "and aea.sugerencia is not null "
				+ "and prog.id_programa = "
				+ "'"
				+ programa
				+ "' "
				+ "and sa.codigo_lapso = '2013-2' "
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