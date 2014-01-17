package sigarep.modelos.servicio.transacciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.repositorio.transacciones.ISolicitudApelacionDAO;
import sigarep.modelos.repositorio.transacciones.IApelacionEstadoApelacionDAO;
import sigarep.modelos.data.maestros.Estudiante;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.data.maestros.ProgramaAcademico;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.maestros.EstadoApelacion;


@Service("serviciolistaveredicto")
public class ServicioVeredicto {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private IApelacionEstadoApelacionDAO apelacionestadoapelacion;
	
	public List<ListaApelacionVeredicto1> buscarApelacionesVeredicto() {
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
		String queryStatementVer =
			"SELECT es.cedula_estudiante, es.primer_nombre, es.primer_apellido," +
			"sa.nombre_sancion,  p.nombre_programa, esa.indice_grado, esa.codigo_lapso FROM sancion_maestro sa," +
			"programa_academico p, estudiante_sancionado esa, lapso_academico la, instancia_apelada i,  estudiante es " +
//			"INNER JOIN estudiante_sancionado AS esa ON es.cedula_estudiante = esa.cedula_estudiante " +
			"INNER JOIN solicitud_apelacion  AS sap ON es.cedula_estudiante = sap.cedula_estudiante " +
			"INNER JOIN apelacion_estado_apelacion AS aea ON aea.cedula_estudiante = sap.cedula_estudiante " +
			"INNER JOIN estado_apelacion AS ea ON ea.id_estado_apelacion = aea.id_estado_apelacion WHERE sa.id_sancion = esa.id_sancion " +
			"AND ea.nombre_estado = 'verificado' " +
			"AND es.cedula_estudiante = esa.cedula_estudiante " +
			"AND esa.codigo_lapso = la.codigo_lapso AND i.id_instancia_apelada = sap.id_instancia_apelada " +
			"AND sap.id_instancia_apelada = aea.id_instancia_apelada AND es.id_programa= p.id_programa";

		Query query = em.createNativeQuery(queryStatementVer);
//		query.setParameter(1, estudiante.getCedulaEstudiante());
//		query.setParameter(1, estudiantesancionado.getId().getCedulaEstudiante());
//		query.setParameter(2, solicitudapelacion.getId().getCedulaEstudiante());
//		query.setParameter(3, apelacionmomento.getId().getCedulaEstudiante());
//		query.setParameter(4, momento.getIdMomento());
		
		
	
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ListaApelacionVeredicto1> results = new ArrayList<ListaApelacionVeredicto1>();
		for (Object[] resultRow : resultSet) {
			System.out.println(resultRow[0]);
			System.out.println(resultRow[1]);
			System.out.println(resultRow[2]);
			System.out.println(resultRow[3]);
			System.out.println(resultRow[4]);
			System.out.println(resultRow[5]);
			System.out.println(resultRow[6]);
			results.add(
					new ListaApelacionVeredicto1(
							(String) resultRow[0],
							(String) resultRow[1],
							(String) resultRow[2], 
							(String) resultRow[3], 
							(String) resultRow[4], 
							(Float) resultRow[5], 
							(String) resultRow[6] ));
		}
		
		return results;
	}

	public List<ListaApelacionVeredicto1> buscarP(FiltroListaApelacionVeredicto1 filtros){
		List<ListaApelacionVeredicto1> result = new ArrayList<ListaApelacionVeredicto1>();
		String ced = filtros.getCedula().toLowerCase();
		String nom = filtros.getNombre().toLowerCase();
        String ape = filtros.getApellido().toLowerCase();
        String san = filtros.getSancion().toLowerCase();
        if(ced==null || nom==null || ape==null ||  san==null){
        	result= buscarApelacionesVeredicto();
        }
        else{
			for (ListaApelacionVeredicto1 b: buscarApelacionesVeredicto())
			{
				if (b.getCedula().toLowerCase().contains(ced)&&
					b.getNombre().toLowerCase().contains(nom)&&
					b.getApellido().toLowerCase().contains(ape)&&
					b.getSancion().toLowerCase().contains(san)){
					result.add(b);
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
