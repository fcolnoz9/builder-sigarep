package sigarep.modelos.servicio.transacciones;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.transacciones.Motivo;
import sigarep.modelos.data.transacciones.MotivoPK;
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
}
