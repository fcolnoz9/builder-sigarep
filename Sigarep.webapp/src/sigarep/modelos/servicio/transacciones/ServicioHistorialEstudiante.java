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
				" es.segundo_apellido, es.fecha_nacimiento,  es.sexo, es.anio_ingreso, max(esa.unidades_aprobadas) as ua, " +
				"max(esa.unidades_cursadas) as uc, esa.indice_grado FROM programa_academico p, estudiante es, " +
				"estudiante_sancionado esa WHERE es.id_programa= p.id_programa AND " +
				"es.cedula_estudiante = esa.cedula_estudiante group by es.cedula_estudiante, es.primer_nombre, es.primer_apellido, " +
				"es.email, es.telefono, p.nombre_programa, es.segundo_nombre, " +
				"es.segundo_apellido, es.fecha_nacimiento,  es.sexo, es.anio_ingreso, esa.indice_grado";
		

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

	
	public List<ListaHistorialEstudianteVeredicto> buscarVeredictos (String cedula) {
		String queryStatement1 = 		
				"SELECT distinct es.cedula_estudiante, es.primer_nombre, es.primer_apellido, " +
				"es.email, es.telefono, p.nombre_programa, es.segundo_nombre, " +
				"es.segundo_apellido, es.fecha_nacimiento,  es.sexo, es.anio_ingreso, esa.unidades_aprobadas,  " +
				"esa.unidades_cursadas, esa.indice_grado, sam.nombre_sancion, la.codigo_lapso, tm.nombre_tipo_motivo,  " +
				"a.nombre_asignatura, (select  sa.veredicto from solicitud_apelacion sa where 	 " +
				"sa.cedula_estudiante = '"+cedula +"' and sa.id_instancia_apelada= '1' and sa.codigo_lapso = la.codigo_lapso) " +
				"as veredicto1, (select  sa.veredicto from solicitud_apelacion sa where " +
				"sa.cedula_estudiante = '"+cedula +"' and sa.id_instancia_apelada= '2' and sa.codigo_lapso = la.codigo_lapso) as veredicto2, " +
				"(select  sa.veredicto from solicitud_apelacion sa where " +
				"sa.cedula_estudiante = '"+cedula +"' and sa.id_instancia_apelada= '3' and sa.codigo_lapso = la.codigo_lapso) as veredicto3  " +
				"FROM programa_academico p, estudiante es,  lapso_academico la, " +
				"sancion_maestro sam, tipo_motivo tm, " +
				"estudiante_sancionado as esa LEFT JOIN asignatura_estudiante_sancionado " +
				"AS asa ON esa.codigo_lapso = asa.codigo_lapso and esa.cedula_estudiante = asa.cedula_estudiante  " +
				"Left join solicitud_apelacion as sa on sa.cedula_estudiante = esa.cedula_estudiante and sa.codigo_lapso  " +
				"= esa.codigo_lapso inner join motivo  as mo on mo.cedula_estudiante = " +
				"sa.cedula_estudiante and mo.codigo_lapso = sa.codigo_lapso " +
				"and mo.id_instancia_apelada = sa.id_instancia_apelada " +
				"left join asignatura as a on  a.codigo_asignatura = asa.codigo_asignatura " +
				"WHERE es.id_programa= p.id_programa AND  es.cedula_estudiante = esa.cedula_estudiante  " +
				"and esa.cedula_estudiante =  '"+cedula +"' and sam.id_sancion = esa.id_sancion and tm.id_tipo_motivo = mo.id_tipo_motivo " +
				"and esa.id_sancion = sam.id_sancion and la.codigo_lapso = esa.codigo_lapso " +
				"order by la.codigo_lapso desc" ;
				

		Query query = em.createNativeQuery(queryStatement1);


		@SuppressWarnings("unchecked")
		List<Object[]> resultSet = query.getResultList();
		List<ListaHistorialEstudianteVeredicto> results = new ArrayList<ListaHistorialEstudianteVeredicto>();
		for (Object[] resultRow : resultSet) {
				results.add(new ListaHistorialEstudianteVeredicto ((String) resultRow[0],
						(String) resultRow[1], (String) resultRow[2], (String) resultRow[3],
						(String) resultRow[4], (String) resultRow[5], (String) resultRow[6], 
						(String) resultRow[7], (Date) resultRow[8], (String) resultRow[9],
						(Date) resultRow[10],(Integer) resultRow[11], (Integer) resultRow[12],
						(float) resultRow[13], (String) resultRow[14], (String) resultRow[15],
						(String) resultRow[16], (String) resultRow[17], (String) resultRow[18],
						(String) resultRow[19], (String) resultRow[20]));
		}
		return results;
		}

}