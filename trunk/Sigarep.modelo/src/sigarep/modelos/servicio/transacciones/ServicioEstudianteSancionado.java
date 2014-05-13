package sigarep.modelos.servicio.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.transacciones.AsignaturaEstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.EstudianteSancionadoPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.repositorio.transacciones.IEstudianteSancionadoDAO;
import sigarep.modelos.repositorio.transacciones.ISolicitudApelacionDAO;

/**
 * Clase ServicioEstudianteSancionado : Clase de la capa servicio web para el manejo de consultas y persistencia de la tabla EstudianteSancionado 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 04/01/2014
 * @last 10/05/2014
 */
@Service("servicioestudiantesancionado") //Definiendo la variable servicio
public class ServicioEstudianteSancionado {
	
	// Atributos de la clase
	private @Autowired IEstudianteSancionadoDAO iEstudianteSancionadoDAO;
	private @Autowired ISolicitudApelacionDAO iSolicitudApelacionDAO;
	
	/**Guarda un estudiante sancionado 
	 * @param estudianteSancionado
	 * @return el objeto EstudianteSancionado
	 * @throws No dispara ninguna excepción.
	 */
	public EstudianteSancionado guardar(EstudianteSancionado estudianteSancionado) {
		return iEstudianteSancionadoDAO.save(estudianteSancionado);
	}
	
	/**Elimina un estudiante sancionado dado su id
	 * @param EstudianteSancionadoPK id
	 * @throws No dispara ninguna excepción.
	 */
	public void eliminar(EstudianteSancionadoPK id){
		EstudianteSancionado miEstudianteSancionado = iEstudianteSancionadoDAO.findOne(id);
		miEstudianteSancionado.setEstatus(false);
		iEstudianteSancionadoDAO.save(miEstudianteSancionado);
	}
	
	/**Busca una lista de todos los estudiantes sancionados 
	 * @return List<EstudianteSancionado> Lista de estudiantes sancionados registrados y activos
	 * @throws No dispara ninguna excepción.
	 */
	public List<EstudianteSancionado> buscarTodos() {
		return iEstudianteSancionadoDAO.buscarSancionadosActivos();
	}
	
	/**Busca una lista de estudiantes sancionados 
	 * @return List<EstudianteSancionado> Lista de estudiantes sancionados registrados
	 * @throws No dispara ninguna excepción.
	 */
	public List<EstudianteSancionado> buscarSancionados() {
		return iEstudianteSancionadoDAO.buscarSancionados();
	}
	
	/**Busca una lista de estudiantes 
	 * @return List<EstudianteSancionado> Lista de estudiantes 
	 * @throws No dispara ninguna excepción.
	 */
	public List<EstudianteSancionado> buscarEstudiante() {
		return iEstudianteSancionadoDAO.buscarEstudiante();
	}
	
	/**Busca un estudiante sancionado dado el id 
	 * @param Objeto EstudianteSancionadoPK id
	 * @return estudiante sancionado registrado y activo
	 * @throws No dispara ninguna excepción.
	 */
	public EstudianteSancionado buscar(EstudianteSancionadoPK id) {
		return iEstudianteSancionadoDAO.findOne(id);
	}
	
	/**Cuenta todos los  estudiantes sancionados registrados 
	 * @return Integer con el número de estudiantes sancionados registrados
	 * @throws No dispara ninguna excepción.
	 */
	public int contarTodos() {
		return iEstudianteSancionadoDAO.findAll().size();
	}

	/**Nuevo estudiante sancionado 
	 * @return Objeto EstudianteSancionado
	 * @throws No dispara ninguna excepción.
	 */
	public EstudianteSancionado crear() {
		return new EstudianteSancionado();
	}
	
	/**Lista de estudiantes sancionados activos
	 * @return List<EstudianteSancionado> Lista de EstudianteSancionado registrados y activos
	 * @throws No dispara ninguna excepción.
	 */
	public List<EstudianteSancionado> listadoEstudianteSancionado() {
		List<EstudianteSancionado> estudiantesancionadoLista = iEstudianteSancionadoDAO.buscarSancionadosActivos();
	    return estudiantesancionadoLista ;
	}
	
