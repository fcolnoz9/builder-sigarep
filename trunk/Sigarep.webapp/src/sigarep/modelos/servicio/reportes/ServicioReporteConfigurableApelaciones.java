package sigarep.modelos.servicio.reportes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import sigarep.modelos.data.reportes.ConfigurableApelaciones;


@Service("servicioreporteconfigurableapelaciones")
public class ServicioReporteConfigurableApelaciones {
	@PersistenceContext
	private EntityManager es;
	
	public List<ConfigurableApelaciones> buscarTodasApelaciones(String lapsoAcademico,String tiposancion,String instanciaApelada,String tipoMotivo, String programaAcademico,String veredicto,String edoApelacion) {
		String queryStatement = 
		"SELECT Distinct es.primer_nombre, es.primer_apellido, es.sexo,prog.nombre_programa,san.nombre_sancion, tm.nombre_tipo_motivo,iap.instancia_apelada,lapso.codigo_lapso,sap.veredicto,edo_ape.nombre_estado  " +
		"FROM estado_apelacion edo_ape, estudiante es  " +
		"INNER JOIN estudiante_sancionado as esa ON esa.cedula_estudiante=es.cedula_estudiante " +
	    "INNER JOIN solicitud_apelacion as sap ON sap.cedula_estudiante=esa.cedula_estudiante " +
	    "INNER JOIN lapso_academico as lapso ON lapso.codigo_lapso=sap.codigo_lapso " +
	    "INNER JOIN motivo as mot ON mot.cedula_estudiante=sap.cedula_estudiante " +
	    "INNER JOIN tipo_motivo as tm ON tm.id_tipo_motivo=mot.id_tipo_motivo " +
	    "INNER JOIN instancia_apelada as iap ON iap.id_instancia_apelada=sap.id_instancia_apelada " +
	    "INNER JOIN programa_academico as prog ON prog.id_programa=es.id_programa " +
	    "INNER JOIN sancion_maestro as san ON san.id_sancion=esa.id_sancion " +
	    "INNER JOIN apelacion_estado_apelacion as ap_edo ON ap_edo.cedula_estudiante =sap.cedula_estudiante " +
	    "where es.id_programa="+""+programaAcademico+"  and sap.id_instancia_apelada="+""+instanciaApelada+" " +
	    "and mot.id_tipo_motivo="+""+tipoMotivo+" and sap.codigo_lapso="+""+lapsoAcademico+" and esa.id_sancion="+""+tiposancion+" and sap.veredicto="+""+veredicto+" and edo_ape.id_estado_apelacion="+""+edoApelacion+" order by es.primer_nombre " ;
		System.out.println(queryStatement);
		Query query = es.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		List<ConfigurableApelaciones> results = new ArrayList<ConfigurableApelaciones>();
		for (Object[] resultRow : resultSet) {
			results.add(new ConfigurableApelaciones(
					(String) resultRow[0],
					(String) resultRow[1],
					(String) resultRow[2],
					(String) resultRow[3],
					(String) resultRow[4],
					(String) resultRow[5],
					(String) resultRow[6]));
		}
		return results;
	}
}
