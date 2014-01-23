package sigarep.modelos.servicio.transacciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.EstudianteSancionado;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.lista.ListaGenericaSancionados;
import sigarep.modelos.repositorio.transacciones.IRecaudoEntregadoDAO;
import sigarep.modelos.repositorio.transacciones.ISolicitudApelacionDAO;

@Service("serviciorecaudoentregado")
public class ServicioRecaudoEntregado {

	private @Autowired IRecaudoEntregadoDAO iRecaudoEntregadoDAO;
	private @Autowired ISolicitudApelacionDAO iSolicitudApelacionDAO;
	@PersistenceContext
	private EntityManager em;
	
	public RecaudoEntregado guardar(RecaudoEntregado recaudoentregado) {
		return iRecaudoEntregadoDAO.save(recaudoentregado);
	}
	
	public List<SolicitudApelacion> buscarApelacionesCargarRecaudo() {
		return iSolicitudApelacionDAO.buscarSolicitudesCargarRecaudoEntregado();
	}
	
	public List<SolicitudApelacion> filtrarApelacionesCargarRecaudo(
			String programa, String cedula, String nombre,
			String apellido, String sancion){
		List<SolicitudApelacion> result = new ArrayList<SolicitudApelacion>();
        if(programa==null || cedula==null || nombre==null || apellido==null || sancion==null){
        	result= buscarApelacionesCargarRecaudo();
        }
        else{
			for (SolicitudApelacion sa : buscarApelacionesCargarRecaudo())
			{
				if (sa.getEstudianteSancionado().getEstudiante().getProgramaAcademico().getNombrePrograma() .toLowerCase().contains(programa.toLowerCase())&&
						sa.getEstudianteSancionado().getEstudiante().getCedulaEstudiante().toLowerCase().contains(cedula.toLowerCase())&&
						sa.getEstudianteSancionado().getEstudiante().getPrimerApellido().toLowerCase().contains(nombre.toLowerCase())&&
						sa.getEstudianteSancionado().getEstudiante().getPrimerApellido().toLowerCase().contains(apellido.toLowerCase())&&
						sa.getEstudianteSancionado().getSancionMaestro().getNombreSancion().toLowerCase().contains(sancion.toLowerCase())){
					result.add(sa);
				}
			}
        }
		return result;
	} 
	
	
	public List<RecaudoEntregado> buscarRecaudosEntregados(String cedula){
		return iRecaudoEntregadoDAO.buscarRecaudosEntregados(cedula);
	}
	
	
	public List<RecaudoEntregado> buscarRecaudosEntregadosReconsideracion(String cedula){
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosReconsideracion(cedula);
	}
}