	/**Busca estudiantes sancionados filtrando po cedula,nombre,apellido,sancion 
	 * @param cedula, nombre, Apellido, Sancion
	 * @return List<EstudianteSancionado> Lista de EstudianteSancionado filtrados
	 * @throws La Excepción es que las variables que entran por parametro sean null
	 */
	public List<EstudianteSancionado> buscarEstudianteSancionadofiltros(String cedula, String nombre, String apellido, String sancion) {
		List<EstudianteSancionado> resultado = new LinkedList<EstudianteSancionado>();
		
		if (cedula == null || nombre == null || apellido == null|| sancion== null) {
			resultado = listadoEstudianteSancionado();
		} else {
			for (EstudianteSancionado inst : listadoEstudianteSancionado()) {
				if (inst.getId().getCedulaEstudiante().toLowerCase().contains(cedula.toLowerCase())
						&& inst.getEstudiante().getPrimerNombre().toLowerCase().contains(nombre.toLowerCase())
						&& inst.getEstudiante().getPrimerApellido().toLowerCase().contains(apellido.toLowerCase())
						&& inst.getSancionMaestro().getNombreSancion().toLowerCase().contains(sancion.toLowerCase())){
					resultado.add(inst);
				}
			}
		}
		return resultado;
	}

	/**Busca Recurso Jerarquico de EstudianteSancionado para ListaGenerica 
	 * @return List<EstudianteSancionado> Lista de EstudianteSancionado
	 * @throws No dispara ninguna excepción.
	 */
	public List<EstudianteSancionado> buscarSancionadosRecursoJerarquico(){
		List<EstudianteSancionado> listaRecursoJerarquico = new ArrayList<EstudianteSancionado>();
		listaRecursoJerarquico = iEstudianteSancionadoDAO.buscarSancionadosRecursoJerarquicoParte1();
		for (EstudianteSancionado es : iEstudianteSancionadoDAO.buscarSancionadosRecursoJerarquicoParte2())
			listaRecursoJerarquico.add(es);
		return listaRecursoJerarquico;
	}
	
	/**Filtra Recurso Jerarquico de EstudianteSancionado para ListaGenerica 
	 * @param cedula, programa, nombre, apellido, Sancion
	 * @return List<EstudianteSancionado> Lista de EstudianteSancionado
	 * @throws La Excepción es que las variables que entran por parametro sean null
	 */
	public List<EstudianteSancionado> filtrarApelacionesRecursoJerarquico(
			String programa, String cedula, String nombre,
			String apellido, String sancion){
		List<EstudianteSancionado> result = new ArrayList<EstudianteSancionado>();
        if(programa==null || cedula==null || nombre==null || apellido==null || sancion==null){
        	result= buscarSancionadosRecursoJerarquico();
        }
        else{
			for (EstudianteSancionado sa : buscarSancionadosRecursoJerarquico())
			{
				if (sa.getEstudiante().getProgramaAcademico().getNombrePrograma() .toLowerCase().contains(programa.toLowerCase())&&
						sa.getEstudiante().getCedulaEstudiante().toLowerCase().contains(cedula.toLowerCase())&&
						sa.getEstudiante().getPrimerNombre().toLowerCase().contains(nombre.toLowerCase())&&
						sa.getEstudiante().getPrimerApellido().toLowerCase().contains(apellido.toLowerCase())&&
						sa.getSancionMaestro().getNombreSancion().toLowerCase().contains(sancion.toLowerCase())){
					result.add(sa);
				}
			}
        }
		return result;
	}

	/**Filtro de EstudianteSancionado ListaGenerica 
	 * @parameters cedula, nombre, Apellido, Sancion
	 * @return List<EstudianteSancionado> Lista de EstudianteSancionado filtrados
	 * @throws La Excepción es que las variables que entran por parametro sean null
	 */
	public List<EstudianteSancionado> filtrarDatosIniciales(String programa, String cedula, String nombre, String apellido, String sancion ) {
		List<EstudianteSancionado> resultado = new LinkedList<EstudianteSancionado>();
		
		if (programa == null || cedula == null || nombre == null || apellido == null|| sancion== null) {
			resultado = buscarSancionados();
		} else {
			for (EstudianteSancionado inst : buscarSancionados()) {
				if (inst.getEstudiante().getProgramaAcademico().getNombrePrograma().toLowerCase().contains(programa.toLowerCase())
						&& inst.getId().getCedulaEstudiante().toLowerCase().contains(cedula.toLowerCase())
						&& inst.getEstudiante().getPrimerNombre().toLowerCase().contains(nombre.toLowerCase())
						&& inst.getEstudiante().getPrimerApellido().toLowerCase().contains(apellido.toLowerCase())
						&& inst.getSancionMaestro().getNombreSancion().toLowerCase().contains(sancion.toLowerCase())){
					resultado.add(inst);
				}
			}
		}
		return resultado;
	}
	
