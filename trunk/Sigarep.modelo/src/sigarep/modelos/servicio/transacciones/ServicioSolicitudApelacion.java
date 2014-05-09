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

@Service("serviciosolicitudapelacion")
public class ServicioSolicitudApelacion {

	private @Autowired
	ISolicitudApelacionDAO iSolicitudApelacionDAO;
	private @Autowired
	IMotivoDAO iMotivoDAO;

	public SolicitudApelacion guardar(SolicitudApelacion solicitudapelacion) {
		return iSolicitudApelacionDAO.save(solicitudapelacion);
	}

	public List<SolicitudApelacion> buscarApelacionesVerificarRecaudosII(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		return iSolicitudApelacionDAO.findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndVerificadoFalse(lapsoAcademico, idInstanciaApelada);
	}

	public List<SolicitudApelacion> buscarApelacionesVerificarRecaudosIII(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		return iSolicitudApelacionDAO.findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndVerificadoFalse(lapsoAcademico, idInstanciaApelada);
	}
	
	public List<SolicitudApelacion> buscarApelacionesRecursoJerarquico(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		return iSolicitudApelacionDAO.findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrue(lapsoAcademico, idInstanciaApelada);
	}
	
	public List<SolicitudApelacion> buscarApelacionesActa(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada ) {
		return iSolicitudApelacionDAO.findByAnalizadoTrueAndEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrue(lapsoAcademico, idInstanciaApelada);
	}

	public SolicitudApelacion buscarSolicitudPorID(SolicitudApelacionPK id) {
		return iSolicitudApelacionDAO.findOne(id);
	}

	public SolicitudApelacion buscarEstudianteSancionadoxSolicitud(
			String cedulaEstudiante) {
		return iSolicitudApelacionDAO.findById_CedulaEstudiante(cedulaEstudiante);
	}

	public void eliminar(SolicitudApelacionPK id) {
		SolicitudApelacion solicitudApelacion = iSolicitudApelacionDAO
				.findOne(id);
		solicitudApelacion.setEstatus(false);
		iSolicitudApelacionDAO.save(solicitudApelacion);
	}

	public List<SolicitudApelacion> buscarTodos() {
		return iSolicitudApelacionDAO.findAll();
	}

	public int contarTodos() {
		return iSolicitudApelacionDAO.findAll().size();
	}

	public long contarApelacionesSinVeredicto() {
		return iSolicitudApelacionDAO.numeroApleacionesSinVeredicto();
	}

	public long contarApelacionesSinSesion() {
		return iSolicitudApelacionDAO.numeroApleacionesSinSesion();
	}

	public SolicitudApelacion crear() {
		return new SolicitudApelacion();
	}

	public List<SolicitudApelacion> buscarApelacionesVerificarRecaudosI(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		return iSolicitudApelacionDAO.findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndVerificadoFalse(lapsoAcademico, idInstanciaApelada);
	}

