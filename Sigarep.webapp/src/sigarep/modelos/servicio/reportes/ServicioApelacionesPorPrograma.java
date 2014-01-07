/*package sigarep.modelos.servicio.reportes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import sigarep.modelos.data.reportes.ApelacionesPorPrograma;
import sigarep.modelos.repositorio.reportes.IApelacionesPorProgramaDAO;

@Service("servicioapelacionesporprograma")
public class ServicioApelacionesPorPrograma implements IApelacionesPorProgramaDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	
	private @Autowired IApelacionesPorProgramaDAO app;
	
	
		@Override
	public List<ApelacionesPorPrograma> buscarTodos() {
			String queryStatement = 
					"SELECT COUNT(*) As numeroapelaciones, prog.nombre_programa " +
					"FROM solicitud_apelacion sa, estudiante_sancionado essa, estudiante es, programa_academico prog " +
					"WHERE sa.cedula_estudiante = essa.cedula_estudiante " +
					"and essa.cedula_estudiante = es.cedula_estudiante " +
					"and es.id_programa = prog.id_programa" +
					"GROUP BY prog.nombre_programa";
					
			Query query = em.createNativeQuery(queryStatement);
			
			@SuppressWarnings("unchecked")
			List<Object[]> resultSet = query.getResultList();
			
			List<ApelacionesPorPrograma> results = new ArrayList<ApelacionesPorPrograma>();
			for (Object[] resultRow : resultSet) {
				results.add(new ApelacionesPorPrograma(
						((BigInteger) resultRow[0]).intValue(),
						(String) resultRow[1]));
			}
			
			return results;
	}

	@Override
	public List<ApelacionesPorPrograma> buscarPorTipoMotivo(String tipomotivo) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, prog.nombre_programa" +
				"FROM solicitud_apelacion sa, motivo mo, tipo_motivo timo, estudiante es, estudiante_sancionado essa, programa_academico prog " +
				"WHERE sa.cedula_estudiante = mo.cedula_estudiante " +
				"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and timo.nombre_tipo_motivo = ? " +
				"and sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"GROUP BY prog.nombre_programa";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, tipomotivo);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorPrograma> results = new ArrayList<ApelacionesPorPrograma>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorPrograma(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	@Override
	public List<ApelacionesPorPrograma> buscarPorTipoSancion(String tiposancion) {

		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, prog.nombre_programa " +
				"FROM solicitud_apelacion sa, estudiante_sancionado essa, programa_academico prog, sancion_maestro sanma, estudiante es " +
				"WHERE sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.id_sancion = sanma.id_sancion " +
				"and sanma.nombre_sancion = ? " +
				"and sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"GROUP BY prog.nombre_programa";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, tiposancion);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorPrograma> results = new ArrayList<ApelacionesPorPrograma>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorPrograma(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	@Override
	public List<ApelacionesPorPrograma> buscarPorLapso(String lapsoinicial,
			String lapsofinal) {

		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, prog.nombre_programa " +
				"FROM solicitud_apelacion sa, estudiante es, estudiante_sancionado essa, programa_academico prog " +
				"WHERE sa.fecha_solicitud between " +
				"(SELECT la.fecha_inicio FROM solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) and " +
				"(SELECT la.fecha_cierre from solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) " +
				"and sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"GROUP BY prog.nombre_programa";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorPrograma> results = new ArrayList<ApelacionesPorPrograma>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorPrograma(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	
	
	@Override
	public List<ApelacionesPorPrograma> buscarPorLapsoYTipoSancion(
			String lapsoinicial, String lapsofinal, String tiposancion) {

		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, prog.nombre_programa " +
				"FROM sancion_maestro sanma, solicitud_apelacion sa, estudiante es, " +
				"estudiante_sancionado essa, programa_academico prog " +
				"WHERE sa.fecha_solicitud " +
				"BETWEEN (SELECT la.fecha_inicio FROM solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) " +
				"and (SELECT la.fecha_cierre FROM solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) " +
				"and sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.id_sancion = sanma.id_sancion and sanma.nombre_sancion = ? " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"GROUP BY prog.nombre_programa";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		query.setParameter(3, tiposancion);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorPrograma> results = new ArrayList<ApelacionesPorPrograma>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorPrograma(
					((BigInteger) resultRow[1]).intValue(),
					(String) resultRow[0]));
		}
		
		return results;
	}

	@Override
	public List<ApelacionesPorPrograma> buscarPorTipoSancionYTipoMotivo(
			String tiposancion, String tipomotivo) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, prog.nombre_programa " +
				"FROM solicitud_apelacion sa, estudiante_sancionado essa, estudiante es, programa_academico prog, sancion_maestro sanma, motivo mo, tipo_motivo timo " +
				"WHERE sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.id_sancion = sanma.id_sancion " +
				"and sanma.descripcion = ? " +
				"and sa.cedula_estudiante = mo.cedula_estudiante " +
				"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and timo.nombre_tipo_motivo = ? " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"GROUP BY prog.nombre_programa";
		
		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, tiposancion);
		query.setParameter(2, tipomotivo);

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorPrograma> results = new ArrayList<ApelacionesPorPrograma>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorPrograma(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	
	
	@Override
	public List<ApelacionesPorPrograma> buscarPorLapsoYTipoMotivo(
			String lapsoinicial, String lapsofinal, String tipomotivo) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, prog.nombre_programa " +
				"FROM solicitud_apelacion sa, estudiante es, " +
				"estudiante_sancionado essa, programa_academico prog, motivo mo, tipo_motivo timo" +
				"WHERE sa.fecha_solicitud " +
				"BETWEEN (SELECT la.fecha_inicio FROM solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) " +
				"and (SELECT la.fecha_cierre FROM solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) " +
				"and sa.cedula_estudiante = mo.cedula_estudiante " +
				"and mo.id_tipo_motivo = timo.id_tipo_motivo" +
				"and timo.nombre_tipo_motivo = ? " +
				"and sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"GROUP BY prog.nombre_programa";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		query.setParameter(3, tipomotivo);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorPrograma> results = new ArrayList<ApelacionesPorPrograma>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorPrograma(
					((BigInteger) resultRow[1]).intValue(),
					(String) resultRow[0]));
		}
		
		return results;
	}

	
	@Override
	public List<ApelacionesPorPrograma> buscarPorLapsoYTipoSancionYTipoMotivo(
			String lapsoinicial, String lapsofinal, String tiposancion,
			String tipomotivo) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, prog.nombre_programa " +
				"FROM motivo mo, tipo_motivo timo, sancion_maestro sanma, solicitud_apelacion sa, estudiante es, " +
				"estudiante_sancionado essa, programa_academico prog " +
				"WHERE sa.fecha_solicitud " +
				"BETWEEN (SELECT la.fecha_inicio FROM solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) " +
				"and (SELECT la.fecha_cierre FROM solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) " +
				"and sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.id_sancion = sanma.id_sancion and sanma.nombre_sancion = ? " +
				"and sa.cedula_estudiante = mo.cedula_estudiante " +
				"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and timo.nombre_tipo_motivo = ? " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"GROUP BY prog.nombre_programa";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		query.setParameter(3, tiposancion);
		query.setParameter(4, tipomotivo);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorPrograma> results = new ArrayList<ApelacionesPorPrograma>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorPrograma(
					((BigInteger) resultRow[1]).intValue(),
					(String) resultRow[0]));
		}
		
		return results;
	}

}
*/