	/**Busca la Apelación de EstudianteSancionado para ListaGenerica 
	 * @param cedula
	 * @return Lista de EstudianteSancionado
	 * @throws No dispara ninguna excepción.
	 */
	public List<EstudianteSancionado> buscarApelacion(String cedula){
		return iEstudianteSancionadoDAO.buscarApelacion(cedula);
	}
	
	/**Busca la Reconsideracion de EstudianteSancionado para ListaGenerica 
	 * @return Lista de EstudianteSancionado
	 * @throws No dispara ninguna excepción.
	 */
	public List<EstudianteSancionado> buscarSancionadosReconsideracion(){
		return iEstudianteSancionadoDAO.buscarSancionadosReconsideracion();
	}
	
	/**Filtra Reconsideracines EstudianteSancionado para ListaGenerica 
	 * @parameters cedula, programa, nombre, apellido, Sancion
	 * @return List<EstudianteSancionado> Lista de EstudianteSancionado filtrada
	 * @throws La Excepción es que las variables que entran por parametro sean null
	 */
	public List<EstudianteSancionado> filtrarApelacionesReconsideracion(
			String programa, String cedula, String nombre, String apellido,
			String sancion) {
		
		List<EstudianteSancionado> result = new ArrayList<EstudianteSancionado>();
        if(programa==null || cedula==null || nombre==null || apellido==null || sancion==null){
        	result= buscarSancionadosReconsideracion();
        }
        else{
			for (EstudianteSancionado sa : buscarSancionadosReconsideracion())
			{
				if (sa.getEstudiante().getProgramaAcademico().getNombrePrograma() .toLowerCase().contains(programa.toLowerCase())&&
						sa.getEstudiante().getCedulaEstudiante().toLowerCase().contains(cedula.toLowerCase())&&
						sa.getEstudiante().getPrimerNombre().toLowerCase().contains(nombre.toLowerCase())&&
						sa.getEstudiante().getPrimerApellido().toLowerCase().contains(apellido.toLowerCase())&&
						sa.getSancionMaestro().getNombreSancion().toLowerCase().contains(sancion.toLowerCase())){
					result.add(sa);
				}
			}
        }
		return result;
	}
	
	/**Busca los estudiantes sancionados en el Lapso Actual
	 * @param cedula
	 * @return EstudianteSancionado en el Lapso Actual
	 * @throws No dispara ninguna excepción.
	 */
	public EstudianteSancionado buscarEstudianteSancionadoLapsoActual(String cedula) {
			return iEstudianteSancionadoDAO.buscarSancionadoLapsoActual(cedula);
	}
	
	/**
	 * Busca los estudiantes sancionados que van a cargar recaudos
	 * @return EstudianteSancionado
	 * @throws No dispara ninguna excepción.
	 */
	public List<EstudianteSancionado> buscarEstudiantesCargarRecaudoEntregado(){
		return iEstudianteSancionadoDAO.buscarEstudiantesCargarRecaudoEntregado();
	}
	
	/**
	 * Filtra la lista para la carga de recaudos entregados de las apelaciones
	 * @param programa, cedula, nombre, apellido, sancion  
	 * @return List<EstudianteSancionado> Lista de EstudianteSancionado filtrada
	 * @throws La Excepción es que las variables que entran por parametro sean null
	 */
	public List<EstudianteSancionado> filtrarApelacionesCargarRecaudoEntregado(
			String programa, String cedula, String nombre,
			String apellido, String sancion){
		List<EstudianteSancionado> result = new ArrayList<EstudianteSancionado>();
        if(programa==null || cedula==null || nombre==null || apellido==null || sancion==null){
        	result= buscarEstudiantesCargarRecaudoEntregado();
        }
        else{
			for (EstudianteSancionado es : buscarEstudiantesCargarRecaudoEntregado())
			{
				if (es.getEstudiante().getProgramaAcademico().getNombrePrograma() .toLowerCase().contains(programa.toLowerCase())&&
						es.getEstudiante().getCedulaEstudiante().toLowerCase().contains(cedula.toLowerCase())&&
						es.getEstudiante().getPrimerApellido().toLowerCase().contains(nombre.toLowerCase())&&
						es.getEstudiante().getPrimerApellido().toLowerCase().contains(apellido.toLowerCase())&&
						es.getSancionMaestro().getNombreSancion().toLowerCase().contains(sancion.toLowerCase())){
					result.add(es);
				}
			}
        }
		return result;
	} 
	
