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
	
	public List<RecaudoEntregado> buscarRecaudosEntregados(String cedula){
		return iRecaudoEntregadoDAO.buscarRecaudosEntregados(cedula);
	}
	
	
	public List<RecaudoEntregado> buscarRecaudosEntregadosReconsideracion(String cedula){
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosReconsideracion(cedula);
	}
	

	public List<RecaudoEntregado> buscarRecaudosEntregadosRecursoJerarquico(String cedula){
		List<RecaudoEntregado> listaRecaudos = iRecaudoEntregadoDAO.buscarRecaudosEntregadosRecursoJerarquico(cedula);
		for (int i = 0; i < listaRecaudos.size(); i++) {
			int idRecaudo = listaRecaudos.get(i).getRecaudo().getIdRecaudo();
			int cont = 0;
			for (int j = i; j < listaRecaudos.size(); j++) 
				if (listaRecaudos.get(j).getRecaudo().getIdRecaudo() == idRecaudo)
					cont++;
			if (cont > 1)
				for (int h = 0; h < listaRecaudos.size(); h++)
					if (listaRecaudos.get(h).getId().getIdRecaudo() == idRecaudo && listaRecaudos.get(h).getId().getIdInstanciaApelada() == 1)
						listaRecaudos.remove(h);
		}
		return listaRecaudos;
	}

	public List<RecaudoEntregado> buscarRecaudosEntregadosVeredictoI(
			String cedula, String codigoLapso) {
		return iRecaudoEntregadoDAO.findById_CedulaEstudianteAndId_CodigoLapsoAndId_IdInstanciaApelada(cedula, codigoLapso, 1);
	}
	public List<RecaudoEntregado> buscarRecaudosEntregadosVeredictoIII(
			String cedula, String codigoLapso) {
		return iRecaudoEntregadoDAO.findById_CedulaEstudianteAndId_CodigoLapsoAndId_IdInstanciaApelada(cedula, codigoLapso, 3);	
	}
	
	public List<RecaudoEntregado> buscarRecaudosEntregadosAnalizarValidezI(
			String cedula) {
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosAnalizarValidezI(cedula);	
	}
	
	public List<RecaudoEntregado> buscarRecaudosEntregadosVeredictoII(
			String cedula, String codigoLapso){
		return iRecaudoEntregadoDAO.findById_CedulaEstudianteAndId_CodigoLapsoAndId_IdInstanciaApelada(cedula, codigoLapso, 2);
	}

	public List<RecaudoEntregado> buscarRecaudosEntregadosObservacionesAnalizar(
			String cedula) {
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosObservacionesanalizarIII(cedula);
	}

	public List<RecaudoEntregado> buscarRecaudosEntregadosAnalizarValidezIII(
			String cedula) {
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosAnalizarValidezIII(cedula);
	}
	
	public List<RecaudoEntregado> buscarRecaudosEntregadosAnalizarValidezII(
			String cedula) {
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosAnalizarValidezII(cedula);
	}
}
