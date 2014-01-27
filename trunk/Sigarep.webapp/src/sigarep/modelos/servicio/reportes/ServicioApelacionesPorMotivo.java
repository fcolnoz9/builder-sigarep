package sigarep.modelos.servicio.reportes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;



import sigarep.modelos.data.reportes.ApelacionesPorMotivo;
import sigarep.modelos.data.reportes.Sancionados;

@Service("servicioapelacionespormotivo")
public class ServicioApelacionesPorMotivo {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<ApelacionesPorMotivo> buscarPorMotivoProgramaTodos(){
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, timo.nombre_tipo_motivo, prog.nombre_programa " +
				"FROM solicitud_apelacion sa, tipo_motivo timo, motivo mo, estudiante_sancionado essa, estudiante es, programa_academico prog " +
				"WHERE sa.cedula_estudiante = mo.cedula_estudiante and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and sa.cedula_estudiante = essa.cedula_estudiante and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"GROUP BY timo.nombre_tipo_motivo, prog.nombre_programa";

		
		Query query = em.createNativeQuery(queryStatement);
		
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorMotivo> results = new ArrayList<ApelacionesPorMotivo>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorMotivo(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1],
					(String) resultRow[2]));
		}
		
		return results;
	}
	
	public List<ApelacionesPorMotivo> buscarPorMotivoProgramaTodos(String codigo_lapso) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, timo.nombre_tipo_motivo, prog.nombre_programa " +
				"FROM solicitud_apelacion sa, tipo_motivo timo, motivo mo, estudiante_sancionado essa, estudiante es, programa_academico prog " +
				"WHERE sa.cedula_estudiante = mo.cedula_estudiante and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and sa.cedula_estudiante = essa.cedula_estudiante and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"and sa.codigo_lapso = "+ "'"+codigo_lapso+"' " +
				"GROUP BY timo.nombre_tipo_motivo, prog.nombre_programa";

		
		Query query = em.createNativeQuery(queryStatement);
		
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorMotivo> results = new ArrayList<ApelacionesPorMotivo>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorMotivo(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1],
					(String) resultRow[2]));
		}
		
		return results;
	}
	
	public List<ApelacionesPorMotivo> buscarPorMotivoPrograma_TipoSancion(String codigo_lapso, String tipo_sancion) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, timo.nombre_tipo_motivo, prog.nombre_programa " +
				"FROM sancion_maestro sanma, solicitud_apelacion sa, tipo_motivo timo, motivo mo, estudiante_sancionado essa, estudiante es, programa_academico prog " +
				"WHERE sa.cedula_estudiante = mo.cedula_estudiante and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and sa.cedula_estudiante = essa.cedula_estudiante and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"and sa.codigo_lapso = "+ "'"+codigo_lapso+"' " +
				"and essa.id_sancion = sanma.id_sancion " +
				"and sanma.nombre_sancion = "+ "'"+tipo_sancion+"' " +
				"GROUP BY timo.nombre_tipo_motivo, prog.nombre_programa";
		
						
		Query query = em.createNativeQuery(queryStatement);
		
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorMotivo> results = new ArrayList<ApelacionesPorMotivo>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorMotivo(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1],
					(String) resultRow[2]));
		}
		
		return results;
	}
	
	
	public List<ApelacionesPorMotivo> buscarPorMotivoResultado_ProgramaSancion(String codigo_lapso, String tipo_sancion, String programa) {
		String queryStatement =
				"SELECT COUNT(*) as numeroapelaciones, timo.nombre_tipo_motivo, sa.veredicto " +
				"FROM tipo_motivo timo, solicitud_apelacion sa " +
				"INNER JOIN estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante " +
				"and essa.codigo_lapso = sa.codigo_lapso " +
				"INNER JOIN motivo as mo on mo.cedula_estudiante = sa.cedula_estudiante " +
				"and mo.codigo_lapso = sa.codigo_lapso " +
				"and mo.id_instancia_apelada = sa.id_instancia_apelada " +
				"INNER JOIN sancion_maestro as sanma on essa.id_sancion=sanma.id_sancion " +
				"INNER JOIN estudiante as es on essa.cedula_estudiante = es.cedula_estudiante " +
				"INNER JOIN programa_academico as prog on es.id_programa = prog.id_programa " +
				"WHERE mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and sa.codigo_lapso = "+ "'"+codigo_lapso+"' " +
				"and sanma.nombre_sancion = "+ "'"+tipo_sancion+"' " +
				"and prog.nombre_programa = "+ "'"+programa+"' " +
				"GROUP BY timo.nombre_tipo_motivo, sa.veredicto " +
				"ORDER BY numeroapelaciones desc, timo.nombre_tipo_motivo asc, sa.veredicto asc";
		
						
		Query query = em.createNativeQuery(queryStatement);
		
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorMotivo> results = new ArrayList<ApelacionesPorMotivo>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorMotivo(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1],
					(String) resultRow[2]));
		}
		
		return results;
	}
	
	public List<ApelacionesPorMotivo> buscarPorMotivoResultado_Programa(String codigo_lapso, String programa) {
		String queryStatement =
				"SELECT COUNT(*) as numeroapelaciones, timo.nombre_tipo_motivo, sa.veredicto " +
				"FROM tipo_motivo timo, solicitud_apelacion sa " +
				"INNER JOIN estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante " +
				"and essa.codigo_lapso = sa.codigo_lapso " +
				"INNER JOIN motivo as mo on mo.cedula_estudiante = sa.cedula_estudiante " +
				"and mo.codigo_lapso = sa.codigo_lapso " +
				"and mo.id_instancia_apelada = sa.id_instancia_apelada " +
				"INNER JOIN sancion_maestro as sanma on essa.id_sancion=sanma.id_sancion " +
				"INNER JOIN estudiante as es on essa.cedula_estudiante = es.cedula_estudiante " +
				"INNER JOIN programa_academico as prog on es.id_programa = prog.id_programa " +
				"WHERE mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and sa.codigo_lapso = "+ "'"+codigo_lapso+"' " +
				"and prog.nombre_programa = "+ "'"+programa+"' " +
				//"and sa.veredicto = 'Aprobado' " +
				"GROUP BY timo.nombre_tipo_motivo, sa.veredicto " +
				"ORDER BY numeroapelaciones desc, timo.nombre_tipo_motivo asc, sa.veredicto asc";
		
						
		Query query = em.createNativeQuery(queryStatement);
		
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorMotivo> results = new ArrayList<ApelacionesPorMotivo>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorMotivo(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1],
					(String) resultRow[2]));
		}
		
		return results;
	}
	
	public List<ApelacionesPorMotivo> buscarPorInstanciaResultado_Programa(String codigo_lapso, String programa) {
		String queryStatement =
				"SELECT COUNT(*) as numeroapelaciones, inap.instancia_apelada, sa.veredicto " +
				"FROM instancia_apelada inap, solicitud_apelacion sa " +
				"INNER JOIN estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante " +
				"and essa.codigo_lapso = sa.codigo_lapso " +
				"INNER JOIN motivo as mo on mo.cedula_estudiante = sa.cedula_estudiante " +
				"and mo.codigo_lapso = sa.codigo_lapso " +
				"and mo.id_instancia_apelada = sa.id_instancia_apelada " +
				"INNER JOIN sancion_maestro as sanma on essa.id_sancion=sanma.id_sancion " +
				"INNER JOIN estudiante as es on essa.cedula_estudiante = es.cedula_estudiante " +
				"INNER JOIN programa_academico as prog on es.id_programa = prog.id_programa " +
				"WHERE sa.codigo_lapso = "+ "'"+codigo_lapso+"' " +
				"and prog.nombre_programa = "+ "'"+programa+"' " +
				"and sa.id_instancia_apelada = inap.id_instancia_apelada " +
				"GROUP BY inap.instancia_apelada, sa.veredicto " +
				"ORDER BY numeroapelaciones desc, inap.instancia_apelada, sa.veredicto";
		
						
		Query query = em.createNativeQuery(queryStatement);
		
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorMotivo> results = new ArrayList<ApelacionesPorMotivo>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorMotivo(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1],
					(String) resultRow[2]));
		}
		
		return results;
	}
	
	public List<ApelacionesPorMotivo> buscarPorInstanciaResultado_ProgramaSancion(String codigo_lapso, String tipo_sancion, String programa) {
		String queryStatement =
				"SELECT COUNT(*) as numeroapelaciones, inap.instancia_apelada, sa.veredicto " +
						"FROM instancia_apelada inap, solicitud_apelacion sa " +
						"INNER JOIN estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante " +
						"and essa.codigo_lapso = sa.codigo_lapso " +
						"INNER JOIN motivo as mo on mo.cedula_estudiante = sa.cedula_estudiante " +
						"and mo.codigo_lapso = sa.codigo_lapso " +
						"and mo.id_instancia_apelada = sa.id_instancia_apelada " +
						"INNER JOIN sancion_maestro as sanma on essa.id_sancion=sanma.id_sancion " +
						"INNER JOIN estudiante as es on essa.cedula_estudiante = es.cedula_estudiante " +
						"INNER JOIN programa_academico as prog on es.id_programa = prog.id_programa " +
						"WHERE sa.codigo_lapso = "+ "'"+codigo_lapso+"' " +
						"and prog.nombre_programa = "+ "'"+programa+"' " +
						"and sanma.nombre_sancion = "+ "'"+tipo_sancion+"' " +
						"and sa.id_instancia_apelada = inap.id_instancia_apelada " +
						"GROUP BY inap.instancia_apelada, sa.veredicto " +
						"ORDER BY numeroapelaciones desc, inap.instancia_apelada, sa.veredicto";
		
						
		Query query = em.createNativeQuery(queryStatement);
		
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorMotivo> results = new ArrayList<ApelacionesPorMotivo>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorMotivo(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1],
					(String) resultRow[2]));
		}
		
		return results;
	}
	

	public List<Sancionados> buscarSancionados(String codigo_lapso, String nombre_programa){
		String queryStatement =
				"SELECT es.cedula_estudiante, es.primer_nombre, es.primer_apellido, " +
				"sanma.nombre_sancion, sa.veredicto, sa.observacion " +
				"FROM tipo_motivo timo, solicitud_apelacion sa " +
				"INNER JOIN estudiante_sancionado as essa on essa.cedula_estudiante = sa.cedula_estudiante " +
				"and essa.codigo_lapso = sa.codigo_lapso " +
				"INNER JOIN motivo as mo on mo.cedula_estudiante = sa.cedula_estudiante " +
				"and mo.codigo_lapso = sa.codigo_lapso " +
				"and mo.id_instancia_apelada = sa.id_instancia_apelada " +
				"INNER JOIN sancion_maestro as sanma on essa.id_sancion=sanma.id_sancion " +
				"INNER JOIN estudiante as es on essa.cedula_estudiante = es.cedula_estudiante " +
				"INNER JOIN programa_academico as prog on es.id_programa = prog.id_programa " +
				"WHERE mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and sa.codigo_lapso = "+ "'"+codigo_lapso+"' " +
				"and prog.nombre_programa = "+ "'"+nombre_programa+"' ";

		
		Query query = em.createNativeQuery(queryStatement);
		
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<Sancionados> results = new ArrayList<Sancionados>();
		for (Object[] resultRow : resultSet) {
			results.add(new Sancionados((String) resultRow[0], (String) resultRow[1], (String) resultRow[2], 
										(String) resultRow[3], (String) resultRow[4], (String) resultRow[5]));
		}
		System.out.println(results.get(0).getCedula());
		return results;
	}




}