	/**
	 * Busca una lista de estudiantes sancionados en un lapso académico
	 * @param lapsoAcademico
	 * @return List<EstudianteSancionado> Lista de elementos insertar de estudiantes sancionados
	 * @throws No dispara ninguna excepcion
	 */
	public List<String> historicoEstudiantesSancionados(LapsoAcademico lapsoAcademico) {
		List<String> listaElementosAInsertar = new ArrayList<String>();
		String elementoAInsertar;
		List<EstudianteSancionado> estudiantesSancionados = iEstudianteSancionadoDAO.findByLapsoAcademico(lapsoAcademico);

		for (int i = 0; i < estudiantesSancionados.size(); i++) {
			EstudianteSancionado estudianteSancionado = estudiantesSancionados.get(i);
			elementoAInsertar = "INSERT INTO sigarep.estudiante_sancionado(cedula_estudiante, codigo_lapso, estatus, indice_grado, lapsos_academicos_rp, periodo_sacion, semestre, unidades_aprobadas, unidades_cursadas, id_sancion)"
					+ "VALUES ('"
					+ estudianteSancionado.getId().getCedulaEstudiante()
					+ "','"
					+ estudianteSancionado.getId().getCodigoLapso()
					+ "','"
					+ estudianteSancionado.getEstatus()
					+ "',"
					+ estudianteSancionado.getIndiceGrado()
					+ ",'"
					+ estudianteSancionado.getLapsosAcademicosRp()
					+ "',"
					+ estudianteSancionado.getPeriodoSancion()
					+ ","
					+ estudianteSancionado.getSemestre()
					+ ","
					+ estudianteSancionado.getUnidadesAprobadas()
					+ ","
					+ estudianteSancionado.getUnidadesCursadas()
					+ ","
					+ estudianteSancionado.getSancionMaestro().getIdSancion() + " );";
			listaElementosAInsertar.add(elementoAInsertar);
			Set<AsignaturaEstudianteSancionado> asignaturaEstudianteSancionados = estudianteSancionado.getAsignaturaEstudianteSancionados();
			for (Iterator<AsignaturaEstudianteSancionado> it = asignaturaEstudianteSancionados.iterator(); it.hasNext();) {
				AsignaturaEstudianteSancionado asignaturaEstudianteSancionado = it.next();
				elementoAInsertar = "INSERT INTO sigarep.asignatura_estudiante_sancionado(cedula_estudiante, codigo_asignatura, codigo_lapso, condicion_asignatura)"
						+ "VALUES ('"
						+ asignaturaEstudianteSancionado.getId().getCedulaEstudiante()
						+ "','"
						+ asignaturaEstudianteSancionado.getId().getCodigoAsignatura()
						+ "','"
						+ asignaturaEstudianteSancionado.getId().getCodigoLapso()
						+ "',"
						+ asignaturaEstudianteSancionado.getCondicionAsignatura() + ");";
				listaElementosAInsertar.add(elementoAInsertar);
			}
			List<SolicitudApelacion> solicitudesApelacion = iSolicitudApelacionDAO.findByEstudianteSancionado(estudianteSancionado);
			for (int j = 0; j < solicitudesApelacion.size(); j++) {
				SolicitudApelacion solicitudApelacion = solicitudesApelacion.get(j);
				elementoAInsertar = "INSERT INTO sigarep.solicitud_apelacion(cedula_estudiante, codigo_lapso, id_instancia_apelada, estatus, fecha_solicitud, numero_caso, fecha_sesion, codigo_sesion, observacion, tipo_sesion, veredicto, analizado, verificado)"
						+ "VALUES ('"
						+ solicitudApelacion.getId().getCedulaEstudiante()
						+ "','"
						+ solicitudApelacion.getId().getCodigoLapso()
						+ "',"
						+ solicitudApelacion.getInstanciaApelada().getIdInstanciaApelada()
						+ ",'"
						+ solicitudApelacion.getEstatus()
						+ "','"
						+ solicitudApelacion.getFechaSolicitud()
						+ "','"
						+ solicitudApelacion.getNumeroCaso()
						+ "','"
						+ solicitudApelacion.getFechaSesion()
						+ "','"
						+ solicitudApelacion.getNumeroSesion()
						+ "','"
						+ solicitudApelacion.getObservacion()
						+ "','"
						+ solicitudApelacion.getTipoSesion()
						+ "','"
						+ solicitudApelacion.getVeredicto()
						+ "','"
						+ solicitudApelacion.isAnalizado()
						+ "','"
						+ solicitudApelacion.isVerificado() + "' );";
				listaElementosAInsertar.add(elementoAInsertar);
			}
		}
		return listaElementosAInsertar;
	}
}