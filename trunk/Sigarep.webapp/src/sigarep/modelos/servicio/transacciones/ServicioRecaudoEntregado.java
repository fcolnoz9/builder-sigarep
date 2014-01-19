package sigarep.modelos.servicio.transacciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.MotivoPK;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.repositorio.transacciones.IRecaudoEntregadoDAO;

@Service("serviciorecaudoentregado")
public class ServicioRecaudoEntregado {

	private @Autowired IRecaudoEntregadoDAO iRecaudoEntregadoDAO;
	@PersistenceContext
	private EntityManager em;
	
	public RecaudoEntregado guardar(RecaudoEntregado recaudoentregado) {
		return iRecaudoEntregadoDAO.save(recaudoentregado);
	}
	
	public List<ListaCargarRecaudoEntregado> buscarApelacionesCargarRecaudo() {
		String tiraSql =
			"SELECT es.cedula_estudiante, es.primer_nombre, es.primer_apellido,"+
				"sa.nombre_sancion, es.email, p.nombre_programa, la.codigo_lapso, i.id_instancia_apelada,"+ 
				"es.segundo_nombre, es.segundo_apellido, a.nombre_asignatura, sap.numero_caso "+
			"FROM sancion_maestro sa, programa_academico p, lapso_academico la, instancia_apelada i,"+
				"solicitud_apelacion sap, estudiante es,"+
				"estudiante_sancionado AS esa LEFT JOIN asignatura_estudiante_sancionado AS aesa ON "+
				"(aesa.codigo_lapso = esa.codigo_lapso AND aesa.cedula_estudiante = esa.cedula_estudiante) "+
				"LEFT JOIN asignatura AS a ON a.codigo_asignatura = aesa.codigo_asignatura "+
			"WHERE sa.id_sancion = esa.id_sancion "+
			"AND esa.codigo_lapso = la.codigo_lapso "+
			"AND i.id_instancia_apelada = sap.id_instancia_apelada "+
			"AND es.id_programa= p.id_programa "+
			"AND la.estatus = 'true' "+
			"AND es.cedula_estudiante = esa.cedula_estudiante "+
			"AND esa.cedula_estudiante = sap.cedula_estudiante "+
			"AND sap.estatus = 'true'";

		Query query = em.createNativeQuery(tiraSql);
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ListaCargarRecaudoEntregado> results = new ArrayList<ListaCargarRecaudoEntregado>();
		for (Object[] resultRow : resultSet) {
			results.add(new ListaCargarRecaudoEntregado((String) resultRow[0], (String) resultRow[1],
					(String) resultRow[2], (String) resultRow[3], (String) resultRow[4],
					(String) resultRow[5], (String) resultRow[6], (Integer)(resultRow[7]), (String) resultRow[8],
					 (String)resultRow[9],(String) resultRow[10], (Integer) resultRow[11]));
		}
		return results;
	}
	
	
	public List<ListaCargarRecaudoEntregado> filtrarApelacionesCargarRecaudo(
			String programa, String cedula, String nombre,
			String apellido, String sancion){
		List<ListaCargarRecaudoEntregado> result = new ArrayList<ListaCargarRecaudoEntregado>();
        if(programa==null || cedula==null 
        		|| nombre==null || apellido==null || sancion==null){
        	result= buscarApelacionesCargarRecaudo();
        }
        else{
			for (ListaCargarRecaudoEntregado ap : buscarApelacionesCargarRecaudo())
			{
				if (ap.getPrograma().toLowerCase().contains(programa.toLowerCase())&&
						ap.getCedulaEstudiante().toLowerCase().contains(cedula.toLowerCase())&&
						ap.getPrimerNombre().toLowerCase().contains(nombre.toLowerCase())&&
						ap.getPrimerApellido().toLowerCase().contains(apellido.toLowerCase())&&
						ap.getNombreSancion().toLowerCase().contains(sancion.toLowerCase())){
					result.add(ap);
				}
			}
        }
		return result;
	} 
	
	public List<ListaBuscarRecaudosEntregados> buscarRecaudosEntregados(String cedula) {
		String queryStatement = 
				"SELECT r.nombre_recaudo, tm.nombre_tipo_motivo, s.nombre_documento, s.contenido_documento, " +
				"s.tipo_documento, tm.id_tipo_motivo, re.id_recaudo FROM  tipo_motivo tm " +
				"INNER JOIN motivo AS m ON m.id_tipo_motivo = tm.id_tipo_motivo " +
				"INNER JOIN solicitud_apelacion  AS sa ON sa.cedula_estudiante = m.cedula_estudiante " +
				"AND sa.codigo_lapso = m.codigo_lapso AND sa.id_instancia_apelada   = m.id_instancia_apelada " +
				"INNER JOIN estudiante_sancionado AS esa ON esa.cedula_estudiante = sa.cedula_estudiante " +
				"AND esa.codigo_lapso = sa.codigo_lapso " +
				"INNER JOIN estudiante as es ON es.cedula_estudiante = esa.cedula_estudiante " +
				"INNER JOIN lapso_academico AS la ON la.codigo_lapso = esa.codigo_lapso," +
				"recaudo_entregado AS re LEFT JOIN soporte AS s ON s.id_recaudo = re.id_recaudo AND " +
				"s.codigo_lapso = re.codigo_lapso AND s.cedula_estudiante = re.cedula_estudiante AND " +
				"s.id_instancia_apelada = re.id_instancia_apelada AND s.id_tipo_motivo = re.id_tipo_motivo " +
				"LEFT JOIN recaudo AS r ON r.id_recaudo = re.id_recaudo " +
				"WHERE r.id_tipo_motivo = tm.id_tipo_motivo AND r.id_recaudo = re.id_recaudo AND " +
				"la.estatus = 'TRUE' AND esa.cedula_estudiante = "+"'"+ cedula +"' AND m.cedula_estudiante = re.cedula_estudiante " +
				"AND m.codigo_lapso = re.codigo_lapso AND m.id_instancia_apelada = re.id_instancia_apelada AND " +
				"m.id_tipo_motivo = re.id_tipo_motivo";

		Query query = em.createNativeQuery(queryStatement);

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();	
		List<ListaBuscarRecaudosEntregados> results = new ArrayList<ListaBuscarRecaudosEntregados>();
		for (Object[] resultRow : resultSet) {
			results.add(new ListaBuscarRecaudosEntregados ((String) resultRow[0], (String) resultRow[1],
					(String) resultRow[2], (byte[]) resultRow[3], (String) resultRow[4], (Integer) resultRow[5],
					(Integer) resultRow[6]));
		}
		return results;
	}
}
