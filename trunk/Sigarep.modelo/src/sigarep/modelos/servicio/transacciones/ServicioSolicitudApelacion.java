package sigarep.modelos.servicio.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.transacciones.ApelacionEstadoApelacion;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.SolicitudApelacionPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.repositorio.transacciones.ISolicitudApelacionDAO;
import sigarep.modelos.repositorio.transacciones.IMotivoDAO;

/**
 * Clase ServicioSolicitudApelacion: Clase de la capa servicio web para el manejo de consultas y persistencia de la tabla SolicitudApelacion 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 04/01/2014
 * @last 10/05/2014
 */
@Service("serviciosolicitudapelacion")
public class ServicioSolicitudApelacion {

	// Atributos de la clase
	private @Autowired
	ISolicitudApelacionDAO iSolicitudApelacionDAO;
	private @Autowired
	IMotivoDAO iMotivoDAO;

	/**
	 * Guarda una solicitud de apelación de un estudiante sancionado
	 * @param solicitudapelacion
	 * @return Objeto SolicitudApelacion
	 * @throws No dispara ninguna excepción.
	 */
	public SolicitudApelacion guardar(SolicitudApelacion solicitudapelacion) {
		return iSolicitudApelacionDAO.save(solicitudapelacion);
	}

	/**
	 * Busca las solicitudes de apelación de estudiantes sancionados en el estado de apelación verificar de la segunda apelación 
	 * @param lapsoAcademico, idInstanciaApelada
	 * @return List<SolicitudApelacion> Lista de solicitudes de apelaciones
	 * @throws No dispara ninguna excepción.
	 */
	public List<SolicitudApelacion> buscarApelacionesVerificarRecaudosII(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		return iSolicitudApelacionDAO.findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndVerificadoFalse(lapsoAcademico, idInstanciaApelada);
	}

	/**
	 * Busca las solicitudes de apelación de estudiantes sancionados en el estado de apelación verificar de la tercera apelación 
	 * @param lapsoAcademico, idInstanciaApelada
	 * @return List<SolicitudApelacion> Lista de solicitudes de apelaciones
	 * @throws No dispara ninguna excepción.
	 */
	public List<SolicitudApelacion> buscarApelacionesVerificarRecaudosIII(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		return iSolicitudApelacionDAO.findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndVerificadoFalse(lapsoAcademico, idInstanciaApelada);
	}
	
	/**
	 * Busca las solicitudes de apelación de estudiantes sancionados en el estado de apelación Recurso jerarquico 
	 * @param lapsoAcademico, idInstanciaApelada
	 * @return List<SolicitudApelacion> Lista de solicitudes de apelaciones
	 * @throws No dispara ninguna excepción.
	 */
	public List<SolicitudApelacion> buscarApelacionesRecursoJerarquico(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		return iSolicitudApelacionDAO.findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrue(lapsoAcademico, idInstanciaApelada);
	}
	
	/**
	 * Busca las actas de las solicitudes de apelación que se encuentran registradas en un lapso académico
	 * @param lapsoAcademico, idInstanciaApelada
	 * @return List<SolicitudApelacion> Lista de solicitudes de apelaciones
	 * @throws No dispara ninguna excepción.
	 */
	public List<SolicitudApelacion> buscarApelacionesActa(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada ) {
		return iSolicitudApelacionDAO.findByAnalizadoTrueAndEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrue(lapsoAcademico, idInstanciaApelada);
	}

	/**
	 * Busca las solicitudes de apelación dado el id
	 * @param id
	 * @return Objeto SolicitudApelacion
	 * @throws No dispara ninguna excepción.
	 */
	public SolicitudApelacion buscarSolicitudPorID(SolicitudApelacionPK id) {
		return iSolicitudApelacionDAO.findOne(id);
	}

	/**
	 * Buscar un estudiante sancionado en una solicitud de apelación dada su cedula
	 * @param cedulaEstudiante
	 * @return Objeto SolicitudApelacion
	 * @throws No dispara ninguna excepción.
	 */
	public SolicitudApelacion buscarEstudianteSancionadoxSolicitud(
			String cedulaEstudiante) {
		return iSolicitudApelacionDAO.findById_CedulaEstudiante(cedulaEstudiante);
	}

