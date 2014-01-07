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
import sigarep.modelos.data.reportes.ApelacionesPorTipoSancion;
import sigarep.modelos.repositorio.reportes.IApelacionesPorTipoSancionDAO;

@Service("servicioapelacionesportiposancion")
public class ServicioApelacionesPorTipoSancion implements IApelacionesPorTipoSancionDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	
	private @Autowired IApelacionesPorTipoSancionDAO apl;
	
	
		@Override
	public List<ApelacionesPorTipoSancion> buscarTodos() {
			String queryStatement = 
					"SELECT COUNT(*) As numeroapelaciones, sanma.nombre_sancion " +
					"FROM solicitud_apelacion sa, sancion_maestro sanma, estudiante_sancionado essa " +
					"WHERE sa.cedula_estudiante = essa.cedula_estudiante " +
					"and essa.id_sancion = sanma.id_sacion" +
					"GROUP BY sanma.nombre_sancion";
					
			Query query = em.createNativeQuery(queryStatement);
			
			@SuppressWarnings("unchecked")
			List<Object[]> resultSet = query.getResultList();
			
			List<ApelacionesPorTipoSancion> results = new ArrayList<ApelacionesPorTipoSancion>();
			for (Object[] resultRow : resultSet) {
				results.add(new ApelacionesPorTipoSancion(
						((BigInteger) resultRow[0]).intValue(),
						(String) resultRow[1]));
			}
			
			return results;
	}

	@Override
	public List<ApelacionesPorTipoSancion> buscarPorPrograma(String programa) {

		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sanma.nombre_sancion " +
				"FROM solicitud_apelacion sa, estudiante es, estudiante_sancionado essa,\n" +
				"programa_academico prog, sancion_maestro sanma " +
				"WHERE sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"and prog.nombre_programa = ? " +
				"and essa.id_sacion = sanma.id_sacion " +
				"GROUP BY sanma.nombre_sancion";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, programa);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorTipoSancion> results = new ArrayList<ApelacionesPorTipoSancion>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorTipoSancion(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	@Override
	public List<ApelacionesPorTipoSancion> buscarPorTipoMotivo(String tipomotivo) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sanma.nombre_sancion " +
				"FROM solicitud_apelacion sa, sancion_maestro sanma, motivo mo, tipo_motivo timo, estudiante_sacionado essa " +
				"WHERE sa.cedula_estudiante = mo.cedula_estudiante " +
				"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and timo.nombre_tipo_motivo = ? " +
				"and sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.id_sacion = sanma.id_sacion " +
				"GROUP BY sanma.nombre_sancion";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, tipomotivo);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorTipoSancion> results = new ArrayList<ApelacionesPorTipoSancion>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorTipoSancion(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}


	@Override
	public List<ApelacionesPorTipoSancion> buscarPorLapso(String lapsoinicial,
			String lapsofinal) {

		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sanma.nombre_sancion " +
				"FROM solicitud_apelacion sa, estudiante es, estudiante_sancionado essa, programa_academico prog, sancion_maestro sanma " +
				"WHERE sa.fecha_solicitud between " +
				"(SELECT la.fecha_inicio FROM solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) and " +
				"(SELECT la.fecha_cierre from solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) " +
				"and sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.id_sacion = sanma.id_sacion " +
				"GROUP BY sanma.nombre_sancion";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorTipoSancion> results = new ArrayList<ApelacionesPorTipoSancion>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorTipoSancion(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	
	

	@Override
	public List<ApelacionesPorTipoSancion> buscarPorLapsoYPrograma(
			String lapsoinicial, String lapsofinal, String programa) {

		String queryStatement =
				"SELECT COUNT(*), sanma.nombre_sancion from solicitud_apelacion sa, estudiante es, " +
				"estudiante_sancionado essa, programa_academico prog, sancion_maestro sanma WHERE sa.fecha_solicitud " +
				"BETWEEN (SELECT la.fecha_inicio FROM solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) and " +
				"(SELECT la.fecha_cierre FROM solicitud_apelacion sa, estudiante_sancionado essa, " +
				"lapso_academico la WHERE sa.codigo_lapso = ? and sa.codigo_lapso = essa.codigo_lapso " +
				"and essa.codigo_lapso = la.codigo_lapso) " +
				"and sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa and prog.nombre_programa = ? " +
				"and essa.id_sacion = sanma.id_sacion " +
				"GROUP BY sanma.nombre_sancion";
		
		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		query.setParameter(3, programa);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorTipoSancion> results = new ArrayList<ApelacionesPorTipoSancion>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorTipoSancion(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}

	@Override
	public List<ApelacionesPorTipoSancion> buscarPorTipoMotivoYPrograma(
			String programa, String tipomotivo) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sanma.nombre_sancion " +
				"FROM solicitud_apelacion sa, sancion_maestro sanma, estudiante_sancionado essa, estudiante es, programa_academico prog, sancion_maestro sanma, motivo mo, tipo_motivo timo\n" +
				"WHERE sa.cedula_estudiante = essa.cedula_estudiante " +
				"and essa.cedula_estudiante = es.cedula_estudiante " +
				"and es.id_programa = prog.id_programa " +
				"and prog.nombre_programa = ? " +
				"and sa.cedula_estudiante = mo.cedula_estudiante " +
				"and mo.id_tipo_motivo = timo.id_tipo_motivo " +
				"and timo.nombre_tipo_motivo = ? " +
				"and essa.id_sacion = sanma.id_sacion " +
				"GROUP BY sanma.nombre_sancion";
		
		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, programa);
		query.setParameter(2, tipomotivo);

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorTipoSancion> results = new ArrayList<ApelacionesPorTipoSancion>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorTipoSancion(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}


	

	@Override
	public List<ApelacionesPorTipoSancion> buscarPorLapsoYTipoMotivo(
			String lapsoinicial, String lapsofinal, String tipomotivo) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sanma.nombre_sancion " +
				"FROM sancion_maestro sanma, solicitud_apelacion sa, estudiante es, " +
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
				"and essa.id_sancion = sanma.id_sancion " +
				"GROUP BY sanma.nombre_sancion";

		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		query.setParameter(3, tipomotivo);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorTipoSancion> results = new ArrayList<ApelacionesPorTipoSancion>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorTipoSancion(
					((BigInteger) resultRow[1]).intValue(),
					(String) resultRow[0]));
		}
		
		return results;
	}

	
	
	@Override
	public List<ApelacionesPorTipoSancion> buscarPorLapsoYTipoMotivoYPrograma(
			String lapsoinicial, String lapsofinal, String tipomotivo,
			String programa) {
		String queryStatement =
				"SELECT COUNT(*) As numeroapelaciones, sanma.nombre_sancion " +
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
				"and essa.id_sancion = sanma.id_sacion " +
				"GROUP BY sanma.nombre_sancion";

		
		Query query = em.createNativeQuery(queryStatement);
		query.setParameter(1, lapsoinicial);
		query.setParameter(2, lapsofinal);
		query.setParameter(3, tipomotivo);
		query.setParameter(4, programa);
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ApelacionesPorTipoSancion> results = new ArrayList<ApelacionesPorTipoSancion>();
		for (Object[] resultRow : resultSet) {
			results.add(new ApelacionesPorTipoSancion(
					((BigInteger) resultRow[0]).intValue(),
					(String) resultRow[1]));
		}
		
		return results;
	}
	
		
	
	
	
}
*/