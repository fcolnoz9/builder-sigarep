package sigarep.modelos.servicio.transacciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;
import sigarep.modelos.data.transacciones.Soporte;
import sigarep.modelos.repositorio.transacciones.ISoporteDAO;

@Service("serviciosoporte")
public class ServicioSoporte {

	private @Autowired ISoporteDAO iSoporteDAO;
	
	
	public void guardar(Soporte soporte) {
		if (soporte.getIdSoporte() != null)
			iSoporteDAO.save(soporte);
		else{
			soporte.setIdSoporte(iSoporteDAO.buscarUltimoID()+1);
			iSoporteDAO.save(soporte);
		}
		
	}
	
	public void eliminar(Integer idSoporte){
		iSoporteDAO.delete(idSoporte);
	}
	
	public Soporte buscarSoportePorID(Integer idSoporte){
		return iSoporteDAO.findOne(idSoporte);
	}

	public Soporte buscarPorIdDeRecaudoEntregado(RecaudoEntregadoPK recaudoEntregadoPK) {
		Integer idRecaudo = recaudoEntregadoPK.getIdRecaudo();
		Integer idTipoMotivo = recaudoEntregadoPK.getIdTipoMotivo();
		Integer idInstancia = recaudoEntregadoPK.getIdInstanciaApelada();
		String cedula = recaudoEntregadoPK.getCedulaEstudiante();
		String codigoLapso = recaudoEntregadoPK.getCodigoLapso();
		return iSoporteDAO.buscarSoportePorIdRecaudoEntregado(idRecaudo, idTipoMotivo, idInstancia, cedula, codigoLapso);
	} 
}