	/**
	 * Eliminar una solicitud de apelación dado el id
	 * @param id
	 * @throws No dispara ninguna excepción.
	 */
	public void eliminar(SolicitudApelacionPK id) {
		SolicitudApelacion solicitudApelacion = iSolicitudApelacionDAO
				.findOne(id);
		solicitudApelacion.setEstatus(false);
		iSolicitudApelacionDAO.save(solicitudApelacion);
	}

	/**
	 * Busca una lista con todas las solicitudes de apelaciones registradas
	 * @return List<SolicitudApelacion> Lista de solicitudes de apelaciones
	 * @throws No dispara ninguna excepción.
	 */
	public List<SolicitudApelacion> buscarTodos() {
		return iSolicitudApelacionDAO.findAll();
	}

	/**
	 * Cuenta todas las solicitudes de apelaciones registradas
	 * @return Número de solicitudes de apelaciones contadas
	 * @throws No dispara ninguna excepción.
	 */
	public int contarTodos() {
		return iSolicitudApelacionDAO.findAll().size();
	}

	/**
	 * Cuenta todas las solicitudes de apelaciones que NO tienen registrado un veredicto
	 * @return Número de solicitudes de apelaciones contadas que NO tienen veredicto
	 * @throws No dispara ninguna excepción.
	 */
	public long contarApelacionesSinVeredicto() {
		return iSolicitudApelacionDAO.numeroApleacionesSinVeredicto();
	}

	/**
	 * Cuenta todas las solicitudes de apelaciones que NO tienen registrada una sesión 
	 * @return Número de solicitudes de apelaciones contadas que NO tienen sesión registrada
	 * @throws No dispara ninguna excepción.
	 */
	public long contarApelacionesSinSesion() {
		return iSolicitudApelacionDAO.numeroApleacionesSinSesion();
	}

	/**
	 * Nueva solicitud de apelación 
	 * @return Objeto SolicitudApelacion
	 * @throws No dispara ninguna excepción.
	 */
	public SolicitudApelacion crear() {
		return new SolicitudApelacion();
	}

	/**
	 * Busca las solicitudes de apelación de estudiantes sancionados en el estado de apelación verificar de la primera apelación 
	 * @param lapsoAcademico, idInstanciaApelada
	 * @return List<SolicitudApelacion> Lista de solicitudes de apelaciones
	 * @throws No dispara ninguna excepción.
	 */
	public List<SolicitudApelacion> buscarApelacionesVerificarRecaudosI(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		return iSolicitudApelacionDAO.findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndVerificadoFalse(lapsoAcademico, idInstanciaApelada);
	}

	/**
	 * Muestra una lista de las solicitudes de apelaciones en un lapso académico
	 * @param lapsoAcademico
	 * @return List<SolicitudApelacion> Lista con las solicitudes encontradas en un lapso
	 * @throws No dispara ninguna excepción.
	 */
	public List<String> historicoSolicitudApelacion(LapsoAcademico lapsoAcademico) {
		List<String> listaElementosAInsertar = new ArrayList<String>();
		String elementoAInsertar;
		List<SolicitudApelacion> solicitudApelaciones = iSolicitudApelacionDAO.findByEstudianteSancionado_LapsoAcademico(lapsoAcademico);

		for (int i = 0; i < solicitudApelaciones.size(); i++) {
			SolicitudApelacion solicitudApelacion = solicitudApelaciones.get(i);
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
					+ "', '"
					+ solicitudApelacion.isAnalizado()
					+ "', '"
					+ solicitudApelacion.isVerificado() + "' );";
			listaElementosAInsertar.add(elementoAInsertar);
			Set<ApelacionEstadoApelacion> apelacionesEstadoApelacion = solicitudApelacion
					.getApelacionEstadosApelacion();
			for (Iterator<ApelacionEstadoApelacion> it = apelacionesEstadoApelacion
					.iterator(); it.hasNext();) {
				ApelacionEstadoApelacion apelacionEstadoApelacion = it.next();
				elementoAInsertar = "INSERT INTO sigarep.apelacion_estado_apelacion(cedula_estudiante, codigo_lapso, id_instancia_apelada, id_estado_apelacion, fecha_estado)"
						+ "VALUES ('"
						+ apelacionEstadoApelacion.getId()
								.getCedulaEstudiante()
						+ "','"
						+ apelacionEstadoApelacion.getId().getCodigoLapso()
						+ "',"
						+ apelacionEstadoApelacion.getId()
								.getIdInstanciaApelada()
						+ ", "
						+ apelacionEstadoApelacion.getEstadoApelacion()
								.getIdEstadoApelacion()
						+ ",'"
						+ apelacionEstadoApelacion.getFechaEstado() + "');";
				listaElementosAInsertar.add(elementoAInsertar);
			}
			List<Motivo> motivos = iMotivoDAO
					.findBySolicitudApelacion(solicitudApelacion);
			for (int j = 0; j < motivos.size(); j++) {
				Motivo motivo = motivos.get(j);
				elementoAInsertar = "INSERT INTO sigarep.motivo(cedula_estudiante, codigo_lapso, id_instancia_apelada, id_tipo_motivo, descripcion, estatus)"
						+ "VALUES ('"
						+ motivo.getId().getCedulaEstudiante()
						+ "','"
						+ motivo.getId().getCodigoLapso()
						+ "',"
						+ motivo.getId().getIdInstanciaApelada()
						+ ", "
						+ motivo.getTipoMotivo().getIdTipoMotivo()
						+ ",'"
						+ motivo.getDescripcion()
						+ "','"
						+ motivo.getEstatus()
						+ "');";
				listaElementosAInsertar.add(elementoAInsertar);
			}
		}
		return listaElementosAInsertar;
	}

