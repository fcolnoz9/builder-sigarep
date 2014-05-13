package sigarep.modelos.servicio.transacciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.MotivoPK;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.Soporte;
import sigarep.modelos.repositorio.transacciones.IMotivoDAO;

/**
 * Clase ServicioMotivo : Clase de la capa servicio web para el manejo de consultas y persistencia de la tabla Motivo 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 04/01/2014
 * @last 10/05/2014
 */
@Service("serviciomotivo")
public class ServicioMotivo {
	
	// Atributos de la clase
	private @Autowired
	IMotivoDAO iMotivoDAO;

	/**
	 * Guarda un motivo de sanción
	 * @param motivo
	 * @throws No dispara ninguna excepción.
	 */
	public void guardarMotivo(Motivo motivo) {
		iMotivoDAO.save(motivo);
	}

	/**
	 * Elimina un motivo dado su PK
	 * @param motivoPK
	 * @throws No dispara ninguna excepción.
	 */
	public void eliminarMotivo(MotivoPK motivoPK) {
		Motivo motivo = iMotivoDAO.findOne(motivoPK);
		motivo.setEstatus(false);
		iMotivoDAO.save(motivo);
	}
	
	/**
	 * Busca un motivo dado su id
	 * @param id
	 * @return Oojeto Motivo
	 * @throws No dispara ninguna excepción.
	 */
	public Motivo buscarMotivoPorID(MotivoPK id) {
		return iMotivoDAO.findOne(id);
	}

	/**
	 * Busca una lista de motivos por apelación
	 * @param cedula, codigoLapso
	 * @return List<String> Lista de Motivos por las cuales apelo el estudiante
	 * @throws No dispara ninguna excepción.
	 */
	public List<String> buscarMotivosApelacion(String cedula, String codigoLapso) {
		return iMotivoDAO.buscarMotivosApelacion(cedula, codigoLapso);
	}
	
	/**
	 * Muestra una lista con los motivos de apelación en un lapso académico
	 * @param lapso
	 * @return List<Motivo> Lista de motivos encontrados en un lapso
	 * @throws No dispara ninguna excepción.
	 */
	public List<String> historicoMotivosApelacion(LapsoAcademico lapso) {
		List<String> listaElementosAInsertar = new ArrayList<String>();
		String elementoAInsertar;
		List<Motivo> motivos = iMotivoDAO.findBySolicitudApelacion_EstudianteSancionado_LapsoAcademico(lapso);

		for (int i = 0; i < motivos.size(); i++) {
				Motivo motivo = motivos.get(i);
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
			List<RecaudoEntregado> recaudosEntregados = motivo.getRecaudoEntregados();
			for (Iterator<RecaudoEntregado> it = recaudosEntregados.iterator(); it.hasNext();) {
				RecaudoEntregado recaudoEntregado = it.next();
				elementoAInsertar = "INSERT INTO sigarep.recaudo_entregado(cedula_estudiante, codigo_lapso, id_instancia_apelada, id_recaudo, id_tipo_motivo, estatus, observacion_experto)"
						+ "VALUES ('"
						+ recaudoEntregado.getId().getCedulaEstudiante()
						+ "','"
						+ recaudoEntregado.getId().getCodigoLapso()
						+ "',"
						+ recaudoEntregado.getId().getIdInstanciaApelada()
						+ ", "
						+ recaudoEntregado.getId().getIdRecaudo()
						+ ","
						+ recaudoEntregado.getId().getIdTipoMotivo()
						+ ",'"
						+ recaudoEntregado.getEstatus()
						+ "','"
						+ recaudoEntregado.getObservacionExperto() + "');";
				listaElementosAInsertar.add(elementoAInsertar);
				if(recaudoEntregado.getSoporte()!=null) {
					Soporte soporte = recaudoEntregado.getSoporte();
					elementoAInsertar = "INSERT INTO sigarep.soporte(id_soporte, contenido_documento, nombre_documento, " +
							"tamano_documento, tipo_documento, estatus, fecha_subida, cedula_estudiante, codigo_lapso, " +
							"id_instancia_apelada, id_recaudo, id_tipo_motivo)"
							+ "VALUES ("
							+ soporte.getIdSoporte()
							+ ",'"
							+ soporte.getDocumento().getContenidoDocumento()
							+ "','"
							+ soporte.getDocumento().getNombreDocumento()
							+ "', "
							+ soporte.getDocumento().getTamanoDocumento()
							+ ",'"
							+ soporte.getDocumento().getTipoDocumento()
							+ "','"
							+ soporte.getEstatus()
							+ "','"
							+ soporte.getFechaSubida()
							+ "','"
							+ soporte.getRecaudoEntregado().getId().getCedulaEstudiante()
							+ "','"
							+ soporte.getRecaudoEntregado().getId().getCodigoLapso()
							+ "',"
							+ soporte.getRecaudoEntregado().getId().getIdInstanciaApelada()
							+ ","
							+ soporte.getRecaudoEntregado().getId().getIdRecaudo()
							+ ","
							+ soporte.getRecaudoEntregado().getId().getIdTipoMotivo() + ");";
					listaElementosAInsertar.add(elementoAInsertar);
				}
			}
		}
		return listaElementosAInsertar;
	}
}
