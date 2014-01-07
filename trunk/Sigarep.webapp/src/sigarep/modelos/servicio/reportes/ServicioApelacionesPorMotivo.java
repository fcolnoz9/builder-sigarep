/*package sigarep.modelos.servicio.reportes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import sigarep.modelos.data.reportes.ApelacionesPorMotivo;
import sigarep.modelos.repositorio.reportes.IApelacionesPorMotivoDAO;

@Service("servicioapelacionespormotivo")
public class ServicioApelacionesPorMotivo implements IApelacionesPorMotivoDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	
	private @Autowired IApelacionesPorMotivoDAO apm;
	
	
		@Override
	public List<ApelacionesPorMotivo> buscarTodos() {
			String queryStatement = 
					"SELECT COUNT(*) As numeroapelaciones, timo.nombre_tipo_motivo " +
					"FROM solicitud_apelacion sa, tipo_motivo timo, motivo mo " +
					"WHERE sa.cedula_estudiante = mo.cedula_estudiante " +
					"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
					"GROUP BY timo.nombre_tipo_motivo";
					
			Query query = em.createNativeQuery(queryStatement);
			
			@SuppressWarnings("unchecked")
			List<Object[]> resultSet = query.getResultList();
			
			List<ApelacionesPorMotivo> results = new ArrayList<ApelacionesPorMotivo>();
			for (Object[] resultRow : resultSet) {
				results.add(new ApelacionesPorMotivo(
						((BigInteger) resultRow[0]).intValue(),
						(String) resultRow[1]));
			}
			
			return results;
	}

	@Override
	public List<ApelacionesPorMotivo> buscarPorPrograma(String programa) {

		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, timo.nombre_tipo_motivo " +
				"FROM tipo_motivo timo, motivo mo, solicitud_apelacion sa, estudiante es, estudiante_sancionado essa, programa_academico prog " +
				"WHERE sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"and prog.nombre_programa = ? " +
				"and sa.cedula_estudiante = mo.cedula_estudiante " +
				"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"GROUP BY timo.nombre_tipo_motivo";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, programa);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorMotivo> results = new ArrayList<ApelacionesPorMotivo>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorMotivo(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	@Override
	public List<ApelacionesPorMotivo> buscarPorTipoSancion(String tiposancion) {

		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, timo.nombre_tipo_motivo " +
				"FROM motivo mo, tipo_motivo timo, solicitud_apelacion sa, estudiante_sancionado essa, programa_academico prog, sancion_maestro sanma " +
				"WHERE sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.id_sancion = sanma.id_sancion " +
				"and sanma.nombre_sancion = ? " +
				"and sa.cedula_estudiante = mo.cedula_estudiante " +
				"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"GROUP BY timo.nombre_tipo_motivo";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, tiposancion);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorMotivo> results = new ArrayList<ApelacionesPorMotivo>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorMotivo(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	@Override
	public List<ApelacionesPorMotivo> buscarPorLapso(String lapsoinicial,
			String lapsofinal) {

		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, timo.nombre_tipo_motivo " +
				"FROM motivo mo, tipo_motivo timo, solicitud_apelacion sa, estudiante es, estudiante_sancionado essa, programa_academico prog " +
				"WHERE sa.fecha_solicitud between " +
				"(SELECT la.fecha_inicio FROM solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) and " +
				"(SELECT la.fecha_cierre from solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) " +
				"and sa.cedula_estudiante = mo.cedula_estudiante " +
				"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"GROUP BY timo.nombre_tipo_motivo";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorMotivo> results = new ArrayList<ApelacionesPorMotivo>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorMotivo(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	
	@Override
	public List<ApelacionesPorMotivo> buscarPorTipoSancionYPrograma(
			String tiposancion, String programa) {
		
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, timo.nombre_tipo_motivo " +
				"FROM tipo_motivo timo, motivo mo, estudiante es, solicitud_apelacion sa, estudiante_sancionado essa, programa_academico prog, sancion_maestro sanma " +
				"WHERE sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.id_sancion = sanma.id_sancion " +
				"and sanma.nombre_sancion = ? " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"and prog.nombre_programa = ? " +
				"and sa.cedula_estudiante = mo.cedula_estudiante " +
				"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"GROUP BY timo.nombre_tipo_motivo";
		
		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, tiposancion);
		query.setParameter(2, programa);

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorMotivo> results = new ArrayList<ApelacionesPorMotivo>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorMotivo(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	@Override
	public List<ApelacionesPorMotivo> buscarPorLapsoYPrograma(
			String lapsoinicial, String lapsofinal, String programa) {

		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, timo.nombre_tipo_motivo from tipo_motivo timo, motivo mo, solicitud_apelacion sa, estudiante es, " +
				"estudiante_sancionado essa, programa_academico prog WHERE sa.fecha_solicitud " +
				"BETWEEN (SELECT la.fecha_inicio FROM solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) and " +
				"(SELECT la.fecha_cierre FROM solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) " +
				"and sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa and prog.nombre_programa = ? " +
				"and sa.cedula_estudiante = mo.cedula_estudiante " +
				"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"GROUP BY timo.nombre_tipo_motivo";
		
		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		query.setParameter(3, programa);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorMotivo> results = new ArrayList<ApelacionesPorMotivo>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorMotivo(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	
	@Override
	public List<ApelacionesPorMotivo> buscarPorLapsoYTipoSancion(
			String lapsoinicial, String lapsofinal, String tiposancion) {

		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, timo.nombre_tipo_motivo " +
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
				"GROUP BY timo.nombre_tipo_motivo";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		query.setParameter(3, tiposancion);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorMotivo> results = new ArrayList<ApelacionesPorMotivo>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorMotivo(
					((BigInteger) resultRow[1]).intValue(),
					(String) resultRow[0]));
		}
		
		return results;
	}

	@Override
	public List<ApelacionesPorMotivo> buscarPorLapsoYTipoSancionYPrograma(
			String lapsoinicial, String lapsofinal, String tiposancion,
			String programa) {

		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, timo.nombre_tipo_motivo " +
				"from tipo_motivo timo, motivo mo, sancion_maestro sanma, solicitud_apelacion sa, estudiante es, " +
				"estudiante_sancionado essa, programa_academico prog " +
				"WHERE sa.fecha_solicitud BETWEEN (SELECT la.fecha_inicio FROM solicitud_apelacion sa, " +
				"estudiante_sancionado essa, lapso_academico la WHERE sa.codigo_lapso = ? " +
				"and sa.codigo_lapso = essa.codigo_lapso and essa.codigo_lapso = la.codigo_lapso) " +
				"and (SELECT la.fecha_cierre FROM solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) " +
				"and sa.cedula_estudiante = essa.cedula_estudiante and essa.id_sancion = sanma.id_sancion " +
				"and sanma.nombre_sancion = ? and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa and prog.nombre_programa = ? " +
				"and sa.cedula_estudiante = mo.cedula_estudiante " +
				"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"GROUP BY timo.nombre_tipo_motivo";

		
		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		query.setParameter(3, tiposancion);
		query.setParameter(4, programa);
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorMotivo> results = new ArrayList<ApelacionesPorMotivo>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorMotivo(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}
	
}
*/