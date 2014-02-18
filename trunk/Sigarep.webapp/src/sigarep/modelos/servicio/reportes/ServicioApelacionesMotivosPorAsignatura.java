package sigarep.modelos.servicio.reportes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import sigarep.modelos.data.reportes.ListaApelacionesMotivoPorAsignatura;
import sigarep.modelos.data.reportes.ListaAsignaturasMayorCantidadSancionados;

@Service("servicioApelacionesMotivos")
public class ServicioApelacionesMotivosPorAsignatura {

	@PersistenceContext
	private EntityManager em;
	
	/** Buscar Apelaciones Motivo por Asignatura
	 * @param String codigoAsignatura,String codigoLapso, Integer idInstanciaApelada
	 * @return Listado de Apelaciones por Motivos y sus Resultados Procedentes por una Asignatura escogida
	 * @throws No dispara ninguna excepcion.
	 */
	
	public List<ListaApelacionesMotivoPorAsignatura> buscarApelacionesMotivoPorAsignatura(String codigoAsignatura,String codigoLapso, Integer idInstanciaApelada){
		String consulta= "select v.asignatura, v.motivo, sum(v.apelaciones) as apelaciones, sum(v.procedentes) as procedentes, sum(v.totalapelaciones) as totalapelaciones, sum(v.totalprocedente) as totalprocedentes from (select aes.codigo_asignatura as asignatura,tm.nombre_tipo_motivo as motivo, count(m.cedula_estudiante) apelaciones, 0 as procedentes, (select sum(u.apelaciones) as apela from (select aes.codigo_asignatura as asignatura, count(m.cedula_estudiante) apelaciones from motivo m, tipo_motivo tm, solicitud_apelacion sa, asignatura_estudiante_sancionado AS aes where tm.id_tipo_motivo = m.id_tipo_motivo and m.cedula_estudiante = sa.cedula_estudiante " +
				"AND m.id_instancia_apelada = sa.id_instancia_apelada and sa.codigo_lapso = aes.codigo_lapso AND sa.cedula_estudiante = aes.cedula_estudiante and m.codigo_lapso= "+ "'"+codigoLapso+"'  and sa.id_instancia_apelada= "+ "'"+idInstanciaApelada+"' and aes.codigo_asignatura= "+ "'"+codigoAsignatura+"' group by aes.codigo_asignatura) as u group by u.asignatura) as totalapelaciones, 0 as totalprocedente from motivo m, tipo_motivo tm, solicitud_apelacion sa, asignatura_estudiante_sancionado AS aes where tm.id_tipo_motivo = m.id_tipo_motivo and m.cedula_estudiante = sa.cedula_estudiante AND m.id_instancia_apelada = sa.id_instancia_apelada and sa.codigo_lapso = aes.codigo_lapso AND sa.cedula_estudiante = aes.cedula_estudiante and m.codigo_lapso= "+ "'"+codigoLapso+"'  and sa.id_instancia_apelada= "+ "'"+idInstanciaApelada+"' and aes.codigo_asignatura= "+ "'"+codigoAsignatura+"' group by aes.codigo_asignatura,tm.nombre_tipo_motivo union all select aes.codigo_asignatura,tm.nombre_tipo_motivo, 0 apelaciones, " +
				"count(sa.veredicto) as procedentes, 0 as totalapelaciones,(select sum(x.procedentes) from (select count(sa.veredicto) as procedentes from motivo m, tipo_motivo tm, solicitud_apelacion sa, asignatura_estudiante_sancionado AS aes where tm.id_tipo_motivo = m.id_tipo_motivo and m.cedula_estudiante = sa.cedula_estudiante AND m.id_instancia_apelada = sa.id_instancia_apelada and sa.codigo_lapso = aes.codigo_lapso AND sa.cedula_estudiante = aes.cedula_estudiante and m.codigo_lapso= "+ "'"+codigoLapso+"'  and sa.id_instancia_apelada= "+ "'"+idInstanciaApelada+"' and sa.veredicto='Procede' and aes.codigo_asignatura= "+ "'"+codigoAsignatura+"' group by aes.codigo_asignatura,tm.nombre_tipo_motivo ) as x) as totalprocedente from motivo m, tipo_motivo tm, solicitud_apelacion sa, asignatura_estudiante_sancionado AS aes where tm.id_tipo_motivo = m.id_tipo_motivo and m.cedula_estudiante = sa.cedula_estudiante AND m.id_instancia_apelada = sa.id_instancia_apelada and sa.codigo_lapso = aes.codigo_lapso AND sa.cedula_estudiante = aes.cedula_estudiante and m.codigo_lapso= "+ "'"+codigoLapso+"'  and sa.id_instancia_apelada= "+ "'"+idInstanciaApelada+"' and sa.veredicto='Procede' and aes.codigo_asignatura= "+ "'"+codigoAsignatura+"' group by aes.codigo_asignatura,tm.nombre_tipo_motivo  ) as v group by v.asignatura,v.motivo order by v.asignatura desc";
			
		Query query= em.createNativeQuery(consulta);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet= query.getResultList();
	
		List<ListaApelacionesMotivoPorAsignatura> resultado= new ArrayList<ListaApelacionesMotivoPorAsignatura>();
		for (Object[] resultRow: resultSet){
			
			resultado.add(new ListaApelacionesMotivoPorAsignatura((String)resultRow[0], (String) resultRow[1], (BigDecimal) resultRow[2],
					(BigDecimal) resultRow[3],(BigDecimal) resultRow[4], (BigDecimal) resultRow[5]));
		}
		
		return resultado;
	} 


}
