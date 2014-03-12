package sigarep.modelos.servicio.reportes;

import java.math.BigDecimal;
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
	
	
	/** Buscar Asignaturas con Mayor Sancionados
	 * @param Integer idPrograma,String codigoLapso, Integer idInstanciaApelada
	 * @return Listado con las 5 Asignaturas con Mayor Cantidad de Sancionados y sus Apelaciones con Resultados
	 * @throws No dispara ninguna excepcion.
	 */
	
	public List<ListaAsignaturasMayorCantidadSancionados> buscarAsignaturasSancionados(Integer idPrograma,String codigoLapso, Integer idInstanciaApelada){
		String queryStatement2= "select v.asignatura, sum(v.sanciones) as sanciones, sum(v.apelo) apelaciones, sum(v.procedente) procedentes, sum(v.noprocedente) noprocedentes,sum(v.totalsancion) as totalsancion, sum(v.TOTALAPELACIONES) TOTALAPELA from (select b.asignatura,count(b.sancionado) sanciones,0 as apelo, 0 as procedente,0 as noprocedente,(select sum(g.sanciones) from (select count(b.sancionado) sanciones from (SELECT distinct  (es.cedula_estudiante) as Sancionado from asignatura as a, programa_academico as p, estudiante_sancionado AS es LEFT JOIN estudiante  AS e ON (es.cedula_estudiante = e.cedula_estudiante) WHERE p.id_programa= a.id_programa AND p.id_programa= e.id_programa  AND ES.CODIGO_LAPSO= "+ "'"+codigoLapso+"' and a.id_programa= "+ "'"+idPrograma+"') as b ) as g) as TOTALSANCION, (SELECT SUM(APELO) AS APELA FROM (SELECT  count(sa.cedula_estudiante) as apelo from asignatura as a, programa_academico as p, asignatura_estudiante_sancionado AS aes LEFT JOIN estudiante_sancionado  AS es ON (es.codigo_lapso = aes.codigo_lapso AND es.cedula_estudiante = aes.cedula_estudiante) LEFT JOIN solicitud_apelacion    AS sa ON (sa.codigo_lapso = es.codigo_lapso AND sa.cedula_estudiante = es.cedula_estudiante) WHERE p.id_programa= a.id_programa AND aes.codigo_asignatura= a.codigo_asignatura AND AES.CODIGO_LAPSO= "+ "'"+codigoLapso+"'  and sa.id_instancia_apelada= "+ "'"+idInstanciaApelada+"' and a.id_programa= "+ "'"+idPrograma+"' group by a.nombre_asignatura) AS F) AS TOTALAPELACIONES from (SELECT distinct a.nombre_asignatura as Asignatura, (aes.cedula_estudiante) as Sancionado from asignatura as a, programa_academico as p, asignatura_estudiante_sancionado AS aes LEFT JOIN estudiante_sancionado  AS es ON (es.codigo_lapso = aes.codigo_lapso AND es.cedula_estudiante = aes.cedula_estudiante) WHERE p.id_programa= a.id_programa AND aes.codigo_asignatura= a.codigo_asignatura AND AES.CODIGO_LAPSO= "+ "'"+codigoLapso+"' and a.id_programa= "+ "'"+idPrograma+"') as b group by b.asignatura union all SELECT  a.nombre_asignatura as asignatura,0 as sanciones,count(sa.cedula_estudiante) as apelo, 0 as procedente,0 as noprocedente,0 as totalsancion, 0 AS TOTALAPELACIONES from asignatura as a, programa_academico as p, asignatura_estudiante_sancionado AS aes " +
				"LEFT JOIN estudiante_sancionado  AS es ON (es.codigo_lapso = aes.codigo_lapso AND es.cedula_estudiante = aes.cedula_estudiante)LEFT JOIN solicitud_apelacion    AS sa ON (sa.codigo_lapso = es.codigo_lapso AND sa.cedula_estudiante = es.cedula_estudiante)WHERE p.id_programa= a.id_programa AND aes.codigo_asignatura= a.codigo_asignatura AND AES.CODIGO_LAPSO= "+ "'"+codigoLapso+"'  and sa.id_instancia_apelada= "+ "'"+idInstanciaApelada+"' and a.id_programa= "+ "'"+idPrograma+"' group by a.nombre_asignatura union all SELECT  a.nombre_asignatura as asignatura,0 as sanciones,0 as apelo,count(sa.veredicto) as procedente,0 as noprocedente , 0 as totalsancion, 0 AS TOTALAPELACIONES from asignatura as a, programa_academico as p, asignatura_estudiante_sancionado AS aes LEFT JOIN estudiante_sancionado  AS es ON (es.codigo_lapso = aes.codigo_lapso AND es.cedula_estudiante = aes.cedula_estudiante) LEFT JOIN solicitud_apelacion    AS sa ON (sa.codigo_lapso = es.codigo_lapso AND sa.cedula_estudiante = es.cedula_estudiante) WHERE p.id_programa= a.id_programa AND aes.codigo_asignatura= a.codigo_asignatura AND AES.CODIGO_LAPSO= "+ "'"+codigoLapso+"'  and sa.id_instancia_apelada= "+ "'"+idInstanciaApelada+"' and a.id_programa= "+ "'"+idPrograma+"' and sa.veredicto='PROCEDENTE' group by a.nombre_asignatura union all SELECT  a.nombre_asignatura as asignatura,0 as sanciones,0 as apelo, 0 as procedente,count(sa.veredicto) as noprocedente , 0 as totalsancion, 0 AS TOTALAPELACIONES from asignatura as a, programa_academico as p, asignatura_estudiante_sancionado AS aes LEFT JOIN estudiante_sancionado  AS es ON (es.codigo_lapso = aes.codigo_lapso AND es.cedula_estudiante = aes.cedula_estudiante) LEFT JOIN solicitud_apelacion    AS sa ON (sa.codigo_lapso = es.codigo_lapso AND sa.cedula_estudiante = es.cedula_estudiante) WHERE p.id_programa= a.id_programa AND aes.codigo_asignatura= a.codigo_asignatura AND AES.CODIGO_LAPSO= "+ "'"+codigoLapso+"'  and sa.id_instancia_apelada= "+ "'"+idInstanciaApelada+"' and a.id_programa= "+ "'"+idPrograma+"' and sa.veredicto='NO PROCEDENTE' group by a.nombre_asignatura) as v group by v.asignatura ORDER BY SANCIONES DESC  LIMIT 5";
				
		Query query= em.createNativeQuery(queryStatement2);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet= query.getResultList();
	
		List<ListaAsignaturasMayorCantidadSancionados> results= new ArrayList<ListaAsignaturasMayorCantidadSancionados>();
		for (Object[] resultRow: resultSet){
			
			results.add(new ListaAsignaturasMayorCantidadSancionados((String)resultRow[0], (BigDecimal) resultRow[1], (BigDecimal) resultRow[2],
					(BigDecimal) resultRow[3], (BigDecimal) resultRow[4],(BigDecimal) resultRow[5], (BigDecimal) resultRow[6]));
		}
		
		return results;
	} 
	
	

}