	/**
	 * Busca las solicitudes de apelación de estudiantes sancionados en el estado de apelación veredicto de la primera apelación 
	 * @return List<SolicitudApelacion> Lista de solicitudes de apelaciones
	 * @throws No dispara ninguna excepción.
	 */
	public List<SolicitudApelacion> buscarApelacionesVeredictoI() {
		return iSolicitudApelacionDAO.buscarApelacionesVeredictoI();
	}

	/**
	 * Busca las solicitudes de apelación de estudiantes sancionados en el estado de apelación veredicto de la segunda apelación 
	 * @return List<SolicitudApelacion> Lista de solicitudes de apelaciones
	 * @throws No dispara ninguna excepción.
	 */
	public List<SolicitudApelacion> buscarApelacionesVeredictoII() {
		return iSolicitudApelacionDAO.buscarApelacionesVeredictoII();
	}

	/**
	 * Busca las solicitudes de apelación de estudiantes sancionados en el estado de apelación veredicto de la tercera apelación 
	 * @return List<SolicitudApelacion> Lista de solicitudes de apelaciones
	 * @throws No dispara ninguna excepción.
	 */
	public List<SolicitudApelacion> buscarApelacionesVeredictoIII() {
		return iSolicitudApelacionDAO.buscarApelacionesVeredictoIII();
	}

