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

/**Clase ServicioEstudianteSancionado
* Suministra los servicios al VMInstanciaApelada
* @author Builder
* @version 1.0
* @since 20/12/13
*/

@Service("servicioestudiantesancionado") //Definiendo la variable servicio
public class ServicioEstudianteSancionado {
	
	private @Autowired IEstudianteSancionadoDAO iEstudianteSancionadoDAO;
	private @Autowired ISolicitudApelacionDAO iSolicitudApelacionDAO;
	
	/** Guardar EstudianteSancionado 
	 * @return el Objeto estudianteSancionado
	 * @parameters el objeto estudianteSancionado
	 * @throws No dispara ninguna excepcion.
	   */
	public EstudianteSancionado guardar(EstudianteSancionado estudianteSancionado) {
		return iEstudianteSancionadoDAO.save(estudianteSancionado);
	}
	
	/** Eliminar EstudianteSancionado
	 * @return nada
	 * @parameters Objeto EstudianteSancionadoPK
	 * @throws No dispara ninguna excepcion.
	   */
	public void eliminar(EstudianteSancionadoPK id){
		EstudianteSancionado miEstudianteSancionado = iEstudianteSancionadoDAO.findOne(id);
		miEstudianteSancionado.setEstatus(false);
		iEstudianteSancionadoDAO.save(miEstudianteSancionado);
	}
	
	/** Lista de EstudianteSancionado 
	 * @return Lista de EstudianteSancionado registrados y activos
	 * @parameters vacio
	 * @throws No dispara ninguna excepcion.
	   */
	public List<EstudianteSancionado> buscarTodos() {
		return iEstudianteSancionadoDAO.buscarSancionadosActivos();
	}
	
	/** Lista de EstudianteSancionado 
	 * @return Lista de EstudianteSancionado registrados y activos
	 * @parameters vacio
	 * @throws No dispara ninguna excepcion.
	   */
	public List<EstudianteSancionado> buscarSancionados() {
		return iEstudianteSancionadoDAO.buscarSancionados();
	}
	
	/** Lista de EstudianteSancionado 
	 * @return Lista de EstudianteSancionado registrados
	 * @parameters vacio
	 * @throws No dispara ninguna excepcion.
	   */
	public List<EstudianteSancionado> buscarEstudiante() {
		return iEstudianteSancionadoDAO.buscarEstudiante();
	}
	
	/** Lista de EstudianteSancionado 
	 * @return Lista de EstudianteSancionado registrados y activos
	 * @parameters Objeto EstudianteSancionadoPK
	 * @throws No dispara ninguna excepcion.
	   */
	public EstudianteSancionado buscar(EstudianteSancionadoPK id) {
		return iEstudianteSancionadoDAO.findOne(id);
	}
	
	/** Contar EstudianteSancionado 
	 * @return Integer con el numero de EstudianteSancionado registrados
	 * @parameters vacio
	 * @throws No dispara ninguna excepcion.
	   */
	public int contarTodos() {
		return iEstudianteSancionadoDAO.findAll().size();
	}

	/** Nuevo EstudianteSancionado 
	 * @return Objeto EstudianteSancionado
	 * @parameters vacio
	 * @throws No dispara ninguna excepcion.
	   */
	public EstudianteSancionado crear() {
		return new EstudianteSancionado();
	}
	
	/** Lista de EstudianteSancionado 
	 * @return Lista de EstudianteSancionado registrados y activos
	 * @parameters vacio
	 * @throws No dispara ninguna excepcion.
	   */
	public List<EstudianteSancionado> listadoEstudianteSancionado() {
		List<EstudianteSancionado> estudiantesancionadoLista = iEstudianteSancionadoDAO.buscarSancionadosActivos();
	    return estudiantesancionadoLista ;
	}
	
	/** Filtro de EstudianteSancionado 
	 * @return Lista de EstudianteSancionado filtrados
	 * @parameters String cedula, String nombre, String Apellido, String Sancion
	 * @throws No dispara ninguna excepcion.
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

//Maria Flores
	public List<EstudianteSancionado> buscarSancionadosRecursoJerarquico(){
		List<EstudianteSancionado> listaRecursoJerarquico = new ArrayList<EstudianteSancionado>();
		listaRecursoJerarquico = iEstudianteSancionadoDAO.buscarSancionadosRecursoJerarquicoParte1();
		for (EstudianteSancionado es : iEstudianteSancionadoDAO.buscarSancionadosRecursoJerarquicoParte2())
			listaRecursoJerarquico.add(es);
		return listaRecursoJerarquico;
	}
	
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

	/** Filtro de EstudianteSancionado ListaGenerica 
	 * @return Lista de EstudianteSancionado filtrados
	 * @parameters String cedula, String nombre, String Apellido, String Sancion
	 * @throws No dispara ninguna excepcion.
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
	
	/** Buscar Apelacion de EstudianteSancionado para ListaGenerica 
	 * @return Lista de EstudianteSancionado
	 * @parameters String cedula
	 * @throws No dispara ninguna excepcion.
	   */
	public List<EstudianteSancionado> buscarApelacion(String cedula){
		return iEstudianteSancionadoDAO.buscarApelacion(cedula);
	}
	
	/** Buscar Reconsideracion de EstudianteSancionado para ListaGenerica 
	 * @return Lista de EstudianteSancionado
	 * @parameters
	 * @throws No dispara ninguna excepcion.
	   */
	public List<EstudianteSancionado> buscarSancionadosReconsideracion(){
		return iEstudianteSancionadoDAO.buscarSancionadosReconsideracion();
	}
	
	/** Filtra Reconsideracines EstudianteSancionado para ListaGenerica 
	 * @return Lista de EstudianteSancionado
	 * @parameters String cedula, String programa, String nombre, String apellido, String Sancion
	 * @throws No dispara ninguna excepcion.
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
	
	
	/** EstudianteSancionado en el Lapso Actual
	 * @return EstudianteSancionado en el Lapso Actual
	 * @parameters cedula
	 * @throws No dispara ninguna excepcion.
	   */
	public EstudianteSancionado buscarEstudianteSancionadoLapsoActual(String cedula) {
			return iEstudianteSancionadoDAO.buscarSancionadoLapsoActual(cedula);
	}
	

	
	public List<String> historicoEstudiantesSancionados(LapsoAcademico lapsoAcademico) {
		List<String> listaElementosAInsertar = new ArrayList<String>();
		String elementoAInsertar;
		List<EstudianteSancionado> estudiantesSancionados = iEstudianteSancionadoDAO.findByLapsoAcademico(lapsoAcademico);

		for (int i = 0; i < estudiantesSancionados.size(); i++) {
			EstudianteSancionado estudianteSancionado = estudiantesSancionados.get(i);
			elementoAInsertar = "INSERT INTO estudiante_sancionado(cedula_estudiante, codigo_lapso, estatus, indice_grado, lapsos_academicos_rp, periodo_sacion, semestre, unidades_aprobadas, unidades_cursadas, id_sancion)"
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
				elementoAInsertar = "INSERT INTO asignatura_estudiante_sancionado(cedula_estudiante, codigo_asignatura, codigo_lapso, condicion_asignatura)"
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
				elementoAInsertar = "INSERT INTO solicitud_apelacion(cedula_estudiante, codigo_lapso, id_instancia_apelada, estatus, fecha_solicitud, numero_caso, fecha_sesion, codigo_sesion, observacion, tipo_sesion, veredicto, analizado, verificado)"
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