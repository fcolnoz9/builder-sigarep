package sigarep.modelos.servicio.reportes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import org.springframework.stereotype.Service;


import sigarep.modelos.data.reportes.ListaAsignaturasMayorCantidadSancionados;


@Service("servicioListaAsignaturasMayor")
public class ServicioListaAsignaturasMayorCantidadSancionados {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<ListaAsignaturasMayorCantidadSancionados> buscarAsignaturasSancionados(Integer idPrograma,String codigoLapso, Integer idInstanciaApelada){
		String queryStatement2= "SELECT * from (select c.asignatura asignatura,sum(c.sanciones) sanciones,sum(c.apelaciones) apelaciones,sum(c.enfermedad) enfermedad,sum(c.judicial) judicial,sum(c.economico) economico,sum(c.medico) medico,sum(c.procedente) procedentes,sum(c.noprocedente) noprocedentes from (select b.asignatura,count(b.sancionado) sanciones,count(b.Apelo) apelaciones, 0 as enfermedad, 0 as Judicial,0 as economico,0 as medico,0 Procedente,0 as noprocedente from (SELECT distinct a.nombre_asignatura as Asignatura, (aes.cedula_estudiante) as Sancionado, sa.cedula_estudiante as Apelo from asignatura as a, programa_academico as p, asignatura_estudiante_sancionado AS aes " +
				"LEFT JOIN estudiante_sancionado  AS es ON (es.codigo_lapso = aes.codigo_lapso AND es.cedula_estudiante = aes.cedula_estudiante)" +
				"LEFT JOIN solicitud_apelacion    AS sa ON (sa.codigo_lapso = es.codigo_lapso AND sa.cedula_estudiante = es.cedula_estudiante)" +
				"LEFT JOIN motivo AS m ON (m.cedula_estudiante = sa.cedula_estudiante AND m.id_instancia_apelada = sa.id_instancia_apelada)" +
				"LEFT JOIN tipo_motivo AS tm ON (tm.id_tipo_motivo = m.id_tipo_motivo ) WHERE p.id_programa= a.id_programa AND aes.codigo_asignatura= a.codigo_asignatura AND AES.codigo_lapso= "+ "'"+codigoLapso+"' AND a.id_programa= "+ "'"+idPrograma+"') as b group by b.asignatura union all SELECT a.nombre_asignatura as Asignatura,0 as sanciones,0 as apelaciones, count(m.id_tipo_motivo) as enfermedad, 0 as Judicial,0 as economico,0 as medico,count(sa.veredicto) Procedente,0 as noprocedente from asignatura as a, programa_academico as p, asignatura_estudiante_sancionado AS aes " + 
				"LEFT JOIN estudiante_sancionado  AS es ON (es.codigo_lapso = aes.codigo_lapso AND es.cedula_estudiante = aes.cedula_estudiante)" +
				"LEFT JOIN solicitud_apelacion    AS sa ON (sa.codigo_lapso = es.codigo_lapso AND sa.cedula_estudiante = es.cedula_estudiante)" +
				"LEFT JOIN motivo AS m ON (m.cedula_estudiante = sa.cedula_estudiante AND m.id_instancia_apelada = sa.id_instancia_apelada)" +
				"LEFT JOIN tipo_motivo AS tm ON (tm.id_tipo_motivo = m.id_tipo_motivo )	WHERE p.id_programa= a.id_programa and sa.id_instancia_apelada= "+ "'"+idInstanciaApelada+"' and (m.id_tipo_motivo)=1 AND aes.codigo_asignatura= a.codigo_asignatura and sa.veredicto='procede' AND a.id_programa= "+ "'"+idPrograma+"' AND AES.codigo_lapso= "+ "'"+codigoLapso+"' group by a.nombre_asignatura,sa.veredicto union all SELECT a.nombre_asignatura as Asignatura,0 as sanciones,0 as apelaciones, 0 as enfermedad, count(m.id_tipo_motivo) as Judicial,0 as economico,0 as medico,count(sa.veredicto) Procedente,0 as noprocedente from asignatura as a, programa_academico as p, asignatura_estudiante_sancionado AS aes " +
				"LEFT JOIN estudiante_sancionado  AS es ON (es.codigo_lapso = aes.codigo_lapso AND es.cedula_estudiante = aes.cedula_estudiante)" +
				"LEFT JOIN solicitud_apelacion    AS sa ON (sa.codigo_lapso = es.codigo_lapso AND sa.cedula_estudiante = es.cedula_estudiante)" +
				"LEFT JOIN motivo AS m ON (m.cedula_estudiante = sa.cedula_estudiante AND m.id_instancia_apelada = sa.id_instancia_apelada)" +
				"LEFT JOIN tipo_motivo AS tm ON (tm.id_tipo_motivo = m.id_tipo_motivo )	WHERE p.id_programa= a.id_programa and sa.id_instancia_apelada= "+ "'"+idInstanciaApelada+"' and (m.id_tipo_motivo)=2 AND aes.codigo_asignatura= a.codigo_asignatura and sa.veredicto='procede' AND a.id_programa="+ "'"+idPrograma+"' AND AES.codigo_lapso= "+ "'"+codigoLapso+"' group by a.nombre_asignatura,sa.veredicto union all SELECT a.nombre_asignatura as Asignatura,0 as sanciones,0 as apelaciones, 0 as enfermedad, 0 as Judicial,count(m.id_tipo_motivo) as economico,0 as medico,count(sa.veredicto) Procedente,0 as noprocedente from asignatura as a, programa_academico as p, asignatura_estudiante_sancionado AS aes " +
				"LEFT JOIN estudiante_sancionado  AS es ON (es.codigo_lapso = aes.codigo_lapso AND es.cedula_estudiante = aes.cedula_estudiante)" +
				"LEFT JOIN solicitud_apelacion    AS sa ON (sa.codigo_lapso = es.codigo_lapso AND sa.cedula_estudiante = es.cedula_estudiante)" +
				"LEFT JOIN motivo AS m ON (m.cedula_estudiante = sa.cedula_estudiante AND m.id_instancia_apelada = sa.id_instancia_apelada)" +
				"LEFT JOIN tipo_motivo AS tm ON (tm.id_tipo_motivo = m.id_tipo_motivo )	WHERE p.id_programa= a.id_programa and sa.id_instancia_apelada= "+ "'"+idInstanciaApelada+"' and (m.id_tipo_motivo)=3 AND aes.codigo_asignatura= a.codigo_asignatura and sa.veredicto='procede' AND a.id_programa= "+ "'"+idPrograma+"' AND AES.codigo_lapso= "+ "'"+codigoLapso+"' group by a.nombre_asignatura,sa.veredicto union all SELECT a.nombre_asignatura as Asignatura,0 as sanciones,0 as apelaciones, 0 as enfermedad, 0 as Judicial,0 as economico,count(m.id_tipo_motivo) as medico,count(sa.veredicto) Procedente,0 as noprocedente from asignatura as a, programa_academico as p, asignatura_estudiante_sancionado AS aes " +
				"LEFT JOIN estudiante_sancionado  AS es ON (es.codigo_lapso = aes.codigo_lapso AND es.cedula_estudiante = aes.cedula_estudiante)" +
				"LEFT JOIN solicitud_apelacion    AS sa ON (sa.codigo_lapso = es.codigo_lapso AND sa.cedula_estudiante = es.cedula_estudiante)" +
				"LEFT JOIN motivo AS m ON (m.cedula_estudiante = sa.cedula_estudiante AND m.id_instancia_apelada = sa.id_instancia_apelada)" +
				"LEFT JOIN tipo_motivo AS tm ON (tm.id_tipo_motivo = m.id_tipo_motivo )	WHERE p.id_programa= a.id_programa and sa.id_instancia_apelada= "+ "'"+idInstanciaApelada+"' and (m.id_tipo_motivo)=4 AND aes.codigo_asignatura= a.codigo_asignatura and sa.veredicto='procede' AND a.id_programa= "+ "'"+idPrograma+"' AND AES.codigo_lapso= "+ "'"+codigoLapso+"' group by a.nombre_asignatura,sa.veredicto union all SELECT a.nombre_asignatura as Asignatura,0 as sanciones,0 as apelaciones, count(m.id_tipo_motivo) as enfermedad, 0 as Judicial,0 as economico,0 as medico,0 Procedente,count(sa.veredicto) as noprocedente from asignatura as a, programa_academico as p, asignatura_estudiante_sancionado AS aes " +
				"LEFT JOIN estudiante_sancionado  AS es ON (es.codigo_lapso = aes.codigo_lapso AND es.cedula_estudiante = aes.cedula_estudiante)" +
				"LEFT JOIN solicitud_apelacion    AS sa ON (sa.codigo_lapso = es.codigo_lapso AND sa.cedula_estudiante = es.cedula_estudiante)" +
				"LEFT JOIN motivo AS m ON (m.cedula_estudiante = sa.cedula_estudiante AND m.id_instancia_apelada = sa.id_instancia_apelada)" +
				"LEFT JOIN tipo_motivo AS tm ON (tm.id_tipo_motivo = m.id_tipo_motivo )	WHERE p.id_programa= a.id_programa and sa.id_instancia_apelada= "+ "'"+idInstanciaApelada+"' and (m.id_tipo_motivo)=1 AND aes.codigo_asignatura= a.codigo_asignatura and sa.veredicto='no procede' AND a.id_programa= "+ "'"+idPrograma+"' AND AES.codigo_lapso= "+ "'"+codigoLapso+"' group by a.nombre_asignatura,sa.veredicto union all SELECT a.nombre_asignatura as Asignatura,0 as sanciones,0 as apelaciones, 0 as enfermedad, count(m.id_tipo_motivo) as Judicial,0 as economico,0 as medico,0 Procedente,count(sa.veredicto) as noprocedente from asignatura as a, programa_academico as p, asignatura_estudiante_sancionado AS aes " +
				"LEFT JOIN estudiante_sancionado  AS es ON (es.codigo_lapso = aes.codigo_lapso AND es.cedula_estudiante = aes.cedula_estudiante)" +
				"LEFT JOIN solicitud_apelacion    AS sa ON (sa.codigo_lapso = es.codigo_lapso AND sa.cedula_estudiante = es.cedula_estudiante)" +
				"LEFT JOIN motivo AS m ON (m.cedula_estudiante = sa.cedula_estudiante AND m.id_instancia_apelada = sa.id_instancia_apelada)" +
				"LEFT JOIN tipo_motivo AS tm ON (tm.id_tipo_motivo = m.id_tipo_motivo )	WHERE p.id_programa= a.id_programa and sa.id_instancia_apelada= "+ "'"+idInstanciaApelada+"' and (m.id_tipo_motivo)=2 AND aes.codigo_asignatura= a.codigo_asignatura and sa.veredicto='no procede' AND a.id_programa= "+ "'"+idPrograma+"' AND AES.codigo_lapso= "+ "'"+codigoLapso+"' group by a.nombre_asignatura,sa.veredicto union all SELECT a.nombre_asignatura as Asignatura,0 as sanciones,0 as apelaciones, 0 as enfermedad, 0 as Judicial,count(m.id_tipo_motivo) as economico,0 as medico,0 Procedente,count(sa.veredicto) as noprocedente from asignatura as a, programa_academico as p, asignatura_estudiante_sancionado AS aes " +
				"LEFT JOIN estudiante_sancionado  AS es ON (es.codigo_lapso = aes.codigo_lapso AND es.cedula_estudiante = aes.cedula_estudiante)" +
				"LEFT JOIN solicitud_apelacion    AS sa ON (sa.codigo_lapso = es.codigo_lapso AND sa.cedula_estudiante = es.cedula_estudiante)" +
				"LEFT JOIN motivo AS m ON (m.cedula_estudiante = sa.cedula_estudiante AND m.id_instancia_apelada = sa.id_instancia_apelada)" +
				"LEFT JOIN tipo_motivo AS tm ON (tm.id_tipo_motivo = m.id_tipo_motivo )	WHERE p.id_programa= a.id_programa and sa.id_instancia_apelada= "+ "'"+idInstanciaApelada+"' and (m.id_tipo_motivo)=3 AND aes.codigo_asignatura= a.codigo_asignatura and sa.veredicto='no procede' AND a.id_programa= "+ "'"+idPrograma+"' AND AES.codigo_lapso= "+ "'"+codigoLapso+"' group by a.nombre_asignatura,sa.veredicto union all SELECT a.nombre_asignatura as Asignatura,0 as sanciones,0 as apelaciones, 0 as enfermedad, 0 as Judicial,0 as economico,count(m.id_tipo_motivo) as medico,0 Procedente,count(sa.veredicto) as noprocedente from asignatura as a, programa_academico as p, asignatura_estudiante_sancionado AS aes " +
				"LEFT JOIN estudiante_sancionado  AS es ON (es.codigo_lapso = aes.codigo_lapso AND es.cedula_estudiante = aes.cedula_estudiante)" +
				"LEFT JOIN solicitud_apelacion    AS sa ON (sa.codigo_lapso = es.codigo_lapso AND sa.cedula_estudiante = es.cedula_estudiante)" +
				"LEFT JOIN motivo AS m ON (m.cedula_estudiante = sa.cedula_estudiante AND m.id_instancia_apelada = sa.id_instancia_apelada)" +
				"LEFT JOIN tipo_motivo AS tm ON (tm.id_tipo_motivo = m.id_tipo_motivo )	WHERE p.id_programa= a.id_programa and sa.id_instancia_apelada= "+ "'"+idInstanciaApelada+"' and (m.id_tipo_motivo)=4  AND aes.codigo_asignatura= a.codigo_asignatura and sa.veredicto='no procede' AND a.id_programa= "+ "'"+idPrograma+"' AND AES.codigo_lapso= "+ "'"+codigoLapso+"' group by a.nombre_asignatura,sa.veredicto) as c group by c.asignatura ) as b order by b.sanciones desc limit 5"; //PARAMETROS
				
				
				
				
				/*"SELECT * from (SELECT b.nombre_asignatura, b.cantidadSancionados,"
				+ " rank()  OVER (PARTITION BY b.lapso ORDER BY b.cantidadSancionados DESC)" 
				+ "from (SELECT a.nombre_asignatura, count(aes.cedula_estudiante) as cantidadSancionados,"
				+ "aes.codigo_lapso as lapso from asignatura as a, asignatura_estudiante_sancionado as aes," 
				+ "programa_academico as p where p.id_programa= a.id_programa and a.id_programa= ? and "
				+ "aes.codigo_asignatura= a.codigo_asignatura and aes.codigo_lapso= ? "
				+ "group by  aes.codigo_asignatura, a.nombre_asignatura, aes.codigo_lapso order by cantidadSancionados desc) as b order by b.lapso asc) sub_query WHERE rank <= 5";*/
	
		Query query= em.createNativeQuery(queryStatement2);
		
		//query.setParameter(1,idPrograma);
		//query.setParameter(2,codigoLapso);
		//query.setParameter(3,idInstanciaApelada);
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet= query.getResultList();
	
		List<ListaAsignaturasMayorCantidadSancionados> results= new ArrayList<ListaAsignaturasMayorCantidadSancionados>();
		for (Object[] resultRow: resultSet){
			
			results.add(new ListaAsignaturasMayorCantidadSancionados((String)resultRow[0], (BigDecimal) resultRow[1], (BigDecimal) resultRow[2],
					(BigDecimal) resultRow[3], (BigDecimal) resultRow[4],(BigDecimal) resultRow[5], (BigDecimal) resultRow[6], 
					(BigDecimal) resultRow[7],(BigDecimal) resultRow[8]));
		}
		
		return results;
	}
}
