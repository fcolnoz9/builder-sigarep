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
	
	public List<EstudianteSancionado> buscarTodosSancionado(String lapsoAcademico,String tiposancion,String instanciaApelada,String tipoMotivo, String programaAcademico,String sexo) {
		String queryStatement = 
		"SELECT Distinct es.primer_nombre, es.primer_apellido, es.sexo,prog.nombre_programa,san.nombre_sancion, tm.nombre_tipo_motivo,iap.instancia_apelada,lapso.codigo_lapso  " +
		"FROM estudiante es  " +
		"INNER JOIN estudiante_sancionado as esa ON esa.cedula_estudiante=es.cedula_estudiante " +
	    "INNER JOIN solicitud_apelacion as sap ON sap.cedula_estudiante=esa.cedula_estudiante " +
	    "INNER JOIN lapso_academico as lapso ON lapso.codigo_lapso=sap.codigo_lapso " +
	    "INNER JOIN motivo as mot ON mot.cedula_estudiante=sap.cedula_estudiante " +
	    "INNER JOIN tipo_motivo as tm ON tm.id_tipo_motivo=mot.id_tipo_motivo " +
	    "INNER JOIN instancia_apelada as iap ON iap.id_instancia_apelada=sap.id_instancia_apelada " +
	    "INNER JOIN programa_academico as prog ON prog.id_programa=es.id_programa " +
	    "INNER JOIN sancion_maestro as san ON san.id_sancion=esa.id_sancion " +
	    "where es.id_programa="+""+programaAcademico+" and es.sexo="+""+sexo+" and sap.id_instancia_apelada="+""+instanciaApelada+" " +
	    "and mot.id_tipo_motivo="+""+tipoMotivo+" and sap.codigo_lapso="+""+lapsoAcademico+" and esa.id_sancion="+""+tiposancion+" order by es.primer_nombre " ;
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
					(String) resultRow[7]));
		}
		return results;
	}
}
