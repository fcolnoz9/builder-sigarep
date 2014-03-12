package sigarep.modelos.repositorio.maestros;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sigarep.modelos.data.maestros.Persona;

/**
 * Repositorio Persona-IPersonaDAO
 * 
 * @author BUILDER
 * @version 1.0
 * @since 12/12/2013
 */
public interface IPersonaDAO extends JpaRepository<Persona, String> {

	/**
	 * Busca las personas que poseen estatus true
	 * @return List<Persona> Lista de Personas con estatus true
	 */
	public List<Persona> findByEstatusTrue();

	/**
	 * Busca una persona por su nombre
	 * @param nombreUsuario
	 *            Nombre "x" que se pretende encontrar
	 * @return persona encontrado por su nombre
	 */
	@Query("select persona from Persona persona where " +
			"persona.nombreUsuario.nombreUsuario = :nombreUsuario")
	public Persona buscarPersonaPorNombreUsuario(
			@Param("nombreUsuario") String nombreUsuario);

}
