package sigarep.modelos.repositorio.maestros;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sigarep.modelos.data.maestros.Persona;

/**
 * Repositorio IPersonaDAO: Repositorio relacionado con el Maestro Persona.
 * 
 * @author Euipo Builder
 * @version 1.0
 * @since 12/12/2013
 * @last 08/05/2014
 */
public interface IPersonaDAO extends JpaRepository<Persona, String> {

	/**
	 * Busca las personas que poseen estatus == true
	 * 
	 * @return List<Persona> Lista de Personas con estatus == true
	 */
	public List<Persona> findByEstatusTrue();

	/**
	 * Busca una persona por su nombre de usuario
	 * 
	 * @param nombreUsuario Nombre "x" que se pretende encontrar
	 * @return Persona encontrada por su nombre de usuario
	 */
	@Query("select persona from Persona persona where " +
			"persona.usuario.nombreUsuario = :nombreUsuario")
	public Persona buscarPersonaPorNombreUsuario(
			@Param("nombreUsuario") String nombreUsuario);

}
