package sigarep.modelos.servicio.transacciones;


import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import sigarep.modelos.data.maestros.Momento;
import sigarep.modelos.data.transacciones.ApelacionMomento;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.repositorio.transacciones.IApelacionMomentoDAO;
import sigarep.modelos.data.maestros.Estudiante;


@Service("serviciolista")
public class ServicioApelacion  {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private IApelacionMomentoDAO apelacionmomento;
	
	public List<ListaApelacionMomento> buscarApelaciones() {
		
//		PARA PARAMETROS
		
//		String queryStatement = 
//				"SELECT es.cedula_estudiante, es.primer_nombre, es.primer_apellido," +
//				" sa.nombre_sancion FROM sigarep.sancionmaestro sa, sigarep.estudiante es " +
//				" INNER JOIN sigarep.estudiantesancionado AS esa ON es.cedula_estudiante =" +
//				" esa.cedula_estudiante" +
//				"INNER JOIN sigarep.solicitudapelacion  AS sa ON esa.cedula_estudiante = " +
//				"sa.cedula_estudiante" +
//				"INNER JOIN sigarep.apelacionmomento AS ap ON sa.cedula_estudiante = " +
//				"ap.cedula_estudiante" +
//				"INNER JOIN sigarep.momento AS m ON m.id_momento = ap.id_momento" +
//				"WHERE es.cedula_estudiante = ? AND esa.cedula_estudiante = ? " +
//				"AND sa.cedula_estudiante = ? AND ap.cedula_estudiante = ? AND m.id_momento = ? ";
//				//"m.nombre_momento = 'veredicto'";
		
//FALTA PERIODO DE SANCION
		String queryStatement2 =
				"SELECT es.cedula_estudiante, es.primer_nombre, es.primer_apellido, " +
				"sa.nombre_sancion, es.email, es.telefono, p.nombre_programa, la.codigo_lapso, i.id_instancia_apelada, " +
				"tm.nombre_tipo_motivo, r.nombre_recaudo, es.segundo_nombre, es.segundo_apellido, a.nombre_asignatura, sap.numero_caso " +
				" FROM sancion_maestro sa, programa_academico p, lapso_academico la, instancia_apelada i, " +
				"tipo_motivo tm, solicitud_apelacion sap, estudiante es " +
				"INNER JOIN apelacion_momento AS ap ON es.cedula_estudiante = ap.cedula_estudiante " +
				"INNER JOIN momento AS m ON m.id_momento = ap.id_momento, " +
				"motivo as mo LEFT JOIN recaudo_entregado AS re ON (mo.id_tipo_motivo = re.id_tipo_motivo AND " +
				"re.id_instancia_apelada = mo.id_instancia_apelada AND " +
				"re.codigo_lapso = mo.codigo_lapso AND re.cedula_estudiante = mo.cedula_estudiante) " +
				"LEFT JOIN recaudo as r ON (r.id_recaudo = re.id_recaudo)," +
				"estudiante_sancionado AS esa LEFT JOIN asignatura_estudiante_sancionado AS aesa ON " +
				"(aesa.codigo_lapso = esa.codigo_lapso " +
				"AND aesa.cedula_estudiante = esa.cedula_estudiante)" +
				"LEFT JOIN asignatura AS a ON a.codigo_asignatura = aesa.codigo_asignatura " +
				"WHERE sa.id_sancion = esa.id_sancion " +
				"AND m.id_momento = ap.id_momento  AND m.nombre_momento = 'veredictoprimeraapelacion' " +
				"AND esa.codigo_lapso = la.codigo_lapso AND i.id_instancia_apelada = sap.id_instancia_apelada " +
				"AND sap.id_instancia_apelada = ap.id_instancia_apelada AND es.id_programa= p.id_programa " +
				"AND sap.id_instancia_apelada = mo.id_instancia_apelada AND tm.id_tipo_motivo = mo.id_tipo_motivo " +
				"AND re.id_tipo_motivo = mo.id_tipo_motivo AND la.estatus = 'true' AND es.cedula_estudiante = " +
				"esa.cedula_estudiante AND es.cedula_estudiante = sap.cedula_estudiante";

		Query query = em.createNativeQuery(queryStatement2);
		
		//PARA PARAMETROS
		
//		query.setParameter(1, estudiante.getCedulaEstudiante());
//		query.setParameter(1, estudiantesancionado.getId().getCedulaEstudiante());
//		query.setParameter(2, solicitudapelacion.getId().getCedulaEstudiante());
//		query.setParameter(3, apelacionmomento.getId().getCedulaEstudiante());
//		query.setParameter(4, momento.getIdMomento());
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ListaApelacionMomento> results = new ArrayList<ListaApelacionMomento>();
		for (Object[] resultRow : resultSet) {
			System.out.println(resultRow[0]);
			System.out.println(resultRow[1]);
			System.out.println(resultRow[2]);
			System.out.println(resultRow[3]);
			
			results.add(new ListaApelacionMomento((String) resultRow[0], (String) resultRow[1],
					(String) resultRow[2], (String) resultRow[3], (String) resultRow[4], (String) resultRow[5],
					(String) resultRow[6], (String) resultRow[7], (Integer)(resultRow[8]), (String) resultRow[9],
					(String) resultRow[10], (String)resultRow[11],(String) resultRow[12], (String) resultRow[13],
					(Integer) resultRow[14]));
		}
		
		return results;
	}


	public List<ListaApelacionMomento> buscarPorFiltros(ListaApelacionMomentoFiltros filtros){
		List<ListaApelacionMomento> result = new ArrayList<ListaApelacionMomento>();
		String programa = filtros.getPrograma().toLowerCase();
		String motivo = filtros.getMotivo().toLowerCase();
		String cedula = filtros.getCedula().toLowerCase();
		String nombre = filtros.getNombre().toLowerCase();
		String apellido = filtros.getApellido().toLowerCase();
		String sancion = filtros.getSancion().toLowerCase();
        if(programa==null || motivo==null || cedula==null 
        		|| nombre==null || apellido==null || sancion==null){
        	result= buscarApelaciones();
        }
        else{
			for (ListaApelacionMomento ap : buscarApelaciones())
			{
				if (ap.getPrograma().toLowerCase().contains(programa)&&
						ap.getMotivo().toLowerCase().contains(motivo)&&
						ap.getCedulaEstudiante().toLowerCase().contains(cedula)&&
						ap.getPrimerNombre().toLowerCase().contains(nombre)&&
						ap.getPrimerApellido().toLowerCase().contains(apellido)&&
						ap.getNombreSancion().toLowerCase().contains(sancion)){
					result.add(ap);
				}
			}
        }
		return result;
        } 
//	@Override
//	public List<ListaApelacionMomento> buscarApelaciones(
//			EstudianteSancionado estudiantesancionado,
//			SolicitudApelacion solicitudapelacion,
//			ApelacionMomento apelacionmomento, Momento momento) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	

}