	public List<String> historicoSolicitudApelacion(LapsoAcademico lapsoAcademico) {
		List<String> listaElementosAInsertar = new ArrayList<String>();
		String elementoAInsertar;
//		String codigolapsoAcademico = lapso.getCodigoLapso();
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
//			iSolicitudApelacionDAO.delete(solicitudApelacion);
		}
		return listaElementosAInsertar;
	}

	public List<SolicitudApelacion> buscarApelacionesVeredictoI() {
		return iSolicitudApelacionDAO.buscarApelacionesVeredictoI();
	}

	public List<SolicitudApelacion> buscarApelacionesVeredictoII() {
		return iSolicitudApelacionDAO.buscarApelacionesVeredictoII();
	}

	public List<SolicitudApelacion> buscarApelacionesVeredictoIII() {
		return iSolicitudApelacionDAO.buscarApelacionesVeredictoIII();
	}

	public String mayorNumeroCaso() {
		try {
			return iSolicitudApelacionDAO.mayorNumeroCaso();
		} catch (Exception e) {
			return "";
		}
	}

	// Flor
	public List<SolicitudApelacion> buscarAnalizarValidezI(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		return iSolicitudApelacionDAO.findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndVerificadoTrueAndAnalizadoFalseAndVeredictoIsNull(lapsoAcademico, idInstanciaApelada);
	}

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

	// Flory Amanda
	public List<SolicitudApelacion> buscarAnalizarValidezII(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		return iSolicitudApelacionDAO.findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndVerificadoTrueAndAnalizadoFalseAndVeredictoIsNull(lapsoAcademico, idInstanciaApelada);
	}

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

	public List<SolicitudApelacion> buscarAnalizarValidezIII(LapsoAcademico lapsoAcademico, Integer idInstanciaApelada) {
		return iSolicitudApelacionDAO.findByEstudianteSancionado_LapsoAcademicoAndId_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndVerificadoTrueAndAnalizadoFalseAndVeredictoIsNull(lapsoAcademico, idInstanciaApelada);
	}

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

	public List<SolicitudApelacion> buscarSolicitudParaDatosSesion(
			Integer instancia) {
		return iSolicitudApelacionDAO.buscarSolicitudParaDatosSesion(instancia);
	}

	public List<SolicitudApelacion> buscarSolicitudEstudiante(
			String cedulaEstudiante) {
		return iSolicitudApelacionDAO
				.buscarSolicitudEstudiante(cedulaEstudiante);
	}
//jose	
	public List<SolicitudApelacion> buscarSesionValida(String sess,Integer insta, Integer instb) {
		return iSolicitudApelacionDAO.buscarSesionValida( sess, insta, instb);
	}
//Sixmar, Eliecer
	/**
	 * Lista de sesiones del lapso actual
	 * 
	 * @param ninguno
	 * @return devuelve una lista de sesiones
	 */
	public List<String> buscarSesion() {
		return iSolicitudApelacionDAO.buscarSesion();
	}

	/**
	 * Lista de solicitudes realizadas filtrada por cedula y lapso
	 * 
	 * @param cedulaEstudiante
	 *            , codigoLapso
	 * @return devuelve una lista con los datos de la apelación relaizada por el
	 *         estudiando en un lapso determinado
	 */
	public List<SolicitudApelacion> buscarSolicitudApelacionLapsoActual(
			String cedula, String lapso) {
		return iSolicitudApelacionDAO.buscarSolicitudApelacionLapsoActual(
				cedula, lapso);
	}
	
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
	 * Retorna true si todas las apelaciones fueron procesadas para una instancia
	 * @param idInstanciaApelada
	 * @return true/false
	 */
	public boolean estanFinalizadasLasApelaciones(int idInstanciaApelada) {
		List<SolicitudApelacion> listaApelacionesSinVeredicto = iSolicitudApelacionDAO.findById_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrueAndNumeroSesionIsNull(idInstanciaApelada);
		if (idInstanciaApelada <= 1){
			if (listaApelacionesSinVeredicto.size()<1)
				return true;
			else
				return false;
		}
		else{//idInstanciaApelada > 1
			List<SolicitudApelacion> listaApelaciones = iSolicitudApelacionDAO.findById_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrue(idInstanciaApelada);
			if (listaApelacionesSinVeredicto.size() < 1 && listaApelaciones.size() > 0)
				return true;
			else
				return false;
		}
	}

	public boolean existenApelacionesIniciadas(int idInstanciaApelada) {
		List<SolicitudApelacion> listaApelacionesPorInstancia = iSolicitudApelacionDAO.findById_IdInstanciaApeladaAndEstudianteSancionado_LapsoAcademico_EstatusTrue(idInstanciaApelada);
		if (listaApelacionesPorInstancia.size() > 0)
			return true;
		else
			return false;
	}

	public String buscarNumeroDeCasoCargarRecaudo(String cedula) {
		return iSolicitudApelacionDAO.buscarNumeroDeCasoCargarRecaudo(cedula);
	}

	public Object buscarFechaApelacionCargarRecaudo(String cedula) {
		return iSolicitudApelacionDAO.buscarFechaApelacionCargarRecaudo(cedula);
	}
}
