package sigarep.modelos.servicio.transacciones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.RecaudoEntregado;
import sigarep.modelos.data.transacciones.RecaudoEntregadoPK;
import sigarep.modelos.repositorio.transacciones.IRecaudoEntregadoDAO;

/**
 * Clase ServicioRecaudoEntregado: Clase de la capa servicio web para el manejo de consultas y persistencia de la tabla RecaudoEntregado 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 04/01/2014
 * @last 10/05/2014
 */
@Service("serviciorecaudoentregado")
public class ServicioRecaudoEntregado {
	
	// Atributos de la clase
	private @Autowired IRecaudoEntregadoDAO iRecaudoEntregadoDAO;
	
	/**
	 * Guarda un recaudo de un motivo de apelación
	 * @param recaudoentregado
	 * @return Objeto RecudoEntregado
	 * @throws No dispara ninguna excepción.
	 */
	public RecaudoEntregado guardar(RecaudoEntregado recaudoentregado) {
		return iRecaudoEntregadoDAO.save(recaudoentregado);
	}
	
	/**Busca una lista de recaudos entregados de un estudiante sancionado en el estado de apelación verificar en la segunda apelación
	 * @param cedula
	 * @return List<RecaudoEntregado> Lista de recaudos entregados
	 * @throws No dispara ninguna excepción.
	 */
	public List<RecaudoEntregado> buscarRecaudosEntregadosVerificarRecaudosII(String cedula){
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosVerificarRecaudosII(cedula);
	}

	/**Busca una lista de recaudos entregados de un estudiante sancionado en el estado de apelación verificar en la tercera apelación
	 * @param cedula
	 * @return List<RecaudoEntregado> Lista de recaudos entregados
	 * @throws No dispara ninguna excepción.
	 */
	public List<RecaudoEntregado> buscarRecaudosEntregadosVerificarRecaudosIII(String cedula) {
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosVerificarRecaudosIII(cedula);
	}
	
	/**
	 * Busca una lista de recaudos entregados pero que No tienen archivos cargados al sistema
	 * @param cedula
	 * @return List<RecaudoEntregado> Lista de recaudos que no tiene soporte
	 * @throws No dispara ninguna excepción.
	 */
	public List<RecaudoEntregado> buscarRecaudosEntregadosSinSoporte(String cedula){
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosSinSoporte(cedula);
	}
	
	/**
	 * Busca una lista de recaudos entregados de estudiantes sancionados en el estado de apelación reconsideración
	 * @param cedula
	 * @return List<RecaudoEntregado> Lista de recaudos entregados
	 * @throws No dispara ninguna excepción.
	 */
	public List<RecaudoEntregado> buscarRecaudosEntregadosReconsideracion(String cedula){
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosReconsideracion(cedula);
	}
	
	/**
	 * Busca una lista de recaudos entregados de estudiantes sancionados en el estado de apelación recurso jerarquico
	 * @param cedula
	 * @return List<RecaudoEntregado> Lista de recaudos entregados
	 * @throws No dispara ninguna excepción.
	 */
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

	/**Busca una lista de recaudos entregados de un estudiante sancionado en el estado de apelación veredicto en la primera apelación
	 * @param cedula, codigoLapso
	 * @return List<RecaudoEntregado> Lista de recaudos entregados
	 * @throws No dispara ninguna excepción.
	 */
	public List<RecaudoEntregado> buscarRecaudosEntregadosVeredictoI(
			String cedula, String codigoLapso) {
		return iRecaudoEntregadoDAO.findById_CedulaEstudianteAndId_CodigoLapsoAndId_IdInstanciaApelada(cedula, codigoLapso, 1);
	}
	
	/**Busca una lista de recaudos entregados de un estudiante sancionado en el estado de apelación veredicto en la tercera apelación
	 * @param cedula, codigoLapso
	 * @return List<RecaudoEntregado> Lista de recaudos entregados
	 * @throws No dispara ninguna excepción.
	 */
	public List<RecaudoEntregado> buscarRecaudosEntregadosVeredictoIII(
			String cedula, String codigoLapso) {
		return iRecaudoEntregadoDAO.findById_CedulaEstudianteAndId_CodigoLapsoAndId_IdInstanciaApelada(cedula, codigoLapso, 3);	
	}
	
	/**Busca una lista de recaudos entregados de un estudiante sancionado en el estado de apelación analizar en la primera apelación
	 * @param cedula
	 * @return List<RecaudoEntregado> Lista de recaudos entregados
	 * @throws No dispara ninguna excepción.
	 */
	public List<RecaudoEntregado> buscarRecaudosEntregadosAnalizarValidezI(
			String cedula) {
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosAnalizarValidezI(cedula);	
	}
	
	/**Busca una lista de recaudos entregados de un estudiante sancionado en el estado de apelación veredicto en la segunda apelación
	 * @param cedula, codigoLapso
	 * @return List<RecaudoEntregado> Lista de recaudos entregados
	 * @throws No dispara ninguna excepción.
	 */
	public List<RecaudoEntregado> buscarRecaudosEntregadosVeredictoII(
			String cedula, String codigoLapso){
		return iRecaudoEntregadoDAO.findById_CedulaEstudianteAndId_CodigoLapsoAndId_IdInstanciaApelada(cedula, codigoLapso, 2);
	}

	/**Busca una lista de observaciones en los recaudos entregados de un estudiante sancionado en el estado de apelación analizar
	 * @param cedula
	 * @return List<RecaudoEntregado> Lista de observaciones en los recaudos faltantes por entregar 
	 * @throws No dispara ninguna excepción.
	 */
	public List<RecaudoEntregado> buscarRecaudosEntregadosObservacionesAnalizar(
			String cedula) {
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosObservacionesanalizarIII(cedula);
	}

	/**Busca una lista de recaudos entregados de un estudiante sancionado en el estado de apelación analizar en la tercera apelación
	 * @param cedula
	 * @return List<RecaudoEntregado> Lista de recaudos entregados
	 * @throws No dispara ninguna excepción.
	 */
	public List<RecaudoEntregado> buscarRecaudosEntregadosAnalizarValidezIII(
			String cedula) {
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosAnalizarValidezIII(cedula);
	}
	
	/**Busca una lista de recaudos entregados de un estudiante sancionado en el estado de apelación analizar en la segunda apelación
	 * @param cedula
	 * @return List<RecaudoEntregado> Lista de recaudos entregados
	 * @throws No dispara ninguna excepción.
	 */
	public List<RecaudoEntregado> buscarRecaudosEntregadosAnalizarValidezII(
			String cedula) {
		return iRecaudoEntregadoDAO.buscarRecaudosEntregadosAnalizarValidezII(cedula);
	}

	/**Busca una lista de recaudos entregados de un estudiante sancionado dado el id 
	 * @param recaudoEntregadoPK 
	 * @return List<RecaudoEntregado> Lista de recaudos entregados
	 * @throws No dispara ninguna excepción.
	 */
	public RecaudoEntregado buscarPorId(RecaudoEntregadoPK recaudoEntregadoPK) {
		return iRecaudoEntregadoDAO.findOne(recaudoEntregadoPK);
	}

	/**
	 * Busca una lista de recaudos entregados que tienen archivos cargados al sistema
	 * @param cedula
	 * @return List<RecaudoEntregado> Lista de recaudos que tienen soporte
	 * @throws No dispara ninguna excepción.
	 */
	public List<RecaudoEntregado> buscarRecaudosEntregadosConSoporte(String cedula) {
		return iRecaudoEntregadoDAO.buscarRecuadosEntregadosConSoporte(cedula);
	}
}
