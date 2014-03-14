package sigarep.modelos.servicio.reportes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.reportes.EstudianteSancionado;

@Service("servicioreporteestudiantesancionado")
public class ServicioReporteEstudianteSancionado {
	@PersistenceContext
	private EntityManager es;
	private String tiraSql ;
	/** Buscar todos los atributos del estudiante sancionado
	 * @param String lapsoAcademico,String tiposancion,String instanciaApelada,String tipoMotivo, String programaAcademico,String sexo,String veredicto,String edoApelacion
	 * @return Listado del Resultado de la busqueda 
	 * @throws No dispara ninguna excepcion.
	 */
	public List<EstudianteSancionado> buscarTodosSancionado(String lapsoAcademico,String tiposancion,String instanciaApelada,String tipoMotivo, String programaAcademico,String sexo,String veredicto,String edoApelacion,String asignatura) {
		if(tiposancion.equals("'2'")) {
			tiraSql="and asig.codigo_asignatura="+""+asignatura+"";
		}
		else{
			tiraSql="";
		}
		String queryStatement = 
		"SELECT Distinct es.primer_nombre, es.primer_apellido, es.sexo,prog.nombre_programa,san.nombre_sancion, " + 
		"tm.nombre_tipo_motivo,iap.instancia_apelada,lapso.codigo_lapso,sap.veredicto,edo_ape.nombre_estado,asig.nombre_asignatura " +
		"FROM estudiante es " + 
		"LEFT JOIN estudiante_sancionado as esa ON esa.cedula_estudiante=es.cedula_estudiante " +
		"LEFT JOIN solicitud_apelacion as sap ON sap.cedula_estudiante=esa.cedula_estudiante " +
		"LEFT JOIN instancia_apelada as iap ON iap.id_instancia_apelada=sap.id_instancia_apelada " +
		"LEFT JOIN apelacion_estado_apelacion as ap_edoap ON ap_edoap.cedula_estudiante=sap.cedula_estudiante " +
		"LEFT JOIN programa_academico as prog ON  prog.id_programa=es.id_programa " +
		"LEFT JOIN sancion_maestro as san ON san.id_sancion=esa.id_sancion " +
		"LEFT JOIN motivo as mot ON mot.cedula_estudiante=sap.cedula_estudiante " +
		"LEFT JOIN lapso_academico as lapso ON lapso.codigo_lapso=sap.codigo_lapso " +
		"LEFT JOIN tipo_motivo as tm ON tm.id_tipo_motivo=mot.id_tipo_motivo " +
		"LEFT JOIN asignatura_estudiante_sancionado as asig_esan ON (asig_esan.cedula_estudiante=sap.cedula_estudiante and asig_esan.codigo_lapso= sap.codigo_lapso) " +
		"LEFT JOIN asignatura as asig ON asig.codigo_asignatura=asig_esan.codigo_asignatura " +
		"LEFT JOIN apelacion_estado_apelacion as ape_edo_ape ON ape_edo_ape.cedula_estudiante=sap.cedula_estudiante " +
		"LEFT JOIN estado_apelacion as edo_ape ON edo_ape.id_estado_apelacion=ape_edo_ape.id_estado_apelacion " +
		"where sap.codigo_lapso=esa.codigo_lapso and esa.id_sancion=san.id_sancion " +
		"and mot.codigo_lapso=sap.codigo_lapso and mot.id_instancia_apelada=sap.id_instancia_apelada and mot.cedula_estudiante=sap.cedula_estudiante " +
		"and mot.id_tipo_motivo="+""+tipoMotivo+" and mot.id_tipo_motivo <> 1 and mot.id_tipo_motivo <> 2 and mot.id_tipo_motivo <> 3 and  " +
		"es.sexo="+""+sexo+" "+
		"and  ape_edo_ape.id_estado_apelacion="+""+edoApelacion+" and sap.id_instancia_apelada="+""+instanciaApelada+" " +
		"and san.id_sancion="+""+tiposancion+" " +
		"and es.id_programa="+""+programaAcademico+"  and sap.veredicto="+""+veredicto+" and sap.codigo_lapso="+""+lapsoAcademico+" "+""+tiraSql+" order by es.primer_nombre  ";
		System.out.println(queryStatement);
		Query query = es.createNativeQuery(queryStatement);
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		List<EstudianteSancionado> results = new ArrayList<EstudianteSancionado>();
		for (Object[] resultRow : resultSet) {
			results.add(new EstudianteSancionado(
					(String) resultRow[0],
					(String) resultRow[1],
					(String) resultRow[2],
					(String) resultRow[3],
					(String) resultRow[4],
					(String) resultRow[5],
					(String) resultRow[6],
					(String) resultRow[7],
					(String) resultRow[8],
					(String) resultRow[9],
					(String) resultRow[10]));
		}
		return results;//retorna el resultado de la Query
	}
}
