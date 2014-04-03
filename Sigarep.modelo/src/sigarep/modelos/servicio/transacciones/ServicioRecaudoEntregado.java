package sigarep.modelos.servicio.transacciones;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.repositorio.transacciones.IRecaudoEntregadoDAO;
import sigarep.modelos.repositorio.transacciones.ISolicitudApelacionDAO;

@Service("serviciorecaudoentregado")
public class ServicioRecaudoEntregado {

	private @Autowired IRecaudoEntregadoDAO iRecaudoEntregadoDAO;
	private @Autowired ISolicitudApelacionDAO iSolicitudApelacionDAO;
	
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
	
	/** lista de recaudos entregados de un estudiante sancionado
	 * en la segunda apelacion
	 * @param cedula
	 * @return lista de recaudos faltantes por entregar
	 */
	public List<RecaudoEntregado> buscarRecaudosEntregadosVerificarRecaudosII(String cedula){
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosVerificarRecaudosII(cedula);
	}

	/** lista de recaudos entregados de un estudiante sancionado
	 * en la tercera apelacion
	 * @param cedula
	 * @return lista de recaudos faltantes por entregar
	 */
	public List<RecaudoEntregado> buscarRecaudosEntregadosVerificarRecaudosIII(String cedula) {
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosVerificarRecaudosIII(cedula);
	}
	
	public List<RecaudoEntregado> buscarRecaudosEntregadosLapsoActual(String cedula){
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosLapsoActual(cedula);
	}
	
	public List<RecaudoEntregado> buscarRecaudosEntregadosPorLapsoAcademico(String cedula, String codigoLapso){
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosPorLapsoAcademico(cedula,codigoLapso);
	}

	public List<RecaudoEntregado> buscarRecaudosEntregadosObservacionesAnalizar(
			String cedula) {
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosObservacionesanalizarIII(cedula);
	}
}
