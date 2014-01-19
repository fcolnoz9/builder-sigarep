package sigarep.modelos.servicio.transacciones;


import java.util.ArrayList;
import java.util.Date;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import sigarep.modelos.data.maestros.EstadoApelacion;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.repositorio.transacciones.IApelacionEstadoApelacionDAO;
import sigarep.modelos.data.maestros.Estudiante;


@Service("serviciohistorial")
public class ServicioHistorialEstudiante  {
	@PersistenceContext
	private EntityManager em;

	
	public List<ListaHistorialEstudiante> buscarEstudiante() {
		
		
//FALTA PERIODO DE SANCION
		String queryStatement2 =
				"SELECT es.cedula_estudiante, es.primer_nombre, es.primer_apellido, " +
				"es.email, es.telefono, p.nombre_programa, es.segundo_nombre," +
				" es.segundo_apellido, es.fecha_nacimiento,  es.sexo, es.anio_ingreso, esa.unidades_aprobadas, " +
				"esa.unidades_cursadas, esa.indice_grado FROM programa_academico p, estudiante es, " +
				" estudiante_sancionado esa WHERE es.id_programa= p.id_programa AND " +
				"es.cedula_estudiante = esa.cedula_estudiante";
		

		Query query = em.createNativeQuery(queryStatement2);

		
		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		
		List<ListaHistorialEstudiante> results = new ArrayList<ListaHistorialEstudiante>();
		for (Object[] resultRow : resultSet) {
			
			results.add(new ListaHistorialEstudiante ((String) resultRow[0], (String) resultRow[1],
					(String) resultRow[2], (String) resultRow[3], (String) resultRow[4], (String) resultRow[5],
					(String) resultRow[6], (String) resultRow[7], (Date) resultRow[8], (String) resultRow[9],
							(Date) resultRow[10],(Integer) resultRow[11], (Integer) resultRow[12],
							(float) resultRow[13]));
		}
		
		return results;
	}
	
	public List<ListaHistorialEstudiante> buscarPorFiltros(ListaHistorialEstudianteFiltros filtros){
		List<ListaHistorialEstudiante> result = new ArrayList<ListaHistorialEstudiante>();
		String programa = filtros.getPrograma().toLowerCase();
		String cedula = filtros.getCedula().toLowerCase();
		String nombre = filtros.getNombre().toLowerCase();
		String apellido = filtros.getApellido().toLowerCase();
	
        if(programa==null ||  cedula==null 
        		|| nombre==null || apellido==null  ){
        	result= buscarEstudiante();
        }
        else{
			for (ListaHistorialEstudiante ap : buscarEstudiante())
			{
				if (ap.getPrograma().toLowerCase().contains(programa)&&
					//	ap.getMotivo().toLowerCase().contains(motivo)&&
						ap.getCedulaEstudiante().toLowerCase().contains(cedula)&&
						ap.getPrimerNombre().toLowerCase().contains(nombre)&&
						ap.getPrimerApellido().toLowerCase().contains(apellido)){
					result.add(ap);
				}
			}
        }
		return result;
        } 
public List<ListaHistorialEstudianteSancion> buscarDatosSancion(String cedula) {
		
		String queryStatement =  "SELECT la.codigo_lapso, sa.nombre_sancion " +
				"FROM   sancion_maestro sa, lapso_academico la " +
				"inner join estudiante_sancionado as esa on la.codigo_lapso = esa.codigo_lapso " +
				"where  esa.cedula_estudiante =  "+"'"+cedula +"' and sa.id_sancion = esa.id_sancion ";
				
				Query query = em.createNativeQuery(queryStatement);

		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();

		List<ListaHistorialEstudianteSancion> results = new ArrayList<ListaHistorialEstudianteSancion>();
		for (Object[] resultRow : resultSet) {
			System.out.println(resultRow[0]);
			System.out.println(resultRow[1]);


			results.add(new ListaHistorialEstudianteSancion(
					(String) resultRow[0], (String) resultRow[1]));
		}

		return results;
	}

public List<ListaHistorialEstudianteMotivo> buscarMotivos(String codigoLapso, String cedula) {
	System.out.println("cedula"+cedula);System.out.println("codigoLapso"+codigoLapso);
			String queryStatement1 =  "SELECT DISTINCT tm.nombre_tipo_motivo, esa.codigo_lapso FROM tipo_motivo tm " +
					"inner join motivo as mo on mo.id_tipo_motivo = tm.id_tipo_motivo "+
					"inner join solicitud_apelacion as sa on sa.cedula_estudiante = mo.cedula_estudiante "+
					"and sa.codigo_lapso = mo.codigo_lapso inner join estudiante_sancionado as esa " +
					"on esa.cedula_estudiante = sa.cedula_estudiante and esa.codigo_lapso = sa.codigo_lapso " +
					"inner join estudiante as es on es.cedula_estudiante = esa.cedula_estudiante " +
					"inner join lapso_academico as la on la.codigo_lapso = esa.codigo_lapso " +
					"where es.cedula_estudiante = '"+cedula +"' and la.codigo_lapso = '"+ codigoLapso +"' ";

	Query query = em.createNativeQuery(queryStatement1);
	

	@SuppressWarnings("unchecked")
	List<Object[]> resultSet = query.getResultList();
	List<ListaHistorialEstudianteMotivo> results = new ArrayList<ListaHistorialEstudianteMotivo>();
	for (Object[] resultRow : resultSet) {
		System.out.println(resultRow[0]);
		System.out.println(resultRow[1]);
		
		results.add(new ListaHistorialEstudianteMotivo ((String) resultRow[0]));
	}
	return results;
	}


}