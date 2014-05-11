package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.repositorio.maestros.IInstanciaApeladaDAO;

/**
 * Clase ServicioInstanciaApelada (Servicio para
 * la Clase InstanciaApelada)
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 18/12/2013
 * @last 10/05/2014
 */
@Service("servicioInstanciaApelada")
public class ServicioInstanciaApelada {
	private @Autowired
	IInstanciaApeladaDAO iInstancia;

	/**
	 * Guardar Instancia apelada
	 * @return objeto guardado
	 * @parameters InstanciaApelada
	 * @throws No
	 *             dispara ninguna excepción.
	 */
	public void guardar(InstanciaApelada instancia) {
		if (instancia.getIdInstanciaApelada() != null)
			iInstancia.save(instancia);
		else {
			instancia.setIdInstanciaApelada(iInstancia.buscarUltimoID() + 1);
			iInstancia.save(instancia);
		}
	}

	/**
	 * Eliminar Instancia apelada
	 * @return nada
	 * @parameters Entero codigoInstancia
	 * @throws No dispara ninguna excepción.
	 */
	public void eliminar(Integer codigoInstancia) {
		InstanciaApelada instanciaapelada = iInstancia.findOne(codigoInstancia);
		instanciaapelada.setEstatus(false);
		iInstancia.save(instanciaapelada);
	}

	/**
	 * Lista de Instancia Apelada
	 * 
	 * @return Lista de las InstanciasApeladas registradas y activas
	 * @parameters vacio
	 * @throws No  dispara ninguna excepción.
	 */
	public List<InstanciaApelada> listadoInstanciaApelada() {
		List<InstanciaApelada> instanciaApeladaLista = iInstancia
				.findByEstatusTrue();
		return instanciaApeladaLista;
	}

	/**
	 * Buscar Instancia Apelada
	 * 
	 * @return Lista de InstanciasApeladas buscadas
	 * @parameters String Instancia, String Recurso
	 * @throws No dispara ninguna excepción.
	 */
	public List<InstanciaApelada> buscarInstancia(String instancia,
			String recurso) {
		List<InstanciaApelada> resultado = new LinkedList<InstanciaApelada>();
		if (instancia == null || recurso == null) {
			resultado = listadoInstanciaApelada();
		} else {
			for (InstanciaApelada inst : listadoInstanciaApelada()) {
				if (inst.getInstanciaApelada().toLowerCase()
						.contains(instancia)
						&& inst.getNombreRecursoApelacion().toLowerCase()
								.contains(recurso)) {
					resultado.add(inst);
				}
			}
		}
		return resultado;
	}

	/**
	 * Buscar Instancia Apelada por código
	 * @return InstanciaApelada buscada
	 * @parameters Integer codigoInstancia
	 * @throws No dispara ninguna excepción.
	 */
	public InstanciaApelada buscar(Integer codigoInstancia) {
		return iInstancia.findOne(codigoInstancia);
	}
	
	/**
	 *eliminarInstancia
	 * @param Integer instancia
	 * @return Objeto con estatus false, eliminado lógicamente
	 * @throws No  dispara ninguna excepción.
	 */
	public void eliminarInstancia(Integer instancia) {
		InstanciaApelada instanciaApelada = iInstancia.findOne(instancia);
		instanciaApelada.setEstatus(false);
		iInstancia.save(instanciaApelada);
	}
}