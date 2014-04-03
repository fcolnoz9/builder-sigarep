package sigarep.modelos.servicio.reportes;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import sigarep.modelos.data.reportes.ApelacionesComparativos;

@Service("servicioreportescomparativos")
public class ServicioReportesComparativos {
	@PersistenceContext
	private EntityManager em;
	/**
	 * Buscar Apelaciones por Motivo y Resultado
	 * 
	 * @param Integer
	 *            programa,String codigo_lapso, Integer tipo_sancion
	 * @return Lista con la cantidad de apelaciones por tipo de motivo y sus
	 *         veredictos
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public List<ApelacionesComparativos> buscarPorMotivoResultado_ProgramaSancion(
			String codigo_lapso, int tipo_sancion, int programa) {
		
		String queryStatement = "select Distinct on (v.cedula) v.cedula, v.motivo as motivo, sum(v.apelaciones) apelaciones, " +
				"sum(v.procedente)-sum(v.apelaciones-v.procedente) procedentes , (SELECT COUNT(DISTINCT sa.cedula_estudiante) totalapelaciones " +
				"FROM sigarep.solicitud_apelacion sa, sigarep.estudiante_sancionado essa, sigarep.estudiante es WHERE sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.cedula_estudiante = es.cedula_estudiante and sa.codigo_lapso = '"+ codigo_lapso +"' and es.id_programa = "+ programa +" and essa.id_sancion = "+ tipo_sancion +" ), " +
				"(SELECT COUNT(DISTINCT essa.cedula_estudiante) sancionados FROM sigarep.estudiante_sancionado essa, sigarep.estudiante es " +
				"WHERE essa.cedula_estudiante = es.cedula_estudiante and essa.codigo_lapso = '"+ codigo_lapso +"' and es.id_programa = "+ programa +" and essa.id_sancion = "+ tipo_sancion +" ) " +
				"from (select b.motivo as motivo, sum(b.apelaciones) apelaciones, 0 as procedente, b.cedula as cedula " +
				"from (SELECT distinct sa.cedula_estudiante as cedula, timo.nombre_tipo_motivo as motivo,count(distinct sa.cedula_estudiante) " +
				"as apelaciones, 0 as procedente from sigarep.tipo_motivo timo, sigarep.solicitud_apelacion sa " +
				"INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante " +
				"and essa.codigo_lapso = sa.codigo_lapso INNER JOIN sigarep.motivo as mo on mo.cedula_estudiante = sa.cedula_estudiante " +
				"and mo.codigo_lapso = sa.codigo_lapso and mo.id_instancia_apelada = sa.id_instancia_apelada " +
				"INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante " +
				"WHERE mo.id_tipo_motivo = timo.id_tipo_motivo and mo.id_tipo_motivo <> 1 and mo.id_tipo_motivo <> 2 " +
				"and mo.id_tipo_motivo <> 3 and sa.codigo_lapso = '"+ codigo_lapso +"' and es.id_programa = "+ programa +" " +
				"and essa.id_sancion = "+ tipo_sancion +"  and sa.veredicto = 'PROCEDENTE' group by cedula, motivo) as b group by b.motivo, " +
				"b.cedula union all select timo.nombre_tipo_motivo as motivo, 0 as apelaciones, count(sa.veredicto) as procedente, " +
				"essa.cedula_estudiante as cedula from sigarep.tipo_motivo timo, sigarep.solicitud_apelacion sa " +
				"INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante and " +
				"essa.codigo_lapso = sa.codigo_lapso INNER JOIN sigarep.motivo as mo on mo.cedula_estudiante = sa.cedula_estudiante " +
				"and mo.codigo_lapso = sa.codigo_lapso and mo.id_instancia_apelada = sa.id_instancia_apelada " +
				"INNER JOIN sigarep.sancion_maestro as sanma on essa.id_sancion=sanma.id_sancion " +
				"INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante " +
				"WHERE mo.id_tipo_motivo = timo.id_tipo_motivo and mo.id_tipo_motivo <> 1 and mo.id_tipo_motivo <> 2 " +
				"and mo.id_tipo_motivo <> 3 and sa.codigo_lapso = '"+ codigo_lapso +"' and es.id_programa = " + programa + " and essa.id_sancion = "+ tipo_sancion +" " +
				"and sa.veredicto = 'PROCEDENTE' group by timo.nombre_tipo_motivo, essa.cedula_estudiante) as v " +
				"group by v.cedula, v.motivo order by v.cedula, apelaciones desc";
		
//		String queryStatement2 = "select Distinct v.motivo as motivo, sum(v.apelaciones) apelaciones, sum(v.procedente)-sum(v.apelaciones-v.procedente) procedentes , "
//				+ "(SELECT COUNT(DISTINCT sa.cedula_estudiante) totalapelaciones FROM sigarep.solicitud_apelacion sa, sigarep.estudiante_sancionado essa, sigarep.estudiante es "
//				+ "WHERE sa.cedula_estudiante = essa.cedula_estudiante "
//				+ "and essa.cedula_estudiante = es.cedula_estudiante "
//				+ "and sa.codigo_lapso = " + "'"+ codigo_lapso+ "' " + "and es.id_programa = "+ "'"+ programa+ "' "
//				+ "and essa.id_sancion = "+ "'"+ tipo_sancion+ "' ), "
//				+ "(SELECT COUNT(DISTINCT essa.cedula_estudiante) sancionados FROM sigarep.estudiante_sancionado essa, sigarep.estudiante es "
//				+ "WHERE essa.cedula_estudiante = es.cedula_estudiante "
//				+ "and essa.codigo_lapso = "+ "'"+ codigo_lapso+ "' "+ "and es.id_programa = "+ "'"+ programa+ "' "
//				+ "and essa.id_sancion = "+ "'"+ tipo_sancion+ "' )"
//				+ "from "
//				+ "(select b.motivo as motivo, sum(b.apelaciones) apelaciones, 0 as procedente "
//				+ "from "
//				+ "(SELECT distinct timo.nombre_tipo_motivo as motivo,count(distinct sa.cedula_estudiante) "
//				+ "as apelaciones, 0 as procedente "
//				+ "from sigarep.tipo_motivo timo, sigarep.solicitud_apelacion sa "
//				+ "INNER JOIN sigarep.estudiante_sancionado as essa "
//				+ "on essa.cedula_estudiante = sa.cedula_estudiante "
//				+ "and essa.codigo_lapso = sa.codigo_lapso "
//				+ "INNER JOIN sigarep.motivo as mo on mo.cedula_estudiante = sa.cedula_estudiante "
//				+ "and mo.codigo_lapso = sa.codigo_lapso "
//				+ "and mo.id_instancia_apelada = sa.id_instancia_apelada "
//				+ "INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante "
//				+ "WHERE mo.id_tipo_motivo = timo.id_tipo_motivo "
//				+ "and mo.id_tipo_motivo <> 1 " + "and mo.id_tipo_motivo <> 2 "+ "and mo.id_tipo_motivo <> 3 "
//				+ "and sa.codigo_lapso = "+ "'"+ codigo_lapso+ "' "
//				+ "and es.id_programa = "+ "'"+ programa+ "' "+ "and essa.id_sancion = "+ "'"+ tipo_sancion+ "' "
//				+ "group by motivo) as b "
//				+ "group by b.motivo "
//				+ "union all "
//				+ "select timo.nombre_tipo_motivo as motivo, 0 as apelaciones, count(sa.veredicto) "
//				+ "as procedente "
//				+ "from sigarep.tipo_motivo timo, sigarep.solicitud_apelacion sa "
//				+ "INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante "
//				+ "and essa.codigo_lapso = sa.codigo_lapso "
//				+ "INNER JOIN sigarep.motivo as mo on mo.cedula_estudiante = sa.cedula_estudiante "
//				+ "and mo.codigo_lapso = sa.codigo_lapso "
//				+ "and mo.id_instancia_apelada = sa.id_instancia_apelada "
//				+ "INNER JOIN sigarep.sancion_maestro as sanma on essa.id_sancion=sanma.id_sancion "
//				+ "INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante "
//				+ "WHERE mo.id_tipo_motivo = timo.id_tipo_motivo "
//				+ "and mo.id_tipo_motivo <> 1 " + "and mo.id_tipo_motivo <> 2 " + "and mo.id_tipo_motivo <> 3 "
//				+ "and sa.codigo_lapso = "+ "'"+ codigo_lapso+ "' " + "and es.id_programa = "+ "'"+ programa+ "' "
//				+ "and essa.id_sancion = "+ "'"+ tipo_sancion+ "' " + "and sa.veredicto = 'PROCEDENTE' "
//				+ "group by timo.nombre_tipo_motivo) as v " + "group by v.motivo " + "order by apelaciones desc";
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();

		List<ApelacionesComparativos> results = new ArrayList<ApelacionesComparativos>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesComparativos((String) resultRow[1],
					((Number) resultRow[2]).intValue(),
					((Number) resultRow[3]).intValue(),
					((Number) resultRow[4]).intValue(),
					((Number) resultRow[5]).intValue()));
		}
		return results;
	}
	/**
	 * Buscar Apelaciones por Motivo y Resultado con todos los tipos de sancion
	 * 
	 * @param Integer
	 *            programa,String codigo_lapso
	 * @return Lista con la cantidad de apelaciones por tipo de motivo y sus
	 *         veredictos
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public List<ApelacionesComparativos> buscarPorMotivoResultado_Programa(
			String codigo_lapso, int programa) {
		String queryStatement = "select Distinct on (v.cedula) v.cedula, v.motivo as motivo, sum(v.apelaciones) apelaciones, sum(v.procedente)-sum(v.apelaciones-v.procedente) procedentes, " +
				"(SELECT COUNT(Distinct sa.cedula_estudiante) total FROM sigarep.solicitud_apelacion sa, sigarep.estudiante_sancionado essa, sigarep.estudiante es " +
				"WHERE sa.cedula_estudiante = essa.cedula_estudiante and essa.cedula_estudiante = es.cedula_estudiante " +
				"and sa.codigo_lapso = '" + codigo_lapso + "' and es.id_programa = " + programa + " ), " +
				"(SELECT COUNT(essa.cedula_estudiante) sancionados FROM sigarep.estudiante_sancionado essa, sigarep.estudiante es " +
				"WHERE essa.cedula_estudiante = es.cedula_estudiante and essa.codigo_lapso = '" + codigo_lapso + "' " +
				"and es.id_programa = " + programa + "  ) " +
				"from (select b.motivo as motivo, sum(b.apelaciones) apelaciones, 0 as procedente, b.cedula as cedula " +
				"from (SELECT timo.nombre_tipo_motivo as motivo,count(distinct sa.cedula_estudiante) " +
				"as apelaciones, 0 as procedente, sa.cedula_estudiante as cedula " +
				"from sigarep.tipo_motivo timo, sigarep.solicitud_apelacion sa " +
				"INNER JOIN sigarep.estudiante_sancionado as essa " +
				"on essa.cedula_estudiante = sa.cedula_estudiante " +
				"and essa.codigo_lapso = sa.codigo_lapso " +
				"INNER JOIN sigarep.motivo as mo on mo.cedula_estudiante = sa.cedula_estudiante " +
				"and mo.codigo_lapso = sa.codigo_lapso " +
				"and mo.id_instancia_apelada = sa.id_instancia_apelada " +
				"INNER JOIN sigarep.sancion_maestro as sanma on essa.id_sancion=sanma.id_sancion " +
				"INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante " +
				"INNER JOIN sigarep.programa_academico as prog on es.id_programa = prog.id_programa " +
				"WHERE mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and mo.id_tipo_motivo <> 1 and mo.id_tipo_motivo <> 2 and mo.id_tipo_motivo <> 3 " +
				"and sa.codigo_lapso = '" + codigo_lapso + "' and prog.id_programa = " + programa + " and sa.veredicto = 'PROCEDENTE' " +
				"group by cedula, " +
				"motivo) as b " +
				"group by b.motivo, b.cedula " +
				"union all " +
				"select timo.nombre_tipo_motivo as motivo, 0 as apelaciones, count(sa.veredicto) " +
				"as procedente, essa.cedula_estudiante as cedula from sigarep.tipo_motivo timo, sigarep.solicitud_apelacion sa " +
				"INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante " +
				"and essa.codigo_lapso = sa.codigo_lapso " +
				"INNER JOIN sigarep.motivo as mo on mo.cedula_estudiante = sa.cedula_estudiante " +
				"and mo.codigo_lapso = sa.codigo_lapso " +
				"and mo.id_instancia_apelada = sa.id_instancia_apelada " +
				"INNER JOIN sigarep.sancion_maestro as sanma on essa.id_sancion=sanma.id_sancion " +
				"INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante " +
				"INNER JOIN sigarep.programa_academico as prog on es.id_programa = prog.id_programa " +
				"WHERE mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and mo.id_tipo_motivo <> 1 and mo.id_tipo_motivo <> 2 and mo.id_tipo_motivo <> 3 " +
				"and sa.codigo_lapso = '" + codigo_lapso + "' and prog.id_programa = " + programa + " " +
				"and sa.veredicto = 'PROCEDENTE' group by timo.nombre_tipo_motivo, essa.cedula_estudiante) as v group by v.cedula, v.motivo order by v.cedula, apelaciones desc";
		
//		String queryStatement2 = "select Distinct v.motivo as motivo, sum(v.apelaciones) apelaciones, sum(v.procedente)-sum(v.apelaciones-v.procedente) procedentes, "
//				+ "(SELECT COUNT(Distinct sa.cedula_estudiante) total FROM sigarep.solicitud_apelacion sa, sigarep.estudiante_sancionado essa, sigarep.estudiante es "
//				+ "WHERE sa.cedula_estudiante = essa.cedula_estudiante " + "and essa.cedula_estudiante = es.cedula_estudiante "
//				+ "and sa.codigo_lapso = " + "'"+ codigo_lapso+ "' " + "and es.id_programa = "+ "'"+ programa+ "' ), "
//				+ "(SELECT COUNT(essa.cedula_estudiante) sancionados FROM sigarep.estudiante_sancionado essa, sigarep.estudiante es "
//				+ "WHERE essa.cedula_estudiante = es.cedula_estudiante " + "and essa.codigo_lapso = "+ "'"+ codigo_lapso+ "' "
//				+ "and es.id_programa = "+ "'"+ programa+ "'  ) "
//				+ "from " + "(select b.motivo as motivo, sum(b.apelaciones) apelaciones, 0 as procedente "
//				+ "from " + "(SELECT timo.nombre_tipo_motivo as motivo,count(distinct sa.cedula_estudiante) "
//				+ "as apelaciones, 0 as procedente "
//				+ "from sigarep.tipo_motivo timo, sigarep.solicitud_apelacion sa "
//				+ "INNER JOIN sigarep.estudiante_sancionado as essa "
//				+ "on essa.cedula_estudiante = sa.cedula_estudiante "
//				+ "and essa.codigo_lapso = sa.codigo_lapso "
//				+ "INNER JOIN sigarep.motivo as mo on mo.cedula_estudiante = sa.cedula_estudiante "
//				+ "and mo.codigo_lapso = sa.codigo_lapso "
//				+ "and mo.id_instancia_apelada = sa.id_instancia_apelada "
//				+ "INNER JOIN sigarep.sancion_maestro as sanma on essa.id_sancion=sanma.id_sancion "
//				+ "INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante "
//				+ "INNER JOIN sigarep.programa_academico as prog on es.id_programa = prog.id_programa "
//				+ "WHERE mo.id_tipo_motivo = timo.id_tipo_motivo "
//				+ "and mo.id_tipo_motivo <> 1 " + "and mo.id_tipo_motivo <> 2 " + "and mo.id_tipo_motivo <> 3 "
//				+ "and sa.codigo_lapso = "+ "'"+ codigo_lapso+ "' " + "and prog.id_programa = "+ "'"+ programa+ "' "
//				+ "group by motivo) as b "
//				+ "group by b.motivo "
//				+ "union all "
//				+ "select timo.nombre_tipo_motivo as motivo, 0 as apelaciones, count(sa.veredicto) "
//				+ "as procedente "
//				+ "from sigarep.tipo_motivo timo, sigarep.solicitud_apelacion sa "
//				+ "INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante "
//				+ "and essa.codigo_lapso = sa.codigo_lapso "
//				+ "INNER JOIN sigarep.motivo as mo on mo.cedula_estudiante = sa.cedula_estudiante "
//				+ "and mo.codigo_lapso = sa.codigo_lapso "
//				+ "and mo.id_instancia_apelada = sa.id_instancia_apelada "
//				+ "INNER JOIN sigarep.sancion_maestro as sanma on essa.id_sancion=sanma.id_sancion "
//				+ "INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante "
//				+ "INNER JOIN sigarep.programa_academico as prog on es.id_programa = prog.id_programa "
//				+ "WHERE mo.id_tipo_motivo = timo.id_tipo_motivo "
//				+ "and mo.id_tipo_motivo <> 1 " + "and mo.id_tipo_motivo <> 2 " + "and mo.id_tipo_motivo <> 3 "
//			    + "and sa.codigo_lapso = "+ "'"+ codigo_lapso+ "' " + "and prog.id_programa = "+ "'"+ programa+ "' "
//				+ "and sa.veredicto = 'PROCEDENTE' " + "group by timo.nombre_tipo_motivo) as v " + "group by v.motivo " + "order by apelaciones desc";
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		List<ApelacionesComparativos> results = new ArrayList<ApelacionesComparativos>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesComparativos((String) resultRow[1],
					((Number) resultRow[2]).intValue(),
					( (Number) resultRow[3]).intValue(),
					( (Number) resultRow[4]).intValue(),
					( (Number) resultRow[5]).intValue()));
		}
		return results;
	}
	/**
	 * Buscar Apelaciones por Instancia y Resultado con todos los tipos de
	 * sancion
	 * 
	 * @param Integer
	 *            programa,String codigo_lapso
	 * @return Lista con la cantidad de apelaciones por tipo de motivo y sus
	 *         veredictos
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public List<ApelacionesComparativos> buscarPorInstanciaResultado_Programa(
			String codigo_lapso, int programa) {
		
		String queryStatement = "select v.apelacion as apelacion, sum(v.apelaciones) apelaciones, sum(v.procedente) procedentes, "
				+ "(SELECT COUNT(sa.cedula_estudiante) total FROM sigarep.solicitud_apelacion sa "
				+ "INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante "
				+ "and essa.codigo_lapso = sa.codigo_lapso "
				+ "INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante "
				+ "WHERE sa.codigo_lapso = " + "'"+ codigo_lapso+ "' " + "and es.id_programa = "+ "'"+ programa+ "' ), "
				+ "(SELECT COUNT(essa.cedula_estudiante) sancionados FROM sigarep.estudiante_sancionado essa, sigarep.estudiante es "
				+ "WHERE essa.cedula_estudiante = es.cedula_estudiante "
				+ "and essa.codigo_lapso = "+ "'"+ codigo_lapso+ "' " + "and es.id_programa = " + "'"+ programa+ "' )"
				+ "from "
				+ "(select b.apelacion as apelacion, sum(b.apelaciones) apelaciones, 0 as procedente "
				+ "from "
				+ "(SELECT ins.nombre_recurso_apelacion as apelacion,count(sa.cedula_estudiante) as apelaciones, 0 as procedente "
				+ "from sigarep.instancia_apelada ins, sigarep.solicitud_apelacion sa "
				+ "INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante "
				+ "and essa.codigo_lapso = sa.codigo_lapso "
				+ "INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante "
				+ "WHERE ins.id_instancia_apelada = sa.id_instancia_apelada " + "and sa.codigo_lapso = "+ "'"+ codigo_lapso+ "' "
				+ "and es.id_programa = "+ "'"+ programa+ "' "
				+ "group by apelacion) as b "
				+ "group by b.apelacion "
				+ "union all "
				+ "select ins.nombre_recurso_apelacion as apelacion, 0 as apelaciones, count(sa.veredicto) as procedente "
				+ "from sigarep.instancia_apelada ins, sigarep.solicitud_apelacion sa "
				+ "INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante "
				+ "and essa.codigo_lapso = sa.codigo_lapso "
				+ "INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante "
				+ "WHERE ins.id_instancia_apelada = sa.id_instancia_apelada " + "and sa.codigo_lapso = "+ "'"+ codigo_lapso+ "' "
				+ "and es.id_programa = "+ "'"+ programa+ "' " + "and sa.veredicto = 'PROCEDENTE' "
				+ "group by apelacion) as v " + "group by v.apelacion " + "order by apelaciones desc";
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		List<ApelacionesComparativos> results = new ArrayList<ApelacionesComparativos>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesComparativos((String) resultRow[0],
					(((Number) resultRow[1]).intValue()),
					( ((Number) resultRow[2]).intValue()),
					( ((Number) resultRow[3]).intValue()),
					( ((Number) resultRow[4]).intValue())));
		}
		return results;
	}
	/**
	 * Buscar Apelaciones por Instancia y Resultado
	 * 
	 * @param Integer
	 *            programa,String codigo_lapso, Integer tipo_sancion
	 * @return Lista con la cantidad de apelaciones por tipo de instancia y sus
	 *         veredictos
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public List<ApelacionesComparativos> buscarPorInstanciaResultado_ProgramaSancion(
			String codigo_lapso, int tipo_sancion, int programa) {
		String queryStatement = "select v.apelacion as apelacion, sum(v.apelaciones) apelaciones, sum(v.procedente) procedentes, "
				+ "(SELECT COUNT(sa.cedula_estudiante) total FROM sigarep.solicitud_apelacion sa "
				+ "INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante "
				+ "and essa.codigo_lapso = sa.codigo_lapso "
				+ "INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante "
				+ "WHERE sa.codigo_lapso = " + "'"+ codigo_lapso+ "' " + "and es.id_programa = "+ "'"+ programa+ "' "
				+ "and essa.id_sancion = "+ "'"+ tipo_sancion+ "' ), "
				+ "(SELECT COUNT(essa.cedula_estudiante) sancionados FROM sigarep.estudiante_sancionado essa, sigarep.estudiante es "
				+ "WHERE essa.cedula_estudiante = es.cedula_estudiante "
				+ "and essa.codigo_lapso = "+ "'"+ codigo_lapso+ "' " + "and es.id_programa = "+ "'"+ programa+ "' "
				+ "and essa.id_sancion = "+ "'"+ tipo_sancion+ "' ) "
				+ "from "
				+ "(select b.apelacion as apelacion, sum(b.apelaciones) apelaciones, 0 as procedente "
				+ "from "
				+ "(SELECT ins.nombre_recurso_apelacion as apelacion,count(sa.cedula_estudiante) as apelaciones, 0 as procedente "
				+ "from sigarep.instancia_apelada ins, sigarep.solicitud_apelacion sa "
				+ "INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante "
				+ "and essa.codigo_lapso = sa.codigo_lapso "
				+ "INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante "
				+ "WHERE ins.id_instancia_apelada = sa.id_instancia_apelada "
				+ "and sa.codigo_lapso = "+ "'"+ codigo_lapso+ "' "
				+ "and es.id_programa = "+ "'"+ programa+ "' " + "and essa.id_sancion = "+ "'"+ tipo_sancion+ "' "
				+ "group by apelacion) as b "
				+ "group by b.apelacion "
				+ "union all "
				+ "select ins.nombre_recurso_apelacion as instancia, 0 as apelaciones, count(sa.veredicto) as procedente "
				+ "from sigarep.instancia_apelada ins, sigarep.solicitud_apelacion sa "
				+ "INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante "
				+ "and essa.codigo_lapso = sa.codigo_lapso "
				+ "INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante "
				+ "WHERE ins.id_instancia_apelada = sa.id_instancia_apelada "
				+ "and sa.codigo_lapso = "
				+ "'"+ codigo_lapso+ "' " + "and es.id_programa = "+ "'"+ programa+ "' " + "and essa.id_sancion = "+ "'"+ tipo_sancion+ "' "
				+ "and sa.veredicto = 'PROCEDENTE' " + "group by instancia) as v " + "group by v.apelacion "
				+ "order by apelaciones desc";
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		List<ApelacionesComparativos> results = new ArrayList<ApelacionesComparativos>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesComparativos((String) resultRow[0],
					((Number) resultRow[1]).intValue(),
					((Number) resultRow[2]).intValue(),
					((Number) resultRow[3]).intValue(),
					((Number) resultRow[4]).intValue()));
		}
		return results;
	}
	/**
	 * Buscar Apelaciones por Tipo de Sexo y Resultado con todos los tipos de
	 * sancion
	 * 
	 * @param Integer
	 *            programa,String codigo_lapso
	 * @return Lista con la cantidad de apelaciones por tipo de sexo y sus
	 *         veredictos
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public List<ApelacionesComparativos> buscarPorSexoResultado_Programa(
			String codigo_lapso, int programa) {
		String queryStatement = "select v.sexo as sexo, sum(v.apelaciones) apelaciones, sum(v.procedente) procedentes, "
				+ "(SELECT COUNT(sa.cedula_estudiante) total FROM sigarep.solicitud_apelacion sa "
				+ "INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante "
				+ "and essa.codigo_lapso = sa.codigo_lapso "
				+ "INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante "
				+ "WHERE sa.codigo_lapso = " + "'"+ codigo_lapso+ "' " + "and es.id_programa = "+ "'"+ programa+ "' ), "
				+ "(SELECT COUNT(essa.cedula_estudiante) sancionados FROM sigarep.estudiante_sancionado essa, sigarep.estudiante es "
				+ "WHERE essa.cedula_estudiante = es.cedula_estudiante " + "and essa.codigo_lapso = "+ "'"+ codigo_lapso+ "' "
				+ "and es.id_programa = "+ "'"+ programa+ "' ) "
				+ "from " + "(select b.sexo as sexo, sum(b.apelaciones) apelaciones, 0 as procedente "
				+ "from " + "(SELECT es.sexo as sexo,count(sa.cedula_estudiante) as apelaciones, 0 as procedente "
				+ "from sigarep.solicitud_apelacion sa "
				+ "INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante "
				+ "and essa.codigo_lapso = sa.codigo_lapso "
				+ "INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante "
				+ "INNER JOIN sigarep.sancion_maestro as sanma on essa.id_sancion=sanma.id_sancion "
				+ "WHERE sa.codigo_lapso = "+ "'"+ codigo_lapso+ "' " + "and es.id_programa = "+ "'"+ programa+ "' "
				+ "group by sexo) as b "
				+ "group by b.sexo "
				+ "union all "
				+ "select es.sexo as sexo, 0 as apelaciones, count(sa.veredicto) as procedente "
				+ "from sigarep.solicitud_apelacion sa "
				+ "INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante "
				+ "and essa.codigo_lapso = sa.codigo_lapso "
				+ "INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante "
				+ "WHERE sa.codigo_lapso = "+ "'"+ codigo_lapso+ "' "
				+ "and es.id_programa = "+ "'"+ programa+ "' " + "and sa.veredicto = 'PROCEDENTE' "
				+ "group by sexo) as v " + "group by v.sexo " + "order by apelaciones desc";
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		List<ApelacionesComparativos> results = new ArrayList<ApelacionesComparativos>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesComparativos((String) resultRow[0],
					((Number) resultRow[1]).intValue(),
					((Number) resultRow[2]).intValue(),
					((Number) resultRow[3]).intValue(),
					((Number) resultRow[4]).intValue()));
		}
		return results;
	}
	/**
	 * Buscar Apelaciones por Tipo de Sexo y Resultado
	 * 
	 * @param Integer
	 *            programa,String codigo_lapso, Integer tipo_sancion
	 * @return Lista con la cantidad de apelaciones por tipo de sexo y sus
	 *         veredictos
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public List<ApelacionesComparativos> buscarPorSexoResultado_ProgramaSancion(
			String codigo_lapso, int tipo_sancion, int programa) {
		String queryStatement = "select v.sexo as sexo, sum(v.apelaciones) apelaciones, sum(v.procedente) procedentes, "
				+ "(SELECT COUNT(sa.cedula_estudiante) total FROM sigarep.solicitud_apelacion sa "
				+ "INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante "
				+ "and essa.codigo_lapso = sa.codigo_lapso "
				+ "INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante "
				+ "WHERE sa.codigo_lapso = " + "'"+ codigo_lapso+ "' "
				+ "and es.id_programa = "+ "'"+ programa+ "' "
				+ "and essa.id_sancion = "+ "'"+ tipo_sancion+ "'), "
				+ "(SELECT COUNT(essa.cedula_estudiante) sancionados FROM sigarep.estudiante_sancionado essa, sigarep.estudiante es "
				+ "WHERE essa.cedula_estudiante = es.cedula_estudiante " + "and essa.codigo_lapso = "+ "'"+ codigo_lapso+ "' "
				+ "and essa.id_sancion = "+ "'"+ tipo_sancion+ "' " + "and es.id_programa = "+ "'"+ programa+ "' ) "
				+ "from "
				+ "(select b.sexo as sexo, sum(b.apelaciones) apelaciones, 0 as procedente "
				+ "from "
				+ "(SELECT es.sexo as sexo,count(sa.cedula_estudiante) as apelaciones, 0 as procedente "
				+ "from sigarep.solicitud_apelacion sa "
				+ "INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante "
				+ "and essa.codigo_lapso = sa.codigo_lapso "
				+ "INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante "
				+ "INNER JOIN sigarep.sancion_maestro as sanma on essa.id_sancion=sanma.id_sancion "
				+ "WHERE sa.codigo_lapso = "+ "'"+ codigo_lapso+ "' " + "and es.id_programa = "+ "'"+ programa+ "' " + "and essa.id_sancion = "+ "'"+ tipo_sancion+ "' "
				+ "group by sexo) as b "
				+ "group by b.sexo "
				+ "union all "
				+ "select es.sexo as sexo, 0 as apelaciones, count(sa.veredicto) as procedente "
				+ "from sigarep.solicitud_apelacion sa "
				+ "INNER JOIN sigarep.estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante "
				+ "and essa.codigo_lapso = sa.codigo_lapso "
				+ "INNER JOIN sigarep.estudiante as es on essa.cedula_estudiante = es.cedula_estudiante "
				+ "WHERE sa.codigo_lapso = "+ "'"+ codigo_lapso+ "' " + "and es.id_programa = "+ "'"+ programa+ "' "
				+ "and essa.id_sancion = "+ "'"+ tipo_sancion+ "' " + "and sa.veredicto = 'PROCEDENTE' "
				+ "group by sexo) as v "
				+ "group by v.sexo "
				+ "order by apelaciones desc";
		Query query = em.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		List<ApelacionesComparativos> results = new ArrayList<ApelacionesComparativos>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesComparativos((String) resultRow[0],
					((Number) resultRow[1]).intValue(),
					((Number) resultRow[2]).intValue(),
					((Number) resultRow[3]).intValue(),
					((Number) resultRow[4]).intValue()));
		}
		return results;
	}
}
