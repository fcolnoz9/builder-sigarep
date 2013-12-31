package sigarep.modelos.servicio.transacciones;

import java.math.BigInteger;
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
//
		String queryStatement2 =
		"SELECT  es.cedula_estudiante, es.primer_nombre, es.primer_apellido," +
		"sa.nombre_sancion, es.email, es.telefono, p.nombre_programa, la.codigo_lapso, i.id_instancia_apelada FROM " +
		"sancion_maestro sa, " +
		"programa_academico p, lapso_academico la, instancia_apelada i, estudiante es " +
		"INNER JOIN estudiante_sancionado AS esa ON es.cedula_estudiante = esa.cedula_estudiante " +
		"INNER JOIN solicitud_apelacion  AS sap ON esa.cedula_estudiante = sap.cedula_estudiante " +
		"INNER JOIN apelacion_momento AS ap ON sap.cedula_estudiante = ap.cedula_estudiante " +
		"INNER JOIN momento AS m ON m.id_momento = ap.id_momento WHERE sa.id_sancion = esa.id_sancion " +
		"AND m.id_momento = ap.id_momento  AND m.nombre_momento = 'veredictoprimeraapelacion' " +
		"AND esa.codigo_lapso = la.codigo_lapso AND i.id_instancia_apelada = sap.id_instancia_apelada " +
		"AND sap.id_instancia_apelada = ap.id_instancia_apelada AND es.id_programa= p.id_programa";
//		, AND esa.codigo_lapso = la.codigo_lapso
		//(esa.id_sancion=sa.id_sancion) and
		Query query = em.createNativeQuery(queryStatement2);
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
					(String) resultRow[6], (String) resultRow[7], (Integer)(resultRow[8])));
		}
		
		return results;
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