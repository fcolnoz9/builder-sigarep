package sigarep.modelos.servicio.transacciones;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.transacciones.InstanciaMiembro;
import sigarep.modelos.data.transacciones.InstanciaMiembroPK;
import sigarep.modelos.repositorio.transacciones.IInstanciaMiembroDAO;

/**
 * Clase ServicioInstanciaMiembro : Clase de la capa servicio web para el manejo de consultas y persistencia de la tabla InstanciaMiembro 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 04/01/2014
 * @last 10/05/2014
 */
@Service("servicioInstanciaMiembro")
public class ServicioInstanciaMiembro {
	
	// Atributos de la clase
	private @Autowired IInstanciaMiembroDAO iInstanciaMiembroDAO;
	
	/**
	 * Guarda una instancia miembro 
	 * @param instanciaMiembro
	 * @return Objeto InstanciaMiembro
	 * @throws No dispara ninguna excepción.
	 */
	public InstanciaMiembro guardar(InstanciaMiembro instanciaMiembro) {
		return iInstanciaMiembroDAO.save(instanciaMiembro);
	}
	
	/**
	 * Elimina una instancia miembro por medio del id
	 * @param id
	 * @throws No dispara ninguna excepción.
	 */
	public void eliminar(InstanciaMiembroPK id){
		InstanciaMiembro miInstanciaMiembro = iInstanciaMiembroDAO.findOne(id);
		miInstanciaMiembro.setEstatus(false);
		miInstanciaMiembro.setFechaSalida(new Date());
		iInstanciaMiembroDAO.save(miInstanciaMiembro);
	}
	
	/**
	 * Cuenta todos las instancias miembros registradas
	 * @return Número de instancias miembros contadas
	 * @throws No dispara ninguna excepción.
	 */
	public int contarTodos() {
		return iInstanciaMiembroDAO.findAll().size();
	}

	/**
	 * Nueva instancia miembro 
	 * @return Objeto InstanciaMiembro
	 * @throws No dispara ninguna excepción.
	 */
	public InstanciaMiembro crear() {
		return new InstanciaMiembro();
	}
	
	/**
	 * Busca miembros de una instancia, dada la cedula de la persona
	 * @param CedulaPersona
	 * @return List<InstanciaMiembro> Lista de miembros de una instancia 
	 * @throws No dispara ninguna excepción.
	 */
	public List<InstanciaMiembro> buscarPorCedula(String CedulaPersona){
		return iInstanciaMiembroDAO.findById_CedulaPersona(CedulaPersona);
	}
	
	/**
	 * Busca miembros de una instancia, dada la instancia
	 * @param idInstanciaApelada
	 * @return List<InstanciaMiembro> Lista de miembros de una instancia 
	 * @throws No dispara ninguna excepción.
	 */
	public List<InstanciaMiembro> buscarPorInstancia(Integer idInstanciaApelada){
		return iInstanciaMiembroDAO.findById_IdInstanciaApelada(idInstanciaApelada);
	}
}