	/**
	 * Mostrar el número de caso mas alto registrado
	 * @return El número de caso mayor encontrado
	 * @throws NullPointerException 
	 */
	public String mayorNumeroCaso() {
		try {
			return iSolicitudApelacionDAO.mayorNumeroCaso();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Busca las solicitudes de apelación de estudiantes sancionados en el estado de apelación analizar de la primera apelación 
	 * @param lapsoAcademico, idInstanciaApelada
	 * @return List<SolicitudApelacion> Lista de solicitudes de apelaciones
	 * @throws No dispara ninguna excepción.
	 */
	public List<SolicitudApelacion> buscarAnalizarValidezI(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		return iSolicitudApelacionDAO.findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndVerificadoTrueAndAnalizadoFalseAndVeredictoIsNull(lapsoAcademico, idInstanciaApelada);
	}

	/**
	 * Busca una lista filtrada de solicitudes de apelaciones de estudiantes sancionados en el estado de apelación analizar de la primera apelación  
	 * @param programa, cedula, nombre, apellido, sancion, lapsoAcademico, idInstanciaApelada
	 * @return List<SolicitudApelacion> Lista de solicitud de apelación filtrada
	 * @throws La Excepción es que las variables que entran por parametro sean null.
	 */
	public List<SolicitudApelacion> filtrarApelacionesAnalizarValidezI(
			String programa, String cedula, String nombre, String apellido,
			String sancion, LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		List<SolicitudApelacion> result = new ArrayList<SolicitudApelacion>();
		if (programa == null || cedula == null || nombre == null
				|| apellido == null || sancion == null) {
			result = buscarAnalizarValidezI(lapsoAcademico, idInstanciaApelada);
		} else {
			for (SolicitudApelacion sa : buscarAnalizarValidezI(lapsoAcademico, idInstanciaApelada)) {
				if (sa.getEstudianteSancionado().getEstudiante()
						.getProgramaAcademico().getNombrePrograma()
						.toLowerCase().contains(programa.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getCedulaEstudiante().toLowerCase()
								.contains(cedula.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerNombre().toLowerCase()
								.contains(nombre.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerApellido().toLowerCase()
								.contains(apellido.toLowerCase())
						&& sa.getEstudianteSancionado().getSancionMaestro()
								.getNombreSancion().toLowerCase()
								.contains(sancion.toLowerCase())) {
					result.add(sa);
				}
			}
		}
		return result;
	}

	/**
	 * Busca una lista filtrada de solicitudes de apelaciones de estudiantes sancionados en el estado de apelación veredicto de la primera apelación  
	 * @param programa, cedula, nombre, apellido, sancion
	 * @return List<SolicitudApelacion> Lista de solicitud de apelación filtrada
	 * @throws La Excepción es que las variables que entran por parametro sean null.
	 */
	public List<SolicitudApelacion> filtrarApelacionesVeredictoI(String cedula,
			String nombre, String apellido, String programa, String sancion) {
		List<SolicitudApelacion> result = new ArrayList<SolicitudApelacion>();
		if (programa == null || cedula == null || nombre == null
				|| apellido == null || sancion == null) {
			result = buscarApelacionesVeredictoI();
		} else {
			for (SolicitudApelacion sa : buscarApelacionesVeredictoI()) {
				if (sa.getEstudianteSancionado().getEstudiante()
						.getProgramaAcademico().getNombrePrograma()
						.toLowerCase().contains(programa.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getCedulaEstudiante().toLowerCase()
								.contains(cedula.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerNombre().toLowerCase()
								.contains(nombre.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerApellido().toLowerCase()
								.contains(apellido.toLowerCase())
						&& sa.getEstudianteSancionado().getSancionMaestro()
								.getNombreSancion().toLowerCase()
								.contains(sancion.toLowerCase())) {
					result.add(sa);
				}
			}
		}
		return result;
	}

	/**
	 * Busca una lista filtrada de solicitudes de apelaciones de estudiantes sancionados en el estado de apelación veredicto de la segunda apelación  
	 * @param programa, cedula, nombre, apellido, sancion
	 * @return List<SolicitudApelacion> Lista de solicitud de apelación filtrada
	 * @throws La Excepción es que las variables que entran por parametro sean null.
	 */
	public List<SolicitudApelacion> filtrarApelacionesVeredictoII(
			String cedula, String nombre, String apellido, String programa,
			String sancion) {
		List<SolicitudApelacion> result = new ArrayList<SolicitudApelacion>();
		if (programa == null || cedula == null || nombre == null
				|| apellido == null || sancion == null) {
			result = buscarApelacionesVeredictoII();
		} else {
			for (SolicitudApelacion sa : buscarApelacionesVeredictoII()) {
				if (sa.getEstudianteSancionado().getEstudiante()
						.getProgramaAcademico().getNombrePrograma()
						.toLowerCase().contains(programa.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getCedulaEstudiante().toLowerCase()
								.contains(cedula.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerNombre().toLowerCase()
								.contains(nombre.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerApellido().toLowerCase()
								.contains(apellido.toLowerCase())
						&& sa.getEstudianteSancionado().getSancionMaestro()
								.getNombreSancion().toLowerCase()
								.contains(sancion.toLowerCase())) {
					result.add(sa);
				}
			}
		}
		return result;
	}

	/**
	 * Busca una lista filtrada de solicitudes de apelaciones de estudiantes sancionados en el estado de apelación veredicto de la tercera apelación  
	 * @param programa, cedula, nombre, apellido, sancion
	 * @return List<SolicitudApelacion> Lista de solicitud de apelación filtrada
	 * @throws La Excepción es que las variables que entran por parametro sean null.
	 */
	public List<SolicitudApelacion> filtrarApelacionesVeredictoIII(
			String cedula, String nombre, String apellido, String programa,
			String sancion) {
		List<SolicitudApelacion> result = new ArrayList<SolicitudApelacion>();
		if (programa == null || cedula == null || nombre == null
				|| apellido == null || sancion == null) {
			result = buscarApelacionesVeredictoIII();
		} else {
			for (SolicitudApelacion sa : buscarApelacionesVeredictoIII()) {
				if (sa.getEstudianteSancionado().getEstudiante()
						.getProgramaAcademico().getNombrePrograma()
						.toLowerCase().contains(programa.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getCedulaEstudiante().toLowerCase()
								.contains(cedula.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerNombre().toLowerCase()
								.contains(nombre.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerApellido().toLowerCase()
								.contains(apellido.toLowerCase())
						&& sa.getEstudianteSancionado().getSancionMaestro()
								.getNombreSancion().toLowerCase()
								.contains(sancion.toLowerCase())) {
					result.add(sa);
				}
			}
		}
		return result;
	}

	/**
	 * Busca las solicitudes de apelación de estudiantes sancionados en el estado de apelación verificar de la segunda apelación 
	 * @param lapsoAcademico, idInstanciaApelada
	 * @return List<SolicitudApelacion> Lista de solicitudes de apelaciones
	 * @throws No dispara ninguna excepción.
	 */
	public List<SolicitudApelacion> buscarAnalizarValidezII(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		return iSolicitudApelacionDAO.findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndVerificadoTrueAndAnalizadoFalseAndVeredictoIsNull(lapsoAcademico, idInstanciaApelada);
	}

	/**
	 * Busca una lista filtrada de solicitudes de apelaciones de estudiantes sancionados en el estado de apelación analizar de la segunda apelación  
	 * @param programa, cedula, nombre, apellido, sancion, lapsoAcademico, idInstanciaApelada
	 * @return List<SolicitudApelacion> Lista de solicitud de apelación filtrada
	 * @throws La Excepción es que las variables que entran por parametro sean null.
	 */
	public List<SolicitudApelacion> filtrarApelacionesAnalizarValidezII(
			String programa, String cedula, String nombre, String apellido,
			String sancion, LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		List<SolicitudApelacion> result = new ArrayList<SolicitudApelacion>();
		if (programa == null || cedula == null || nombre == null
				|| apellido == null || sancion == null) {
			result = buscarAnalizarValidezI(lapsoAcademico, idInstanciaApelada);
		} else {
			for (SolicitudApelacion sa : buscarAnalizarValidezII(lapsoAcademico, idInstanciaApelada)) {
				if (sa.getEstudianteSancionado().getEstudiante()
						.getProgramaAcademico().getNombrePrograma()
						.toLowerCase().contains(programa.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getCedulaEstudiante().toLowerCase()
								.contains(cedula.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerNombre().toLowerCase()
								.contains(nombre.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerApellido().toLowerCase()
								.contains(apellido.toLowerCase())
						&& sa.getEstudianteSancionado().getSancionMaestro()
								.getNombreSancion().toLowerCase()
								.contains(sancion.toLowerCase())) {
					result.add(sa);
				}
			}
		}
		return result;
	}

	/**
	 * Busca las solicitudes de apelación de estudiantes sancionados en el estado de apelación verificar de la tercera apelación 
	 * @param lapsoAcademico, idInstanciaApelada
	 * @return List<SolicitudApelacion> Lista de solicitudes de apelaciones
	 * @throws No dispara ninguna excepción.
	 */
	public List<SolicitudApelacion> buscarAnalizarValidezIII(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		return iSolicitudApelacionDAO.findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndVerificadoTrueAndAnalizadoFalseAndVeredictoIsNull(lapsoAcademico, idInstanciaApelada);
	}

	/**
	 * Busca una lista filtrada de solicitudes de apelaciones de estudiantes sancionados en el estado de apelación analizar de la tercera apelación  
	 * @param programa, cedula, nombre, apellido, sancion, lapsoAcademico, idInstanciaApelada
	 * @return List<SolicitudApelacion> Lista de solicitud de apelación filtrada
	 * @throws La Excepción es que las variables que entran por parametro sean null.
	 */
	public List<SolicitudApelacion> filtrarApelacionesAnalizarValidezIII(
			String programa, String cedula, String nombre, String apellido,
			String sancion, LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		List<SolicitudApelacion> result = new ArrayList<SolicitudApelacion>();
		if (programa == null || cedula == null || nombre == null
				|| apellido == null || sancion == null) {
			result = buscarAnalizarValidezI(lapsoAcademico, idInstanciaApelada);
		} else {
			for (SolicitudApelacion sa : buscarAnalizarValidezIII(lapsoAcademico, idInstanciaApelada)) {
				if (sa.getEstudianteSancionado().getEstudiante()
						.getProgramaAcademico().getNombrePrograma()
						.toLowerCase().contains(programa.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getCedulaEstudiante().toLowerCase()
								.contains(cedula.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerNombre().toLowerCase()
								.contains(nombre.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerApellido().toLowerCase()
								.contains(apellido.toLowerCase())
						&& sa.getEstudianteSancionado().getSancionMaestro()
								.getNombreSancion().toLowerCase()
								.contains(sancion.toLowerCase())) {
					result.add(sa);
				}
			}
		}
		return result;
	}

	/**
	 * Busca una lista de solicitudes de apelaciones para los datos de sesión por instancia apelada
	 * @param instancia
	 * @return List<SolicitudApelacion> Lista de solicitud de apelación 
	 * @throws No dispara ninguna excepción.
	 */
	public List<SolicitudApelacion> buscarSolicitudParaDatosSesion(
			Integer instancia) {
		return iSolicitudApelacionDAO.buscarSolicitudParaDatosSesion(instancia);
	}

	/**
	 * Busca una lista de solicitudes de apelaciones por cedula de estudiante
	 * @param cedulaEstudiante
	 * @return List<SolicitudApelacion> Lista de solicitudes de apelaciones
	 * @throws No dispara ninguna excepción.
	 */
	public List<SolicitudApelacion> buscarSolicitudEstudiante(
			String cedulaEstudiante) {
		return iSolicitudApelacionDAO
				.buscarSolicitudEstudiante(cedulaEstudiante);
	}
	
	/**
	 * Busca una lista de solicitudes de apelaciones para iniciar una sesión valida para dar veredicto
	 * @param sess, insta, instb
	 * @return List<SolicitudApelacion> Lista de solicitudes de apelaciones registradas
	 * @throws No dispara ninguna excepción.
	 */
	public List<SolicitudApelacion> buscarSesionValida(String sess,Integer insta, Integer instb) {
		return iSolicitudApelacionDAO.buscarSesionValida( sess, insta, instb);
	}
	/**
	 * Busca la lista de sesiones del lapso actual
	 * @return List<String> Lista de sesiones
	 * @throws No dispara ninguna excepción.
	 */
	public List<String> buscarSesion() {
		return iSolicitudApelacionDAO.buscarSesion();
	}

	/**
	 * Busca una lista de solicitudes realizadas filtrada por cedula y lapso
	 * @param cedula, lapso
	 * @return<SolicitudApelacion> Lista con los datos de la apelación relaizada por el estudiante en un lapso determinado
	 * @throws No dispara ninguna excepción.
	 */
	public List<SolicitudApelacion> buscarSolicitudApelacionLapsoActual(
			String cedula, String lapso) {
		return iSolicitudApelacionDAO.buscarSolicitudApelacionLapsoActual(
				cedula, lapso);
	}
	
	/**
	 * Lista de solicitudes de apelaciones filtrada en el combo de veredicto por veredictos dados en instancias anteriores
	 * @param listaFiltrarVeredicto, veredicto
	 * @return List<SolicitudApelacion> Lista de solicitudes de apelaciones filtrada en el combo de veredicto
	 * @throws No dispara ninguna excepción.
	 */
	public List<SolicitudApelacion> filtrarComboVeredictoListaGenerica(
			List<SolicitudApelacion> listaFiltrarVeredicto, String veredicto) {
		List<SolicitudApelacion> result = new ArrayList<SolicitudApelacion>();
		if (veredicto.equalsIgnoreCase("SIN VEREDICTO"))
			for (SolicitudApelacion sa1 : listaFiltrarVeredicto)
				if (sa1.getVeredicto() == null)
					result.add(sa1);
		else
			for (SolicitudApelacion sa2 : listaFiltrarVeredicto)
				if (sa2.getVeredicto() != null)
					if (sa2.getVeredicto().equalsIgnoreCase(veredicto))
						result.add(sa2);
			return result;
	}

	/**
	 * Busca una lista filtrada de solicitudes de apelaciones de estudiantes sancionados en el estado de apelación verificar de la primera apelación  
	 * @param programa, cedula, nombre, apellido, sancion, lapsoAcademico, idInstanciaApelada
	 * @return List<SolicitudApelacion> Lista de solicitud de apelación filtrada
	 * @throws La Excepción es que las variables que entran por parametro sean null.
	 */
	public List<SolicitudApelacion> filtrarApelacionesVerificarRecaudosI(
			String programa, String cedula, String nombre, String apellido,
			String sancion, LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		List<SolicitudApelacion> result = new ArrayList<SolicitudApelacion>();
		if (programa == null || cedula == null || nombre == null
				|| apellido == null || sancion == null) {
			result = buscarApelacionesVerificarRecaudosI(lapsoAcademico, idInstanciaApelada);
		} else {
			for (SolicitudApelacion sa : buscarApelacionesVerificarRecaudosI(lapsoAcademico, idInstanciaApelada)) {
				if (sa.getEstudianteSancionado().getEstudiante()
						.getProgramaAcademico().getNombrePrograma()
						.toLowerCase().contains(programa.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getCedulaEstudiante().toLowerCase()
								.contains(cedula.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerNombre().toLowerCase()
								.contains(nombre.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerApellido().toLowerCase()
								.contains(apellido.toLowerCase())
						&& sa.getEstudianteSancionado().getSancionMaestro()
								.getNombreSancion().toLowerCase()
								.contains(sancion.toLowerCase())) {
					result.add(sa);
				}
			}
		}
		return result;
	}

	/**
	 * Busca una lista filtrada de solicitudes de apelaciones de estudiantes sancionados en el estado de apelación verificar de la segunda apelación  
	 * @param programa, cedula, nombre, apellido, sancion, lapsoAcademico, idInstanciaApelada
	 * @return List<SolicitudApelacion> Lista de solicitud de apelación filtrada
	 * @throws La Excepción es que las variables que entran por parametro sean null.
	 */
	public List<SolicitudApelacion> filtrarApelacionesVerificarRecaudosII(
			String programa, String cedula, String nombre, String apellido,
			String sancion, LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
	
		List<SolicitudApelacion> result = new ArrayList<SolicitudApelacion>();
		if (programa == null || cedula == null || nombre == null
				|| apellido == null || sancion == null) {
			result = buscarApelacionesVerificarRecaudosII(lapsoAcademico, idInstanciaApelada);
		} else {
			for (SolicitudApelacion sa : buscarApelacionesVerificarRecaudosII(lapsoAcademico, idInstanciaApelada)) {
				if (sa.getEstudianteSancionado().getEstudiante()
						.getProgramaAcademico().getNombrePrograma()
						.toLowerCase().contains(programa.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getCedulaEstudiante().toLowerCase()
								.contains(cedula.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerNombre().toLowerCase()
								.contains(nombre.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerApellido().toLowerCase()
								.contains(apellido.toLowerCase())
						&& sa.getEstudianteSancionado().getSancionMaestro()
								.getNombreSancion().toLowerCase()
								.contains(sancion.toLowerCase())) {
					result.add(sa);
				}
			}
		}
		return result;
	}

	/**
	 * Busca una lista filtrada de solicitudes de apelaciones de estudiantes sancionados en el estado de apelación verificar de la tercera apelación  
	 * @param programa, cedula, nombre, apellido, sancion, lapsoAcademico, idInstanciaApelada
	 * @return List<SolicitudApelacion> Lista de solicitud de apelación filtrada
	 * @throws La Excepción es que las variables que entran por parametro sean null.
	 */
	public List<SolicitudApelacion> filtrarApelacionesVerificarRecaudosIII(
			String programa, String cedula, String nombre, String apellido,
			String sancion, LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		List<SolicitudApelacion> result = new ArrayList<SolicitudApelacion>();
		if (programa == null || cedula == null || nombre == null
				|| apellido == null || sancion == null) {
			result = buscarApelacionesVerificarRecaudosIII(lapsoAcademico, idInstanciaApelada);
		} else {
			for (SolicitudApelacion sa : buscarApelacionesVerificarRecaudosIII(lapsoAcademico, idInstanciaApelada)) {
				if (sa.getEstudianteSancionado().getEstudiante()
						.getProgramaAcademico().getNombrePrograma()
						.toLowerCase().contains(programa.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getCedulaEstudiante().toLowerCase()
								.contains(cedula.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerNombre().toLowerCase()
								.contains(nombre.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerApellido().toLowerCase()
								.contains(apellido.toLowerCase())
						&& sa.getEstudianteSancionado().getSancionMaestro()
								.getNombreSancion().toLowerCase()
								.contains(sancion.toLowerCase())) {
					result.add(sa);
				}
			}
		}
		return result;
	}
	/**
	 * Lista de solicitudes de apelaciones por acta filtrada
	 * @param programa, cedula , nombre , apellido , sancion , lapsoAcademico, idInstanciaApelada 
	 * @return List<SolicitudApelacion> Lista de solicitudes de apelaciones por acta
	 * @throws La Excepción es que las variables que entran por parametro sean null.
	 */
	public List<SolicitudApelacion> filtrarActa(String programa, String cedula, String nombre, String apellido,
			String sancion, LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		List<SolicitudApelacion> result = new ArrayList<SolicitudApelacion>();
		if (programa == null || cedula == null || nombre == null
				|| apellido == null || sancion == null) {
			result = buscarApelacionesActa(lapsoAcademico, idInstanciaApelada);
		} else {
			for (SolicitudApelacion sa : buscarApelacionesActa(lapsoAcademico, idInstanciaApelada)) {
				if (sa.getEstudianteSancionado().getEstudiante()
						.getProgramaAcademico().getNombrePrograma()
						.toLowerCase().contains(programa.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getCedulaEstudiante().toLowerCase()
								.contains(cedula.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerNombre().toLowerCase()
								.contains(nombre.toLowerCase())
						&& sa.getEstudianteSancionado().getEstudiante()
								.getPrimerApellido().toLowerCase()
								.contains(apellido.toLowerCase())
						&& sa.getEstudianteSancionado().getSancionMaestro()
								.getNombreSancion().toLowerCase()
								.contains(sancion.toLowerCase())) {
					result.add(sa);
				}
			}
		}
		return result;
	}
	/**
	 * Muestra el estado en que se encuentran las apelaciones por instancia
	 * @param idInstanciaApelada
	 * @return true si todas las apelaciones fueron procesadas para una instancia si no false
	 * @throws No dispara ninguna excepción.
	 */
	public boolean estanFinalizadasLasApelaciones(int idInstanciaApelada) {
		List<SolicitudApelacion> listaApelacionesSinVeredicto = iSolicitudApelacionDAO.findById_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndNumeroSesionIsNull(idInstanciaApelada);
		if (idInstanciaApelada <= 1){
			if (listaApelacionesSinVeredicto.size()<1)
				return true;
			else
				return false;
		}
		else{
			List<SolicitudApelacion> listaApelaciones = iSolicitudApelacionDAO.findById_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrue(idInstanciaApelada);
			if (listaApelacionesSinVeredicto.size() < 1 && listaApelaciones.size() > 0)
				return true;
			else
				return false;
		}
	}

	/**
	 * Muestra el estado de inicio de una apelación 
	 * @param idInstanciaApelada
	 * @return true si todas las apelaciones fueron iniciadas para una instancia si no false
	 * @throws No dispara ninguna excepción.
	 */
	public boolean existenApelacionesIniciadas(int idInstanciaApelada) {
		List<SolicitudApelacion> listaApelacionesPorInstancia = iSolicitudApelacionDAO.findById_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrue(idInstanciaApelada);
		if (listaApelacionesPorInstancia.size() > 0)
			return true;
		else
			return false;
	}

	/**
	 * Busca el número de caso de la solicitud de apelación para cargar recaudos de la apelación   
	 * @param cedula
	 * @return Número de caso del estudiante para cargar recaudos
	 * @throws No dispara ninguna excepción.
	 */
	public String buscarNumeroDeCasoCargarRecaudo(String cedula) {
		return iSolicitudApelacionDAO.buscarNumeroDeCasoCargarRecaudo(cedula);
	}

	/**
	 * Busca la fecha de la solicitud de apelación para cargar recaudos de la apelación   
	 * @param cedula
	 * @return Fecha de solicitud de apelación del estudiante para cargar recaudos
	 * @throws No dispara ninguna excepción.
	 */
	public Object buscarFechaApelacionCargarRecaudo(String cedula) {
		return iSolicitudApelacionDAO.buscarFechaApelacionCargarRecaudo(cedula);
	}
}
