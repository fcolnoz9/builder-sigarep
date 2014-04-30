package sigarep.modelos.servicio.transacciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;
import sigarep.modelos.repositorio.transacciones.IRecaudoEntregadoDAO;

@Service("serviciorecaudoentregado")
public class ServicioRecaudoEntregado {

	private @Autowired IRecaudoEntregadoDAO iRecaudoEntregadoDAO;
	public RecaudoEntregado guardar(RecaudoEntregado recaudoentregado) {
		return iRecaudoEntregadoDAO.save(recaudoentregado);
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
	
	public List<RecaudoEntregado> buscarRecaudosEntregadosSinSoporte(String cedula){
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosSinSoporte(cedula);
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

	public RecaudoEntregado buscarPorId(RecaudoEntregadoPK recaudoEntregadoPK) {
		return iRecaudoEntregadoDAO.findOne(recaudoEntregadoPK);
	}

	public List<RecaudoEntregado> buscarRecaudosEntregadosConSoporte(String cedula) {
		return iRecaudoEntregadoDAO.buscarRecuadosEntregadosConSoporte(cedula);
	}
}
