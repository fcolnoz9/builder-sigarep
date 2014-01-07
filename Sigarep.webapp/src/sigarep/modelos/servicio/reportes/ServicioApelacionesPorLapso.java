/*package sigarep.modelos.servicio.reportes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import sigarep.modelos.data.reportes.ApelacionesPorLapso;
import sigarep.modelos.repositorio.reportes.IApelacionesPorLapsoDAO;

@Service("servicioapelacionesporlapso")
public class ServicioApelacionesPorLapso implements IApelacionesPorLapsoDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	
	private @Autowired IApelacionesPorLapsoDAO apl;
	
	
		@Override
	public List<ApelacionesPorLapso> buscarTodos() {
			String queryStatement = 
					"SELECT COUNT(*) As numeroapelaciones, sa.codigo_lapso " +
					"FROM solicitud_apelacion sa " +
					"GROUP BY sa.codigo_lapso";
					
			Query query = em.createNativeQuery(queryStatement);
			
			@SuppressWarnings("unchecked")
			List<Object[]> resultSet = query.getResultList();
			
			List<ApelacionesPorLapso> results = new ArrayList<ApelacionesPorLapso>();
			for (Object[] resultRow : resultSet) {
				results.add(new ApelacionesPorLapso(
						((BigInteger) resultRow[0]).intValue(),
						(String) resultRow[1]));
			}
			
			return results;
	}

	@Override
	public List<ApelacionesPorLapso> buscarPorPrograma(String programa) {

		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sa.codigo_lapso " +
				"FROM solicitud_apelacion sa, estudiante es, estudiante_sancionado essa, programa_academico prog " +
				"WHERE sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"and prog.nombre_programa = ? " +
				"GROUP BY sa.codigo_lapso";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, programa);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorLapso> results = new ArrayList<ApelacionesPorLapso>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorLapso(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	@Override
	public List<ApelacionesPorLapso> buscarPorTipoMotivo(String tipomotivo) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sa.codigo_lapso " +
				"FROM solicitud_apelacion sa, motivo mo, tipo_motivo timo " +
				"WHERE sa.cedula_estudiante = mo.cedula_estudiante " +
				"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and timo.nombre_tipo_motivo = ? " +
				"GROUP BY sa.codigo_lapso";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, tipomotivo);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorLapso> results = new ArrayList<ApelacionesPorLapso>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorLapso(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	@Override
	public List<ApelacionesPorLapso> buscarPorTipoSancion(String tiposancion) {

		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sa.codigo_lapso " +
				"FROM solicitud_apelacion sa, estudiante_sancionado essa, programa_academico prog, sancion_maestro sanma " +
				"WHERE sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.id_sancion = sanma.id_sancion " +
				"and sanma.nombre_sancion = ? " +
				"GROUP BY sa.codigo_lapso";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, tiposancion);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorLapso> results = new ArrayList<ApelacionesPorLapso>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorLapso(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	@Override
	public List<ApelacionesPorLapso> buscarPorLapso(String lapsoinicial,
			String lapsofinal) {

		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sa.codigo_lapso " +
				"FROM solicitud_apelacion sa, estudiante es, estudiante_sancionado essa, programa_academico prog " +
				"WHERE sa.fecha_solicitud between " +
				"(SELECT la.fecha_inicio FROM solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) and " +
				"(SELECT la.fecha_cierre from solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) " +
				"GROUP BY sa.codigo_lapso";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorLapso> results = new ArrayList<ApelacionesPorLapso>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorLapso(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	
	@Override
	public List<ApelacionesPorLapso> buscarPorTipoSancionYPrograma(
			String tiposancion, String programa) {
		
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sa.codigo_lapso " +
				"FROM estudiante es, solicitud_apelacion sa, estudiante_sancionado essa, programa_academico prog, sancion_maestro sanma " +
				"WHERE sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.id_sancion = sanma.id_sancion " +
				"and sanma.nombre_sancion = ? " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"and prog.nombre_programa = ? " +
				"GROUP BY sa.codigo_lapso";
		
		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, tiposancion);
		query.setParameter(2, programa);

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorLapso> results = new ArrayList<ApelacionesPorLapso>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorLapso(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	@Override
	public List<ApelacionesPorLapso> buscarPorLapsoYPrograma(
			String lapsoinicial, String lapsofinal, String programa) {

		String queryStatement =
				"SELECT COUNT(*), sa.codigo_lapso from solicitud_apelacion sa, estudiante es, " +
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
				"GROUP BY sa.codigo_lapso";
		
		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		query.setParameter(3, programa);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorLapso> results = new ArrayList<ApelacionesPorLapso>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorLapso(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	@Override
	public List<ApelacionesPorLapso> buscarPorTipoMotivoYPrograma(
			String programa, String tipomotivo) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sa.codigo_lapso " +
				"FROM solicitud_apelacion sa, estudiante_sancionado essa, estudiante es, programa_academico prog, sancion_maestro sanma, motivo mo, tipo_motivo timo " +
				"WHERE sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"and prog.nombre_programa = ? " +
				"and sa.cedula_estudiante = mo.cedula_estudiante " +
				"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and timo.nombre_tipo_motivo = ? " +
				"GROUP BY sa.codigo_lapso";
		
		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, programa);
		query.setParameter(2, tipomotivo);

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorLapso> results = new ArrayList<ApelacionesPorLapso>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorLapso(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}


	@Override
	public List<ApelacionesPorLapso> buscarPorLapsoYTipoSancion(
			String lapsoinicial, String lapsofinal, String tiposancion) {

		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sa.codigo_lapso " +
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
				"and essa.id_sancion = sanma.id_sancion and sanma.nombre_sancion = ?" +
				"GROUP BY sa.codigo_lapso";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		query.setParameter(3, tiposancion);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorLapso> results = new ArrayList<ApelacionesPorLapso>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorLapso(
					((BigInteger) resultRow[1]).intValue(),
					(String) resultRow[0]));
		}
		
		return results;
	}

	@Override
	public List<ApelacionesPorLapso> buscarPorLapsoYTipoMotivo(
			String lapsoinicial, String lapsofinal, String tipomotivo) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sa.codigo_lapso " +
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
				"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and timo.nombre_tipo_motivo = ? " +
				"GROUP BY sa.codigo_lapso";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		query.setParameter(3, tipomotivo);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorLapso> results = new ArrayList<ApelacionesPorLapso>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorLapso(
					((BigInteger) resultRow[1]).intValue(),
					(String) resultRow[0]));
		}
		
		return results;
	}
	
	
	@Override
	public List<ApelacionesPorLapso> buscarPorTipoSancionYTipoMotivo(
			String tiposancion, String tipomotivo) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sa.codigo_lapso " +
				"FROM solicitud_apelacion sa, estudiante_sancionado essa, estudiante es, programa_academico prog, sancion_maestro sanma, motivo mo, tipo_motivo timo " +
				"WHERE sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.id_sancion = sanma.id_sancion " +
				"and sanma.descripcion = ? " +
				"and sa.cedula_estudiante = mo.cedula_estudiante " +
				"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and timo.nombre_tipo_motivo = ? " +
				"GROUP BY sa.codigo_lapso";
		
		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, tiposancion);
		query.setParameter(2, tipomotivo);

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorLapso> results = new ArrayList<ApelacionesPorLapso>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorLapso(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}
	
	
	
	@Override
	public List<ApelacionesPorLapso> buscarPorLapsoYTipoSancionYPrograma(
			String lapsoinicial, String lapsofinal, String tiposancion,
			String programa) {

		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sa.codigo_lapso " +
				"from sancion_maestro sanma, solicitud_apelacion sa, estudiante es, " +
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
				"GROUP BY sa.codigo_lapso";

		
		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		query.setParameter(3, tiposancion);
		query.setParameter(4, programa);
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorLapso> results = new ArrayList<ApelacionesPorLapso>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorLapso(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	@Override
	public List<ApelacionesPorLapso> buscarPorLapsoYTipoMotivoYPrograma(
			String lapsoinicial, String lapsofinal, String tipomotivo,
			String programa) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sa.codigo_lapso " +
				"from sancion_maestro sanma, solicitud_apelacion sa, estudiante es, " +
				"estudiante_sancionado essa, programa_academico prog, motivo mo, tipo_motivo timo " +
				"WHERE sa.fecha_solicitud BETWEEN (SELECT la.fecha_inicio FROM solicitud_apelacion sa, " +
				"estudiante_sancionado essa, lapso_academico la WHERE sa.codigo_lapso = ? " +
				"and sa.codigo_lapso = essa.codigo_lapso and essa.codigo_lapso = la.codigo_lapso) " +
				"and (SELECT la.fecha_cierre FROM solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) " +
				"and sa.cedula_estudiante = mo.cedula_estudiante " +
				"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and timo.nombre_tipo_motivo = ? " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa and prog.nombre_programa = ? " +
				"GROUP BY sa.codigo_lapso";

		
		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		query.setParameter(3, tipomotivo);
		query.setParameter(4, programa);
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorLapso> results = new ArrayList<ApelacionesPorLapso>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorLapso(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	@Override
	public List<ApelacionesPorLapso> buscarPorProgramaYTipoSancionYTipoMotivo(
			String programa, String tiposancion, String tipomotivo) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sa.codigo_lapso " +
				"FROM motivo mo, tipo_motivo timo, estudiante es, solicitud_apelacion sa, estudiante_sancionado essa, programa_academico prog, sancion_maestro sanma " +
				"WHERE sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.id_sancion = sanma.id_sancion " +
				"and sanma.nombre_sancion = ? " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"and prog.nombre_programa = ? " +
				"and sa.cedula_estudiante = mo.cedula_estudiante " +
				"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and timo.nombre_tipo_motivo = ? " +
				"GROUP BY sa.codigo_lapso";
		
		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, tiposancion);
		query.setParameter(2, programa);
		query.setParameter(3, tipomotivo);

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorLapso> results = new ArrayList<ApelacionesPorLapso>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorLapso(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	
	@Override
	public List<ApelacionesPorLapso> buscarPorLapsoYTipoSancionYTipoMotivo(
			String lapsoinicial, String lapsofinal, String tiposancion,
			String tipomotivo) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sa.codigo_lapso " +
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
				"GROUP BY sa.codigo_lapso";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		query.setParameter(3, tiposancion);
		query.setParameter(4, tipomotivo);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorLapso> results = new ArrayList<ApelacionesPorLapso>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorLapso(
					((BigInteger) resultRow[1]).intValue(),
					(String) resultRow[0]));
		}
		
		return results;
	}

	
	@Override
	public List<ApelacionesPorLapso> buscarPorLapsoYTipoSancionYTipoMotivoYPrograma(
			String lapsoinicial, String lapsofinal, String tiposancion,
			String programa, String tipomotivo) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sa.codigo_lapso " +
				"from motivo mo, tipo_motivo timo, sancion_maestro sanma, solicitud_apelacion sa, estudiante es, " +
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
				"and timo.nombre_tipo_motivo = ? " +
				"GROUP BY sa.codigo_lapso";

		
		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		query.setParameter(3, tiposancion);
		query.setParameter(4, programa);
		query.setParameter(5, tipomotivo);
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorLapso> results = new ArrayList<ApelacionesPorLapso>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorLapso(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}	
	
}*/