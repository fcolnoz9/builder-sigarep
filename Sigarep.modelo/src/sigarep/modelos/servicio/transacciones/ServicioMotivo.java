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

@Service("serviciomotivo")
public class ServicioMotivo {
	private @Autowired
	IMotivoDAO iMotivoDAO;

	// metodo que permite Guardar
	public void guardarMotivo(Motivo motivo) {
		iMotivoDAO.save(motivo);
	}

	// metodo que permite eliminar
	public void eliminarMotivo(MotivoPK motivoPK) {
		Motivo motivo = iMotivoDAO.findOne(motivoPK);
		motivo.setEstatus(false);
		iMotivoDAO.save(motivo);
	}

	public Motivo buscarMotivoPorID(MotivoPK id) {
		return iMotivoDAO.findOne(id);
	}

	/**
	 * Lista de Motivos por Apelacion
	 * 
	 * @param cedulaEstudiante
	 *            y codigoLapso
	 * @return resultado es un lista de Motivos por las cuales apelo el
	 *         estudiante
	 */
	public List<String> buscarMotivosApelacion(String cedula, String codigoLapso) {
		return iMotivoDAO.buscarMotivosApelacion(cedula, codigoLapso);
	}
	
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
